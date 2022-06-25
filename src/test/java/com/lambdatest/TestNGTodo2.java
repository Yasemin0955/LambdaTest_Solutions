package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TestNGTodo2 {
    public RemoteWebDriver driver = null;
    String username = ConfigurationReader.get("username");
    String accessKey = ConfigurationReader.get("accessKey");

    public void setUp(String BrowserType, String Version, String Os, String TestName) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", Os);
        capabilities.setCapability("browserName", BrowserType);
        capabilities.setCapability("version", Version);
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("build", "First Build");
        capabilities.setCapability("name", TestName);
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);

        try {

            driver = new RemoteWebDriver(
                    new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
    }

    @Test
    public void test1() throws Exception {
        try {

            System.out.println("Test1 initiated");
            ArrayList<String> thirdVMProperties = FetchExcelData.getData("Chrome");

            setUp(thirdVMProperties.get(0), thirdVMProperties.get(1), thirdVMProperties.get(2), "First Test");

            driver.get("https://www.lambdatest.com/selenium-playground");
            driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).click();

            if (driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).getText()
                    .equalsIgnoreCase("Simple Form Demo"))
                Assert.assertTrue(true);

            else
                Assert.assertFalse(false);

            String ourMassage = "This is Test";
            driver.findElement(By.id("user-message")).clear();
            driver.findElement(By.id("user-message")).sendKeys(ourMassage);
            driver.findElement(By.id("showInput")).click();

            if (driver.findElement(By.id("message")).getText().equalsIgnoreCase(ourMassage)) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }

            driver.quit();
            System.out.println("Test1 is concluded");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test2() throws Exception {
        try {
            System.out.println("Test2 initiated");

            ArrayList<String> secondVMProperties = FetchExcelData.getData("MicrosoftEdge");

            setUp(secondVMProperties.get(0), secondVMProperties.get(1), secondVMProperties.get(2), "Second Test");

            driver.get("https://www.lambdatest.com/selenium-playground");

            driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();

            WebElement slider = driver.findElement(By.cssSelector("input[value='15']"));

            for (int i = 1; i <= 80; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }

            if (driver.findElement(By.xpath("//*[text()='95']")).getText().equalsIgnoreCase("95"))
                Assert.assertTrue(true);
            else
                Assert.assertTrue(false);

            driver.quit();
            System.out.println("Test2 is concluded");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test3() throws Exception {
        try {
            System.out.println("Test3 is initiated");
            ArrayList<String> firstVMProperties = FetchExcelData.getData("Chrome");

            setUp(firstVMProperties.get(0), firstVMProperties.get(1), firstVMProperties.get(2), "Third Test");

            driver.get("https://www.lambdatest.com/selenium-playground");

            driver.findElement(By.xpath("//a[contains(@href, 'input-form-demo')]")).click();
            Thread.sleep(6000L);

            driver.findElement(By.xpath("//a[contains(@href, 'input-form-demo')]")).click();

            driver.findElement(By.id("name")).sendKeys("yasemin");
            driver.findElement(By.id("inputEmail4")).sendKeys("zeytin0955@gmail.com");
            driver.findElement(By.xpath("//*[@type='password']")).sendKeys("samay0955");
            driver.findElement(By.id("company")).sendKeys("yasemin");
            driver.findElement(By.id("websitename")).sendKeys("yasemin.com");
            Select country = new Select(driver.findElement(By.name("country")));
            country.selectByVisibleText("United States");

            driver.findElement(By.id("inputCity")).sendKeys("Luton");
            driver.findElement(By.id("inputAddress1")).sendKeys("park street");
            driver.findElement(By.id("inputAddress2")).sendKeys("bedfordshire");
            driver.findElement(By.id("inputState")).sendKeys("road");
            driver.findElement(By.id("inputZip")).sendKeys("456543");

            driver.findElement(By.xpath("//*[text()='Submit']")).click();

            if (driver.findElement(By.xpath("//*[@class = 'success-msg hidden']")).getText()
                    .equalsIgnoreCase("Thanks for contacting us, we will get back to you shortly."))
                Assert.assertTrue(true);
            else
                Assert.assertTrue(false);

            driver.quit();
            System.out.println("Test3 is concluded");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}