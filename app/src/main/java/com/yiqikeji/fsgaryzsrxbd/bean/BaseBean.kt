package com.yiqikeji.fsgaryzsrxbd.bean


class BaseBean {

    var jsonrpc: String? = null
    var id: String? = null
    var result: String? = null
    var error: String? = null

    override fun toString(): String {
        return "BaseBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result='" + result + '\'' +
                ", error='" + error + '\'' +
                '}'
    }
}