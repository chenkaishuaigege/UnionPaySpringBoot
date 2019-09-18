package com.rrtx.dataobject;

import com.rrtx.util.JavaUtil;

import java.io.Serializable;

public class MsgResponse implements Serializable {

    String responseCode;
    String responseMsg;


    public Builder builder() {
        return new Builder();
    }

    public class Builder {

        MsgResponse msgResponse = new MsgResponse();

        public MsgResponse createEntity() {
            return msgResponse;
        }

        public Builder setResponseCode(String responseCode_) {
            responseCode = responseCode_;
            msgResponse.setResponseCode(responseCode);
            return this;
        }

        public Builder setResponseMsg(String responseMsg_) {
            responseMsg = responseMsg_;
            msgResponse.setResponseMsg(responseMsg);
            return this;
        }
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    private void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    private void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public String toString() {
        return "{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                '}';
    }
}
