package com.yf.amp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Properties;

public class Amp{
    static {
        if (System.getProperties().getProperty("os.name").toLowerCase().startsWith("mac")) {
//            System.loadLibrary("amp-jni");
            System.load("/Users/Infi/Documents/COROS-so-file/amp-2018-05-14/libamp-jni.dylib");
        } else {
            System.loadLibrary("amp-jni");
        }
    }
    
    /**
     * 步幅算法：根据训练数据计算k/b值
     * @param data eq训练数据,double类型数据的小端buffer
     * @param lastResult 历史的k/b值，为null或者AmpResult.available为false，历史k/b无效
     * @return k/b值的计算结果
     */
    public static AmpResult solveWideEquations(byte[] data, AmpResult lastResult){
        if(data == null || data.length < 16){
            return new AmpResult(0, 0, false);
        }
        
        ByteBuffer configBB = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
        if(lastResult != null && lastResult.available){
            configBB.putDouble(lastResult.k);
            configBB.putDouble(lastResult.b);
        }else{
            configBB.putDouble(0);
            configBB.putDouble(0);
        }
        
        byte[] resultBuf = natvieSolveWideEquations(data, configBB.array());
        
        if(resultBuf == null || resultBuf.length < 16){
            return new AmpResult(Double.MIN_VALUE, 0, false);
        }
        
        ByteBuffer bb = ByteBuffer.wrap(resultBuf).order(ByteOrder.LITTLE_ENDIAN);
        return new AmpResult(bb.getDouble(), bb.getDouble(), true); 
        
    }
    
    public static double calcWide(byte[] data, double k, double b){
        if(data == null || data.length < 16){
            return 0;
        }
        byte[] resultBuf = natvieCalcWide(data, k, b);
        
        if(resultBuf == null || resultBuf.length < 8){
            return 0;
        }
        
        ByteBuffer bb = ByteBuffer.wrap(resultBuf).order(ByteOrder.LITTLE_ENDIAN);
        return bb.getDouble(); 
    }
    
    public native static byte[] natvieSolveWideEquations(byte[] data, byte[] config);
    
    public native static byte[] natvieCalcWide(byte[] data, double k, double b);
}