package com.coach.pro.entity;

/**
 * @author wanghui
 * @date 2021/09/05 8:56
 */
public class RetailSku {
    String UserSKUCode;//产品目录
    String BiaoZhunPrice;//标准价
    String GuidancePrice;//官方指导促销价
    String FinalPrice;//
    String GF_Jiacha;//官方指导价价差
    String GFCX_Jiacha;//官方指导促销价价差
    String Final_Jiacha;//到手价价差

    String TMPrice;//天猫官方指导价
    String TMGuidancePrice;//天猫官方指导促销价
    String TmPriceDifference;//天猫是否有差异
    String TMFinalPrice;//天猫到手价
    String TMUrl;//天猫链接
    String TMUrl2;//天猫链接
    String TMUrl3;//天猫链接

    String CoachPrice;//Coach官方指导价
    String CoachGuidancePrice;//Coach官方指导促销价
    String CoachPriceDifference;//Coach是否有差异
    String CoachFinalPrice;//Coach到手价
    String CoachUrl;//Coach官方链接
    String CoachUrl2;//Coach官方链接
    String CoachUrl3;//Coach官方链接

    String LingShouPrice;//零售官方指导价
    String LingShouGuidancePrice;//零售官方指导促销价
    String LingShouPriceDifference;//零售是否有差异
    String LingShouFinalPrice;//零售到手价
    String LingShouUrl;//零售链接
    String LingShouUrl2;//零售链接
    String LingShouUrl3;//零售链接

    String JDPrice;//京东官方指导价
    String JDGuidancePrice;//京东官方指导促销价
    String JDPriceDifference;//京东是否有差异
    String JDFinalPrice;//京东到手价
    String JDUrl;//京东链接
    String JDUrl2;//京东链接
    String JDUrl3;//京东链接

    String OverseasPrice;//海外官方指导价
    String OverseasGuidancePrice;//海外官方指导促销价
    String OverseasPriceDifference;//海外是否有差异
    String OverseasFinalPrice;//海外到手价
    String OverseasUrl;//海外链接
    String OverseasUrl2;//海外链接
    String OverseasUrl3;//海外链接

    public RetailSku(){};
    public RetailSku(String userSKUCode){
        UserSKUCode = userSKUCode;
    };
    public RetailSku(String userSKUCode, String biaoZhunPrice, String guidancePrice, String finalPrice, String GF_Jiacha, String GFCX_Jiacha, String final_Jiacha, String TMPrice, String TMGuidancePrice, String tmPriceDifference, String TMFinalPrice, String TMUrl, String coachPrice, String coachGuidancePrice, String coachPriceDifference, String coachFinalPrice, String coachUrl, String lingShouPrice, String lingShouGuidancePrice, String lingShouPriceDifference, String lingShouFinalPrice, String lingShouUrl, String JDPrice, String JDGuidancePrice, String JDPriceDifference, String JDFinalPrice, String JDUrl, String overseasPrice, String overseasGuidancePrice, String overseasPriceDifference, String overseasFinalPrice, String overseasUrl) {
        UserSKUCode = userSKUCode;
        BiaoZhunPrice = biaoZhunPrice;
        GuidancePrice = guidancePrice;
        FinalPrice = finalPrice;
        this.GF_Jiacha = GF_Jiacha;
        this.GFCX_Jiacha = GFCX_Jiacha;
        Final_Jiacha = final_Jiacha;
        this.TMPrice = TMPrice;
        this.TMGuidancePrice = TMGuidancePrice;
        TmPriceDifference = tmPriceDifference;
        this.TMFinalPrice = TMFinalPrice;
        this.TMUrl = TMUrl;
        CoachPrice = coachPrice;
        CoachGuidancePrice = coachGuidancePrice;
        CoachPriceDifference = coachPriceDifference;
        CoachFinalPrice = coachFinalPrice;
        CoachUrl = coachUrl;
        LingShouPrice = lingShouPrice;
        LingShouGuidancePrice = lingShouGuidancePrice;
        LingShouPriceDifference = lingShouPriceDifference;
        LingShouFinalPrice = lingShouFinalPrice;
        LingShouUrl = lingShouUrl;
        this.JDPrice = JDPrice;
        this.JDGuidancePrice = JDGuidancePrice;
        this.JDPriceDifference = JDPriceDifference;
        this.JDFinalPrice = JDFinalPrice;
        this.JDUrl = JDUrl;
        OverseasPrice = overseasPrice;
        OverseasGuidancePrice = overseasGuidancePrice;
        OverseasPriceDifference = overseasPriceDifference;
        OverseasFinalPrice = overseasFinalPrice;
        OverseasUrl = overseasUrl;
    }

    public String getUserSKUCode() {
        return UserSKUCode;
    }

    public void setUserSKUCode(String userSKUCode) {
        UserSKUCode = userSKUCode;
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

    public String getTMPrice() {
        return TMPrice;
    }

    public void setTMPrice(String TMPrice) {
        this.TMPrice = TMPrice;
    }

    public String getTMGuidancePrice() {
        return TMGuidancePrice;
    }

    public void setTMGuidancePrice(String TMGuidancePrice) {
        this.TMGuidancePrice = TMGuidancePrice;
    }

    public String getTmPriceDifference() {
        return TmPriceDifference;
    }

    public void setTmPriceDifference(String tmPriceDifference) {
        TmPriceDifference = tmPriceDifference;
    }

    public String getTMFinalPrice() {
        return TMFinalPrice;
    }

    public void setTMFinalPrice(String TMFinalPrice) {
        this.TMFinalPrice = TMFinalPrice;
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

    public String getCoachGuidancePrice() {
        return CoachGuidancePrice;
    }

    public void setCoachGuidancePrice(String coachGuidancePrice) {
        CoachGuidancePrice = coachGuidancePrice;
    }

    public String getCoachPriceDifference() {
        return CoachPriceDifference;
    }

    public void setCoachPriceDifference(String coachPriceDifference) {
        CoachPriceDifference = coachPriceDifference;
    }

    public String getCoachFinalPrice() {
        return CoachFinalPrice;
    }

    public void setCoachFinalPrice(String coachFinalPrice) {
        CoachFinalPrice = coachFinalPrice;
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

    public String getLingShouGuidancePrice() {
        return LingShouGuidancePrice;
    }

    public void setLingShouGuidancePrice(String lingShouGuidancePrice) {
        LingShouGuidancePrice = lingShouGuidancePrice;
    }

    public String getLingShouPriceDifference() {
        return LingShouPriceDifference;
    }

    public void setLingShouPriceDifference(String lingShouPriceDifference) {
        LingShouPriceDifference = lingShouPriceDifference;
    }

    public String getLingShouFinalPrice() {
        return LingShouFinalPrice;
    }

    public void setLingShouFinalPrice(String lingShouFinalPrice) {
        LingShouFinalPrice = lingShouFinalPrice;
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

    public String getJDGuidancePrice() {
        return JDGuidancePrice;
    }

    public void setJDGuidancePrice(String JDGuidancePrice) {
        this.JDGuidancePrice = JDGuidancePrice;
    }

    public String getJDPriceDifference() {
        return JDPriceDifference;
    }

    public void setJDPriceDifference(String JDPriceDifference) {
        this.JDPriceDifference = JDPriceDifference;
    }

    public String getJDFinalPrice() {
        return JDFinalPrice;
    }

    public void setJDFinalPrice(String JDFinalPrice) {
        this.JDFinalPrice = JDFinalPrice;
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

    public String getOverseasGuidancePrice() {
        return OverseasGuidancePrice;
    }

    public void setOverseasGuidancePrice(String overseasGuidancePrice) {
        OverseasGuidancePrice = overseasGuidancePrice;
    }

    public String getOverseasPriceDifference() {
        return OverseasPriceDifference;
    }

    public void setOverseasPriceDifference(String overseasPriceDifference) {
        OverseasPriceDifference = overseasPriceDifference;
    }

    public String getOverseasFinalPrice() {
        return OverseasFinalPrice;
    }

    public void setOverseasFinalPrice(String overseasFinalPrice) {
        OverseasFinalPrice = overseasFinalPrice;
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
