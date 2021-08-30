
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import net.sf.json.JSONArray;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLConnection;

import java.net.URL;
import java.util.*;

/**
 * @author wanghui
 * @date 2021/08/20 11:45
 */
public class jiekou {
    @SuppressWarnings("unchecked")

    public static final String KEY = "FnJL7EDzjqWjcbps";//密钥key
    public static final String IV  = "FnJL7EDzjqWjcaY9";//偏移量iv
    public static final String url  = "http://imagetest.simplybrand.com/api/product/findProductInfoPage";//接口url
    public static String param="TaskID=47&SubID=1&page=1&size=";//参数
    public static String size="5";
    public static void main(String [] as)throws Exception{
        int count=Integer.parseInt(getCount(sendGet(url,param+size+"&token="+Encrypt("BPSProductInfo"))));
        JSONObject jsonObject= JSONObject.parseObject(sendGet(url,param+size+"&token="+Encrypt("BPSProductInfo")));
        JSONObject jsonData=jsonObject.getJSONObject("data");
        JSONArray jsonArray=JSONArray.fromObject(jsonData);
        net.sf.json.JSONObject jsonObject1=jsonArray.getJSONObject(0);
        JSONArray jsonArray1=jsonObject1.getJSONArray("data");
        System.out.println(jsonArraySort(jsonArray1));
        //        count=2;
//        net.sf.json.JSONObject json=generateData(sendGet(url,param+count+"&token="+Encrypt("BPSProductInfo")));
//        System.out.println(toExcel("E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\新建工作簿.xlsx",json));

        //sendGet(url,param+size+"&token="+Encrypt("BPSProductInfo"));
    }

//请求接口并分析数据
//    public static void getUrl(){
//        String param="?TaskID=47&SubID=1&page=1&size=25&token=58bf88f480c3127ac566a79d7796ef15";
//        StringBuilder sb=new StringBuilder();
//        InputStream is=null;
//        BufferedReader br=null;
//        PrintWriter out =null;
//
//        try{
//            //接口地址
//            String url="http://imagetest.simplybrand.com/api/product/findProductInfoPage";
//            //String url="https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb";
//            URL uri=new URL(url);
//            HttpURLConnection connection=(HttpURLConnection) uri.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setReadTimeout(10000);
//            connection.setConnectTimeout(10000);
//            connection.setRequestProperty("accept","*/*");
//            //发送信息
//            connection.setDoOutput(true);
//            out=new PrintWriter(connection.getOutputStream());
//            out.print(param);
//            out.flush();
//            System.out.println(out);
//            //接收结果
//            is= connection.getInputStream();
//            br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
//            String line;
//            //缓冲逐行读取
//            while((line=br.readLine())!=null){
//                sb.append(line);
//            }
//            String backStr=sb.toString();
//            System.out.println(backStr);
////            JSONObject jsonObject=JSONObject.parseObject(backStr);
////            JSONArray heWeather6=jsonObject.getJSONArray("HeWeather6");
////            JSONObject jsonObject1=heWeather6.getJSONObject(0);
//
////            System.out.println(backStr);
////            System.out.println(heWeather6);
////            System.out.println(jsonObject1);
//
////            JSONObject basic=jsonObject1.getJSONObject("basic");
////            System.out.println(basic);
////            String parent_city=basic.getString("parent_city");
////            System.out.println(parent_city);
////            String lon=basic.getString("lon");
////            System.out.println(lon);
////            String lat=basic.getString("lat");
////            System.out.println(lat);
//
////            System.out.println("-----------------------------");
////
////            JSONArray pcpn_5m=jsonObject.getJSONArray("HeWeather6");
////            for (int i=0;i<pcpn_5m.size();i++){
////                JSONObject jsonObject2=pcpn_5m.getJSONObject(i);
////                System.out.println(jsonObject2);
////            }
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            try {
//                if (is!=null){
//                    is.close();
//                }
//                if (br!=null){
//                    br.close();
//                }
//                if (out!=null){
//                    out.close();
//                }
//            }catch (Exception ignored){
//                System.out.println(ignored);
//            }
//        }
//    }

    //aes-cbc-128加密 返回hex
    public static String Encrypt(String content) throws Exception {
        byte[] raw = KEY.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        System.out.println("加密后的值:"+Hex.encodeHexString(encrypted));
        return Hex.encodeHexString(encrypted);
        //return new Base64().encodeToString(encrypted);
    }

    //带参访问接口
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println("查询数据量的url:"+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }

    //获取总共有多少数据
    public static String getCount(String data){
        System.out.println("-------------------------------------------------------------");
        JSONObject jsonObject= JSONObject.parseObject(data);
        //System.out.println(jsonObject);
        JSONObject jsonData=jsonObject.getJSONObject("data");
        //System.out.println(jsonData);
        JSONObject page=jsonData.getJSONObject("page");
        System.out.println("数据总量:"+page.getString("count"));
        return page.getString("count");
    }

    //解析数据
    public static net.sf.json.JSONObject generateData(String data){
        JSONObject jsonObject= JSONObject.parseObject(data);
        JSONObject jsonData=jsonObject.getJSONObject("data");
        JSONArray jsonArray=JSONArray.fromObject(jsonData);
        net.sf.json.JSONObject json=jsonArray.getJSONObject(0);
        return json;
    }

//    //json 排序
//    public static JSONArray paixv(String jsonStr){
//        JSONObject originalJsonData = JSONObject.parseObject(jsonStr);
//        com.alibaba.fastjson.JSONArray dataArray = originalJsonData.getJSONArray("dataArray");
//        List<JSONObject> jsonObjectList = new ArrayList<>();
//        //将原始数据中的json数组中的json对象存到一个list集合中
//        for (Object o : dataArray) {
//            jsonObjectList.add((JSONObject) o);
//        }
//        //对集合中的json对象进行分组
//        //然后返回一个map集合，key代表组名，value代表该组中的数据
//        Map<String, List<JSONObject>> groupByLineNumData = jsonObjectList.stream().collect(Collectors.groupingBy(x -> x.getString("MarginPrice")));
//
//        //遍历所有的分组，对每组内的JSON对象进行排序
//        for (String s : groupByLineNumData.keySet()) {
//            List<JSONObject> list = groupByLineNumData.get(s);
//            JSONArray jsonSorted = jsonArraySort(list.toString());
//            //后续有对排序后的json对象的业务操作
//            //这里只将它输出
//            System.out.println(jsonSorted);
//        }
//
//    }

    /**
     * 对多个JOSN对象进行自定义字段排序
     *
     * @param jsonArr 要排序的JSONArray
     * @return 返回排序后的结果
     */
    private static JSONArray jsonArraySort(JSONArray jsonArr) {
        //com.alibaba.fastjson.JSONArray jsonArr = JSON.parseArray(jsonArrStr);
        JSONArray sortedJsonArray = new JSONArray();
        List<net.sf.json.JSONObject> jsonValues = new ArrayList<>();
        //存放json数组中的每个json对象
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }

        //对集合中的JOSN对象进行自定义排序
        jsonValues.sort((a, b) -> {
            Integer valA = a.getInt("MarginPrice");
            Integer valB = b.getInt("MarginPrice");
            return valB.compareTo(valA);
        });

        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }

    //生成excel
    public static net.sf.json.JSONObject toExcel(String src, net.sf.json.JSONObject json){
        //用来反馈函数调用结果
        net.sf.json.JSONObject result=new net.sf.json.JSONObject();
        try {
            //新建文件
            File file=new File(src);
            file.createNewFile();
            //创建工作簿
            OutputStream outputStream=new FileOutputStream(file);
            WritableWorkbook writableWorkbook= Workbook.createWorkbook(outputStream);
            //创建新一页
            WritableSheet sheet=writableWorkbook.createSheet("店铺",0);
            JSONArray jsonArray=json.getJSONArray("data");
            //单元格对象
            Label label;
            //列计数
            int column = 0;
            //将表头加到sheet中
            net.sf.json.JSONObject first=jsonArray.getJSONObject(0);
            //得到第一项的key集合
            Iterator<String>iterator=first.keys();
            //遍历key集合
            while(iterator.hasNext()){
                //得到key
                String key=(String)iterator.next();
                //第一个参数是单元格所在列，第二个参数是单元格所在行，第三个是值
                label=new Label(column++,0,key);
                sheet.addCell(label);
            }
            //遍历jsonArray
            for(int i=0;i<jsonArray.size();i++){
                //得到每项的数组
                net.sf.json.JSONObject item=jsonArray.getJSONObject(i);
                //得到key集合
                iterator=item.keys();
                //从第0列开始放
                column=0;
                while(iterator.hasNext()){
                    //获得key
                    String key=iterator.next();
                    //获得key对应的value
                    String value=item.getString(key);
                    //第一个参数是单元格所在列，第二个参数是单元格所在行，第三个是值
                    label=new Label(column++,(i+1),value);
                    //将单元格加到页
                    sheet.addCell(label);
                }
            }
            //加入到文件中
            writableWorkbook.write();
            //关闭文件释放资源
            writableWorkbook.close();
        }catch (Exception e){
            //将调用该函数的结果返回
            result.put("result","failed");
            //将调用该函数失败的原因返回
            result.put("reason",e.getMessage());
            return result;
        }
        result.put("result","successes");
        return result;
    }

}