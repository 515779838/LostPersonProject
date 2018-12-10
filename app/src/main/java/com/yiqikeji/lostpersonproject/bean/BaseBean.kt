package com.yiqikeji.lostpersonproject.bean


class BaseBean {

    var code: String? = null
    var msg: String? = null
    var data: String? = null

    override fun toString(): String {
        return "BaseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}'
    }
}