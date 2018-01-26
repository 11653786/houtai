package com.thinkgem.jeesite.wx.entity;


import com.thinkgem.jeesite.util.SHA1;
import com.thinkgem.jeesite.util.SwiftPassSignUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author Leevey
 * @create 2017-12-20 17:46
 */
public class JsApiTicketSignInfo {

    private String noncestr;
    private String jsapi_ticket;
    private String timestamp;
    private String url;
    private String sign;

    public JsApiTicketSignInfo() {
    }

    /**
     *
     * @param noncestr 随机字符串
     * @param jsapi_ticket  有效的jsapi_ticket
     * @param timestamp 时间戳
     * @param url 当前网页的URL，不包含#及其后面部分
     */
    public JsApiTicketSignInfo(String noncestr, String jsapi_ticket, String timestamp, String url) {
        setNoncestr(noncestr);
        setJsapi_ticket(jsapi_ticket);
        setTimestamp(timestamp);
        setUrl(url);
        //根据API给的签名规则进行签名
        Map<String,String> params = SwiftPassSignUtils.paraFilter(toMap());
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SwiftPassSignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = SHA1.encode(preStr);
        setSign(sign);//把签名数据设置到Sign这个属性中
    }

    public Map<String, String> toMap(){
        Map<String,String> map = new HashMap<String, String>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String s;
            try {
                s = (String) field.get(this);
                if(s!=null){
                    map.put(field.getName(), s);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
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
