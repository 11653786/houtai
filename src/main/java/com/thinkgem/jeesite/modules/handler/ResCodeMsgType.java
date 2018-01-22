package com.thinkgem.jeesite.modules.handler;

public enum ResCodeMsgType {
    SUCCESS_("0", "成功"),
    PARAMS_NOT_EMPTY("401", "参数不能为空"),
    PHONE_WAS_REGISTER("402","手机号已被注册"),
    FAIL("501", "失败");

    private String code;
    private String desc;

    private ResCodeMsgType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

}
