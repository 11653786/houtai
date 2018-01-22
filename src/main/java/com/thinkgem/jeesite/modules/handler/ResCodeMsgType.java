package com.thinkgem.jeesite.modules.handler;

public enum ResCodeMsgType {
    SUCCESS_("0", "成功"),
    PARAMS_NOT_EMPTY("401", "参数不能为空"),
    USER_NOT_IN("402","用户不存在"),
    PHONE_WAS_REGISTER("403","当前用户已被注册"),
    PHONE_FORMATTER_ERROR("405","手机号码不正确"),
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
