package com.coach.pro.entity;

/**
 * @author wanghui
 * @date 2021/08/30 14:44
 */
public class CoachItem {
    int id;//ID
    String ItemCode;//ItemCode
    String SKUID;//SKUID
    String ProductName;//商品名称
    String AssetID;//AssetID
    String Brand;//品牌
    String SKUDesc;//商品简介
    String SKUShortName; //SKUShortName
    String SkuSeries;//系列
    String SKUModel;//型号
    String SKUSpec;//参数
    String UserSKUCode;//产品目录
    String URL;//链接
    String Channel;//电商平台
    String ShopName;//店铺名称
    String IsAuthor;//是否授权
    String Daigou;//代购
    String OriginalPrice;//吊牌价
    String ActivityPrice;//页面活动价
    String GuidancePrice;//官方指导促销价
    String FinalPrice;//到手价
    String MarginPrice;//价差
    String MarginPercent;//折扣率
    String MarkdownPercent;//降价率
    String InsertDate;//最近低价时间
    String ShopId;//店铺ID,
    String ViewTime;//观察时间
    String SellerLocation;//发货地
    String IsbreakPrice;//是否破价
    String IsTariff;//是否含税
    String Tariff;//进口税
    String TariffPrice;//含进口税到手价
    String AllPromotion;//所有促销信息
    String UsePromotion;//应用促销信息
    String SuperPromotion;//超级促销
    String ScrollSales;//近30天滚动销量
    String CommentNum;//评论数
    String StockNum;//库存
    String StockStatus;//在库状态
    String CreateTime;//数据生成时间


    public CoachItem(){};
    public CoachItem(int id, String itemCode, String SKUID, String productName, String assetID, String brand, String SKUDesc, String SKUShortName, String skuSeries, String SKUModel, String SKUSpec, String userSKUCode, String URL, String channel, String shopName, String isAuthor, String daigou, String originalPrice, String activityPrice, String guidancePrice, String finalPrice, String marginPrice, String marginPercent, String markdownPercent, String insertDate, String shopId, String viewTime, String sellerLocation, String isbreakPrice, String isTariff, String tariff, String tariffPrice, String allPromotion, String usePromotion, String superPromotion, String scrollSales, String commentNum, String stockNum, String stockStatus, String createTime) {
        this.id = id;
        ItemCode = itemCode;
        this.SKUID = SKUID;
        ProductName = productName;
        AssetID = assetID;
        Brand = brand;
        this.SKUDesc = SKUDesc;
        this.SKUShortName = SKUShortName;
        SkuSeries = skuSeries;
        this.SKUModel = SKUModel;
        this.SKUSpec = SKUSpec;
        UserSKUCode = userSKUCode;
        this.URL = URL;
        Channel = channel;
        ShopName = shopName;
        IsAuthor = isAuthor;
        Daigou = daigou;
        OriginalPrice = originalPrice;
        ActivityPrice = activityPrice;
        GuidancePrice = guidancePrice;
        FinalPrice = finalPrice;
        MarginPrice = marginPrice;
        MarginPercent = marginPercent;
        MarkdownPercent = markdownPercent;
        InsertDate = insertDate;
        ShopId = shopId;
        ViewTime = viewTime;
        SellerLocation = sellerLocation;
        IsbreakPrice = isbreakPrice;
        IsTariff = isTariff;
        Tariff = tariff;
        TariffPrice = tariffPrice;
        AllPromotion = allPromotion;
        UsePromotion = usePromotion;
        SuperPromotion = superPromotion;
        ScrollSales = scrollSales;
        CommentNum = commentNum;
        StockNum = stockNum;
        StockStatus = stockStatus;
        CreateTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getSKUID() {
        return SKUID;
    }

    public void setSKUID(String SKUID) {
        this.SKUID = SKUID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getAssetID() {
        return AssetID;
    }

    public void setAssetID(String assetID) {
        AssetID = assetID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getSKUDesc() {
        return SKUDesc;
    }

    public void setSKUDesc(String SKUDesc) {
        this.SKUDesc = SKUDesc;
    }

    public String getSKUShortName() {
        return SKUShortName;
    }

    public void setSKUShortName(String SKUShortName) {
        this.SKUShortName = SKUShortName;
    }

    public String getSkuSeries() {
        return SkuSeries;
    }

    public void setSkuSeries(String skuSeries) {
        SkuSeries = skuSeries;
    }

    public String getSKUModel() {
        return SKUModel;
    }

    public void setSKUModel(String SKUModel) {
        this.SKUModel = SKUModel;
    }

    public String getSKUSpec() {
        return SKUSpec;
    }

    public void setSKUSpec(String SKUSpec) {
        this.SKUSpec = SKUSpec;
    }

    public String getUserSKUCode() {
        return UserSKUCode;
    }

    public void setUserSKUCode(String userSKUCode) {
        UserSKUCode = userSKUCode;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getIsAuthor() {
        return IsAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        IsAuthor = isAuthor;
    }

    public String getDaigou() {
        return Daigou;
    }

    public void setDaigou(String daigou) {
        Daigou = daigou;
    }

    public String getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        OriginalPrice = originalPrice;
    }

    public String getActivityPrice() {
        return ActivityPrice;
    }

    public void setActivityPrice(String activityPrice) {
        ActivityPrice = activityPrice;
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

    public String getMarginPrice() {
        return MarginPrice;
    }

    public void setMarginPrice(String marginPrice) {
        MarginPrice = marginPrice;
    }

    public String getMarginPercent() {
        return MarginPercent;
    }

    public void setMarginPercent(String marginPercent) {
        MarginPercent = marginPercent;
    }

    public String getMarkdownPercent() {
        return MarkdownPercent;
    }

    public void setMarkdownPercent(String markdownPercent) {
        MarkdownPercent = markdownPercent;
    }

    public String getInsertDate() {
        return InsertDate;
    }

    public void setInsertDate(String insertDate) {
        InsertDate = insertDate;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getViewTime() {
        return ViewTime;
    }

    public void setViewTime(String viewTime) {
        ViewTime = viewTime;
    }

    public String getSellerLocation() {
        return SellerLocation;
    }

    public void setSellerLocation(String sellerLocation) {
        SellerLocation = sellerLocation;
    }

    public String getIsbreakPrice() {
        return IsbreakPrice;
    }

    public void setIsbreakPrice(String isbreakPrice) {
        IsbreakPrice = isbreakPrice;
    }

    public String getIsTariff() {
        return IsTariff;
    }

    public void setIsTariff(String isTariff) {
        IsTariff = isTariff;
    }

    public String getTariff() {
        return Tariff;
    }

    public void setTariff(String tariff) {
        Tariff = tariff;
    }

    public String getTariffPrice() {
        return TariffPrice;
    }

    public void setTariffPrice(String tariffPrice) {
        TariffPrice = tariffPrice;
    }

    public String getAllPromotion() {
        return AllPromotion;
    }

    public void setAllPromotion(String allPromotion) {
        AllPromotion = allPromotion;
    }

    public String getUsePromotion() {
        return UsePromotion;
    }

    public void setUsePromotion(String usePromotion) {
        UsePromotion = usePromotion;
    }

    public String getSuperPromotion() {
        return SuperPromotion;
    }

    public void setSuperPromotion(String superPromotion) {
        SuperPromotion = superPromotion;
    }

    public String getScrollSales() {
        return ScrollSales;
    }

    public void setScrollSales(String scrollSales) {
        ScrollSales = scrollSales;
    }

    public String getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(String commentNum) {
        CommentNum = commentNum;
    }

    public String getStockNum() {
        return StockNum;
    }

    public void setStockNum(String stockNum) {
        StockNum = stockNum;
    }

    public String getStockStatus() {
        return StockStatus;
    }

    public void setStockStatus(String stockStatus) {
        StockStatus = stockStatus;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @Override
    public String toString() {
        return "CoachItem{" +
                "id=" + id +
                ", ItemCode='" + ItemCode + '\'' +
                ", SKUID='" + SKUID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", AssetID='" + AssetID + '\'' +
                ", Brand='" + Brand + '\'' +
                ", SKUDesc='" + SKUDesc + '\'' +
                ", SKUShortName='" + SKUShortName + '\'' +
                ", SkuSeries='" + SkuSeries + '\'' +
                ", SKUModel='" + SKUModel + '\'' +
                ", SKUSpec='" + SKUSpec + '\'' +
                ", UserSKUCode='" + UserSKUCode + '\'' +
                ", URL='" + URL + '\'' +
                ", Channel='" + Channel + '\'' +
                ", ShopName='" + ShopName + '\'' +
                ", IsAuthor='" + IsAuthor + '\'' +
                ", Daigou='" + Daigou + '\'' +
                ", OriginalPrice='" + OriginalPrice + '\'' +
                ", ActivityPrice='" + ActivityPrice + '\'' +
                ", GuidancePrice='" + GuidancePrice + '\'' +
                ", FinalPrice='" + FinalPrice + '\'' +
                ", MarginPrice='" + MarginPrice + '\'' +
                ", MarginPercent='" + MarginPercent + '\'' +
                ", MarkdownPercent='" + MarkdownPercent + '\'' +
                ", InsertDate='" + InsertDate + '\'' +
                ", ShopId='" + ShopId + '\'' +
                ", ViewTime='" + ViewTime + '\'' +
                ", SellerLocation='" + SellerLocation + '\'' +
                ", IsbreakPrice='" + IsbreakPrice + '\'' +
                ", IsTariff='" + IsTariff + '\'' +
                ", Tariff='" + Tariff + '\'' +
                ", TariffPrice='" + TariffPrice + '\'' +
                ", AllPromotion='" + AllPromotion + '\'' +
                ", UsePromotion='" + UsePromotion + '\'' +
                ", SuperPromotion='" + SuperPromotion + '\'' +
                ", ScrollSales='" + ScrollSales + '\'' +
                ", CommentNum='" + CommentNum + '\'' +
                ", StockNum='" + StockNum + '\'' +
                ", StockStatus='" + StockStatus + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
