package com.yiqikeji.fsgaryzsrxbd.bean;

/**
 * 查询提交bean
 */
public class QueryRequestBean {
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
            public String dataObjId = "2.0";
            public String condition = "2.0";
            public String fields = "2.0";
            public String orderBy = "2.0";
            public UserInfo userInfo = null;
            public Page page = null;

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

            public static class Page{
                private String pageSize;
                private String pageNo;

                public String getPageSize() {
                    return pageSize;
                }

                public void setPageSize(String pageSize) {
                    this.pageSize = pageSize;
                }

                public String getPageNo() {
                    return pageNo;
                }

                public void setPageNo(String pageNo) {
                    this.pageNo = pageNo;
                }

                @Override
                public String toString() {
                    return "Page{" +
                            "pageSize='" + pageSize + '\'' +
                            ", pageNo='" + pageNo + '\'' +
                            '}';
                }
            }

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

            public String getFields() {
                return fields;
            }

            public void setFields(String fields) {
                this.fields = fields;
            }

            public String getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(String orderBy) {
                this.orderBy = orderBy;
            }

            public UserInfo getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
            }

            public Page getPage() {
                return page;
            }

            public void setPage(Page page) {
                this.page = page;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "version='" + version + '\'' +
                        ", sessionId='" + sessionId + '\'' +
                        ", dataObjId='" + dataObjId + '\'' +
                        ", condition='" + condition + '\'' +
                        ", fields='" + fields + '\'' +
                        ", orderBy='" + orderBy + '\'' +
                        ", userInfo=" + userInfo +
                        ", page=" + page +
                        '}';
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
