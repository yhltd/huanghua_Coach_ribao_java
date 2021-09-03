package com.coach.pro.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coach.pro.entity.CoachItem;
import com.coach.pro.entity.RetailShop;
import com.coach.pro.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wanghui
 * @date 2021/08/30 10:47
 */
@RestController
@RequestMapping("/item_info")
public class ItemInfoController {
    /**
     * @return 返回jsonArray
     */
    @PostMapping("/getItem")

    public ResultInfo getList() {
        try {
            //接口url
            String url = "http://imagetest.simplybrand.com/api/product/findProductInfoPage";
            //参数
            String param = "TaskID=47&SubID=1&page=1&size=";
            //参数
            String size = "1";
            String content = "BPSProductInfo";
            //获取总数据量
            //size = "12";
            size = AccessInterface.getCount(AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content)));
            //获取全部数据
            String json = AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content));
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("data");

            //JsonToString.jsonArraySort(jsonArray);

            return ResultInfo.success("获取成功", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.error("错误!");
        }
    }

    @PostMapping("/toExcel")
    public ResultInfo toExcel(HttpServletResponse response) throws IOException {
        try {
            //接口url
            String url = "http://imagetest.simplybrand.com/api/product/findProductInfoPage";
            //参数
            String param = "TaskID=47&SubID=1&page=1&size=";
            //参数
            String size = "1";
            String content = "BPSProductInfo";
            //获取总数据量
            //size = "1000";
            size = AccessInterface.getCount(AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content)));
            //获取全部数据
            String json = AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content));
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("data");
            List<CoachItem> list = JSONObject.parseArray(jsonArray.toJSONString(), CoachItem.class);

            //retail_全网
            List<CoachItem> Retail_Quanwanglist = new ArrayList<>();
            //排序
            Collections.sort(list, new Comparator<CoachItem>() {
                @Override
                public int compare(CoachItem o1, CoachItem o2) {
                    double a = o1.getMarginPrice() == null ? 0 : Double.parseDouble(o1.getMarginPrice());
                    double b = o2.getMarginPrice() == null ? 0 : Double.parseDouble(o2.getMarginPrice());
                    if (a > b) {
                        return 1;
                    } else if (a == b) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            //根据SKU分类信息生成retail_全网数据
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.retail.contains(list.get(i).getUserSKUCode())) {
                    Retail_Quanwanglist.add(list.get(i));
                }
                if (Retail_Quanwanglist.size() == 10) {
                    break;
                }
            }
            //retail_店铺
            List<RetailShop> retail_shop = new ArrayList();
            //去重
            HashSet set = new HashSet();
            for (int i = 0; i < list.size(); i++) {
                set.add(list.get(i).getUserSKUCode());
            }
            //set转为list
            List<String> quchong = new ArrayList<>();
            quchong.addAll(set);
            for (int i = 0; i < quchong.size(); i++) {
                //根据SKU分类信息判断
                if (StringUtils.retail.contains(quchong.get(i))) {
                    RetailShop retailShop = new RetailShop(quchong.get(i));
                    retail_shop.add(retailShop);
                }
            }

            //循环三家店铺和coach官网
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < retail_shop.size(); j++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopName().equals("COACH天猫旗舰店")) {
                            retail_shop.get(j).setTmDiscount(list.get(i).getUsePromotion());
                            retail_shop.get(j).setTmPrice(list.get(i).getFinalPrice());
                            retail_shop.get(j).setTMBiaoZhunPrice(list.get(i).getGuidancePrice());
                        }
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < retail_shop.size(); j++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopName().equals("COACH京东自营店")) {
                            retail_shop.get(j).setJDPrice(list.get(i).getFinalPrice());
                            retail_shop.get(j).setJDPriceDiscount(list.get(i).getUsePromotion());
                            retail_shop.get(j).setJDBiaoZhunPrice(list.get(i).getGuidancePrice());
                        }
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < retail_shop.size(); j++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopName().equals("COACH蔻驰官网")) {
                            retail_shop.get(j).setCoachPrice(list.get(i).getGuidancePrice());
                            retail_shop.get(j).setCoachPriceDiscount(list.get(i).getUsePromotion());
                            retail_shop.get(j).setCoachBiaoZhunPrice(list.get(i).getGuidancePrice());
                        }
                    }
                }
            }
            //把不符合的剔除掉并判断价差
            for (int i = 0; i < retail_shop.size(); i++) {
                if (retail_shop.get(i).getTmPrice() == null && retail_shop.get(i).getJDPrice() == null && retail_shop.get(i).getOverseasPrice() == null) {
                    retail_shop.remove(i);
                    if (i != 0) {
                        i = i - 1;
                    }else if (i==0){
                        i=i-1;
                    }
                } else if (retail_shop.get(i).getTmPrice() != null) {
                    if (Math.abs(Double.parseDouble(retail_shop.get(i).getTMBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getTmPrice())) > 100) {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getTMBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getTMBiaoZhunPrice());
                        retail_shop.get(i).setTmPrice(retail_shop.get(i).getTmPrice()+"(-"+Math.abs(Double.parseDouble(retail_shop.get(i).getTMBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getTmPrice()))+")");
                        retail_shop.get(i).setTmPriceDifference("Y");
                    } else {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getTMBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getTMBiaoZhunPrice());
                        retail_shop.get(i).setTmPriceDifference("N");
                    }
                    retail_shop.get(i).setJDPrice(null);
                    retail_shop.get(i).setJDPriceDiscount(null);
                    retail_shop.get(i).setJDBiaoZhunPrice(null);
                    retail_shop.get(i).setCoachPrice(null);
                    retail_shop.get(i).setCoachPriceDiscount(null);
                    retail_shop.get(i).setCoachBiaoZhunPrice(null);
                } else if (retail_shop.get(i).getJDPrice() != null) {
                    if (Math.abs(Double.parseDouble(retail_shop.get(i).getJDBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getJDPrice())) > 100) {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getJDBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getJDBiaoZhunPrice());
                        retail_shop.get(i).setJDPrice(retail_shop.get(i).getJDPrice()+"(-"+Math.abs(Double.parseDouble(retail_shop.get(i).getJDBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getJDPrice()))+")");
                        retail_shop.get(i).setJDPriceDifference("Y");
                    } else {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getJDBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getJDBiaoZhunPrice());
                        retail_shop.get(i).setJDPriceDifference("N");
                    }
                    retail_shop.get(i).setCoachPrice(null);
                    retail_shop.get(i).setCoachPriceDiscount(null);
                    retail_shop.get(i).setCoachBiaoZhunPrice(null);
                }else{
                    if (Math.abs(Double.parseDouble(retail_shop.get(i).getCoachBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getCoachPrice())) > 100) {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getCoachBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getCoachBiaoZhunPrice());
                        retail_shop.get(i).setCoachPrice(retail_shop.get(i).getCoachPrice()+"(-"+Math.abs(Double.parseDouble(retail_shop.get(i).getCoachBiaoZhunPrice()) - Double.parseDouble(retail_shop.get(i).getCoachPrice()))+")");
                        retail_shop.get(i).setCoachPriceDifference("Y");
                    } else {
                        retail_shop.get(i).setGuanFang_price(retail_shop.get(i).getCoachBiaoZhunPrice());
                        retail_shop.get(i).setGuanFangCuXiao_price(retail_shop.get(i).getCoachBiaoZhunPrice());
                        retail_shop.get(i).setCoachPriceDifference("N");
                    }
                }
            }
            GenerateExcel g=new GenerateExcel();
            g.retailShopToExcel(retail_shop);
            System.out.println(retail_shop);

            return ResultInfo.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.error("错误!");
        }
    }
}
