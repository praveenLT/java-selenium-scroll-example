package com.lambdatest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ScrollingExample {

    private RemoteWebDriver driver;
    private String Status = "failed";

    public void setup() throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "Extension Test");
        caps.setCapability("name", this.getClass().getName());
        caps.setCapability("plugin", "git-java");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    public void ScrollingExampleTest() throws InterruptedException {
        System.out.println("Loading Url");

        driver.get("https://lambdatest.com");

        // Locating element by link text 
        WebElement Element = driver.findElement(By.linkText("Book a Demo"));

        // Scrolling down the page till the element is found
        driver.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(1500);

        // Scrolling down by pixels
        driver.executeScript("window.scrollBy(0,-500)", "");

        Thread.sleep(1500);

        // Scrolling up by pixels
        driver.executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(1500);

        Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        ScrollingExample test = new ScrollingExample();
        test.setup();
        test.ScrollingExampleTest();
        test.tearDown();
    }

}