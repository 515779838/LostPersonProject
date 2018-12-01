package com.android.lostpersonproject.bean;

import java.util.ArrayList;
import java.util.List;

public class PersonDetailBean {

    private String id;
    private String isNewRecord;
    private String createDate;
    private String createBy;
    private String remarks;
    private String updateDate;
    private String updateBy;
    private String status;
    private String name;
    private String cardId;
    private String address;
    private String mePhone;
    private String guardianOne;
    private String relationOne;
    private String guardianOneCardId;
    private String guardianOneAddress;
    private String guardianOneTel;
    private String guardianTwo;
    private String relationTwo;
    private String guardianTwoCardId;
    private String guardianTwoAddress;
    private String guardianTwoTel;
    private String policeStationId;
    private String localPoliceStationId;
    private String responsibilityAreaId;
    private String portrait_nav;
    private String isState;
    private ArrayList<PhotosBean> photos;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(String isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public void setIsState(String isState) {
        this.isState = isState;
    }

    public void setPhotos(ArrayList<PhotosBean> photos) {
        this.photos = photos;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMePhone() {
        return mePhone;
    }

    public void setMePhone(String mePhone) {
        this.mePhone = mePhone;
    }

    public String getGuardianOne() {
        return guardianOne;
    }

    public void setGuardianOne(String guardianOne) {
        this.guardianOne = guardianOne;
    }

    public String getRelationOne() {
        return relationOne;
    }

    public void setRelationOne(String relationOne) {
        this.relationOne = relationOne;
    }

    public String getGuardianOneCardId() {
        return guardianOneCardId;
    }

    public void setGuardianOneCardId(String guardianOneCardId) {
        this.guardianOneCardId = guardianOneCardId;
    }

    public String getGuardianOneAddress() {
        return guardianOneAddress;
    }

    public void setGuardianOneAddress(String guardianOneAddress) {
        this.guardianOneAddress = guardianOneAddress;
    }

    public String getGuardianOneTel() {
        return guardianOneTel;
    }

    public void setGuardianOneTel(String guardianOneTel) {
        this.guardianOneTel = guardianOneTel;
    }

    public String getGuardianTwo() {
        return guardianTwo;
    }

    public void setGuardianTwo(String guardianTwo) {
        this.guardianTwo = guardianTwo;
    }

    public String getRelationTwo() {
        return relationTwo;
    }

    public void setRelationTwo(String relationTwo) {
        this.relationTwo = relationTwo;
    }

    public String getGuardianTwoCardId() {
        return guardianTwoCardId;
    }

    public void setGuardianTwoCardId(String guardianTwoCardId) {
        this.guardianTwoCardId = guardianTwoCardId;
    }

    public String getGuardianTwoAddress() {
        return guardianTwoAddress;
    }

    public void setGuardianTwoAddress(String guardianTwoAddress) {
        this.guardianTwoAddress = guardianTwoAddress;
    }

    public String getGuardianTwoTel() {
        return guardianTwoTel;
    }

    public void setGuardianTwoTel(String guardianTwoTel) {
        this.guardianTwoTel = guardianTwoTel;
    }

    public String getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(String policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getLocalPoliceStationId() {
        return localPoliceStationId;
    }

    public void setLocalPoliceStationId(String localPoliceStationId) {
        this.localPoliceStationId = localPoliceStationId;
    }

    public String getResponsibilityAreaId() {
        return responsibilityAreaId;
    }

    public void setResponsibilityAreaId(String responsibilityAreaId) {
        this.responsibilityAreaId = responsibilityAreaId;
    }

    public String getPortrait_nav() {
        return portrait_nav;
    }

    public void setPortrait_nav(String portrait_nav) {
        this.portrait_nav = portrait_nav;
    }

    public String getIsState() {
        return isState;
    }

    public ArrayList<PhotosBean> getPhotos() {
        return photos;
    }

    public static class PhotosBean {
        /**
         * portrait : http://47.92.250.134:8980/js/userfiles/fileupload/201811/1068451611007320064.jpeg
         */

        private String portrait;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        @Override
        public String toString() {
            return "PhotosBean{" +
                    "portrait='" + portrait + '\'' +
                    '}';
        }
    }
}
