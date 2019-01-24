package com.yiqikeji.fsgaryzsrxbd.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchBean implements Serializable {

    private String isScore;
    private String flag;
    private ArrayList<ResultBean> result;

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{

        private String customId;
        private String name;
        private String score;
        private String portrait;

        public String getCustomId() {
            return customId;
        }

        public void setCustomId(String customId) {
            this.customId = customId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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
                    "customId='" + customId + '\'' +
                    ", name='" + name + '\'' +
                    ", score='" + score + '\'' +
                    ", portrait='" + portrait + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "isScore='" + isScore + '\'' +
                ", flag='" + flag + '\'' +
                ", result=" + result +
                '}';
    }
}
