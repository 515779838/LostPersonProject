package com.yiqikeji.fsgaryzsrxbd.bean;

import java.util.ArrayList;

public class OperateResponseBean {
    public String jsonrpc = "2.0";
    public String id = "";
    public Result result = null;
    public Error error = null;

    public class Result {
        public String code = "";
        public String msg = "";
        public ArrayList<Data> data;

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

        public ArrayList<Data> getData() {
            return data;
        }

        public void setData(ArrayList<Data> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }

        public class Data {
            public String operationId = "";
            public String operationCode = "";
            public String operationMsg = "";
            public String operationNum = "";

            public String getOperationId() {
                return operationId;
            }

            public void setOperationId(String operationId) {
                this.operationId = operationId;
            }

            public String getOperationCode() {
                return operationCode;
            }

            public void setOperationCode(String operationCode) {
                this.operationCode = operationCode;
            }

            public String getOperationMsg() {
                return operationMsg;
            }

            public void setOperationMsg(String operationMsg) {
                this.operationMsg = operationMsg;
            }

            public String getOperationNum() {
                return operationNum;
            }

            public void setOperationNum(String operationNum) {
                this.operationNum = operationNum;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "operationId='" + operationId + '\'' +
                        ", operationCode='" + operationCode + '\'' +
                        ", operationMsg='" + operationMsg + '\'' +
                        ", operationNum='" + operationNum + '\'' +
                        '}';
            }
        }
    }

    public class Error {

        public String code = "";
        public String message = "";

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OperateResponseBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result=" + result +
                ", error=" + error +
                '}';
    }
}
