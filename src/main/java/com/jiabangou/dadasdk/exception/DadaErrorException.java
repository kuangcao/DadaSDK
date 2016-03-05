package com.jiabangou.dadasdk.exception;

import com.jiabangou.dadasdk.model.DadaError;

/**
 * 达达快递错误码说明
 * https://open.imdada.cn/wiki/errorCode/
 */
public class DadaErrorException extends Exception {

    protected int code;

    public DadaErrorException(DadaError dadaError) {
        super(dadaError.getErrorMsg());
        this.code = dadaError.getErrorCode();
    }

    public DadaErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public DadaErrorException(int code) {
        super(ErrorCode.getMsg(code));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "DadaErrorException{" +
                "code=" + code +
                ", message=" + getMessage() +
                "} " + super.toString();
    }

}
