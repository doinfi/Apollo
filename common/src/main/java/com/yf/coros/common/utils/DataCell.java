/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.coros.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 每分钟统计的数据<br />
 * 包括2部分, cnt和tps<br />
 * cnt: count, total, avg, min, max<br />
 * pointcut, 每分钟的tps_unit可配置, 默认15秒结算一次tps, 即1分钟有4个tps数据<br />
 * 在默认4个的情况下, tps为4个15秒的平均tps<br />
 * 注意cnt是按分钟统计, tps是按tps_unit统计<br />
 * <br />
 * 使用示例<br />
 * DataCell cell=new DataCell(app,10);		//tps按10秒统计<br />
 * <br />
 * @author lizhiwei
 */
@Slf4j
public class DataCell {
	
	/**
	 * 接口名
	 */
	String app;
	/**
	 * key - 分钟数据, 如201706222138
	 * value -  Object[2], 0-cnt, 1-pointcut, 为了减少开销, 这里使用基础类型<br />
	 * cnt: 当前分钟数据对应的并发和耗时数据结构: count, total, avg, min, max<br />
	 * pointcut: 当前分钟数据对应的tps数据结构, 如果统计单位为15秒的话, 样例数据为: 300, 250, 200, 350, 如第一个15秒的300, 这里不是指每秒300, 而是15秒内总共300<br />
	 */
	Map<String,Object[]> map;
	
	/**
	 * 结构同map, 但这个是结算完毕的
	 */
	Map<String,Object[]> map_done;
	
	
	Timer t;		//分钟结算定时器
	
//	long[] min;		//当前分钟值, 如201706222236
//	long[] cnt;		//当前分钟数据对应的并发和耗时数据结构: count, total, avg, min, max
//	long[] pointcut;		//当前分钟数据对应的tps数据结构, 如果统计单位为15秒的话, 样例数据为: 300, 250, 200, 350, 如第一个15秒的300, 这里不是指每秒300, 而是15秒内总共300
	
	int tps_unit=15;	//tps统计单位(秒), 默认15秒, 要求是1分钟内能取除整, 如3,5,10,15之类, 不能是11, 18这种, 这样1分钟不能完成循环
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
	
	public DataCell(String app) {
		this.app=app;
		map=new HashMap<>();
		map_done=new HashMap<>();
		calcIndex();
	}
	
	public DataCell(String app, int tps_unit) {
		this.app=app;
		map=new HashMap<>();
		map_done=new HashMap<>();
		this.tps_unit=tps_unit;
		calcIndex();
	}
	
	public void clearDoneData(){
		map_done.clear();
	}
	
	public void release(){
		t.cancel();
		t=null;
	}
	
	/**
	 * 这里只累计当前分钟数据, 分钟结算交给定时器处理
	 * @param startTime
	 * @param endTime 
	 */
	public synchronized void addData(long startTime, long endTime){
		Date d=new Date(endTime);
		String min=sdf.format(d);
		Object[] obj=map.get(min);
		long[] cnt;
		long[] tps;
		if(null==obj){
			cnt=new long[5];
			int tps_len=60/tps_unit;
			tps=new long[tps_len];
			obj=new Object[2];
			obj[0]=cnt;
			obj[1]=tps;
			map.put(min, obj);
		}else{
			cnt=(long[])obj[0];
			tps=(long[])obj[1];
		}
		long diff=endTime-startTime;
		int second=d.getSeconds();
		//处理并发和耗时
		{
			//count
			cnt[0]++;
			//total
			cnt[1]+=diff;
			//avg
			//最后分钟结算时计算
			//min
			if(0==cnt[3] || diff<cnt[3])
				cnt[3]=diff;
			//max
			if(diff>cnt[4])
				cnt[4]=diff;
		}
		//处理tps
		{
			int n=0;
			n=second/tps_unit;			//计算tps的索引, 如13/15=0, 第0个tps区, 25/15=1, 第1个tps区, 59/15=3, 第3个tps区
			log.trace(String.format("pointcut 入栈 min:%s second:%d n:%d",min, second, n));
			tps[n]++;
		}
		log.trace(JSON.toJSONString(obj));
//		obj[0]=cnt;
//		obj[1]=pointcut;
//		map.put(min, obj);
		//数据是持久对象, 所以这里不用再次添加
	}
	
	/**
	 * 定时器, 防止请求太松时, 错过00秒, 所以外定时器每秒跑一次, 当00秒时, 结算分钟数据
	 */
	private void calcIndex(){
		log.info(String.format("接口(%s)性能监控收集器开启", app));
		t=new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				Calendar c=Calendar.getInstance();
				int second=c.get(Calendar.SECOND);
				try{
					//每分钟结算, 为了提高准确度, 多缓冲2秒
					c.add(Calendar.MINUTE, -1);	//结算上一分钟数据
					Date d=c.getTime();
					String min=sdf.format(d);
					log.info(String.format("接口%s性能监控收集器分钟触发, min:%s", app, min));
					Object[] obj=map.get(min);
					long[] cnt;
					long[] tps;
					if(null==obj){
						cnt=new long[5];
						int tps_len=60/tps_unit;
						tps=new long[tps_len];
						obj=new Object[]{cnt, tps};
						map.put(min, obj);
	//						map_done.put(min, obj);		//结算时没有数据就不出结算了
					}else{
						cnt=(long[])obj[0];
						tps=(long[])obj[1];
						map_done.put(min, obj);
					}
					//计算cnt_avg
					cnt[2]=(0!=cnt[0])?cnt[1]/cnt[0]:0;
					log.info(String.format("接口%s性能监控收集器分钟结算完成, second:%d, min:%s, \ncnt:%s\npointcut:%s", app, second, min, JSON.toJSONString(cnt), JSON.toJSONString(tps)));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}, 5000, 60*1000);
	}
	
	/**
	 * 单元测试用例
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("d:/test.log"));
		System.out.println("start");
		String app="FirmwareService.getWatchface";
		DataCell cell=new DataCell(app,10);
		int len=10240;
		for(int i=0;i<len;i++){
			long n=(long)(Math.random()*100);
			System.out.println(String.format("no: %d, sleep: %dms", i+1, n));
			Thread.sleep(n);
			Date d=new Date();
			long t2=d.getTime();
			long t1=t2-(long)(Math.random()*100);
			System.out.println(String.format("t1:%d,t2:%d,diff:%d",t1,t2,t2-t1));
			cell.addData(t1, t2);
		}
		System.out.println("end");
		System.exit(0);
	}
	
}
