package com.thinkgem.jeesite.wx.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * Created by Leevey on 2017/7/11.
 */
@Component
public class WXJsPayConfigInfo implements Serializable {
    @Value("${app_id}")
    private String appId;
    @Value("${pay.wxJsPay.partner}")
    private String partner;
    @Value("${pay.wxJsPay.appKey}")
    private String key;
    @Value("${app_sercet}")
    private String secret;
    @Value("${pay.wxJsPay.unifiedOrderUrl}")
    private String unifiedOrderUrl;
    @Value("${pay.wxJsPay.refundUrl}")
    private String refundUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUnifiedOrderUrl() {
        return unifiedOrderUrl;
    }

    public void setUnifiedOrderUrl(String unifiedOrderUrl) {
        this.unifiedOrderUrl = unifiedOrderUrl;
    }

    public String getRefundUrl() {
        return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }
}
