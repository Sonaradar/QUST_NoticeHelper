package top.sonaradar.qust_noticeHelper.core;


import top.sonaradar.qust_noticeHelper.config.myConst;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class mailSender {
    static Authenticator auth = new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myConst.mailSenderAccount, myConst.mailSenderPassword);
        }
    };

    public static void send(String title,String text,String toMail){
        try {
        Properties props = new Properties();
        props.put("mail.smtp.host", myConst.mailSmtp);
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", myConst.mailSenderAccount);
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom();
        msg.setRecipients(Message.RecipientType.TO, toMail);
        msg.setSubject(title);
        msg.setSentDate(new Date());
        msg.setText("<html><body><span style='color:black;'>"+text+"</span></body></html>", "utf-8", "html");
        Transport.send(msg);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public static void boardcast(String title,String releaseTime,String releaseType,String releaseDepartment,String text){
        String preList = dao.read(dao.getMapPath()+"/data/boardcastList.json");
        String bList[] = preList.split(";");
        for(int i=0;i<bList.length;i++){
            ///System.out.println(bList[i]);
            send("[QUST Service]"+title,
                    ("公告信息标题:"+ title +"\n"+ "\n"+
                            "公告发布时间:" + releaseTime +"\n"+
                            "公告类型:" + releaseType +"\n"+
                            "公告发布部门:" + releaseDepartment +"\n"+ "\n" +
                            "公告内容:" + "\n"  + text + "\n" + "\n" +
                            "温馨提示:\n请勿回复此邮件，上述信息请以https://wvpn.qust.edu.cn为准.\n反馈信息请登录:http://www.sonaradar.top"+ "\n\n" +
                            "QUST Notice Service powered by Sonaradar Electronic Inc." + "\n" +
                            "© 2022 Sonaradar Electronic Inc.All rights reserved.").replace("\n","<br>"),
                    bList[i]
            );
        }
    }
}
