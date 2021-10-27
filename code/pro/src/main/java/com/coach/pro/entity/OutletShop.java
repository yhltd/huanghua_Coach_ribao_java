package com.coach.pro.entity;

/**
 * @author wanghui
 * @date 2021/09/05 10:37
 */
public class OutletShop {
    String UserSKUCode;//产品目录
    String GuanFang_price;//官方指导价
    String GuanFangCuXiao_price;//官方指导促销价

    String OutletPrice;//outlet旗舰店到手价
    String OutletPriceDifference;//outlet旗舰店是否有差异
    String OutletDiscount;//outlet旗舰店应用机制
    String OutletBiaoZhunPrice;//outlet旗舰店标准价(官方指导促销价)
    String OutletUrl;

    String CoachPrice;//COACH蔻驰奥莱官网到手价
    String CoachPriceDifference;//COACH蔻驰奥莱官网是否有差异
    String CoachPriceDiscount;//COACH蔻驰奥莱官网应用机制
    String CoachBiaoZhunPrice;//COACH蔻驰奥莱官网标准价(官方指导促销价)
    String CoachUrl;

    String CoachOSPrice;//COACH蔻驰品牌授权海外店到手价
    String CoachOSPriceDifference;//COACH蔻驰品牌授权海外店是否有差异
    String CoachOSPriceDiscount;//COACH蔻驰品牌授权海外店应用机制
    String CoachOSBiaoZhunPrice;//COACH蔻驰品牌授权海外店标准价(官方指导促销价)
    String CoachOSUrl;

    String JDPrice;//京东到手价
    String JDPriceDifference;//京东店是否有差异
    String JDPriceDiscount;//京东店应用机制
    String JDBiaoZhunPrice;//京东标准价(官方指导促销价)
    String JDUrl;

    String OverseasPrice;//海外到手价
    String OverseasPriceDifference;//海外是否有差异
    String OverseasPriceDiscount;//海外应用机制
    String OverseasBiaoZhunPrice;//海外标准价(官方指导促销价)
    String OverseasUrl;

    public OutletShop(){}
    public OutletShop(String userSKUCode) {
        UserSKUCode = userSKUCode;
    }
    public OutletShop(String userSKUCode, String guanFang_price, String guanFangCuXiao_price, String outletPrice, String outletPriceDifference, String outletDiscount, String outletBiaoZhunPrice, String coachPrice, String coachPriceDifference, String coachPriceDiscount, String coachBiaoZhunPrice, String coachOSPrice, String coachOSPriceDifference, String coachOSPriceDiscount, String coachOSBiaoZhunPrice, String JDPrice, String JDPriceDifference, String JDPriceDiscount, String JDBiaoZhunPrice, String overseasPrice, String overseasPriceDifference, String overseasPriceDiscount, String overseasBiaoZhunPrice) {
        UserSKUCode = userSKUCode;
        GuanFang_price = guanFang_price;
        GuanFangCuXiao_price = guanFangCuXiao_price;
        OutletPrice = outletPrice;
        OutletPriceDifference = outletPriceDifference;
        OutletDiscount = outletDiscount;
        OutletBiaoZhunPrice = outletBiaoZhunPrice;
        CoachPrice = coachPrice;
        CoachPriceDifference = coachPriceDifference;
        CoachPriceDiscount = coachPriceDiscount;
        CoachBiaoZhunPrice = coachBiaoZhunPrice;
        CoachOSPrice = coachOSPrice;
        CoachOSPriceDifference = coachOSPriceDifference;
        CoachOSPriceDiscount = coachOSPriceDiscount;
        CoachOSBiaoZhunPrice = coachOSBiaoZhunPrice;
        this.JDPrice = JDPrice;
        this.JDPriceDifference = JDPriceDifference;
        this.JDPriceDiscount = JDPriceDiscount;
        this.JDBiaoZhunPrice = JDBiaoZhunPrice;
        OverseasPrice = overseasPrice;
        OverseasPriceDifference = overseasPriceDifference;
        OverseasPriceDiscount = overseasPriceDiscount;
        OverseasBiaoZhunPrice = overseasBiaoZhunPrice;
    }

    public String getOutletUrl() {
        return OutletUrl;
    }

    public void setOutletUrl(String outletUrl) {
        OutletUrl = outletUrl;
    }

    public String getCoachUrl() {
        return CoachUrl;
    }

    public void setCoachUrl(String coachUrl) {
        CoachUrl = coachUrl;
    }

    public String getCoachOSUrl() {
        return CoachOSUrl;
    }

    public void setCoachOSUrl(String coachOSUrl) {
        CoachOSUrl = coachOSUrl;
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

    public String getUserSKUCode() {
        return UserSKUCode;
    }

    public void setUserSKUCode(String userSKUCode) {
        UserSKUCode = userSKUCode;
    }

    public String getGuanFang_price() {
        return GuanFang_price;
    }

    public void setGuanFang_price(String guanFang_price) {
        GuanFang_price = guanFang_price;
    }

    public String getGuanFangCuXiao_price() {
        return GuanFangCuXiao_price;
    }

    public void setGuanFangCuXiao_price(String guanFangCuXiao_price) {
        GuanFangCuXiao_price = guanFangCuXiao_price;
    }

    public String getOutletPrice() {
        return OutletPrice;
    }

    public void setOutletPrice(String outletPrice) {
        OutletPrice = outletPrice;
    }

    public String getOutletPriceDifference() {
        return OutletPriceDifference;
    }

    public void setOutletPriceDifference(String outletPriceDifference) {
        OutletPriceDifference = outletPriceDifference;
    }

    public String getOutletDiscount() {
        return OutletDiscount;
    }

    public void setOutletDiscount(String outletDiscount) {
        OutletDiscount = outletDiscount;
    }

    public String getOutletBiaoZhunPrice() {
        return OutletBiaoZhunPrice;
    }

    public void setOutletBiaoZhunPrice(String outletBiaoZhunPrice) {
        OutletBiaoZhunPrice = outletBiaoZhunPrice;
    }

    public String getCoachPrice() {
        return CoachPrice;
    }

    public void setCoachPrice(String coachPrice) {
        CoachPrice = coachPrice;
    }

    public String getCoachPriceDifference() {
        return CoachPriceDifference;
    }

    public void setCoachPriceDifference(String coachPriceDifference) {
        CoachPriceDifference = coachPriceDifference;
    }

    public String getCoachPriceDiscount() {
        return CoachPriceDiscount;
    }

    public void setCoachPriceDiscount(String coachPriceDiscount) {
        CoachPriceDiscount = coachPriceDiscount;
    }

    public String getCoachBiaoZhunPrice() {
        return CoachBiaoZhunPrice;
    }

    public void setCoachBiaoZhunPrice(String coachBiaoZhunPrice) {
        CoachBiaoZhunPrice = coachBiaoZhunPrice;
    }

    public String getCoachOSPrice() {
        return CoachOSPrice;
    }

    public void setCoachOSPrice(String coachOSPrice) {
        CoachOSPrice = coachOSPrice;
    }

    public String getCoachOSPriceDifference() {
        return CoachOSPriceDifference;
    }

    public void setCoachOSPriceDifference(String coachOSPriceDifference) {
        CoachOSPriceDifference = coachOSPriceDifference;
    }

    public String getCoachOSPriceDiscount() {
        return CoachOSPriceDiscount;
    }

    public void setCoachOSPriceDiscount(String coachOSPriceDiscount) {
        CoachOSPriceDiscount = coachOSPriceDiscount;
    }

    public String getCoachOSBiaoZhunPrice() {
        return CoachOSBiaoZhunPrice;
    }

    public void setCoachOSBiaoZhunPrice(String coachOSBiaoZhunPrice) {
        CoachOSBiaoZhunPrice = coachOSBiaoZhunPrice;
    }

    public String getJDPrice() {
        return JDPrice;
    }

    public void setJDPrice(String JDPrice) {
        this.JDPrice = JDPrice;
    }

    public String getJDPriceDifference() {
        return JDPriceDifference;
    }

    public void setJDPriceDifference(String JDPriceDifference) {
        this.JDPriceDifference = JDPriceDifference;
    }

    public String getJDPriceDiscount() {
        return JDPriceDiscount;
    }

    public void setJDPriceDiscount(String JDPriceDiscount) {
        this.JDPriceDiscount = JDPriceDiscount;
    }

    public String getJDBiaoZhunPrice() {
        return JDBiaoZhunPrice;
    }

    public void setJDBiaoZhunPrice(String JDBiaoZhunPrice) {
        this.JDBiaoZhunPrice = JDBiaoZhunPrice;
    }

    public String getOverseasPrice() {
        return OverseasPrice;
    }

    public void setOverseasPrice(String overseasPrice) {
        OverseasPrice = overseasPrice;
    }

    public String getOverseasPriceDifference() {
        return OverseasPriceDifference;
    }

    public void setOverseasPriceDifference(String overseasPriceDifference) {
        OverseasPriceDifference = overseasPriceDifference;
    }

    public String getOverseasPriceDiscount() {
        return OverseasPriceDiscount;
    }

    public void setOverseasPriceDiscount(String overseasPriceDiscount) {
        OverseasPriceDiscount = overseasPriceDiscount;
    }

    public String getOverseasBiaoZhunPrice() {
        return OverseasBiaoZhunPrice;
    }

    public void setOverseasBiaoZhunPrice(String overseasBiaoZhunPrice) {
        OverseasBiaoZhunPrice = overseasBiaoZhunPrice;
    }

    @Override
    public String toString() {
        return "OutletShop{" +
                "UserSKUCode='" + UserSKUCode + '\'' +
                ", GuanFang_price='" + GuanFang_price + '\'' +
                ", GuanFangCuXiao_price='" + GuanFangCuXiao_price + '\'' +
                ", OutletPrice='" + OutletPrice + '\'' +
                ", OutletPriceDifference='" + OutletPriceDifference + '\'' +
                ", OutletDiscount='" + OutletDiscount + '\'' +
                ", OutletBiaoZhunPrice='" + OutletBiaoZhunPrice + '\'' +
                ", CoachPrice='" + CoachPrice + '\'' +
                ", CoachPriceDifference='" + CoachPriceDifference + '\'' +
                ", CoachPriceDiscount='" + CoachPriceDiscount + '\'' +
                ", CoachBiaoZhunPrice='" + CoachBiaoZhunPrice + '\'' +
                ", CoachOSPrice='" + CoachOSPrice + '\'' +
                ", CoachOSPriceDifference='" + CoachOSPriceDifference + '\'' +
                ", CoachOSPriceDiscount='" + CoachOSPriceDiscount + '\'' +
                ", CoachOSBiaoZhunPrice='" + CoachOSBiaoZhunPrice + '\'' +
                ", JDPrice='" + JDPrice + '\'' +
                ", JDPriceDifference='" + JDPriceDifference + '\'' +
                ", JDPriceDiscount='" + JDPriceDiscount + '\'' +
                ", JDBiaoZhunPrice='" + JDBiaoZhunPrice + '\'' +
                ", OverseasPrice='" + OverseasPrice + '\'' +
                ", OverseasPriceDifference='" + OverseasPriceDifference + '\'' +
                ", OverseasPriceDiscount='" + OverseasPriceDiscount + '\'' +
                ", OverseasBiaoZhunPrice='" + OverseasBiaoZhunPrice + '\'' +
                '}';
    }
}
