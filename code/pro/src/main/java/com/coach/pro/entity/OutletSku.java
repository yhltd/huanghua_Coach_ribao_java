package com.coach.pro.entity;

public class OutletSku {
    String UserSKUCode;//产品目录

    String BiaoZhunPrice;//标准价
    String GuidancePrice;//官方指导促销价
    String FinalPrice;//到手价


    String outlieFlagshipStore_gfzdj;//COACH官方outlet旗舰店官方指导价
    String outlieFlagshipStore_gfzdcxj;//COACH官方outlet旗舰店官方指导促销价
    String outlieFlagshipStore_dsj;//COACH官方outlet旗舰店到手价
    String outlieUrl;

    String website_gfzdj;//COACH蔻驰奥莱官网官方指导价
    String website_gfzdcxj;//COACH蔻驰奥莱官网官方指导促销价
    String website_dsj;//COACH蔻驰奥莱官网到手价
    String websiteUrl;

    String TMallGlobalStore_gfzdj;//COACH海外旗舰店官方指导价
    String TMallGlobalStore_gfzdcxj;//COACH海外旗舰店官方指导促销价
    String TMallGlobalStore_dsj;//COACH海外旗舰店到手价
    String TMallUrl;

    String JDSelfOperated_gfzdj;//COACH京东自营店官方指导价
    String JDSelfOperated_gfzdcxj;//COACH京东自营店官方指导促销价
    String JDSelfOperated_dsj;//COACH京东自营店到手价
    String JDUrl;

    String OverseasStores_gfzdj;//COACH蔻驰品牌授权海外店官方指导价
    String OverseasStores_gfzdcxj;//COACH蔻驰品牌授权海外店官方指导促销价
    String OverseasStores_dsj;//COACH蔻驰品牌授权海外店到手价
    String OverseasUrl;

    String PricrDifference_gfzdj;//Pricr Difference官方指导价
    String PricrDifference_gfzdcxj;//Pricr Difference官方指导促销价
    String PricrDifference_dsj;//Pricr Difference到手价



    public OutletSku(){}
    public OutletSku(String UserSKUCode){
        this.UserSKUCode=UserSKUCode;
    }

    public OutletSku(String userSKUCode, String biaoZhunPrice, String guidancePrice, String finalPrice, String outlieFlagshipStore_gfzdj, String outlieFlagshipStore_gfzdcxj, String outlieFlagshipStore_dsj, String outlieUrl, String website_gfzdj, String website_gfzdcxj, String website_dsj, String websiteUrl, String TMallGlobalStore_gfzdj, String TMallGlobalStore_gfzdcxj, String TMallGlobalStore_dsj, String TMallUrl, String JDSelfOperated_gfzdj, String JDSelfOperated_gfzdcxj, String JDSelfOperated_dsj, String JDUrl, String overseasStores_gfzdj, String overseasStores_gfzdcxj, String overseasStores_dsj, String overseasUrl, String pricrDifference_gfzdj, String pricrDifference_gfzdcxj, String pricrDifference_dsj) {
        UserSKUCode = userSKUCode;
        BiaoZhunPrice = biaoZhunPrice;
        GuidancePrice = guidancePrice;
        FinalPrice = finalPrice;
        this.outlieFlagshipStore_gfzdj = outlieFlagshipStore_gfzdj;
        this.outlieFlagshipStore_gfzdcxj = outlieFlagshipStore_gfzdcxj;
        this.outlieFlagshipStore_dsj = outlieFlagshipStore_dsj;
        this.outlieUrl = outlieUrl;
        this.website_gfzdj = website_gfzdj;
        this.website_gfzdcxj = website_gfzdcxj;
        this.website_dsj = website_dsj;
        this.websiteUrl = websiteUrl;
        this.TMallGlobalStore_gfzdj = TMallGlobalStore_gfzdj;
        this.TMallGlobalStore_gfzdcxj = TMallGlobalStore_gfzdcxj;
        this.TMallGlobalStore_dsj = TMallGlobalStore_dsj;
        this.TMallUrl = TMallUrl;
        this.JDSelfOperated_gfzdj = JDSelfOperated_gfzdj;
        this.JDSelfOperated_gfzdcxj = JDSelfOperated_gfzdcxj;
        this.JDSelfOperated_dsj = JDSelfOperated_dsj;
        this.JDUrl = JDUrl;
        OverseasStores_gfzdj = overseasStores_gfzdj;
        OverseasStores_gfzdcxj = overseasStores_gfzdcxj;
        OverseasStores_dsj = overseasStores_dsj;
        OverseasUrl = overseasUrl;
        PricrDifference_gfzdj = pricrDifference_gfzdj;
        PricrDifference_gfzdcxj = pricrDifference_gfzdcxj;
        PricrDifference_dsj = pricrDifference_dsj;
    }

    public String getBiaoZhunPrice() {
        return BiaoZhunPrice;
    }

    public void setBiaoZhunPrice(String biaoZhunPrice) {
        BiaoZhunPrice = biaoZhunPrice;
    }

    public String getGuidancePrice() {
        return GuidancePrice;
    }

    public void setGuidancePrice(String guidancePrice) {
        GuidancePrice = guidancePrice;
    }

    public String getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        FinalPrice = finalPrice;
    }

    public String getOutlieUrl() {
        return outlieUrl;
    }

    public void setOutlieUrl(String outlieUrl) {
        this.outlieUrl = outlieUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTMallUrl() {
        return TMallUrl;
    }

    public void setTMallUrl(String TMallUrl) {
        this.TMallUrl = TMallUrl;
    }

    public String getJDUrl() {
        return JDUrl;
    }

    public void setJDUrl(String JDUrl) {
        this.JDUrl = JDUrl;
    }

    public String getOverseasUrl() {
        return OverseasUrl;
    }

    public void setOverseasUrl(String overseasUrl) {
        OverseasUrl = overseasUrl;
    }

    public String getOutlieFlagshipStore_gfzdj() {
        return outlieFlagshipStore_gfzdj;
    }

    public void setOutlieFlagshipStore_gfzdj(String outlieFlagshipStore_gfzdj) {
        this.outlieFlagshipStore_gfzdj = outlieFlagshipStore_gfzdj;
    }

    public void setUserSKUCode(String userSKUCode) {
        UserSKUCode = userSKUCode;
    }

    public String getUserSKUCode() {
        return UserSKUCode;
    }


    public String getOutlieFlagshipStore_gfzdcxj() {
        return outlieFlagshipStore_gfzdcxj;
    }

    public void setOutlieFlagshipStore_gfzdcxj(String outlieFlagshipStore_gfzdcxj) {
        this.outlieFlagshipStore_gfzdcxj = outlieFlagshipStore_gfzdcxj;
    }

    public String getOutlieFlagshipStore_dsj() {
        return outlieFlagshipStore_dsj;
    }

    public void setOutlieFlagshipStore_dsj(String outlieFlagshipStore_dsj) {
        this.outlieFlagshipStore_dsj = outlieFlagshipStore_dsj;
    }

    public String getWebsite_gfzdj() {
        return website_gfzdj;
    }

    public void setWebsite_gfzdj(String website_gfzdj) {
        this.website_gfzdj = website_gfzdj;
    }

    public String getWebsite_gfzdcxj() {
        return website_gfzdcxj;
    }

    public void setWebsite_gfzdcxj(String website_gfzdcxj) {
        this.website_gfzdcxj = website_gfzdcxj;
    }

    public String getWebsite_dsj() {
        return website_dsj;
    }

    public void setWebsite_dsj(String website_dsj) {
        this.website_dsj = website_dsj;
    }

    public String getTMallGlobalStore_gfzdj() {
        return TMallGlobalStore_gfzdj;
    }

    public void setTMallGlobalStore_gfzdj(String TMallGlobalStore_gfzdj) {
        this.TMallGlobalStore_gfzdj = TMallGlobalStore_gfzdj;
    }

    public String getTMallGlobalStore_gfzdcxj() {
        return TMallGlobalStore_gfzdcxj;
    }

    public void setTMallGlobalStore_gfzdcxj(String TMallGlobalStore_gfzdcxj) {
        this.TMallGlobalStore_gfzdcxj = TMallGlobalStore_gfzdcxj;
    }

    public String getTMallGlobalStore_dsj() {
        return TMallGlobalStore_dsj;
    }

    public void setTMallGlobalStore_dsj(String TMallGlobalStore_dsj) {
        this.TMallGlobalStore_dsj = TMallGlobalStore_dsj;
    }

    public String getJDSelfOperated_gfzdj() {
        return JDSelfOperated_gfzdj;
    }

    public void setJDSelfOperated_gfzdj(String JDSelfOperated_gfzdj) {
        this.JDSelfOperated_gfzdj = JDSelfOperated_gfzdj;
    }

    public String getJDSelfOperated_gfzdcxj() {
        return JDSelfOperated_gfzdcxj;
    }

    public void setJDSelfOperated_gfzdcxj(String JDSelfOperated_gfzdcxj) {
        this.JDSelfOperated_gfzdcxj = JDSelfOperated_gfzdcxj;
    }

    public String getJDSelfOperated_dsj() {
        return JDSelfOperated_dsj;
    }

    public void setJDSelfOperated_dsj(String JDSelfOperated_dsj) {
        this.JDSelfOperated_dsj = JDSelfOperated_dsj;
    }

    public String getOverseasStores_gfzdj() {
        return OverseasStores_gfzdj;
    }

    public void setOverseasStores_gfzdj(String overseasStores_gfzdj) {
        OverseasStores_gfzdj = overseasStores_gfzdj;
    }

    public String getOverseasStores_gfzdcxj() {
        return OverseasStores_gfzdcxj;
    }

    public void setOverseasStores_gfzdcxj(String overseasStores_gfzdcxj) {
        OverseasStores_gfzdcxj = overseasStores_gfzdcxj;
    }

    public String getOverseasStores_dsj() {
        return OverseasStores_dsj;
    }

    public void setOverseasStores_dsj(String overseasStores_dsj) {
        OverseasStores_dsj = overseasStores_dsj;
    }

    public String getPricrDifference_dsj() {
        return PricrDifference_dsj;
    }

    public String getPricrDifference_gfzdcxj() {
        return PricrDifference_gfzdcxj;
    }

    public String getPricrDifference_gfzdj() {
        return PricrDifference_gfzdj;
    }

    public void setPricrDifference_dsj(String pricrDifference_dsj) {
        PricrDifference_dsj = pricrDifference_dsj;
    }

    public void setPricrDifference_gfzdcxj(String pricrDifference_gfzdcxj) {
        PricrDifference_gfzdcxj = pricrDifference_gfzdcxj;
    }

    public void setPricrDifference_gfzdj(String pricrDifference_gfzdj) {
        PricrDifference_gfzdj = pricrDifference_gfzdj;
    }

    @Override
    public String toString() {
        return "MarginPrice{" +
                "UserSKUCode='" + UserSKUCode + '\'' +
                ",outlieFlagshipStore_gfzdj='" + outlieFlagshipStore_gfzdj + '\'' +
                ", website_gfzdj='" + website_gfzdj + '\'' +
                ", TMallGlobalStore_gfzdj='" + TMallGlobalStore_gfzdj + '\'' +
                ", JDSelfOperated_gfzdj='" + JDSelfOperated_gfzdj + '\'' +
                ", OverseasStores_gfzdj='" + OverseasStores_gfzdj + '\'' +
                ", PricrDifference_gfzdj='" + PricrDifference_gfzdj + '\'' +
                ", outlieFlagshipStore_gfzdcxj='" + outlieFlagshipStore_gfzdcxj + '\'' +
                ", website_gfzdcxj='" + website_gfzdcxj + '\'' +
                ", TMallGlobalStore_gfzdcxj='" + TMallGlobalStore_gfzdcxj + '\'' +
                ", JDSelfOperated_gfzdcxj='" + JDSelfOperated_gfzdcxj + '\'' +
                ", OverseasStores_gfzdcxj='" + OverseasStores_gfzdcxj + '\'' +
                ", PricrDifference_gfzdcxj='" + PricrDifference_gfzdcxj + '\'' +
                ", outlieFlagshipStore_dsj='" + outlieFlagshipStore_dsj + '\'' +
                ", website_dsj='" + website_dsj + '\'' +
                ", TMallGlobalStore_dsj='" + TMallGlobalStore_dsj + '\'' +
                ", JDSelfOperated_dsj='" + JDSelfOperated_dsj + '\'' +
                ", OverseasStores_dsj='" + OverseasStores_dsj + '\'' +
                ", PricrDifference_dsj='" + PricrDifference_dsj + '\'' +
                '}';
    }


}
