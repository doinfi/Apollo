/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubbo.demo.proxy;

import com.dubbo.demo.proxy.config.CallConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * netty server<br />
 * 启动netty服务器<br />
 * 需要配合一个ClientInilizer, 处理http instream的解码和用户定义事件<br />
 *
 * @author lizhiwei
 */
public class DubboNettyServer {
    private static final Logger log = LoggerFactory.getLogger(DubboNettyServer.class);

    /**
     * dubbo 加载配置, spring加载
     */
    ClassPathXmlApplicationContext ctx = null;
    /**
     * 后端dubbo服务路由配置信息(url<->app 转化)
     */
    CallConfig config = null;
    /**
     * 处理路由信息方面的http请求, 如热加载或列表显示
     */
    ConfigProcessor conPro = null;
    /**
     * 处理dubbo加载方面的http请求, 如热加载(未实现)或列表显示
     */
    DubboProcessor dubboPro = null;
    /**
     * 后端服务处理, http请求转发后端各模块dubbo接口服务
     */
    BizProcessor wePro = null;

    public static void main(String[] args) {
        DubboNettyServer server = new DubboNettyServer();
        server.startupDubbo();
        server.startupProcessor();
        server.startupNetty();
    }

    /**
     * 启动dubbo, 加载后端服务引用
     */
    public void startupDubbo() {
        System.out.println("dubbo startup...");
        try {
            ctx = new ClassPathXmlApplicationContext(new String[]{"dubbo_consumer.xml"});
            ctx.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dubbo startup success");
    }

    /**
     * 启动配置路由, 加载配置路由信息
     * 启动dubbo路由, 加载dubbo引用信息
     */
    public void startupProcessor() {
        config = new CallConfig();
        config.reload();
        conPro = new ConfigProcessor(config);
        wePro = new BizProcessor(config, ctx);
        dubboPro = new DubboProcessor(ctx);
    }

    /**
     * 启动netty
     */
    public void startupNetty() {
        //启动netty
        System.out.println("netty startup...");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.option(ChannelOption.SO_BACKLOG, 1024);
            boot.group(bossGroup, workGroup);
            boot.channel(NioServerSocketChannel.class);
            boot.handler(new LoggingHandler(LogLevel.WARN));
            boot.childHandler(new LizhiweiInitializer(this));
            Channel ch = boot.bind(8007).sync().channel();
            ch.closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
        System.out.println("netty startup success");
    }
}

class LizhiweiInitializer extends ChannelInitializer<SocketChannel> {

    DubboNettyServer server;

    public LizhiweiInitializer(DubboNettyServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpRequestDecoder());
        p.addLast("compressor", new HttpContentCompressor());
        System.out.println("server: " + server);
        p.addLast(new LizhiweiInstreamHandler(this.server));
    }
}

/**
 * ChannelInboundHandlerAdapter调用顺序<br />
 * channelActive channelRead (DefaultHttpRequest) channelRead
 * (EmptyLastHttpContent) channelReadComplete channelInactive
 *
 * @author lizhiwei
 */
class LizhiweiInstreamHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(DubboNettyServer.class);

    DubboNettyServer server = null;
    boolean isByte;        //判断http提交内容里是否带流数据, 比如上传文件, 是-doProcessMap, 否-doProcess

    public LizhiweiInstreamHandler(DubboNettyServer server) {
        this.server = server;
    }

    Map<String, Object> mapField = null;    //保存参数, key-value {id=lizhiwei},{happenDate,20170306}
    Map<String, Object> mapFile = null;    //保存文件, key-value {filename1,byte[]},{filename2,byte[]}
    HttpRequest request = null;
    FullHttpResponse response = null;
    boolean readingChunks = false;
    HttpPostRequestDecoder decoder = null;

    private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

    {
        DiskFileUpload.deleteOnExitTemporaryFile = true; // should delete file
        // on exit (in normal
        // exit)
        DiskFileUpload.baseDirectory = null; // system temp directory
        DiskAttribute.deleteOnExitTemporaryFile = true; // should delete file on
        // exit (in normal exit)
        DiskAttribute.baseDirectory = null; // system temp directory
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		super.channelRead(ctx, msg); //To change body of generated methods, choose Tools | Templates.
        System.out.println("channelRead");
        System.out.println("msg: " + msg);
        if (msg instanceof HttpRequest) {
//			testZK();
            mapField = new HashMap<>();
            mapFile = new HashMap<>();
            log.debug("channelRead of HttpRequest");
            request = (HttpRequest) msg;
            //有些浏览器会自动发送一个图标的请求, 如chrome
            //eg: "/favicon.ico"
            if ("/exit".equals(request.getUri())) {
                response = writeHttpEntiry(request, "netty shutdonw");
                ctx.write(response);
                ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            } else if ("/favicon.ico".equalsIgnoreCase(request.getUri())) {
                System.out.println("get favicon.ico");
                String path = "D:/lizhiwei/msn人仔2.jpg";
                FileInputStream fin = new FileInputStream(path);
                byte[] b = new byte[fin.available()];
                fin.read(b);
                response = writeHttpEntiry(request, b);
                ctx.write(response);
            } else {
                if ("get".equalsIgnoreCase(request.getMethod().toString())) {
                    Map<String, Object> map1 = new HashMap<>();
                    response = writeHttpEntiry(request, "this is lizw(from get)");
                    //处理参数
                    {
                        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
                        Map<String, List<String>> map = decoder.parameters();
                        Set<String> set = map.keySet();
                        for (String key : set) {
                            List<String> l = map.get(key);
                            System.out.println(String.format("key: %s, value: %s", key, Arrays.toString(l.toArray(new String[0]))));
                            map1.put(key, l.get(0));
                        }
                    }
                    String text = doProcess(request.getUri(), map1);
                    ctx.writeAndFlush(writeHttpEntiry(request, text));
                    ctx.close();
                } else if ("post".equalsIgnoreCase(request.getMethod().toString())) {
                    /*
                     组件处理方式
					 */
                    decoder = new HttpPostRequestDecoder(factory, request);
                    readingChunks = HttpHeaders.isTransferEncodingChunked(request);
                    while (decoder.hasNext()) {
                        InterfaceHttpData d = decoder.next();
                        String key = d.getName();
                        String value = "null";
                        if (d.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                            Attribute attr = (Attribute) d;
                            value = attr.getValue();
                        }
                        System.out.println(String.format("key: %s, value: %s", key, value));
                    }
//					ctx.close();
                }
            }
        } else if (msg instanceof HttpResponse) {
            log.debug("channelRead of HttpResponse");
        } else if (msg instanceof HttpContent) {
            if (null != decoder) {
                HttpContent content = (HttpContent) msg;
                decoder.offer(content);
                readHttpDataChunkByChunk();        //获取数据
                if (content instanceof LastHttpContent) {
                    readingChunks = false;
                    String uri = request.getUri();
                    System.out.println("uri1: " + uri);
                    reset();
                    //执行业务代码
                    String text = doProcess(uri, mapField);
//					ctx.writeAndFlush(writeHttpEntiry(request, "this is lizw(from post)"));
                    ctx.writeAndFlush(writeHttpEntiry(request, text));
                }
            }

        }
    }

    /**
     * Example of reading request by chunk and getting values from chunk to
     * chunk 从decoder中读出数据，写入临时对象，然后写入...哪里？ 这个封装主要是为了释放临时对象
     */
    private void readHttpDataChunkByChunk() {
        try {
            while (decoder.hasNext()) {
                InterfaceHttpData data = decoder.next();
                if (data != null) {
                    try {
                        // new value
                        writeHttpData(data);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        data.release();
                    }
                }
            }
        } catch (EndOfDataDecoderException e1) {
            // end
        }
    }

    private void writeHttpData(InterfaceHttpData data) throws IOException {
        // Attribute就是form表单里带的各种 name= 的属性
        if (data.getHttpDataType() == HttpDataType.Attribute) {
            Attribute attr = (Attribute) data;
            log.debug(String.format("抽取表单内容: name=%s, value=%s", attr.getName(), attr.getValue()));
            mapField.put(attr.getName(), attr.getValue());
        } else if (data.getHttpDataType() == HttpDataType.InternalAttribute) {

        } else {
            log.debug("data.toString: " + data.toString());
            FileUpload fileUpload = (FileUpload) data;
            if (fileUpload.isCompleted() && fileUpload.content().capacity() > 0) {
                System.out.println("fileUpload.isInMemory:" + fileUpload.isInMemory());
                System.out.println("fileUpload.content:" + fileUpload.content().capacity());
                //在内存中, 直接getFile会报错, 要先写磁盘, 用rename函数, 如果直接走内存而不用
                System.out.println(fileUpload.getFilename());
                byte[] b = fileUpload.get();
                System.out.println(b.length);
                mapFile.put(fileUpload.getFilename(), b);
                isByte = true;    //设置为上传标志, 为了识别使用doProcess,还是doProcessMap
            }
        }
    }

    private String getUploadFileName(InterfaceHttpData data) {
        String content = data.toString();
        String temp = content.substring(0, content.indexOf("\n"));
        content = temp.substring(temp.lastIndexOf("=") + 2, temp.lastIndexOf("\""));
        return content;
    }

    private void reset() {
        request = null;
        // destroy the decoder to release all resources
        decoder.destroy();
        decoder = null;
    }

    /**
     * 执行业务任务
     *
     * @param uri 如/abcd
     * @param map key-value, 如 {id,745591}, {filename,byte[]>
     */
    private String doProcess(String uri, Map<String, Object> map) {
        String result = "";
        try {
            log.debug("执行业务代码开始");
            log.debug("uri: " + uri);
            Set<String> set = map.keySet();
            for (String key : set) {
                log.debug(String.format("httprequest 参数 key:%s, value:%s", key, map.get(key)));
            }
            /*
            if(uri.equalsIgnoreCase("/FirmwareService.getWatchfaceList")){
				WeatherService service=(WeatherService)ctx.getBean("WeatherService");
				String json="{\"userId\":123456, \"userName\":\"lizw\", \"msgString\":\"测试\"}";
				result=(String)service.call("weatherService.helloWorld", json);
//				result=(String)service.call("weatherService.helloWorld","{\"id\":\"test一下\"}");
				System.out.println("reuslt: "+result);
			}
			*/
            //"执行业务代码结束"

            if (0 == uri.indexOf("/config/json")) {
                result = server.conPro.doProcess(uri, map);
            } else if (0 == uri.indexOf("/config/dubbo")) {
                result = server.dubboPro.doProcess(uri, map);
            } else {
                log.debug("server.wePro.doProcess(uri, map);");
                if (isByte)
                    result = server.wePro.doProcessMap(uri, map);
                else
                    result = server.wePro.doProcess(uri, map);
            }
            log.debug("执行业务代码结束");
        } catch (Exception e) {
            String msg = String.format("调用dubbo rpc失败, uri: %s, \nmessage: %s", uri, e.getMessage());
            result = msg.replaceAll("\n", "<br />");            //换成http格式
            e.printStackTrace();
            log.error(msg);
        }
        return result;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete1");
        super.channelReadComplete(ctx); //To change body of generated methods, choose Tools | Templates.
        ctx.flush();
        System.out.println("channelReadComplete2");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause); //To change body of generated methods, choose Tools | Templates.
        System.out.println("exceptionCaught");
        ctx.writeAndFlush("error from server");
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//		super.userEventTriggered(ctx, evt); //To change body of generated methods, choose Tools | Templates.
        System.out.println("userEventTriggered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		super.channelActive(ctx); //To change body of generated methods, choose Tools | Templates.
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		super.channelInactive(ctx); //To change body of generated methods, choose Tools | Templates.
        System.out.println("channelInactive");
    }

    /**
     * 封装输出流<br />
     * 把文本转化成http协议方式
     *
     * @param request
     * @param text
     * @return
     */
    private FullHttpResponse writeHttpEntiry(HttpRequest request, String text) throws UnsupportedEncodingException {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(text.getBytes("utf8")
        ));
        response.headers().set(CONTENT_TYPE, "text/html;charset=utf-8");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(DATE, new Date().toString());
        response.headers().set(SERVER, "netty-4.0.29-Final");
        response.headers().set(ACCEPT_RANGES, "bytes");
        return response;
    }

    /**
     * 封装输出流<br />
     * 把流转化成http协议方式
     *
     * @param request
     * @param b
     * @return
     */
    private FullHttpResponse writeHttpEntiry(HttpRequest request, byte[] b) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(b));
        response.headers().set(CONTENT_TYPE, "text/html");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(DATE, new Date().toString());
        response.headers().set(SERVER, "netty-4.0.29-Final");
        response.headers().set(ACCEPT_RANGES, "bytes");
        return response;
    }

}

