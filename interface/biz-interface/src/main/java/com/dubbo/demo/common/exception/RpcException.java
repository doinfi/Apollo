package com.dubbo.demo.common.exception;

/**
 * RPC反射调用时出现的异常
 *
 * @author hudahua
 */
public class RpcException extends Exception {

    private static final long serialVersionUID = 3389773943568294257L;

    public RpcException(String msg) {
        super(msg);
    }
}
