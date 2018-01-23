package com.thinkgem.jeesite.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

    private static final Log logger = LogFactory.getLog(CorsFilter.class);
    @Override

    public void init(FilterConfig filterConfig) throws ServletException {

// TODO Auto-generated method stub
        logger.info("跨域浏览器初始化~~~~~~~~~~~");


    }


    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

// TODO Auto-generated method stub

        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;
            res.setContentType("text/html;charset=UTF-8");

            res.setHeader("Access-Control-Allow-Origin", req.getHeader("origin"));

            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            res.setHeader("Access-Control-Max-Age", "0");

            res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");

            res.setHeader("Access-Control-Allow-Credentials", "true");

            res.setHeader("XDomainRequestAllowed", "1");

            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.info("跨域拦截器异常: "+e.getMessage());
        }


    }


    @Override

    public void destroy() {

// TODO Auto-generated method stub


    }


}
