import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Users {

    public static WebDriver driver;

    public String randomString(int kol) {

        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder sb = new StringBuilder(kol);
        Random random = new Random();

        for (int i = 0; i < kol; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        String output = sb.toString();
        return output;

    }

    public String randomInteger(int kol) {

        char[] chars = "1234567890".toCharArray();

        StringBuilder sb = new StringBuilder(kol);
        Random random = new Random();

        for (int i = 0; i < kol; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        String output = sb.toString();
        return output;

    }

    ThreadLocalRandom randomInt = ThreadLocalRandom.current();


    @Test
    public void login() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        // Test for login

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://users.bugred.ru/user/login/index.html");

        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("email123@gmail.com");
        driver.findElement(By.xpath("//div[@class='row']//div[1]//form[1]//table[1]//tbody[1]//tr[2]//td[2]//input[1]")).sendKeys("123");
        driver.findElement(By.xpath("//div[@class='row']//div[1]//form[1]//table[1]//tbody[1]//tr[3]//td[2]//input[1]")).click();

        Thread.sleep(1000);
        WebElement addUser = driver.findElement(By.xpath("//*[contains(text(), 'Добавить пользователя')]"));
        Assert.assertEquals(true, addUser.isDisplayed());

        // Test for search

        driver.findElement(By.xpath("//input[@placeholder='Введите email или имя']")).sendKeys("email123");
        driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[5]/td[1]/button")).click();

        Thread.sleep(1000);
        WebElement emailCheck = driver.findElement(By.xpath("//td[contains(text(),'email123@gmail.com')]"));
        Assert.assertEquals(true, emailCheck.isDisplayed());

        // Test for add new user

        driver.findElement(By.xpath("//a[@class='btn btn-danger']")).click();
        driver.findElement(By.xpath("//input[@name='noibiz_name']")).sendKeys("email1234");
        driver.findElement(By.xpath("//input[@name='noibiz_email']")).sendKeys("email1234@gmail.com");
        driver.findElement(By.xpath("//input[@name='noibiz_password']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@name='act_create']")).click();

        Thread.sleep(1000);
        emailCheck = driver.findElement(By.xpath("//td[contains(text(),'email1234@gmail.com')]"));
        Assert.assertEquals(true, emailCheck.isDisplayed());

        driver.findElement(By.xpath("//td[6]//a[1]")).click(); // Delete for test stability

        // Test for personal cabinet

        driver.findElement(By.xpath("//*[@id=\"fat-menu\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"fat-menu\"]/ul/li[1]/a")).click();

        Thread.sleep(1000);
        WebElement checkPersonal = driver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[3]/a"));
        Assert.assertEquals(true, checkPersonal.isDisplayed());

        driver.findElement(By.xpath("//input[@name='name']")).clear();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(randomString(10));

        Actions downArrow = new Actions(driver);
        downArrow.sendKeys(Keys.chord(Keys.DOWN)).perform();

        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys(String.valueOf(randomInt.nextInt(100000, 999999)));
        driver.findElement(By.xpath("//input[@name='date_start']")).sendKeys(String.valueOf(randomInt.nextInt(100000, 999999)));
        driver.findElement(By.xpath("//textarea[@name='hobby']")).clear();
        driver.findElement(By.xpath("//textarea[@name='hobby']")).sendKeys(randomString(10));
        driver.findElement(By.xpath("//input[@name='inn']")).clear();
        driver.findElement(By.xpath("//input[@name='inn']")).sendKeys(randomInteger(12));
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/form/table/tbody/tr[8]/td[2]/input")).click();

    }

}