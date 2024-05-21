package com.nguyen1o2.payload;

public class ResponseData {
    private int status=200;
    private String desc;
    private Object data;
    private boolean isSucsess = true;

    public boolean isSucsess() {
        return isSucsess;
    }

    public void setSucsess(boolean sucsess) {
        isSucsess = sucsess;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
