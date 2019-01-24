package com.yiqikeji.fsgaryzsrxbd.bean;

import java.util.ArrayList;

/**
 * 修改提交bean
 */
public class OperateRequestBean {
    public String jsonrpc = "2.0";
    public String id = "";
    public String method = "";
    public Params params = null;

    public static class Params {
        public String sign = "";
        public Data data = null;

        public static class Data {
            public String version = "20180523";
            public String sessionId = "2.0";
            public String transaction = "0";
            public ArrayList<Operation> operations = null;
            public UserInfo userInfo = null;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getSessionId() {
                return sessionId;
            }

            public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
            }

            public String getTransaction() {
                return transaction;
            }

            public void setTransaction(String transaction) {
                this.transaction = transaction;
            }

            public ArrayList<Operation> getOperations() {
                return operations;
            }

            public void setOperations(ArrayList<Operation> operations) {
                this.operations = operations;
            }

            public UserInfo getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "version='" + version + '\'' +
                        ", sessionId='" + sessionId + '\'' +
                        ", transaction='" + transaction + '\'' +
                        ", operations=" + operations +
                        ", userInfo=" + userInfo +
                        '}';
            }

            public static class UserInfo {
                public String userId = "";
                public String userName = "";
                public String userDeptNo = "";
                public String sn = "";
                public String sfzh = "";

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getUserDeptNo() {
                    return userDeptNo;
                }

                public void setUserDeptNo(String userDeptNo) {
                    this.userDeptNo = userDeptNo;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public String getSfzh() {
                    return sfzh;
                }

                public void setSfzh(String sfzh) {
                    this.sfzh = sfzh;
                }

                @Override
                public String toString() {
                    return "UserInfo{" +
                            "userId='" + userId + '\'' +
                            ", userName='" + userName + '\'' +
                            ", userDeptNo='" + userDeptNo + '\'' +
                            ", sn='" + sn + '\'' +
                            ", sfzh='" + sfzh + '\'' +
                            '}';
                }
            }

            public static class Operation {
                public String operationType = "2";
                public String operationId = "";
                public String sourceId = "";
                public String dataObjId = "";
                public String condition = "";
                public ArrayList<OperationData> data = null;

                public String getOperationType() {
                    return operationType;
                }

                public void setOperationType(String operationType) {
                    this.operationType = operationType;
                }

                public String getOperationId() {
                    return operationId;
                }

                public void setOperationId(String operationId) {
                    this.operationId = operationId;
                }

                public String getSourceId() {
                    return sourceId;
                }

                public void setSourceId(String sourceId) {
                    this.sourceId = sourceId;
                }

                public String getDataObjId() {
                    return dataObjId;
                }

                public void setDataObjId(String dataObjId) {
                    this.dataObjId = dataObjId;
                }

                public String getCondition() {
                    return condition;
                }

                public void setCondition(String condition) {
                    this.condition = condition;
                }

                public ArrayList<OperationData> getData() {
                    return data;
                }

                public void setData(ArrayList<OperationData> data) {
                    this.data = data;
                }

                @Override
                public String toString() {
                    return "Operation{" +
                            "operationType='" + operationType + '\'' +
                            ", operationId='" + operationId + '\'' +
                            ", sourceId='" + sourceId + '\'' +
                            ", dataObjId='" + dataObjId + '\'' +
                            ", condition='" + condition + '\'' +
                            ", data=" + data +
                            '}';
                }

                public static class OperationData {
                    public ArrayList<FieldValues> fieldValues = null;

                    public ArrayList<FieldValues> getFieldValues() {
                        return fieldValues;
                    }

                    public void setFieldValues(ArrayList<FieldValues> fieldValues) {
                        this.fieldValues = fieldValues;
                    }

                    public static class FieldValues {
                        public String field = "";
                        public String value = "";

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

                        @Override
                        public String toString() {
                            return "FieldValues{" +
                                    "field='" + field + '\'' +
                                    ", value='" + value + '\'' +
                                    '}';
                        }
                    }
                }


            }


        }

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
        return "JsonBean{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", method='" + method + '\'' +
                ", params=" + params +
                '}';
    }
}
