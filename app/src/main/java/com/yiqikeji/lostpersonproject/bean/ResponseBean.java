package com.yiqikeji.lostpersonproject.bean;

import java.util.ArrayList;

public class ResponseBean {
    public String jsonrpc = "2.0";
    public String id = "";
    public Result result = null;

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
        return "ResultBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result=" + result +
                '}';
    }

    public class Result {
        public String code = "";
        public String msg = "";
        public String sign = "";
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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
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
                    ", sign='" + sign + '\'' +
                    ", data=" + data +
                    '}';
        }

        public class Data {

            public String sourceId = "";
            public ArrayList<FieldValue> fieldValues;

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public ArrayList<FieldValue> getFieldValues() {
                return fieldValues;
            }

            public void setFieldValues(ArrayList<FieldValue> fieldValues) {
                this.fieldValues = fieldValues;
            }

            public class FieldValue {
                public String field = "";
                public String value = "";
                public String isCode = "";
                public String codeValue = "";

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getIsCode() {
                    return isCode;
                }

                public void setIsCode(String isCode) {
                    this.isCode = isCode;
                }

                public String getCodeValue() {
                    return codeValue;
                }

                public void setCodeValue(String codeValue) {
                    this.codeValue = codeValue;
                }

                @Override
                public String toString() {
                    return "FieldValue{" +
                            "field='" + field + '\'' +
                            ", value='" + value + '\'' +
                            ", isCode='" + isCode + '\'' +
                            ", codeValue='" + codeValue + '\'' +
                            '}';
                }
            }

        }

    }

}
