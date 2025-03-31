package org.uma.web.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.uma.web.pages.LandingPage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Web {
    protected static WebDriver driver; // Make driver static
    public LandingPage landingpage;

    public static WebDriver getDriver() { // Static getter for driver
        return driver;
    }

    public WebDriver Initializedriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream Fs = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\UmaSangada\\Resourses\\Global.properties");
        prop.load(Fs);

        String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
                : prop.getProperty("browser");

        if (browsername.contains("chrome")) {
            ChromeOptions option = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browsername.contains("headless")) {
                option.addArguments("headless");
            }
            driver = new ChromeDriver(option);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browsername.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    

    @AfterMethod(alwaysRun = true)
    public void closebrowse() throws IOException {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;

    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        File Source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(Source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}