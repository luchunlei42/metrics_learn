package com.chunlei.account.domain;

public class CommonResult<T> {
    private T date;
    private String code;
    private String message;
    public static CommonResult FAILED(String message){
        return new CommonResult(message,"500");
    }
    public static <T> CommonResult SUCCESS(T data){
        return new CommonResult(data,"调用成功","200");
    }
    private <T> CommonResult(T data, String message, String code){
        this.code = code;
        this.message = message;
    }
    private CommonResult(String message, String code){
        this.code = code;
        this.message = message;
    }
}
