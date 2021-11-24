package com.coach.pro.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coach.pro.entity.*;
import com.coach.pro.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
            String url = "http://similarpic.simplybrand.com/api/product/findProductInfoPage";
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.add(Calendar.DAY_OF_MONTH, -1);
            System.out.println(sdf.format(now.getTime()));

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
            String url = "http://similarpic.simplybrand.com/api/product/findProductInfoPage";
            //参数
            String param = "TaskID=47&SubID=1&page=1&size=";
            //参数
            String size = "1";
            String content = "BPSProductInfo";
            //获取总数据量
            //size = "3";
            size = AccessInterface.getCount(AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content)));
            //获取全部数据
            String json = AccessInterface.sendGet(url, param + size + "&token=" + Encryption.Encrypt(content));
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject jsonData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("data");
            List<CoachItem> list = JSONObject.parseArray(jsonArray.toJSONString(), CoachItem.class);

            //去除小数点后面的0
            DecimalFormat format = new DecimalFormat("0.##");

            /**
             * retail_店铺
             */
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

            //循环获取官方指导价和到手价(原本是官方指导促销价，现在改为到手价)
            for (int j = 0; j < retail_shop.size(); j++) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("49346")) {
                                retail_shop.get(j).setGuanFang_price(list.get(i).getOriginalPrice());
                                retail_shop.get(j).setGuanFangCuXiao_price(list.get(i).getFinalPrice());
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < retail_shop.size(); j++) {
                if (retail_shop.get(j).getGuanFang_price() != null && !retail_shop.get(j).getGuanFang_price().equals("")) {
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("49905")) {
                                retail_shop.get(j).setGuanFang_price(list.get(i).getOriginalPrice());
                                retail_shop.get(j).setGuanFangCuXiao_price(list.get(i).getFinalPrice());
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < retail_shop.size(); j++) {
                if (retail_shop.get(j).getGuanFang_price() != null && !retail_shop.get(j).getGuanFang_price().equals("")) {
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("57383")) {
                                retail_shop.get(j).setGuanFang_price(list.get(i).getOriginalPrice());
                                retail_shop.get(j).setGuanFangCuXiao_price(list.get(i).getFinalPrice());
                            }
                        }
                    }
                }
            }

            //循环获取各店铺到手价和差价等
            for (int j = 0; j < retail_shop.size(); j++) {
                if (retail_shop.get(j).getGuanFang_price() != null && !retail_shop.get(j).getGuanFang_price().equals("")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getShopId() == null) {
                            continue;
                        }
                        if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode()) && list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("49346")) {
                                retail_shop.get(j).setTmDiscount(list.get(i).getUsePromotion());
                                retail_shop.get(j).setTMUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    retail_shop.get(j).setTmPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setTmPriceDifference("Y");
                                } else if (Math.abs( Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price())) ==0){
                                    retail_shop.get(j).setTmPrice(list.get(i).getFinalPrice());
                                    retail_shop.get(j).setTmPriceDifference("N");
                                } else{
                                    retail_shop.get(j).setTmPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setTmPriceDifference("N");
                                }

                            }
                        }
                        if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                            if (list.get(i).getShopId().equals("49905")) {
                                retail_shop.get(j).setLingShouPriceDiscount(list.get(i).getUsePromotion());
                                retail_shop.get(j).setLingShouUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) > 100) {
                                    retail_shop.get(j).setLingShouPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price())  ) + ")");
                                    retail_shop.get(j).setLingShouPriceDifference("Y");
                                } else if (Math.abs( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) ==0){
                                    retail_shop.get(j).setLingShouPrice(list.get(i).getFinalPrice());
                                    retail_shop.get(j).setLingShouPriceDifference("N");
                                } else{
                                    retail_shop.get(j).setLingShouPrice(list.get(i).getFinalPrice() + "(" + format.format(  Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price())) + ")");
                                    retail_shop.get(j).setLingShouPriceDifference("N");
                                }
                            }
                        }
                        if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                            if (list.get(i).getShopId().equals("57383")) {
                                retail_shop.get(j).setCoachPriceDiscount(list.get(i).getUsePromotion());
                                retail_shop.get(j).setCoachUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    retail_shop.get(j).setCoachPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setCoachPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) ==0){
                                    retail_shop.get(j).setCoachPrice(list.get(i).getFinalPrice());
                                    retail_shop.get(j).setCoachPriceDifference("N");
                                } else{
                                    retail_shop.get(j).setCoachPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setCoachPriceDifference("N");
                                }
                            }
                        }
                        if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                            if (list.get(i).getShopId().equals("16797")) {
                                retail_shop.get(j).setJDPriceDiscount(list.get(i).getUsePromotion());
                                retail_shop.get(j).setJDUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    retail_shop.get(j).setJDPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setJDPriceDifference("Y");
                                } else if(Math.abs( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price())) ==0){
                                    retail_shop.get(j).setJDPrice(list.get(i).getFinalPrice());
                                    retail_shop.get(j).setJDPriceDifference("N");
                                }else{
                                    retail_shop.get(j).setJDPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setJDPriceDifference("N");
                                }
                            }
                        }
                        if (list.get(i).getUserSKUCode().equals(retail_shop.get(j).getUserSKUCode())) {
                            if (list.get(i).getShopId().equals("49369")) {
                                retail_shop.get(j).setOverseasPriceDiscount(list.get(i).getUsePromotion());
                                retail_shop.get(j).setOverseasUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) >100) {
                                    retail_shop.get(j).setOverseasPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setOverseasPriceDifference("Y");
                                } else if((Math.abs( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) ==0)){
                                    retail_shop.get(j).setOverseasPrice(list.get(i).getFinalPrice());
                                    retail_shop.get(j).setOverseasPriceDifference("N");
                                } else{
                                    retail_shop.get(j).setOverseasPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(retail_shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    retail_shop.get(j).setOverseasPriceDifference("N");
                                }
                            }
                        }
                    }
                    if (retail_shop.get(j).getTmPriceDifference() == null || retail_shop.get(j).getTmPriceDifference().equals("")) {
                        retail_shop.get(j).setTmPriceDifference("");
                    }
                    if (retail_shop.get(j).getLingShouPriceDifference() == null || retail_shop.get(j).getLingShouPriceDifference().equals("")) {
                        retail_shop.get(j).setLingShouPriceDifference("");
                    }
                    if (retail_shop.get(j).getCoachPriceDifference() == null || retail_shop.get(j).getCoachPriceDifference().equals("")) {
                        retail_shop.get(j).setCoachPriceDifference("");
                    }
                    if ((retail_shop.get(j).getTmPriceDifference().equals("") && retail_shop.get(j).getLingShouPriceDifference().equals("") && retail_shop.get(j).getCoachPriceDifference().equals("")) || (retail_shop.get(j).getTmPriceDifference().equals("N") && retail_shop.get(j).getLingShouPriceDifference().equals("N") && retail_shop.get(j).getCoachPriceDifference().equals("N"))) {
                        retail_shop.remove(j);
                        j = j - 1;
                    }
                } else {
                    retail_shop.remove(j);
                    j = j - 1;
                }
            }


            /**
             * 全网
             */
            List<CoachItem> QuanWang = new ArrayList<>();
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

            //循环将每条sku价差最大的前10条数据放入数组中
            int cishu = 0;
            for (int i = 0; i < quchong.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (cishu == 10) {
                        break;
                    }
                    if (list.get(j).getMarginPrice() != null && !list.get(j).getMarginPrice().equals("")) {
                        if (quchong.get(i).equals(list.get(j).getUserSKUCode()) && Double.parseDouble(list.get(j).getMarginPrice()) < 0) {
                            QuanWang.add(list.get(j));
                            cishu = cishu + 1;
                        }
                    }
                }
                cishu = 0;
            }

            /**
             * retail_sku
             */
            List<RetailSku> retail_sku = new ArrayList<>();
            //无需再次去重,上面的quchong可以拿来继续用
            for (int i = 0; i < quchong.size(); i++) {
                if (StringUtils.retail.contains(quchong.get(i))) {
                    RetailSku retailSku = new RetailSku(quchong.get(i));
                    retail_sku.add(retailSku);
                }
            }
            //循环将获取官方指导价作为标准价
            for (int i = 0; i < retail_sku.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getUserSKUCode().equals(retail_sku.get(i).getUserSKUCode()) && list.get(j).getShopId() != null) {
                        if (list.get(j).getShopId().equals("49346")) {
                            retail_sku.get(i).setBiaoZhunPrice(list.get(j).getOriginalPrice());
                            retail_sku.get(i).setGuidancePrice(list.get(j).getGuidancePrice());
                        }
                    }
                }
            }
            for (int i = 0; i < retail_sku.size(); i++) {
                if (retail_sku.get(i).getBiaoZhunPrice() == null) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getUserSKUCode().equals(retail_sku.get(i).getUserSKUCode()) && list.get(j).getShopId() != null) {
                            if (list.get(j).getShopId().equals("57383")) {
                                retail_sku.get(i).setBiaoZhunPrice(list.get(j).getOriginalPrice());
                                retail_sku.get(i).setGuidancePrice(list.get(j).getGuidancePrice());
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < retail_sku.size(); i++) {
                if (retail_sku.get(i).getBiaoZhunPrice() == null) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getUserSKUCode().equals(retail_sku.get(i).getUserSKUCode()) && list.get(j).getShopId() != null) {
                            if (list.get(j).getShopId().equals("49905")) {
                                retail_sku.get(i).setBiaoZhunPrice(list.get(j).getOriginalPrice());
                                retail_sku.get(i).setGuidancePrice(list.get(j).getGuidancePrice());
                            }
                        }
                    }
                }
            }

            //循环判断差价
            for (int i = 0; i < retail_sku.size(); i++) {
                if (retail_sku.get(i).getBiaoZhunPrice() != null && retail_sku.get(i).getGuidancePrice() != null && retail_sku.get(i).getBiaoZhunPrice() != null) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getShopId() == null || list.get(j).getShopId().equals("")) {
                            continue;
                        }
                        if (list.get(j).getUserSKUCode().equals(retail_sku.get(i).getUserSKUCode())) {
                            if (list.get(j).getOriginalPrice() != null && !list.get(j).getOriginalPrice().equals("")) {
                                if (list.get(j).getShopId().equals("49346")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            retail_sku.get(i).setTMPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice())  ) + ")");
                                            retail_sku.get(i).setGF_Jiacha("Y");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            retail_sku.get(i).setTMPrice(list.get(j).getOriginalPrice());
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setTMPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice()) - Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice())) + ")");
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            retail_sku.get(i).setTMGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setGFCX_Jiacha("Y");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0){
                                            retail_sku.get(i).setTMGuidancePrice(list.get(j).getGuidancePrice());
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setTMGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            retail_sku.get(i).setTMFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setFinal_Jiacha("Y");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0) {
                                            retail_sku.get(i).setTMFinalPrice(list.get(j).getFinalPrice());
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setTMFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                            retail_sku.get(i).setTMUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("57383")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            retail_sku.get(i).setCoachPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            retail_sku.get(i).setGF_Jiacha("Y");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            retail_sku.get(i).setCoachPrice(list.get(j).getOriginalPrice());
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setCoachPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            retail_sku.get(i).setCoachGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setGFCX_Jiacha("Y");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0){
                                            retail_sku.get(i).setCoachGuidancePrice(list.get(j).getGuidancePrice());
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setCoachGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            retail_sku.get(i).setCoachFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setFinal_Jiacha("Y");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0){
                                            retail_sku.get(i).setCoachFinalPrice(list.get(j).getFinalPrice());
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setCoachFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                            retail_sku.get(i).setCoachUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("49905")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            retail_sku.get(i).setLingShouPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            retail_sku.get(i).setGF_Jiacha("Y");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        } else if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            retail_sku.get(i).setLingShouPrice(list.get(j).getOriginalPrice());
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setLingShouPrice(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            retail_sku.get(i).setLingShouGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setGFCX_Jiacha("Y");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        } else if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0) {
                                            retail_sku.get(i).setLingShouGuidancePrice(list.get(j).getGuidancePrice());
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setLingShouGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            retail_sku.get(i).setLingShouFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setFinal_Jiacha("Y");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        } else if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0) {
                                            retail_sku.get(i).setLingShouFinalPrice(list.get(j).getFinalPrice());
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setLingShouFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                            retail_sku.get(i).setLingShouUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("16797")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            retail_sku.get(i).setJDPrice(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            retail_sku.get(i).setGF_Jiacha("Y");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            retail_sku.get(i).setJDPrice(list.get(j).getOriginalPrice());
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setJDPrice(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            retail_sku.get(i).setJDGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setGFCX_Jiacha("Y");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0){
                                            retail_sku.get(i).setJDGuidancePrice(list.get(j).getGuidancePrice());
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }else{
                                            retail_sku.get(i).setJDGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            retail_sku.get(i).setJDFinalPrice(list.get(j).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            retail_sku.get(i).setFinal_Jiacha("Y");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0) {
                                            retail_sku.get(i).setJDFinalPrice(list.get(j).getFinalPrice());
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                        } else{
                                            retail_sku.get(i).setJDFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                            retail_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("49369")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            retail_sku.get(i).setOverseasPrice(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            retail_sku.get(i).setGF_Jiacha("Y");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if (Math.abs(Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            retail_sku.get(i).setOverseasPrice(list.get(j).getOriginalPrice());
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setOverseasPrice(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(retail_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //retail_sku.get(i).setGF_Jiacha("N");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            retail_sku.get(i).setOverseasGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice())  ) + ")");
                                            retail_sku.get(i).setGFCX_Jiacha("Y");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0) {
                                            retail_sku.get(i).setOverseasGuidancePrice(list.get(j).getGuidancePrice());
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setOverseasGuidancePrice(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice()) -Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setGFCX_Jiacha("N");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            retail_sku.get(i).setOverseasFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(retail_sku.get(i).getGuidancePrice())  ) + ")");
                                            retail_sku.get(i).setFinal_Jiacha("Y");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(retail_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0) {
                                            retail_sku.get(i).setOverseasFinalPrice(list.get(j).getFinalPrice());
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                        }else{
                                            retail_sku.get(i).setOverseasFinalPrice(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(retail_sku.get(i).getGuidancePrice()) ) + ")");
                                            //retail_sku.get(i).setFinal_Jiacha("N");
                                            retail_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (retail_sku.get(i).getGF_Jiacha() == null && retail_sku.get(i).getGFCX_Jiacha() == null && retail_sku.get(i).getFinal_Jiacha() == null) {
                        retail_sku.remove(i);
                        i = i - 1;
                    }
                } else {
                    retail_sku.remove(i);
                    i = i - 1;
                }
            }

            /**
             * outlet店铺
             */
            List<OutletShop> outlet_Shop = new ArrayList<>();
            for (int i = 0; i < quchong.size(); i++) {
                //根据SKU分类信息判断
                if (StringUtils.outlet.contains(quchong.get(i))) {
                    OutletShop outletShop = new OutletShop(quchong.get(i));
                    outlet_Shop.add(outletShop);
                }
            }

            //循环获取官方指导价和官方指导促销价
            for (int j = 0; j < outlet_Shop.size(); j++) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserSKUCode().equals(outlet_Shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("49357")) {
                                outlet_Shop.get(j).setGuanFang_price(list.get(i).getOriginalPrice());
                                outlet_Shop.get(j).setGuanFangCuXiao_price(list.get(i).getFinalPrice());
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < outlet_Shop.size(); j++) {
                if (outlet_Shop.get(j).getGuanFang_price() != null && !outlet_Shop.get(j).getGuanFang_price().equals("")) {
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserSKUCode().equals(outlet_Shop.get(j).getUserSKUCode())) {
                        if (list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("57523")) {
                                outlet_Shop.get(j).setGuanFang_price(list.get(i).getOriginalPrice());
                                outlet_Shop.get(j).setGuanFangCuXiao_price(list.get(i).getFinalPrice());
                            }
                        }
                    }
                }
            }

            //循环获取各店铺到手价和差价等
            for (int j = 0; j < outlet_Shop.size(); j++) {
                if (outlet_Shop.get(j).getGuanFang_price() != null && !outlet_Shop.get(j).getGuanFang_price().equals("")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUserSKUCode().equals(outlet_Shop.get(j).getUserSKUCode()) && list.get(i).getShopId() != null) {
                            if (list.get(i).getShopId().equals("49357")) {
                                outlet_Shop.get(j).setOutletDiscount(list.get(i).getUsePromotion());
                                outlet_Shop.get(j).setOutletUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    outlet_Shop.get(j).setOutletPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setOutletPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) ==0){
                                    outlet_Shop.get(j).setOutletPrice(list.get(i).getFinalPrice());
                                    outlet_Shop.get(j).setOutletPriceDifference("N");
                                }else{
                                    outlet_Shop.get(j).setOutletPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setOutletPriceDifference("N");
                                }
                            }
                            if (list.get(i).getShopId().equals("57523")) {
                                outlet_Shop.get(j).setCoachPriceDiscount(list.get(i).getUsePromotion());
                                outlet_Shop.get(j).setCoachUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    outlet_Shop.get(j).setCoachPrice(list.get(i).getFinalPrice() + "(" + format.format(  Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setCoachPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) ==0){
                                    outlet_Shop.get(j).setCoachPrice(list.get(i).getFinalPrice());
                                    outlet_Shop.get(j).setCoachPriceDifference("N");
                                }else{
                                    outlet_Shop.get(j).setCoachPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setCoachPriceDifference("N");
                                }
                            }
                            if (list.get(i).getShopId().equals("49369")) {
                                outlet_Shop.get(j).setOverseasPriceDiscount(list.get(i).getUsePromotion());
                                outlet_Shop.get(j).setOverseasUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    outlet_Shop.get(j).setOverseasPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setOverseasPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) ==0){
                                    outlet_Shop.get(j).setOverseasPrice(list.get(i).getFinalPrice());
                                    outlet_Shop.get(j).setOverseasPriceDifference("N");
                                }else{
                                    outlet_Shop.get(j).setOverseasPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setOverseasPriceDifference("N");
                                }
                            }
                            if (list.get(i).getShopId().equals("16797")) {
                                outlet_Shop.get(j).setJDPriceDiscount(list.get(i).getUsePromotion());
                                outlet_Shop.get(j).setJDUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    outlet_Shop.get(j).setJDPrice(list.get(i).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setJDPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) ==0){
                                    outlet_Shop.get(j).setJDPrice(list.get(i).getFinalPrice());
                                    outlet_Shop.get(j).setJDPriceDifference("N");
                                }else{
                                    outlet_Shop.get(j).setJDPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setJDPriceDifference("N");
                                }
                            }
                            if (list.get(i).getShopId().equals("49935")) {
                                outlet_Shop.get(j).setCoachOSPriceDiscount(list.get(i).getUsePromotion());
                                outlet_Shop.get(j).setCoachOSUrl(list.get(i).getURL());
                                if (Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) >100) {
                                    outlet_Shop.get(j).setCoachOSPrice(list.get(i).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(i).getFinalPrice())- Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) ) + ")");
                                    outlet_Shop.get(j).setCoachOSPriceDifference("Y");
                                } else if(Math.abs(Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price()) - Double.parseDouble(list.get(i).getFinalPrice())) ==0){
                                    outlet_Shop.get(j).setCoachOSPrice(list.get(i).getFinalPrice());
                                    outlet_Shop.get(j).setCoachOSPriceDifference("N");
                                }else{
                                    outlet_Shop.get(j).setCoachOSPrice(list.get(i).getFinalPrice() + "(" + format.format(  Double.parseDouble(list.get(i).getFinalPrice())-Double.parseDouble(outlet_Shop.get(j).getGuanFangCuXiao_price())) + ")");
                                    outlet_Shop.get(j).setCoachOSPriceDifference("N");
                                }
                            }
                        }
                    }
                    if (outlet_Shop.get(j).getOutletPriceDifference() == null || outlet_Shop.get(j).getOutletPriceDifference().equals("")) {
                        outlet_Shop.get(j).setOutletPriceDifference("");
                    }
                    if (outlet_Shop.get(j).getCoachPriceDifference() == null || outlet_Shop.get(j).getCoachPriceDifference().equals("")) {
                        outlet_Shop.get(j).setCoachPriceDifference("");
                    }
                    if ((outlet_Shop.get(j).getOutletPriceDifference().equals("N") && outlet_Shop.get(j).getCoachPriceDifference().equals("N")) || (outlet_Shop.get(j).getOutletPriceDifference().equals("") && outlet_Shop.get(j).getCoachPriceDifference().equals(""))) {
                        outlet_Shop.remove(j);
                        j = j - 1;
                    }
                } else {
                    outlet_Shop.remove(j);
                    j = j - 1;
                }
            }

            /**
             * outlet_sku
             */
            List<OutletSku> outlet_sku = new ArrayList();
            for (int i = 0; i < quchong.size(); i++) {
                //根据SKU分类信息判断
                if (StringUtils.outlet.contains(quchong.get(i))) {
                    OutletSku outletSku = new OutletSku(quchong.get(i));
                    outlet_sku.add(outletSku);
                }
            }

            //循环将获取官方指导价作为标准价
            for (int i = 0; i < outlet_sku.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getUserSKUCode().equals(outlet_sku.get(i).getUserSKUCode()) && list.get(j).getShopId() != null) {
                        if (list.get(j).getShopId().equals("49357")) {
                            outlet_sku.get(i).setBiaoZhunPrice(list.get(j).getOriginalPrice());
                            outlet_sku.get(i).setGuidancePrice(list.get(j).getGuidancePrice());
                        }
                    }
                }
            }

            for (int i = 0; i < outlet_sku.size(); i++) {
                if (outlet_sku.get(i).getBiaoZhunPrice() == null) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getUserSKUCode().equals(outlet_sku.get(i).getUserSKUCode()) && list.get(j).getShopId() != null) {
                            if (list.get(j).getShopId().equals("57523")) {
                                outlet_sku.get(i).setBiaoZhunPrice(list.get(j).getOriginalPrice());
                                outlet_sku.get(i).setGuidancePrice(list.get(j).getGuidancePrice());
                            }
                        }
                    }
                }
            }

            //循环判断差价
            for (int i = 0; i < outlet_sku.size(); i++) {
                if (outlet_sku.get(i).getBiaoZhunPrice() != null && outlet_sku.get(i).getGuidancePrice() != null && outlet_sku.get(i).getBiaoZhunPrice() != null) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getShopId() == null || list.get(j).getShopId().equals("")) {
                            continue;
                        }
                        if (list.get(j).getUserSKUCode().equals(outlet_sku.get(i).getUserSKUCode())) {
                            if (list.get(j).getOriginalPrice() != null && !list.get(j).getOriginalPrice().equals("")) {
                                if (list.get(j).getShopId().equals("49357")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdj("Y");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdj(list.get(j).getOriginalPrice());
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                        }else{
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdcxj("Y");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0) {
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdcxj(list.get(j).getGuidancePrice());
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                        }else{
                                            outlet_sku.get(i).setOutlieFlagshipStore_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            outlet_sku.get(i).setOutlieFlagshipStore_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_dsj("Y");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0) {
                                            outlet_sku.get(i).setOutlieFlagshipStore_dsj(list.get(j).getFinalPrice());
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                        } else{
                                            outlet_sku.get(i).setOutlieFlagshipStore_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setOutlieUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("57523")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            outlet_sku.get(i).setWebsite_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdj("Y");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            outlet_sku.get(i).setWebsite_gfzdj(list.get(j).getOriginalPrice());
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                        }else{
                                            outlet_sku.get(i).setWebsite_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            outlet_sku.get(i).setWebsite_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdcxj("Y");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0){
                                            outlet_sku.get(i).setWebsite_gfzdcxj(list.get(j).getGuidancePrice());
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                        }else{
                                            outlet_sku.get(i).setWebsite_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            outlet_sku.get(i).setWebsite_dsj(list.get(j).getFinalPrice() + "(" + format.format(  Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice())) + ")");
                                            outlet_sku.get(i).setPricrDifference_dsj("Y");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0){
                                            outlet_sku.get(i).setWebsite_dsj(list.get(j).getFinalPrice());
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                        }else{
                                            outlet_sku.get(i).setWebsite_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setWebsiteUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("49369")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdj("Y");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdj(list.get(j).getOriginalPrice());
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                        }else{
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format(Double.parseDouble(list.get(j).getGuidancePrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdcxj("Y");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0) {
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdcxj(list.get(j).getGuidancePrice());
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                        } else{
                                            outlet_sku.get(i).setTMallGlobalStore_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            outlet_sku.get(i).setTMallGlobalStore_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_dsj("Y");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0){
                                            outlet_sku.get(i).setTMallGlobalStore_dsj(list.get(j).getFinalPrice());
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                        } else{
                                            outlet_sku.get(i).setTMallGlobalStore_dsj(list.get(j).getFinalPrice() + "(" + format.format(  Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice())) + ")");
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setTMallUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("16797")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            outlet_sku.get(i).setJDSelfOperated_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getOriginalPrice())-Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdj("Y");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0) {
                                            outlet_sku.get(i).setJDSelfOperated_gfzdj(list.get(j).getOriginalPrice());
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                        } else{
                                            outlet_sku.get(i).setJDSelfOperated_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            outlet_sku.get(i).setJDSelfOperated_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdcxj("Y");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0) {
                                            outlet_sku.get(i).setJDSelfOperated_gfzdcxj(list.get(j).getGuidancePrice());
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                        }else{
                                            outlet_sku.get(i).setJDSelfOperated_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            outlet_sku.get(i).setJDSelfOperated_dsj(list.get(j).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_dsj("Y");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0){
                                            outlet_sku.get(i).setJDSelfOperated_dsj(list.get(j).getFinalPrice());
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                        }else{
                                            outlet_sku.get(i).setJDSelfOperated_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setJDUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                                if (list.get(j).getShopId().equals("49935")) {
                                    if (list.get(j).getOriginalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) >100) {
                                            outlet_sku.get(i).setOverseasStores_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdj("Y");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) - Double.parseDouble(list.get(j).getOriginalPrice())) ==0){
                                            outlet_sku.get(i).setOverseasStores_gfzdj(list.get(j).getOriginalPrice());
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                        }else{
                                            outlet_sku.get(i).setOverseasStores_gfzdj(list.get(j).getOriginalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getOriginalPrice())- Double.parseDouble(outlet_sku.get(i).getBiaoZhunPrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdj("N");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getGuidancePrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) >100) {
                                            outlet_sku.get(i).setOverseasStores_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            outlet_sku.get(i).setPricrDifference_gfzdcxj("Y");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getGuidancePrice())) ==0){
                                            outlet_sku.get(i).setOverseasStores_gfzdcxj(list.get(j).getGuidancePrice());
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                        }else{
                                            outlet_sku.get(i).setOverseasStores_gfzdcxj(list.get(j).getGuidancePrice() + "(" + format.format( Double.parseDouble(list.get(j).getGuidancePrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_gfzdcxj("N");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                    if (list.get(j).getFinalPrice() != null) {
                                        if (Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) >100) {
                                            outlet_sku.get(i).setOverseasStores_dsj(list.get(j).getFinalPrice() + "(" + format.format( Double.parseDouble(list.get(j).getFinalPrice())- Double.parseDouble(outlet_sku.get(i).getGuidancePrice())) + ")");
                                            outlet_sku.get(i).setPricrDifference_dsj("Y");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        } else if(Math.abs(Double.parseDouble(outlet_sku.get(i).getGuidancePrice()) - Double.parseDouble(list.get(j).getFinalPrice())) ==0){
                                            outlet_sku.get(i).setOverseasStores_dsj(list.get(j).getFinalPrice());
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }else{
                                            outlet_sku.get(i).setOverseasStores_dsj(list.get(j).getFinalPrice() + "(" + format.format(Double.parseDouble(list.get(j).getFinalPrice())-Double.parseDouble(outlet_sku.get(i).getGuidancePrice())  ) + ")");
                                            //outlet_sku.get(i).setPricrDifference_dsj("N");
                                            outlet_sku.get(i).setOverseasUrl(list.get(j).getURL());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if ((outlet_sku.get(i).getPricrDifference_dsj() == null || outlet_sku.get(i).getPricrDifference_dsj().equals("N")) && (outlet_sku.get(i).getPricrDifference_gfzdcxj() == null || outlet_sku.get(i).getPricrDifference_gfzdcxj().equals("N")) && (outlet_sku.get(i).getPricrDifference_gfzdj() == null || outlet_sku.get(i).getPricrDifference_gfzdj().equals("N"))) {
                        outlet_sku.remove(i);
                        i = i - 1;
                    }
                } else {
                    outlet_sku.remove(i);
                    i = i - 1;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.add(Calendar.DAY_OF_MONTH, -1);
            System.out.println(sdf.format(now.getTime()));
//            String path = Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath();
//
//            if(path.substring(0,4).equals("file")){
//                path=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(5);
//            }else{
//                path=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(1);
//            }
            String path = "C:\\coach\\excel\\";
            GenerateExcel.retail_Shop_QuanWang_excel(path + "retail_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx", QuanWang, retail_shop);
            GenerateExcel.retail_sku_excel(path + "Price Variance Check Report – Retail Date" + sdf.format(now.getTime()) + ".xlsx", retail_sku);
            GenerateExcel.outlet_Shop_QuanWang_excel(path + "outlet_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx", QuanWang, outlet_Shop);
            GenerateExcel.outlet_sku_excel(path + "Price Variance Check Report – Outlet Date" + sdf.format(now.getTime()) + ".xlsx", outlet_sku);

//            GenerateExcel.retail_Shop_QuanWang_excel("E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\retail_店铺&全网日报日期"+sdf.format(new Date())+".xlsx",Retail_QuanWang,retail_shop);
//            GenerateExcel.retail_sku_excel("E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\Price Variance Check Report – Retail Date"+sdf.format(new Date())+".xlsx",retail_sku);
//            GenerateExcel.outlet_Shop_QuanWang_excel("E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\outlet_店铺&全网日报日期"+sdf.format(new Date())+".xlsx",outlet_QuanWang,outlet_Shop);
//            GenerateExcel.outlet_sku_excel("E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\Price Variance Check Report – Outlet Date"+sdf.format(new Date())+".xlsx",outlet_sku);

            Email email = new Email();
            email.retailShopSend("retail_店铺&全网日报", path + "retail_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx", "Dear All,<br>" +
                    "<br>" +
                    "Attached please find the COACH Online Shop&Entire Network Report for your reference." +
                    "<br>" + "Please be noted — Date is capture from" + sdf.format(now.getTime()) + " 12am _ " + sdf.format(now.getTime()) + " 12pm." +
                    "<br>" + "Please promptly to the link for more data information: http://dailyreport.simplybrand.com/coach-report/#/a0b923820dcc509a/Sku" +
                    "<br><br>" +
                    "simplyBrand BPS <br>" +
                    "System service mail", "retail_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx");

            email.retailSkuSend("Price Variance Check Report – Retail", path + "Price Variance Check Report – Retail Date" + sdf.format(now.getTime()) + ".xlsx", "Dear All,<br>" +
                    "<br>" +
                    "Attached please find the COACH Online Shop&Entire Network Report for your reference. <br>" +
                    "Please be noted — Date is capture from" + sdf.format(now.getTime()) + " 12am _ " + sdf.format(now.getTime()) + " 12pm. <br>" +
                    "Please promptly to the link for more data information: http://dailyreport.simplybrand.com/coach-report/#/9d4c2f636f067f89/Sku <br>" +
                    "<br><br>" +
                    "simplyBrand BPS <br>" +
                    "System service mail", "Price Variance Check Report – Retail Date" + sdf.format(now.getTime()) + ".xlsx");

            email.outletShopSend("outlet_店铺&全网日报", path + "outlet_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx", "Dear All,<br>" +
                    "<br>" +
                    "Attached please find the Price Variance Check Report – Retail for your reference. <br>" +
                    "Please be noted — Date is capture from " + sdf.format(now.getTime()) + " 12am _ " + sdf.format(now.getTime()) + " 12pm. <br>" +
                    "Please promptly to the link for more data information: http://dailyreport.simplybrand.com/coach-report/#/a0b923820dcc509a/Sku <br>" +
                    "<br>" +
                    "simplyBrand BPS <br>" +
                    "System service mail", "outlet_店铺&全网日报日期" + sdf.format(now.getTime()) + ".xlsx");
            email.outletSkuSend("Price Variance Check Report – Outlet", path + "Price Variance Check Report – Outlet Date" + sdf.format(now.getTime()) + ".xlsx", "Dear All,<br>" +
                    "<br>" +
                    "Attached please find the COACH Online Pricing Report for your reference. <br>" +
                    "Please be noted — Date is capture from " + sdf.format(now.getTime()) + " 12am _ " + sdf.format(now.getTime()) + " 12pm. <br>" +
                    "Please promptly to the link for more data information: http://dailyreport.simplybrand.com/coach-report/#/9d4c2f636f067f89/Sku <br>" +
                    "<br>" +
                    "simplyBrand BPS <br>" +
                    "System service mail", "Price Variance Check Report – Outlet Date" + sdf.format(now.getTime()) + ".xlsx");
            return ResultInfo.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.error("错误!");
        }
    }


}
