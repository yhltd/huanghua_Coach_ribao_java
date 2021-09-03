package com.coach.pro.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coach.pro.entity.CoachItem;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanghui
 * @date 2021/08/30 14:43
 */
public class JsonToString {
    /**
     * json字符串转list
     */
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

    /**
     * 对多个JOSN对象进行自定义字段排序
     *
     * @param jsonArr 要排序的JSONArray
     * @return 返回排序后的结果
     */
    public static JSONArray jsonArraySort(com.alibaba.fastjson.JSONArray jsonArr) {
        //com.alibaba.fastjson.JSONArray jsonArr = JSON.parseArray(jsonArrStr);
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<>();
        //存放json数组中的每个json对象
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        JSONObject jsonObject=new JSONObject();
        //对集合中的JOSN对象进行自定义排序
        jsonValues.sort((a, b) -> {
            Integer valA = a.getInteger("MarginPrice");
            Integer valB = b.getInteger("MarginPrice");
            return valB.compareTo(valA);
        });

        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}
