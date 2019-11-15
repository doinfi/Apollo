/**
 * @Title: FileReader.java
 * @Package org.com.yf.coros.common.label.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author pichunhan
 * @date 2015年12月7日 上午10:16:07
 * @version V1.0
 */

package com.yf.coros.common.label.util;

import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.exception.YfException;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * 类描述：
 *
 * @author pichunhan
 * @ClassName: FileReader
 * @date 2015年12月7日 上午10:16:07
 */
@Slf4j
public class FileReader {

    /**
     * 日常数据头部格式
     * Data=Count[4Byte]Head1…HeadnData1…Datan
     * Head=Length[4Byte]
     * <p>
     * 把原始数据文件流转化成byte二维数组
     *
     * @param sourceArr 原始数据数组
     * @return 原始数据二维数组
     */
    public static byte[][] resolutionSourceStream(byte[] sourceArr) throws YfException {
        DataInputStream dis = null;
        try {
            byte[][] datas;
            ByteBuffer sourceBuffer = ByteBuffer.wrap(sourceArr).order(ByteOrder.LITTLE_ENDIAN);
            int dataNum = sourceBuffer.getInt();
            int[] lengths = new int[dataNum];
            datas = new byte[dataNum][];
            for (int i = 0; i < dataNum; i++) {
                lengths[i] = sourceBuffer.getInt();
            }
            for (int i = 0; i < lengths.length; i++) {
                datas[i] = new byte[lengths[i]];
                sourceBuffer.get(datas[i], 0, lengths[i]);

            }
            return datas;
        } catch (Exception e) {
            log.error("读取原始数据文件出错：" + e.getMessage());
            throw new YfException(MessageKey.RAW_DATA_ANALYSIS_ERROR);
        }
    }

    /**
     * 将多段运动数据包装成一段buffer，与上面的resolutionSourceStream相反
     * @param dataBlocks
     * @return
     */
    public static byte[] blockParse(List<byte[]> dataBlocks) {
        //System.out.println("111 buffer=" + SecureUtil.toHexString(buffer));
        if(null==dataBlocks || dataBlocks.size()==0){
            return null;
        }

        int dataOffset = 4 + (4 * dataBlocks.size());
        for (byte[] buffer:dataBlocks){
            dataOffset+=buffer.length;
        }
        ByteBuffer sourceBuffer = ByteBuffer.allocate(dataOffset).order(ByteOrder.LITTLE_ENDIAN);
        sourceBuffer.putInt(dataBlocks.size());
        for (byte[] buffer:dataBlocks){
            sourceBuffer.putInt(buffer.length);
        }
        for (byte[] buffer:dataBlocks){
            sourceBuffer.put(buffer);
        }
        return sourceBuffer.array();
    }

}
