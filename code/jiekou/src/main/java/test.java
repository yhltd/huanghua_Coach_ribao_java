import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.ResourceBundle;



/**
 * @author wanghui
 * @date 2021/08/19 13:43
 */
public class test {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("mail");
    private static final String sendFrom = bundle.getString("email.from");
    private static final String username = bundle.getString("username");
    private static final String password = bundle.getString("password");
    private static final String host = bundle.getString("email.host");

    public static void main(String []as )throws IOException {
        //https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb
        //https://api.heweather.net/s6/weather/gird-minute

        String urlParam="https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb";
        //System.out.println(sendPost(urlParam));
        System.out.println(sendGet(urlParam));
        //String content=sendGet(urlParam);
        //sendEmail("1435329071@qq.com","标题",content);
    }

    public static String sendPost(String urlParam)throws IOException{
        //创建HttpClient实例
        HttpClient httpClient=new HttpClient();
        //设置HttpClient连接主机服务器超时时间：以毫秒为单位1000ms=1s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        //创建post请求方法实例
        PostMethod postMethod=new PostMethod(urlParam);
        //设置get请求超时时间,value以毫秒为单位
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,10000);
        //设置请求头
        postMethod.addRequestHeader("Content-Type","application/json");
        //执行post
        httpClient.executeMethod(postMethod);
        //获取返回数据
        String result=postMethod.getResponseBodyAsString();
        //释放http连接
        postMethod.releaseConnection();

        return result;
    }

    public static String sendGet(String urlParam) throws IOException{
        //创建HttpClient实例
        HttpClient httpClient=new HttpClient();
        //设置HttpClient连接主机服务器超时时间：以毫秒为单位1000ms=1s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        //创建get请求方法实例
        GetMethod getMethod=new GetMethod(urlParam);
        //设置get请求超时时间,value以毫秒为单位
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,10000);
        //设置请求头
        getMethod.addRequestHeader("Content-Type","application/json");
        //执行get
        httpClient.executeMethod(getMethod);
        //获取返回数据
        String result=getMethod.getResponseBodyAsString();
        //释放http连接
        getMethod.releaseConnection();

        return result;
    }

    public static void sendEmail(String someone, String subject, String content){
        Properties props = new Properties();
        props.setProperty("mail.host", host);
        props.setProperty("mail.smtp.auth", "true");

        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sendFrom));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(someone));
            //message.setRecipients(RecipientType.TO,InternetAddress.parse("测试的接收的邮件多个以逗号隔开"));
            try {
                message.setSubject(subject);
                message.setContent(content,"text/html;charset=UTF-8");
                Transport.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

//json数据格式
//    {"errno":0,"errmsg":"",
//            "data":{
//                "data":[
//                        {
//                            "id":65533,
//                            "ViewTime":"2021-08-24T21:40:02.000Z",
//                            "ItemCode":"612453560631","TaskId":47,
//                            "Brand":"Coach/蔻驰","SkuId":11829,
//                            "UserSKUCode":"91215 B4OOH",
//                            "ProductName":"【直营】COACH蔻驰 女包tabby潮人同款腋下包牛皮中号手提单肩包-tmall.hk天猫国际",
//                            "AssetId":2305523,"SKUDesc":"",
//                            "SKUshortName":"",
//                            "SKUSeries":"91215 B4OOH",
//                            "SKUModel":"",
//                            "SKUSpec":"",
//                            "URL":"https://detail.tmall.hk/hk/item.htm?id=612453560631&ns=1&abbucket=12",
//                            "Channel":"天猫",
//                            "ShopId":85520,
//                            "ShopName":"天猫国际时尚直营",
//                            "IsAuthor":null,
//                            "ShopLab":null,
//                            "OriginalPrice":4950,
//                            "ActivityPrice":2599,
//                            "GuidancePrice":4500,
//                            "FinalPrice":2499,
//                            "MarginPrice":-2001,
//                            "MarginPercent":"0.5552999973297119",
//                            "MarkdownPercent":"0.4447000026702881",
//                            "InsertDate":"2021-08-24T21:40:02.000Z",
//                            "IsbreakPrice":1,
//                            "Tariff":"",
//                            "IsTariff":null,
//                            "TariffPrice":0,
//                            "AllPromotion":"满500减100",
//                            "UsePromotion":"满500减100",
//                            "SuperPromotion":"",
//                            "SellerLocation":"浙江 杭州",
//                            "ScrollSales":40,
//                            "CommentNum":552,
//                            "StockNum":74,
//                            "StockStatus":"有货",
//                            "CreateTime":"2021-08-25T03:10:00.000Z"
//                        },
//                        {
//                            "id":65534,
//                            "ViewTime":"2021-08-24T20:27:06.000Z",
//                            "ItemCode":"68750322240",
//                            "TaskId":47,
//                            "Brand":"Coach/蔻驰",
//                            "SkuId":11829,
//                            "UserSKUCode":"91215 B4OOH",
//                            "ProductName":"\t\nCOACH 蔻驰 奢侈品 女士专柜款Tabby系列大号拼色酒神包经典标志涂层帆布配麂皮单肩斜挎包 【W】卡其配黑91215B4OOH 其他"
//                            ,"AssetId":2381968,
//                            "SKUDesc":"",
//                            "SKUshortName":"",
//                            "SKUSeries":"91215 B4OOH",
//                            "SKUModel":"",
//                            "SKUSpec":"",
//                            "URL":"https://item.jd.com/68750322240.html",
//                            "Channel":"京东",
//                            "ShopId":14885,
//                            "ShopName":"clim海外旗舰店",
//                            "IsAuthor":null,
//                            "ShopLab":"跨境",
//                            "OriginalPrice":8379,
//                            "ActivityPrice":3329,
//                            "GuidancePrice":4500,
//                            "FinalPrice":3329,
//                            "MarginPrice":-1171,
//                            "MarginPercent":"0.739799976348877",
//                            "MarkdownPercent":"0.26020002365112305",
//                            "InsertDate":"2021-08-24T20:27:06.000Z",
//                            "IsbreakPrice":1,
//                            "Tariff":"Y",
//                            "IsTariff":"商家承担",
//                            "TariffPrice":0,
//                            "AllPromotion":"",
//                            "UsePromotion":"",
//                            "SuperPromotion":"",
//                            "SellerLocation":null,
//                            "ScrollSales":null,
//                            "CommentNum":null,"StockNum":null,
//                            "StockStatus":"无货",
//                            "CreateTime":"2021-08-25T03:10:00.000Z"
//                        }]
//        ,"page":{
//                    "count":22721,"page":1,"size":3
//                }
//    }}

//json数据格式
//    {"errno":0,"errmsg":"",
//            "data":{
//                "data":[
//                        {
//                          key:value
//                        },
//                        {
//                          key:value
//                        }]
//        ,"page":{
//                    "count":22721,"page":1,"size":3
//                }
//    }}



}