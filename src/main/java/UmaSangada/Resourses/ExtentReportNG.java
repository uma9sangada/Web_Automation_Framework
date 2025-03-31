package UmaSangada.Resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	 

	public static  ExtentReports getReportObject() {
		String Path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(Path);
		report.config().setDocumentTitle("Selenium  Framework results");
		report.config().setReportName("application E2e results ");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("runned by", "uma sangada");
		return extent;

	}

}
