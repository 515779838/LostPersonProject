package com.android.lostpersonproject.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String policeStationName;
    private String localPoliceStationName;
    private String policeName;
    private String policeId;
    private String token;
    private String responsibilityAreaName;

    public UserBean() {
    }

    public UserBean(String policeStationName, String localPoliceStationName, String policeName, String policeId, String token, String responsibilityAreaName) {
        this.policeStationName = policeStationName;
        this.localPoliceStationName = localPoliceStationName;
        this.policeName = policeName;
        this.policeId = policeId;
        this.token = token;
        this.responsibilityAreaName = responsibilityAreaName;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }

    public String getLocalPoliceStationName() {
        return localPoliceStationName;
    }

    public void setLocalPoliceStationName(String localPoliceStationName) {
        this.localPoliceStationName = localPoliceStationName;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponsibilityAreaName() {
        return responsibilityAreaName;
    }

    public void setResponsibilityAreaName(String responsibilityAreaName) {
        this.responsibilityAreaName = responsibilityAreaName;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "policeStationName='" + policeStationName + '\'' +
                ", localPoliceStationName='" + localPoliceStationName + '\'' +
                ", policeName='" + policeName + '\'' +
                ", policeId='" + policeId + '\'' +
                ", token='" + token + '\'' +
                ", responsibilityAreaName='" + responsibilityAreaName + '\'' +
                '}';
    }
}
