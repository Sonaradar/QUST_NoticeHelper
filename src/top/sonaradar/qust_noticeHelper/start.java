package top.sonaradar.qust_noticeHelper;

import top.sonaradar.qust_noticeHelper.CMD.userGUI;
import top.sonaradar.qust_noticeHelper.config.constManager;
import top.sonaradar.qust_noticeHelper.core.*;

public class start {
    public static void main(String args[]){
        ///
        ///autoSelenium as = new autoSelenium();
        ///as.execute();
        news.init();
        constManager.init();
        constManager.autoLoad();
        autoRun ar = new autoRun();
        ar.execute();
        userGUI.run();

        ///dao.write("2","test","123","J:\\test.ini");
        ///System.out.println(dao.read("2","test","J:\\test.ini"));
        // 读取一般的属性文件
    }
}
