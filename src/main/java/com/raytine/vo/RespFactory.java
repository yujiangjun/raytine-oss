package com.raytine.vo;

public class RespFactory {

    private RespFactory() {
    }

    public static Resp<Void> createVoidResp(int code, String message){
        Resp<Void> voidResp = new Resp<>();
        voidResp.setCode(code);
        voidResp.setMessage(message);
        return voidResp;
    }

    public static <T> Resp<T> createResp(int code,String message,T data){
        Resp<T> resp = new Resp<>();
        resp.setData(data);
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }
}
