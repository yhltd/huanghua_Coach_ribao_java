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
}