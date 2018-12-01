package com.android.lostpersonproject.bean;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoggingBean implements Serializable {

    private String missingNum;
    private String findNum;

    private ArrayList<ResultBean> result;


    public class ResultBean implements Serializable{
        private String id;
        private String name;
        private String address;
        private String portrait;

        public ResultBean() {
        }

        public ResultBean(String id, String name, String address, String portrait) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.portrait = portrait;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", portrait='" + portrait + '\'' +
                    '}';
        }
    }

    public LoggingBean() {
    }

    public LoggingBean(String missingNum, String findNum, ArrayList<ResultBean> result) {
        this.missingNum = missingNum;
        this.findNum = findNum;
        this.result = result;
    }

    public String getMissingNum() {
        return missingNum;
    }

    public void setMissingNum(String missingNum) {
        this.missingNum = missingNum;
    }

    public String getFindNum() {
        return findNum;
    }

    public void setFindNum(String findNum) {
        this.findNum = findNum;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoggingBean{" +
                "missingNum='" + missingNum + '\'' +
                ", findNum='" + findNum + '\'' +
                ", result=" + result +
                '}';
    }
}
