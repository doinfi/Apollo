package com.yf.coros.common.utils;

import com.yf.coros.common.entity.data.SportDataImageData;
import com.yf.coros.common.entity.data.Track;
import com.yf.coros.common.enums.ImageAndDataType;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 远峰IOUtils工具类
 *
 * @author Infi
 * @date 17/5/11
 */
public class YfIOUtils {

    /**
     * 读取Coros运动数据或者轨迹缩率图数据
     * 数据格式如下:
     * Data=Count[2Byte]Head1…HeadnData1…Datan
     * Head=UUID[16Byte]Mode[1Byte]Length[4Byte]
     *
     * @param sportData 运动数据
     * @return uuid对应的运动数据或者轨迹缩率图列表
     */
    public static List<SportDataImageData> readImageDataAndSportData(byte[] sportData)
            throws IOException {
        List<SportDataImageData> corosDatas = new ArrayList<>();
        // 1. 读取文件数据内容并解析成算法需要的byte数组
        ByteBuffer byteBuffer = ByteBuffer.wrap(sportData).order(ByteOrder.LITTLE_ENDIAN);
        // 2. 读取Count[2Byte]
        int count = 0xff & byteBuffer.getShort();
        if (count < 1) {
            return new ArrayList<>();
        }
//        int indexCount = 2 + 21 * count;
        // 3. 获取运动数据的头部信息,UUID[16Byte]Mode[1Byte]Length[4Byte]
        for (int i = 0; i < count; i++) {
            // 3. 读取UUID[16Byte]
            byte[] UUIDBytes = new byte[16];
            byteBuffer.get(UUIDBytes, 0, UUIDBytes.length);
            String UUID = YfTools.toHexString(UUIDBytes);
            // 4. 读取Mode[1Byte]
            int mode = 0xff & byteBuffer.get();
            // 5. 读取数据长度Length[4Byte]
            int dataLength = byteBuffer.getInt();
            SportDataImageData sportDataImageData = new SportDataImageData();
            sportDataImageData.setUuid(UUID);
            sportDataImageData.setMode(mode);
            sportDataImageData.setDataLength(dataLength);
            corosDatas.add(sportDataImageData);
        }
        // 4. 读取data的内容
        for (int i = 0; i < count; i++) {
            SportDataImageData sportDataImageData = corosDatas.get(i);
            int dataLength = sportDataImageData.getDataLength();
            byte[] data = new byte[dataLength];
            byteBuffer.get(data, 0, data.length);
            sportDataImageData.setData(data);
        }
        return corosDatas;
    }


    /**
     * 读取Coros轨迹数据或者轨迹缩率图数据
     * 数据格式如下:
     * Data=Count[2Byte]Head1…HeadnData1…Datan
     * Head=UUID[8Byte]Length[4Byte]
     *
     * @param fileData 运动数据
     * @return uuid对应的运动数据或者轨迹缩率图列表
     */
    public static List<Track> readTrackAndImage(byte[] fileData, Integer type) {
        List<Track> trackList = new ArrayList<>();
        // 1. 读取文件数据内容并解析成算法需要的byte数组
        ByteBuffer byteBuffer = ByteBuffer.wrap(fileData).order(ByteOrder.LITTLE_ENDIAN);
        // 2. 读取Count[2Byte]
        int count = 0xff & byteBuffer.getShort();
        if (count < 1) {
            return new ArrayList<>();
        }
        //int indexCount = 2 + 21 * count;
        // 3. 获取运动数据的头部信息,UUID[16Byte]Mode[1Byte]Length[4Byte]
        for (int i = 0; i < count; i++) {
            // 3. 读取UUID[16Byte]
            byte[] UUIDBytes = new byte[8];
            byteBuffer.get(UUIDBytes, 0, UUIDBytes.length);
            String UUID = YfTools.toHexString(UUIDBytes);
            // 5. 读取数据长度Length[4Byte]
            int dataLength = byteBuffer.getInt();
            Track track = new Track();
            track.setUuid(UUID);
            track.setDataLength(dataLength);
            trackList.add(track);
        }
        // 4. 读取data的内容
        for (int i = 0; i < count; i++) {
            Track track = trackList.get(i);
            int dataLength = track.getDataLength();
            byte[] data = new byte[dataLength];
            byteBuffer.get(data, 0, data.length);
            if (ImageAndDataType.DATA.equals(type)) {
                track.setTrackData(data);
            } else if (ImageAndDataType.IMAGE.equals(type)) {
                track.setImageData(data);
            }
        }
        return trackList;
    }
}
