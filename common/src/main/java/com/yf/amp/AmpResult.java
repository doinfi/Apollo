package com.yf.amp;

public class AmpResult{
    public final double k; //步幅算法的k值
    public final double b; //步幅算法的b值
    public final boolean available; //ture-表示程序正常，k/b值是正常计算出来的，false-表示程序异常，k/b不可以用
    
    public AmpResult(double k, double b, boolean available){
        this.k = k;
        this.b = b;
        this.available = available;
    }
}