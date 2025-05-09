package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class CommonMethods extends PageInitializer{
    public static WebDriver driver;

    public void openBrowserAndLaunchApplication() {
        //Declare the instance
       /* String browserName = ConfigReader.read("browser");*/
        switch (ConfigReader.read("browser")){
            case "Chrome":
                /*ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");*/
                driver=new ChromeDriver();//(options);
                break;
            case "FireFox":
                driver = new FirefoxDriver();
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }


        //maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //Navigate to https://www.syntaxprojects.com/synchronization-waits.php
        //take me to the url

        driver.get(ConfigReader.read("url"));

        initializerPageObjects();

        //*String url = ConfigReader.read(Constants.CONFIG_FILE_PATH, "url");
        //driver.get(url);
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);

    }

    public static WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    public static void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();

    }
    public JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    public void jsClick(WebElement element){getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public static void selectFromDropDown(String value, WebElement element){
        Select sel=new Select(element);
        sel.selectByValue(value);
    }

    public static void selectFromDropDown( WebElement element,String text){
        Select sel=new Select(element);
        sel.selectByVisibleText(text);
    }


    public static void selectFromDropDown( WebElement element,int index){
        Select sel=new Select(element);
        sel.selectByIndex(index);
    }
    public byte[] takeScreenshot(String fileName){
        //it accepts array of byte in cucumber for the screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH +
                            fileName+" "+
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picByte;
    }

    public String getTimeStamp(String pattern){
        //this method will return the timestamp which we will add in ss method
        Date date =new Date();
        //12-01-1992-21-32-34
        //yyyy-mm-dd-hh-mm-ss
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String generateUniqueEmployeeId() {
        // Pattern: MMddHHmmss (MonthDayHourMinuteSecond)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp; // Example: EMP0508143052
    }
    // Optional main method to test
    public static void main(String[] args) {
        String empId = generateUniqueEmployeeId();
        System.out.println("Generated Employee ID: " + empId);
    }





}



