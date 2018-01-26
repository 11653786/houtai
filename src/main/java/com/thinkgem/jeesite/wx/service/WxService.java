package com.thinkgem.jeesite.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.handler.PlatformRes;
import com.thinkgem.jeesite.util.HttpRequestUtil;
import com.thinkgem.jeesite.wx.WxController;
import com.thinkgem.jeesite.wx.entity.*;
import com.thinkgem.jeesite.wx.mapper.AccessTokenMapper;
import com.thinkgem.jeesite.wx.vo.AccessToken;
import com.thinkgem.jeesite.wx.vo.AccessTokenExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yangtao on 2018-01-26.
 */
@Service
public class WxService {


    @Autowired
    private WXJsPayConfigInfo wxJsPayConfigInfo;

    @Autowired
    private AccessTokenMapper accessTokenMapper;


    protected static final Logger logger = LoggerFactory.getLogger(WxController.class);

    /**
     * 获取access_token<br/>
     * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     */
    private PlatformRes<String> getAccessToken() {
        Date now = new Date();
        AccessTokenExample example = new AccessTokenExample();
        AccessTokenExample.Criteria query = example.createCriteria();
        query.andInTimeLessThan(now).andInOutTimeGreaterThan(now).andTokenTypeEqualTo(0);
        List<AccessToken> list = accessTokenMapper.selectByExample(example);

        if (list != null && !list.isEmpty())
            return PlatformRes.success(list.get(0).getAccess_token());

        //从网络上获取access_token
        String secret = wxJsPayConfigInfo.getSecret();
        byte[] buf = HttpRequestUtil.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + wxJsPayConfigInfo.getAppId() + "&secret=" + secret);
        String content = new String(buf);

        logger.info("获取access_token: " + content);

        AccessTokenInfo tokenInfo = JSONObject.parseObject(content, AccessTokenInfo.class);
        //token内容
        String accessToken = tokenInfo.getAccess_token();

        if (StringUtils.isBlank(accessToken)) {
            logger.info("获取access_token的错误信息: " + tokenInfo.getErrmsg());
            return PlatformRes.error(tokenInfo.getErrcode() + "", tokenInfo.getErrmsg());
        }


        AccessToken token = new AccessToken();
        token.setInTime(now);
        token.setInOutTime(getTwoHoursDate(now));
        token.setTokenType(0);
        token.setAccess_token(accessToken);
        accessTokenMapper.insert(token);
        return PlatformRes.success(accessToken);
    }


    /**
     * 获取jsApi_ticket<br/>
     * （有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     */
    private PlatformRes<String> getJsApiTicket() {

        Date now = new Date();
        AccessTokenExample example = new AccessTokenExample();
        AccessTokenExample.Criteria query = example.createCriteria();
        query.andInTimeLessThan(now).andInOutTimeGreaterThan(now).andTokenTypeEqualTo(1);
        List<AccessToken> list = accessTokenMapper.selectByExample(example);


        if (list != null && !list.isEmpty())
            return PlatformRes.success(list.get(0).getAccess_token());

        //重新获取
        PlatformRes<String> result = getAccessToken();
        if (!result.getCode().equals("0")) {
            return result;
        }
        //从网络上获取jsapi_ticket
        byte[] buf = HttpRequestUtil.httpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + result.getData() + "&type=jsapi");
        String content = new String(buf);
        logger.info("获取jsapi_ticket: " + content);
        JsApiTicketInfo tokenInfo = JSONObject.parseObject(content, JsApiTicketInfo.class);
        if (tokenInfo.getErrcode() != 0) {
            logger.info("获取jsapi_ticket的错误信息: " + tokenInfo.getErrmsg());
            return PlatformRes.error(tokenInfo.getErrcode() + "", tokenInfo.getErrmsg());
        }


        AccessToken token = new AccessToken();
        token.setInTime(now);
        token.setInOutTime(getTwoHoursDate(now));
        token.setTokenType(0);
        token.setAccess_token(tokenInfo.getTicket());
        accessTokenMapper.insert(token);
        return PlatformRes.success(tokenInfo.getTicket());


    }

    /**
     * 获取权限验证配置
     *
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return
     */
    public PlatformRes<String> getWxJsConfig(String url) {
        PlatformRes<String> result = getJsApiTicket();
        if (!result.getCode().equals("0")) {
            return result;
        }
        String jsApiTicket = result.getData();
        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        String timestamp = String.valueOf(new Date().getTime());
        JsApiTicketSignInfo signInfo = new JsApiTicketSignInfo(noncestr, jsApiTicket, timestamp, url);
        JsApiTicketSignInfoVO signInfoVO = new JsApiTicketSignInfoVO();
        try {
            BeanUtils.copyProperties(signInfo, signInfoVO);
            signInfoVO.setAppId(wxJsPayConfigInfo.getAppId());
            logger.info("JS权限验证配置信息: " + JSONObject.toJSONString(signInfoVO));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return PlatformRes.success(JSONObject.toJSONString(signInfoVO));
    }


    private Date getTwoHoursDate(Date now) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);
        ca.add(Calendar.HOUR_OF_DAY, 2);
        return ca.getTime();
    }


}
