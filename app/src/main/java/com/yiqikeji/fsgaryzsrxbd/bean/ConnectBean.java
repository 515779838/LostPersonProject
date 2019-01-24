package com.yiqikeji.fsgaryzsrxbd.bean;

public class ConnectBean {
    public String jsonrpc = "2.0";
    public String id = "";
    public String method = "connect";
    public Params params = null;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ConnectBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", params=" + params +
                '}';
    }

    public static class Params {
        public String sign = "";
        public Data data = null;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "sign='" + sign + '\'' +
                    ", data=" + data +
                    '}';
        }

        public static class Data {
            public String version = "";
            public String appId = "";//申请后提供
            public String timestamp = "";
            public String nonce = "";//32位随机字符串

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNonce() {
                return nonce;
            }

            public void setNonce(String nonce) {
                this.nonce = nonce;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "version='" + version + '\'' +
                        ", appId='" + appId + '\'' +
                        ", timestamp='" + timestamp + '\'' +
                        ", nonce='" + nonce + '\'' +
                        '}';
            }
        }
    }
}
