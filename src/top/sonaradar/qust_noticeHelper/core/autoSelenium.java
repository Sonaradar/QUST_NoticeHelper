package top.sonaradar.qust_noticeHelper.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import top.sonaradar.qust_noticeHelper.config.myConst;

public class autoSelenium {
    public void execute(){
        System.setProperty("webdriver.chrome.driver", myConst.chromePath);
        WebDriver driver = new ChromeDriver();
        driver.get(myConst.qustDomain);
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"un\"]")).sendKeys(myConst.userName);
        driver.findElement(By.xpath("//*[@id=\"pd\"]")).sendKeys(myConst.password);
        driver.findElement(By.xpath("//*[@id=\"index_login_btn\"]")).click();
        sleep(7000);
        for(int i=1;i<=10;i++){
            try{
                String newsTitle = driver.findElement(By.xpath("//*[@id=\"pimlistcontentdiv\"]/a["+i+"]")).getText().replace("\n"," ");
                String herf = driver.findElement(By.xpath("//*[@id=\"pimlistcontentdiv\"]/a["+i+"]")).getAttribute("href");
                driver.get(herf.replace("http://i.qust.edu.cn/tp_up/view?m=up","https://wvpn.qust.edu.cn/http/77726476706e69737468656265737421f9b95089342426557a1dc7af96/tp_up/view?m=up"));
                sleep(6000);
                String releaseTime = driver.findElement(By.xpath("//*[@id=\"pim_container\"]/div/div/div/div[1]/p[3]/span[2]")).getText();
                String releaseDepartment  = driver.findElement(By.xpath("//*[@id=\"pim_container\"]/div/div/div/div[1]/p[2]/span[2]")).getText();
                String releaseType = driver.findElement(By.xpath("//*[@id=\"pim_container\"]/div/div/div/div[1]/p[1]/span[2]")).getText();
                String newsText = driver.findElement(By.xpath("//*[@id=\"pim_content\"]")).getText();
                if(news.pushNews(newsTitle,releaseTime,releaseType,releaseDepartment,newsText)==true){
                    news.saveNews(newsTitle,releaseTime,releaseType,releaseDepartment,newsText,dao.getMapPath()+"/data/history/" + newsTitle.replaceAll("[^\u4E00-\u9FA5]", "") + ".json");
                    mailSender.boardcast(newsTitle,releaseTime,releaseType,releaseDepartment,newsText);
                }
                driver.get(myConst.qustDomain);
                sleep(4000);
            }catch (Exception e){}
        }
        driver.close();
    }
    public static void sleep(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static boolean loginQust(String username,String password){
        System.setProperty("webdriver.chrome.driver", myConst.chromePath);
        WebDriver driver = new ChromeDriver();
        driver.get(myConst.qustDomain);
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"un\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"pd\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"index_login_btn\"]")).click();
        sleep(7000);
        try{
            if(driver.findElement(By.xpath("//*[@id=\"page-content\"]/div/ul[1]/li[1]/div/div[1]/div/div[1]/h4")).getText().contains("校")==true){
                driver.close();
                return true;
            }
        }catch (Exception e){}
        driver.close();
        return false;
    }

}
