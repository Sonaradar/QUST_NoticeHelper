package top.sonaradar.qust_noticeHelper.config;

import top.sonaradar.qust_noticeHelper.core.dao;

public class constManager {
    public static void init(){
        dao.createDirtory(dao.getMapPath()+"/data/config/");
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "username_qust" + ".json")==false){
            setQustUser(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "password_qust" + ".json")==false){
            setQustPassword(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "username_mail" + ".json")==false){
            setMailUser(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "password_mail" + ".json")==false){
            setMailPassword(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "smtp_mail" + ".json")==false){
            setMailSmtp(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "chromedrive" + ".json")==false){
            setChromeDrive(" ");
        }
        if(dao.fileExists(dao.getMapPath()+"/data/config/" + "buggerinterval" + ".json")==false){
            setBuggerInterval(" ");
        }
    }
    public static void setQustUser(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "username_qust" + ".json");
    }
    public static void setQustPassword(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "password_qust" + ".json");
    }
    public static void setMailUser(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "username_mail" + ".json");
    }
    public static void setMailPassword(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "password_mail" + ".json");
    }
    public static void setMailSmtp(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "smtp_mail" + ".json");
    }
    public static void setChromeDrive(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "chromedrive" + ".json");
    }
    public static void setBuggerInterval(String value){
        dao.write(value,dao.getMapPath()+"/data/config/" + "buggerinterval" + ".json");
    }

    public static String getQustUser(){
        return dao.read(dao.getMapPath()+"/data/config/" + "username_qust" + ".json");
    }
    public static String getQustPassword(){
        return dao.read(dao.getMapPath()+"/data/config/" + "password_qust" + ".json");
    }
    public static String getMailUser(){
        return dao.read(dao.getMapPath()+"/data/config/" + "username_mail" + ".json");
    }
    public static String getMailPassword(){
        return dao.read(dao.getMapPath()+"/data/config/" + "password_mail" + ".json");
    }
    public static String getMailSmtp(){
        return dao.read(dao.getMapPath()+"/data/config/" + "smtp_mail" + ".json");
    }
    public static String getChromeDrive(){
        return dao.read(dao.getMapPath()+"/data/config/" + "chromedrive" + ".json");
    }
    public static String getBuggerInterval(){
        return dao.read(dao.getMapPath()+"/data/config/" + "buggerinterval" + ".json");
    }
    public static void autoLoad(){
        myConst.userName = getQustUser();
        myConst.password = getQustPassword();
        myConst.mailSenderAccount = getMailUser();
        myConst.mailSenderPassword = getMailPassword();
        myConst.mailSmtp = getMailSmtp();
        myConst.chromePath = getChromeDrive();
        try{
            myConst.timerInterval = new Long(getBuggerInterval());
        }catch (Exception e){}
    }
}
