//package org.uma.web.utilities;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.markuputils.CodeLanguage;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//import io.restassured.http.Header;
//
//public class ReportManager implements ITestListener {
//
//    private static ExtentReports extentReports;
//    private ExtentSparkReporter sparkReporter;
//    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
//
//    @Override
//    public void onStart(ITestContext testContext) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String formattedTime = dateTimeFormatter.format(localDateTime);
//        String reportName = "TestReport" + formattedTime + ".html";
//        String fullReportPath = System.getProperty("user.dir") + "\\test-reports\\" + reportName;
//
//        sparkReporter = new ExtentSparkReporter(fullReportPath);
//        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
//        sparkReporter.config().setReportName("Pet Store Users API");
//
//        extentReports = new ExtentReports();
//        extentReports.attachReporter(sparkReporter);
//        extentReports.setSystemInfo("Application", "Pest Store Users API");
//        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
//        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
//        extentReports.setSystemInfo("Environment", "QA");
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
//        extentTest.set(test);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        logPassDetails("Test Passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        logFailureDetails(result.getThrowable().getMessage());
//        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
//        stackTrace = stackTrace.replaceAll(",", "<br>");
//        String formattedTrace = "<details>\n" +
//                "    <summary>Click Here To See Exception Logs</summary>\n" +
//                "    " + stackTrace + "\n" +
//                "</details>\n";
//        logExceptionDetails(formattedTrace);
//    }
//
//    public static void logPassDetails(String log) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
//        }
//    }
//
//    public static void logFailureDetails(String log) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.fail(MarkupHelper.createLabel(log, ExtentColor.RED));
//        }
//    }
//
//    public static void logExceptionDetails(String log) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.fail(log);
//        }
//    }
//
//    public static void logInfoDetails(String log) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.info(MarkupHelper.createLabel(log, ExtentColor.GREY));
//        }
//    }
//
//    public static void logJson(String json) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
//        }
//    }
//
//    public static void logHeaders(List<Header> headersList) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            String[][] arrayHeaders = headersList.stream()
//                    .map(header -> new String[]{header.getName(), header.getValue()})
//                    .toArray(String[][]::new);
//            test.info(MarkupHelper.createTable(arrayHeaders));
//        }
//    }
//
//    public static void logWarningDetails(String log) {
//        ExtentTest test = extentTest.get();
//        if (test != null) {
//            test.warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        if (extentReports != null) {
//            extentReports.flush();
//        }
//    }
//}