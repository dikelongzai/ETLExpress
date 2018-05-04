package com.etl.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class ResultStatusCode {
    public static ResultStatusCode OK = new ResultStatusCode("C0000", "OK");
    public static ResultStatusCode AUTHOR_ERROR= new ResultStatusCode("C0001","校验失败");
    public static ResultStatusCode PARAM_ERROR = new ResultStatusCode("C0002","参数有误");
    public static ResultStatusCode SERVICE_ERROR= new ResultStatusCode("C0003","服务器执行异常");
    public static ResultStatusCode VNO_ERROR= new ResultStatusCode("C0004","车牌对应的车辆ID不存在");
    private String code;
    private String message;

    public String getLastRow() {
        return lastRow;
    }

    public void setLastRow(String lastRow) {
        this.lastRow = lastRow;
    }

    private String lastRow;

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    private Object returnData;
    public ResultStatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResultStatusCode() {

    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("code = ").append(code).append(", message = ").append(message);
        return sb.toString();
    }
    public JSONObject toJson(){
        JSONObject json=new JSONObject();
        json.put("code",code);
        json.put("msg",message);
        json.put("data",returnData);
        if(getLastRow()!=null){
            json.put("lastRow",getLastRow());
        }
        return json;
    }
}
