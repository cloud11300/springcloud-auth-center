package com.cheryev.crm.auth.pojo;

/**
 * 返回响应
 * @param <T>
 */
public class ResponseData<T> {

    public static final String SUCCESS_CODE = "03010000";

    public static final String FAIL_CODE = "03010001";//参数错误

    public static final String SUCCESS_STATUS = "SUCCESS";

    public static final String FAIL_STATUS = "FAIL";

    protected String respCode;

    protected String respMessage;

    protected T data;

    public ResponseData() {
    }

    public ResponseData(String respCode, String respMessage) {
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public ResponseData(String respCode, String respMessage, T data) {
        this.respCode = respCode;
        this.respMessage = respMessage;
        this.data = data;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseData<T> success (T data) {
        return ResponseData.success(data, SUCCESS_STATUS);
    }

    public static <T> ResponseData<T> success (T data, String message) {
        return new ResponseData(SUCCESS_CODE, message, data);
    }


    public static ResponseData fail (String respCode, String respMessage) {
        return new ResponseData(respCode,  respMessage);
    }

    public static ResponseData fail (String message) {
        return new ResponseData(FAIL_CODE,  message);
    }

    public static ResponseData fail() {
        return ResponseData.fail(FAIL_STATUS);
    }

}
