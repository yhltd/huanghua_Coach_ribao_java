package com.coach.pro.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coach.pro.entity.CoachItem;
import com.coach.pro.util.AccessInterface;
import com.coach.pro.util.Encryption;
import com.coach.pro.util.JsonToString;
import com.coach.pro.util.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanghui
 * @date 2021/08/30 10:47
 */
@RestController
@RequestMapping("/item_info")
public class ItemInfoController {
    /**
     *
     * @return 返回json数据
     */
    @PostMapping("/getItem")
    public ResultInfo getList(){
        try {
            //接口url
            String url  = "http://imagetest.simplybrand.com/api/product/findProductInfoPage";
            //参数
            String param="TaskID=47&SubID=1&page=1&size=";
            //参数
            String size="1";
            String content="BPSProductInfo";
            //获取总数据量
            size="5";
            //size=AccessInterface.getCount(AccessInterface.sendGet(url,param+size+"&token="+Encryption.Encrypt(content)));
            //获取全部数据
            String json=AccessInterface.sendGet(url,param+size+"&token="+Encryption.Encrypt(content));
            JSONObject jsonObject= JSONObject.parseObject(json);
            JSONObject jsonData=jsonObject.getJSONObject("data");
            JSONArray jsonArray=jsonData.getJSONArray("data");
            List<CoachItem> list = JSONObject.parseArray(jsonArray.toJSONString(), CoachItem.class);
            return ResultInfo.success("获取成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResultInfo.error("错误!");
        }
    }
}
