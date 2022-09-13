package top.sonaradar.qust_noticeHelper.core;

import top.sonaradar.qust_noticeHelper.config.constManager;
import top.sonaradar.qust_noticeHelper.config.myConst;

public class autoRun {
    public void execute(){
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    while(true){
                        long interval = 0;
                        try{interval = new Long(constManager.getBuggerInterval());}catch (Exception e){}
                        if(interval<300){
                            interval = 300;
                        }
                        if(canRun()==true){
                            try{
                                ///System.out.println("execute bugger");
                                autoSelenium as = new autoSelenium();
                                as.execute();
                            }catch (Exception e){}
                        }else  {
                            System.out.println("无法执行爬虫脚本,原因:账户核验失败");
                        }
                        for(long i=1;i<=interval;i++){
                            if(myConst.timerInterval!= interval){
                                interval = myConst.timerInterval;
                            }
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
    public boolean canRun(){
        try{
            if(new Long(constManager.getQustUser()) == 0){
                return false;
            }
        }catch (Exception e){return false;}
        if(constManager.getMailUser().contains("@")==false){
            return false;
        }
        return true;
    }
}
