package top.sonaradar.qust_noticeHelper.CMD;

import top.sonaradar.qust_noticeHelper.config.constManager;
import top.sonaradar.qust_noticeHelper.core.autoSelenium;
import top.sonaradar.qust_noticeHelper.core.dao;

import java.util.Scanner;

public class userGUI {
    static Scanner sc = new Scanner(System.in);
    public static void run(){
        mainpage();
    }
    public static void mainpage(){
        System.out.println("===========================================");
        System.out.println("       QUST NOTICE HELPER - SERVER");
        System.out.println("               1.0.O-SNAPSHOT");
        System.out.println("===========================================");
        System.out.println("信息面板");
        try{
            if(new Long(constManager.getQustUser()) == 0){
                System.out.println("QUST网上门户核验状态:未核验");
            }else{
                System.out.println("QUST网上门户核验状态:已核验 (用户:"+constManager.getQustUser()+")");
            }
        }catch (Exception e){System.out.println("QUST网上门户核验状态:未核验");}
        if(constManager.getMailUser().contains("@")==false){
            System.out.println("邮箱核验状态:未核验");
        }else{
            System.out.println("邮箱核验状态:已核验 (用户:"+constManager.getMailUser()+")");
        }
        System.out.println("===========================================");
        System.out.println("1.登录QUST网上门户");
        System.out.println("2.登录邮箱");
        System.out.println("3.设置广播邮箱");
        System.out.println("4.设置爬虫速度");
        System.out.println("5.设置ChromeDrive路径");
        System.out.print("请输入您需要操作的序号:");
        switch (sc.nextInt()){
            case 1:f_loginQust();break;
            case 2:f_loginMail();break;
            case 3:f_boardcastMail();break;
            case 4:f_buggerInterval();break;
            case 5:f_chromeDrivePath();break;
            default:break;
        }
        mainpage();
    }
    public static void f_loginQust(){
        constManager.autoLoad();
        ///初始化
        System.out.print("请输入QUST账号:");
        String username = sc.next();
        System.out.print("请输入QUST密码:");
        String password = sc.next();
        System.out.println("正在核验账户");
        if (autoSelenium.loginQust(username,password)==true){
            System.out.println("QUST网上门户账户核验成功,用户名:"+username);
            constManager.setQustUser(username);
            constManager.setQustPassword(password);
            return;
        }
        System.out.println("QUST网上门户账户核验失败!");
        return;
    }
    public static void f_loginMail(){
        System.out.print("请输入邮箱账户:");
        String username = sc.next();
        System.out.print("请输入邮箱密码:");
        String password = sc.next();
        System.out.print("请输入stmp服务器地址:");
        String smtp = sc.next();
        constManager.setMailUser(username);
        constManager.setMailPassword(password);
        constManager.setMailSmtp(smtp);
        System.out.println("邮箱账户核验成功,用户名:"+username);
        return;
    }
    public static void f_boardcastMail(){
        String preList = dao.read(dao.getMapPath()+"/data/boardcastList.json");
        String bList[] = preList.split(";");
        System.out.println("广播邮箱列表");
        for(int i=0;i<bList.length;i++){
            System.out.println("NO:" + i + ",MailAddress:" + bList[i]);
        }
        System.out.println("1.添加邮箱");
        System.out.println("2.删除邮箱");
        System.out.println("3.返回");
        System.out.print("请输入您需要操作的序号:");
        switch (sc.nextInt()){
            case 1:
                System.out.print("请输入添加的邮箱:");
                String str = "";
                for(int i=0;i<bList.length;i++){
                    str = str + bList[i] + ";";
                }
                str = str + sc.next();
                dao.write(str,dao.getMapPath()+"/data/boardcastList.json");
                System.out.println("添加成功!");
                break;
            case 2:
                System.out.print("请输入删除的邮箱的编号:");
                int deleteMail = sc.nextInt();
                String str2 = "";
                for(int i=0;i<bList.length-1;i++){
                    if(i-1==deleteMail){continue;}
                    str2 = str2 + bList[i] + ";";
                }
                if(deleteMail!=bList.length-1){
                    str2 = str2 + bList[bList.length-1];
                }
                dao.write(str2,"删除成功!");
                break;
            default:break;
        }
    }
    public static void f_buggerInterval(){
        System.out.print("请输入爬虫间隔(单位为秒):");
        constManager.setBuggerInterval(String.valueOf(sc.nextInt()));
        System.out.println("爬虫间隔时间设置为:" + constManager.getBuggerInterval() + "秒");
        return;
    }
    public static void f_chromeDrivePath(){
        System.out.print("请输入ChromeDrive路径(如G:/ChromeDrive/105/chromedriver.exe):");
        constManager.setChromeDrive(sc.next());
        System.out.println("ChromeDrive路径设置为:" + constManager.getChromeDrive());
        return;
    }
}
