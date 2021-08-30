package com.coach.pro.util;

import com.alibaba.fastjson.JSON;
import com.coach.pro.entity.CoachItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanghui
 * @date 2021/08/30 14:43
 */
public class JsonToString {
    //json字符串转list
    public static List<Map<String, CoachItem>> jsonToList(String json){//PendingInfo可以换成其它bean类
        List<Object> list = JSON.parseArray(json);
        List< Map<String,CoachItem>> listw = new ArrayList<Map<String,CoachItem>>();
        for (Object object : list){
            Map<String,Object> ageMap = new HashMap<String,Object>();
            Map <String,CoachItem> ret = (Map<String, CoachItem>) object;//取出list里面的值转为map
            listw.add(ret);
        }
        return listw;
    }
}
