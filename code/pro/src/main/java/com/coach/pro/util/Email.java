package com.coach.pro.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author wanghui
 * @date 2021/09/05 14:08
 */
public class Email {
    // 初始化参数
    private static Properties prop;
    // 发件人
    private static InternetAddress sendMan=null;

    static {
        File param=new File("C:\\coach\\addressee\\senderPeizhi.txt");
        String zhuji = "";
        int duankou=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(param));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(s.substring(0,2).equals("主机")){
                    zhuji=s.substring(3);
                }else if(s.substring(0,2).equals("端口")){
                    duankou = Integer.parseInt(s.substring(3));
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        File senderFile=new File("C:\\coach\\addressee\\sender.txt");
        String sender = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(senderFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(sender.equals("")){
                    sender=s;
                }else{
                    sender = sender + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        prop = new Properties();
        prop.put("mail.transport.protocol", "smtp"); // 协议
        prop.put("mail.smtp.host", zhuji); // 主机
        prop.put("mail.smtp.port", duankou); // 端口
        prop.put("mail.smtp.auth", "true"); // 用户密码认证
        prop.put("mail.debug", "true"); // 调试模式
        try {
            sendMan = new InternetAddress(sender);
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }
    }

    public void retailShopSend(String title,String filename,String wenben,String nfilename) throws Exception {
        //获取记事本内容
        File file=new File("C:\\coach\\addressee\\retailShop.txt");
        String result = "";
        try{
             BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
             String s = null;
             while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    if(result.equals("")){
                        result=s;
                    }else{
                        result = result + "," +s;
                    }

                 }
             br.close();
         }catch(Exception e){
             e.printStackTrace();
         }
        // 1. 创建邮件会话
        Session session = Session.getDefaultInstance(prop);
        // 2. 创建邮件对象
        MimeMessage message=new MimeMessage(session);
        // 3. 设置参数：标题、发件人、收件人、发送时间、内容
        message.setSubject(title);
        message.setSender(sendMan);
        //message.setRecipient(RecipientType.TO, new InternetAddress("1435329071@qq.com"));
        //多个抄送人
        String[] shoujianren=result.split(",");
        //String[] shoujianren = {"305004215@qq.com","1435329071@qq.com"};
        //String[] shoujianren = {"sxu4@coach.com","azhao@tapestry.com","rluo1@tapestry.com","tchen4@tapestry.com","wying@coach.com","slei@coach.com","vxie@coach.com","hyuan1@coach.com","sarah@rehub.tech"};

        // 构建一个群发地址数组
        InternetAddress[] adr = new InternetAddress[shoujianren.length];
        for (int i = 0; i < shoujianren.length; i++) {
            adr[i] = new InternetAddress(shoujianren[i]);
        }
        // Message的setRecipients方法支持群发。。注意:setRecipients方法是复数和点 到点不一样
        message.setRecipients(Message.RecipientType.CC, adr);
        message.setSentDate(new Date());


        /*
         * 带附件(图片)邮件开发
         */
        // 构建一个总的邮件块
        MimeMultipart mixed = new MimeMultipart("mixed");
        // ---> 总邮件快，设置到邮件对象中
        message.setContent(mixed);
        // 左侧： （文本+图片资源）
        MimeBodyPart left = new MimeBodyPart();
        // 右侧： 附件
        MimeBodyPart right = new MimeBodyPart();
        // 设置到总邮件块
        mixed.addBodyPart(left);
        mixed.addBodyPart(right);

        /****** 附件 ********/
        String attr_path =filename;
//        String attr_path = this.getClass().getResource("").getPath();
        DataSource attr_ds = new FileDataSource(new File(attr_path));
        DataHandler attr_handler = new DataHandler(attr_ds);
        right.setDataHandler(attr_handler);
        right.setFileName(nfilename);

        /*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
        // 4.1 构建一个多功能邮件块
        MimeMultipart related = new MimeMultipart("related");
        // ----> 设置到总邮件快的左侧中
        left.setContent(related);

        // 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
        MimeBodyPart content = new MimeBodyPart();
        MimeBodyPart resource = new MimeBodyPart();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        // 设置具体内容: b.文本
        content.setContent(wenben, "text/html;charset=UTF-8");
        resource.setContent("", "text/html;charset=UTF-8");

        related.addBodyPart(content);
        related.addBodyPart(resource);

        // 5. 发送
        Transport trans = session.getTransport();
        //获取发件人邮箱和密码
        File senderFile=new File("C:\\coach\\addressee\\sender.txt");
        File pwdFile=new File("C:\\coach\\addressee\\senderPwd.txt");
        String sender = "";
        String pwd="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(senderFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(sender.equals("")){
                    sender=s;
                }else{
                    sender = sender + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(pwdFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(pwd.equals("")){
                    pwd=s;
                }else{
                    pwd = pwd + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        trans.connect(sender, pwd);
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();
    }

    public void retailSkuSend(String title,String filename,String wenben,String nfilename) throws Exception {
        //获取记事本内容
        File file=new File("C:\\coach\\addressee\\retailSku.txt");
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(result.equals("")){
                    result=s;
                }else{
                    result = result + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        // 1. 创建邮件会话
        Session session = Session.getDefaultInstance(prop);
        // 2. 创建邮件对象
        MimeMessage message=new MimeMessage(session);
        // 3. 设置参数：标题、发件人、收件人、发送时间、内容
        message.setSubject(title);
        message.setSender(sendMan);
        //message.setRecipient(RecipientType.TO, new InternetAddress("1435329071@qq.com"));
        //多个抄送人
        //String[] shoujianren = {"305004215@qq.com","1435329071@qq.com"};
        String[] shoujianren=result.split(",");
        // 构建一个群发地址数组
        InternetAddress[] adr = new InternetAddress[shoujianren.length];
        for (int i = 0; i < shoujianren.length; i++) {
            adr[i] = new InternetAddress(shoujianren[i]);
        }
        // Message的setRecipients方法支持群发。。注意:setRecipients方法是复数和点 到点不一样
        message.setRecipients(Message.RecipientType.CC, adr);
        message.setSentDate(new Date());


        /*
         * 带附件(图片)邮件开发
         */
        // 构建一个总的邮件块
        MimeMultipart mixed = new MimeMultipart("mixed");
        // ---> 总邮件快，设置到邮件对象中
        message.setContent(mixed);
        // 左侧： （文本+图片资源）
        MimeBodyPart left = new MimeBodyPart();
        // 右侧： 附件
        MimeBodyPart right = new MimeBodyPart();
        // 设置到总邮件块
        mixed.addBodyPart(left);
        mixed.addBodyPart(right);

        /****** 附件 ********/
        String attr_path =filename;
//        String attr_path = this.getClass().getResource("").getPath();
        DataSource attr_ds = new FileDataSource(new File(attr_path));
        DataHandler attr_handler = new DataHandler(attr_ds);
        right.setDataHandler(attr_handler);
        right.setFileName(nfilename);

        /*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
        // 4.1 构建一个多功能邮件块
        MimeMultipart related = new MimeMultipart("related");
        // ----> 设置到总邮件快的左侧中
        left.setContent(related);

        // 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
        MimeBodyPart content = new MimeBodyPart();
        MimeBodyPart resource = new MimeBodyPart();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        // 设置具体内容: b.文本
        content.setContent(wenben, "text/html;charset=UTF-8");
        resource.setContent("", "text/html;charset=UTF-8");

        related.addBodyPart(content);
        related.addBodyPart(resource);

        // 5. 发送
        Transport trans = session.getTransport();
        //获取发件人邮箱和密码
        File senderFile=new File("C:\\coach\\addressee\\sender.txt");
        File pwdFile=new File("C:\\coach\\addressee\\senderPwd.txt");
        String sender = "";
        String pwd="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(senderFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(sender.equals("")){
                    sender=s;
                }else{
                    sender = sender + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(pwdFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(pwd.equals("")){
                    pwd=s;
                }else{
                    pwd = pwd + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(sender+","+pwd);
        trans.connect(sender, pwd);
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();
    }

    public void outletShopSend(String title,String filename,String wenben,String nfilename) throws Exception {
        //获取记事本内容
        File file=new File("C:\\coach\\addressee\\outletShop.txt");
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(result.equals("")){
                    result=s;
                }else{
                    result = result + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        // 1. 创建邮件会话
        Session session = Session.getDefaultInstance(prop);
        // 2. 创建邮件对象
        MimeMessage message=new MimeMessage(session);
        // 3. 设置参数：标题、发件人、收件人、发送时间、内容
        message.setSubject(title);
        message.setSender(sendMan);
        //message.setRecipient(RecipientType.TO, new InternetAddress("1435329071@qq.com"));
        //多个抄送人
        String[]shoujianren=result.split(",");
        //String[] shoujianren = {"305004215@qq.com","1435329071@qq.com"};
        //String[] shoujianren = {"sxu4@coach.com","azhao@tapestry.com","rluo1@tapestry.com","tchen4@tapestry.com","wying@coach.com","slei@coach.com","vxie@coach.com","hyuan1@coach.com","sarah@rehub.tech"};
        // 构建一个群发地址数组
        InternetAddress[] adr = new InternetAddress[shoujianren.length];
        for (int i = 0; i < shoujianren.length; i++) {
            adr[i] = new InternetAddress(shoujianren[i]);
        }
        // Message的setRecipients方法支持群发。。注意:setRecipients方法是复数和点 到点不一样
        message.setRecipients(Message.RecipientType.CC, adr);
        message.setSentDate(new Date());


        /*
         * 带附件(图片)邮件开发
         */
        // 构建一个总的邮件块
        MimeMultipart mixed = new MimeMultipart("mixed");
        // ---> 总邮件快，设置到邮件对象中
        message.setContent(mixed);
        // 左侧： （文本+图片资源）
        MimeBodyPart left = new MimeBodyPart();
        // 右侧： 附件
        MimeBodyPart right = new MimeBodyPart();
        // 设置到总邮件块
        mixed.addBodyPart(left);
        mixed.addBodyPart(right);

        /****** 附件 ********/
        String attr_path =filename;
//        String attr_path = this.getClass().getResource("").getPath();
        DataSource attr_ds = new FileDataSource(new File(attr_path));
        DataHandler attr_handler = new DataHandler(attr_ds);
        right.setDataHandler(attr_handler);
        right.setFileName(nfilename);

        /*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
        // 4.1 构建一个多功能邮件块
        MimeMultipart related = new MimeMultipart("related");
        // ----> 设置到总邮件快的左侧中
        left.setContent(related);

        // 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
        MimeBodyPart content = new MimeBodyPart();
        MimeBodyPart resource = new MimeBodyPart();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        // 设置具体内容: b.文本
        content.setContent(wenben, "text/html;charset=UTF-8");
        resource.setContent("", "text/html;charset=UTF-8");

        related.addBodyPart(content);
        related.addBodyPart(resource);

        // 5. 发送
        Transport trans = session.getTransport();
        //获取发件人邮箱和密码
        File senderFile=new File("C:\\coach\\addressee\\sender.txt");
        File pwdFile=new File("C:\\coach\\addressee\\senderPwd.txt");
        String sender = "";
        String pwd="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(senderFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(sender.equals("")){
                    sender=s;
                }else{
                    sender = sender + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(pwdFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(pwd.equals("")){
                    pwd=s;
                }else{
                    pwd = pwd + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        trans.connect(sender, pwd);
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();
    }

    public void outletSkuSend(String title,String filename,String wenben,String nfilename) throws Exception {
        //获取记事本内容
        File file=new File("C:\\coach\\addressee\\outletSku.txt");
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(result.equals("")){
                    result=s;
                }else{
                    result = result + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        // 1. 创建邮件会话
        Session session = Session.getDefaultInstance(prop);
        // 2. 创建邮件对象
        MimeMessage message=new MimeMessage(session);
        // 3. 设置参数：标题、发件人、收件人、发送时间、内容
        message.setSubject(title);
        message.setSender(sendMan);
        //message.setRecipient(RecipientType.TO, new InternetAddress("1435329071@qq.com"));
        //多个抄送人
        String[] shoujianren =result.split(",");
        //String[] shoujianren = {"305004215@qq.com","1435329071@qq.com"};
        //String[] shoujianren = {"ybozec@coach.com", "jchang@coach.com", "llucioni@coach.com", "rli3@tapestry.com", "CC_Ecommerce@coach.com", "czhu1@coach.com", "AsiaOutletBuyers@coach.com", "czhang4@coach.com", "itang@coach.com", "stang@coach.com", "mdebortolialbricci@coach.com", "cjiang1@coach.com", "bkuah@coach.com", "rluo1@tapestry.com", "tchen4@tapestry.com", "wying@coach.com", "slei@coach.com", "vxie@coach.com", "hyuan1@coach.com", "sarah@rehub.tech"};

        // 构建一个群发地址数组
        InternetAddress[] adr = new InternetAddress[shoujianren.length];
        for (int i = 0; i < shoujianren.length; i++) {
            adr[i] = new InternetAddress(shoujianren[i]);
        }
        // Message的setRecipients方法支持群发。。注意:setRecipients方法是复数和点 到点不一样
        message.setRecipients(Message.RecipientType.CC, adr);
        message.setSentDate(new Date());
        /*
         * 带附件(图片)邮件开发
         */
        // 构建一个总的邮件块
        MimeMultipart mixed = new MimeMultipart("mixed");
        // ---> 总邮件快，设置到邮件对象中
        message.setContent(mixed);
        // 左侧： （文本+图片资源）
        MimeBodyPart left = new MimeBodyPart();
        // 右侧： 附件
        MimeBodyPart right = new MimeBodyPart();
        // 设置到总邮件块
        mixed.addBodyPart(left);
        mixed.addBodyPart(right);

        /****** 附件 ********/
        String attr_path =filename;
//        String attr_path = this.getClass().getResource("").getPath();
        DataSource attr_ds = new FileDataSource(new File(attr_path));
        DataHandler attr_handler = new DataHandler(attr_ds);
        right.setDataHandler(attr_handler);
        right.setFileName(nfilename);

        /*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
        // 4.1 构建一个多功能邮件块
        MimeMultipart related = new MimeMultipart("related");
        // ----> 设置到总邮件快的左侧中
        left.setContent(related);

        // 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
        MimeBodyPart content = new MimeBodyPart();
        MimeBodyPart resource = new MimeBodyPart();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        // 设置具体内容: b.文本
        content.setContent(wenben, "text/html;charset=UTF-8");
        resource.setContent("", "text/html;charset=UTF-8");

        related.addBodyPart(content);
        related.addBodyPart(resource);

        // 5. 发送
        Transport trans = session.getTransport();
        //获取发件人邮箱和密码
        File senderFile=new File("C:\\coach\\addressee\\sender.txt");
        File pwdFile=new File("C:\\coach\\addressee\\senderPwd.txt");
        String sender = "";
        String pwd="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(senderFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(sender.equals("")){
                    sender=s;
                }else{
                    sender = sender + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(pwdFile));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                if(pwd.equals("")){
                    pwd=s;
                }else{
                    pwd = pwd + "," +s;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        trans.connect(sender, pwd);
        trans.sendMessage(message, message.getAllRecipients());
        trans.close();
    }

}
