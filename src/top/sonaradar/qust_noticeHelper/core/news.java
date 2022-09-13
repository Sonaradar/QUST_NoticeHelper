package top.sonaradar.qust_noticeHelper.core;

public class news {
    public static void init(){
        dao.createDirtory(dao.getMapPath()+"/data/current/");
        dao.createDirtory(dao.getMapPath()+"/data/history/");
        if(dao.fileExists(dao.getMapPath()+"/data/boardcastList.json")==false){
            dao.write("",dao.getMapPath()+"/data/boardcastList.json");
        }
    }
    private static boolean pushNews_v1(String newsTitle,String releaseTime,String releaseType,String releaseDepartment,String newsText){
        for(int i=1;i<=10;i++){
            if(dao.fileExists(dao.getMapPath()+"/data/current/"+ i + ".json")==false){
                saveNews(newsTitle,releaseTime,releaseType,releaseDepartment,newsText,dao.getMapPath()+"/data/current/"+ i + ".json");
                return false;
            }
        }
        for(int i=1;i<=10;i++){
            ///System.out.println(newsTitle);
            ///System.out.println(getNews_title(dao.getMapPath()+"/data/current/"+ i + ".json"));
            if(newsTitle.equals(getNews_title(dao.getMapPath()+"/data/current/"+ i + ".json"))==true){
                return false;
            }
        }
        for(int i=1;i<=9;i++){
            dao.renameFile(dao.getMapPath()+"/data/current/"+ i + ".json",dao.getMapPath()+"/data/current/"+ (i+1) + ".json");
        }
        saveNews(newsTitle,releaseTime,releaseType,releaseDepartment,newsText,dao.getMapPath()+"/data/current/" + 1 + ".json");
        return true;
    }
    public static boolean pushNews(String newsTitle,String releaseTime,String releaseType,String releaseDepartment,String newsText){
        for(int i=1;;i++){
            if(newsTitle.equals(getNews_title(dao.getMapPath()+"/data/current/"+ i + ".json"))==true){
                return false;
            }
            if(dao.fileExists(dao.getMapPath()+"/data/current/"+ i + ".json")==false){
                saveNews(newsTitle,releaseTime,releaseType,releaseDepartment,newsText,dao.getMapPath()+"/data/current/"+ i + ".json");
                return true;
            }
        }
    }
    public static void saveNews(String newsTitle,String releaseTime,String releaseType,String releaseDepartment,String newsText,String path){
        String str = "<newsTitle:"+newsTitle+">\n"+
                "<releaseTime:"+releaseTime+">\n"+
                "<releaseType:"+releaseType+">\n"+
                "<releaseDepartment:"+releaseDepartment+">\n"+
                "<newsText:"+newsText+">";
        dao.write(str,path);
    }
    public static String getNews_title(String path){
        String newsTitle = dao.read(path);
        return dao.getSubString(newsTitle,"<newsTitle:",">");
    }
    public static String getNews_releaseTime(String path){
        String newsTitle = dao.read(path);
        return dao.getSubString(newsTitle,"<releaseTime:",">");
    }
    public static String getNews_text(String path){
        String newsTitle = dao.read(path);
        return dao.getSubString(newsTitle,"<newsText:",">");
    }
}
