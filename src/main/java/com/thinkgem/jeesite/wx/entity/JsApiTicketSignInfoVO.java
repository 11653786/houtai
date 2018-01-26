package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author Leevey
 * @create 2017-12-20 17:46
 */
public class JsApiTicketSignInfoVO implements Serializable {

    private static final long serialVersionUID = 1316014881318558033L;
    private String appId;
    private String noncestr;
    private String jsapi_ticket;
    private String timestamp;
    private String url;
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
