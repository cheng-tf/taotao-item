package com.taotao.springboot.item.domain.enums;

/**
 * <p>Title: ErrorCodeEnum</p>
 * <p>Description: </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 16:37</p>
 * @author ChengTengfei
 * @version 1.0
 */
public enum ErrorCodeEnum {

    SUCCESS("0000","成功"),
    FAIL("0001","未知错误");

    // 编码
    public String code;
    // 编码描述
    public String meaning;

    ErrorCodeEnum(String code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public String getCode() {
        return code;
    }

    public String getMeaning() {
        return meaning;
    }
}