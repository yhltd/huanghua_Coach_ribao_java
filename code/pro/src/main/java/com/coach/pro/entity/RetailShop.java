package com.coach.pro.entity;

/**
 * @author wanghui
 * @date 2021/09/03 11:07
 */
public class RetailShop {
    String UserSKUCode;//产品目录
    String GuanFang_price;//官方指导价
    String GuanFangCuXiao_price;//官方指导促销价
    String GF_Jiacha;//官方指导价价差
    String GFCX_Jiacha;//官方指导促销价价差
    String Final_Jiacha;//到手价价差


    String TmPrice;//天猫到手价
    String TmPriceDifference;//天猫是否有差异
    String TmDiscount;//天猫应用机制
    String TMBiaoZhunPrice;//天猫标准价(官方指导促销价)
    String TMUrl;//链接

    String CoachPrice;//官网到手价
    String CoachPriceDifference;//官网是否有差异
    String CoachPriceDiscount;//官网应用机制
    String CoachBiaoZhunPrice;//官网标准价(官方指导促销价)
    String CoachUrl;//链接

    String LingShouPrice;//零售精品店到手价
    String LingShouPriceDifference;//零售精品店是否有差异
    String LingShouPriceDiscount;//零售精品店应用机制
    String LingShouBiaoZhunPrice;//官网标准价(官方指导促销价)
    String LingShouUrl;//链接

    String JDPrice;//京东到手价
    String JDPriceDifference;//京东店是否有差异
    String JDPriceDiscount;//京东店应用机制
    String JDBiaoZhunPrice;//京东标准价(官方指导促销价)
    String JDUrl;//链接

    String OverseasPrice;//海外到手价
    String OverseasPriceDifference;//海外是否有差异
    String OverseasPriceDiscount;//海外应用机制
    String OverseasBiaoZhunPrice;//海外标准价(官方指导促销价)
    String OverseasUrl;//链接

    public RetailShop(){}
    public RetailShop(String userSKUCode){
        UserSKUCode = userSKUCode;
    }
    public RetailShop(String userSKUCode, String guanFang_price, String guanFangCuXiao_price, String tmPrice, String tmPriceDifference, String tmDiscount, String TMBiaoZhunPrice, String TMUrl, String coachPrice, String coachPriceDifference, String coachPriceDiscount, String coachBiaoZhunPrice, String coachUrl, String lingShouPrice, String lingShouPriceDifference, String lingShouPriceDiscount, String lingShouBiaoZhunPrice, String lingShouUrl, String JDPrice, String JDPriceDifference, String JDPriceDiscount, String JDBiaoZhunPrice, String JDUrl, String overseasPrice, String overseasPriceDifference, String overseasPriceDiscount, String overseasBiaoZhunPrice, String overseasUrl) {
        UserSKUCode = userSKUCode;
        GuanFang_price = guanFang_price;
        GuanFangCuXiao_price = guanFangCuXiao_price;
        TmPrice = tmPrice;
        TmPriceDifference = tmPriceDifference;
        TmDiscount = tmDiscount;
        this.TMBiaoZhunPrice = TMBiaoZhunPrice;
        this.TMUrl = TMUrl;
        CoachPrice = coachPrice;
        CoachPriceDifference = coachPriceDifference;
        CoachPriceDiscount = coachPriceDiscount;
        CoachBiaoZhunPrice = coachBiaoZhunPrice;
        CoachUrl = coachUrl;
        LingShouPrice = lingShouPrice;
        LingShouPriceDifference = lingShouPriceDifference;
        LingShouPriceDiscount = lingShouPriceDiscount;
        LingShouBiaoZhunPrice = lingShouBiaoZhunPrice;
        LingShouUrl = lingShouUrl;
        this.JDPrice = JDPrice;
        this.JDPriceDifference = JDPriceDifference;
        this.JDPriceDiscount = JDPriceDiscount;
        this.JDBiaoZhunPrice = JDBiaoZhunPrice;
        this.JDUrl = JDUrl;
        OverseasPrice = overseasPrice;
        OverseasPriceDifference = overseasPriceDifference;
        OverseasPriceDiscount = overseasPriceDiscount;
        OverseasBiaoZhunPrice = overseasBiaoZhunPrice;
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

    public String getTmPrice() {
        return TmPrice;
    }

    public void setTmPrice(String tmPrice) {
        TmPrice = tmPrice;
    }

    public String getTmPriceDifference() {
        return TmPriceDifference;
    }

    public void setTmPriceDifference(String tmPriceDifference) {
        TmPriceDifference = tmPriceDifference;
    }

    public String getTmDiscount() {
        return TmDiscount;
    }

    public void setTmDiscount(String tmDiscount) {
        TmDiscount = tmDiscount;
    }

    public String getTMBiaoZhunPrice() {
        return TMBiaoZhunPrice;
    }

    public void setTMBiaoZhunPrice(String TMBiaoZhunPrice) {
        this.TMBiaoZhunPrice = TMBiaoZhunPrice;
    }

    public String getTMUrl() {
        return TMUrl;
    }

    public void setTMUrl(String TMUrl) {
        this.TMUrl = TMUrl;
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

    public String getCoachUrl() {
        return CoachUrl;
    }

    public void setCoachUrl(String coachUrl) {
        CoachUrl = coachUrl;
    }

    public String getLingShouPrice() {
        return LingShouPrice;
    }

    public void setLingShouPrice(String lingShouPrice) {
        LingShouPrice = lingShouPrice;
    }

    public String getLingShouPriceDifference() {
        return LingShouPriceDifference;
    }

    public void setLingShouPriceDifference(String lingShouPriceDifference) {
        LingShouPriceDifference = lingShouPriceDifference;
    }

    public String getLingShouPriceDiscount() {
        return LingShouPriceDiscount;
    }

    public void setLingShouPriceDiscount(String lingShouPriceDiscount) {
        LingShouPriceDiscount = lingShouPriceDiscount;
    }

    public String getLingShouBiaoZhunPrice() {
        return LingShouBiaoZhunPrice;
    }

    public void setLingShouBiaoZhunPrice(String lingShouBiaoZhunPrice) {
        LingShouBiaoZhunPrice = lingShouBiaoZhunPrice;
    }

    public String getLingShouUrl() {
        return LingShouUrl;
    }

    public void setLingShouUrl(String lingShouUrl) {
        LingShouUrl = lingShouUrl;
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

    public String getJDUrl() {
        return JDUrl;
    }

    public void setJDUrl(String JDUrl) {
        this.JDUrl = JDUrl;
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

    public String getOverseasUrl() {
        return OverseasUrl;
    }

    public void setOverseasUrl(String overseasUrl) {
        OverseasUrl = overseasUrl;
    }

    public String getGF_Jiacha() {
        return GF_Jiacha;
    }

    public void setGF_Jiacha(String GF_Jiacha) {
        this.GF_Jiacha = GF_Jiacha;
    }

    public String getGFCX_Jiacha() {
        return GFCX_Jiacha;
    }

    public void setGFCX_Jiacha(String GFCX_Jiacha) {
        this.GFCX_Jiacha = GFCX_Jiacha;
    }

    public String getFinal_Jiacha() {
        return Final_Jiacha;
    }

    public void setFinal_Jiacha(String final_Jiacha) {
        Final_Jiacha = final_Jiacha;
    }
}
