package com.taotao.springboot.item.domain.result;

import com.taotao.springboot.item.domain.enums.ErrorCodeEnum;

import java.io.Serializable;

/**
 * <p>Title: Result</p>
 * <p>Description: 基本结果信息类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 16:38</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -8383976430703581921L;

    // 系统问题
    private boolean isSuccess;
    // 错误码
    private String code;
    // 错误描述
    private String msg;

    public Result(){}

    public Result(ErrorCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMeaning();
        this.setSuccess(true);
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCodeAndMsgByEnum(ErrorCodeEnum e){
        this.code = e.getCode();
        this.msg = e.getMeaning();
        this.setSuccess(true);
    }

    public void setCodeAndMsgByEnumInCatch(ErrorCodeEnum e){
        this.code = e.getCode();
        this.msg = e.getMeaning();
        this.setSuccess(false);
    }
}

