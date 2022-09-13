package top.sonaradar.qust_noticeHelper.core;

import java.io.*;

public class dao {
        public static Boolean write(String value,String path){
            FileOutputStream fileOutputStream = null;
            File file = new File(path);
            try {
                if(file.exists()){
                    //判断文件是否存在，如果不存在就新建一个txt
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(value.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        public static String read(String path){
            File file = new File(path);
            if(file.isFile() && file.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuffer sb = new StringBuffer();
                    String text = null;
                    while((text = bufferedReader.readLine()) != null){
                        sb.append(text);
                    }
                    return sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "";
        }
        public static String getMapPath(){
            return System.getProperty("user.dir");
        }
        public static void createDirtory(String path){
            new File(path).mkdirs();
        }
        public static boolean fileExists(String path){
            File file = new File(path);
            return file.exists();
        }
    //取中间文本
    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
    public static boolean renameFile(String oPath,String cPath){
        File oldName = new File(oPath);
        File newName = new File(cPath);
        return oldName.renameTo(newName);
    }
}
