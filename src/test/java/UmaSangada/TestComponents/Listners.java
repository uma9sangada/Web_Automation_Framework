package UmaSangada.TestComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import UmaSangada.Resourses.ExtentReportNG;

public class Listners extends BaseTest  implements ITestListener 
{
	ExtentTest  test;
	ExtentReports extent = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) 
	{
	test =extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);//unique thread id 
	}
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extenttest.get().log(Status.PASS, "Test passed");
		String Filepath = null;
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			Filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		}
		catch (IOException e1) 
		{
		e1.printStackTrace();
		}
		//result.getThrowable();
		extenttest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName() );
	
	}


	public void onTestFailure(ITestResult result)
	{
		extenttest.get().log(Status.FAIL, "Test fail");
		String Filepath = null;
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			Filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		}
		catch (IOException e1) 
		{
		e1.printStackTrace();
		}
		//test.fail(result.getThrowable());//replace text with extenttest.get()
		extenttest.get().fail(result.getThrowable());
		extenttest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName() );
	}

	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
