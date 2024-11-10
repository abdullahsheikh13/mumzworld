package resources;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG implements IReporter {
	 private ExtentReports extent;

	    @Override
	    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
	        // Initialize ExtentReports with ExtentSparkReporter
	        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(outputDirectory + File.separator + "Extent.html");
	        sparkReporter.config().setDocumentTitle("Automation Test Report");
	        sparkReporter.config().setReportName("Test Results");

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

	        for (ISuite suite : suites) {
	            Map<String, ISuiteResult> result = suite.getResults();

	            for (ISuiteResult r : result.values()) {
	                ITestContext context = r.getTestContext();
	                buildTestNodes(context.getPassedTests(), Status.PASS);
	                buildTestNodes(context.getFailedTests(), Status.FAIL);
	                buildTestNodes(context.getSkippedTests(), Status.SKIP);
	            }
	        }

	        extent.flush();
	    }

	    private void buildTestNodes(IResultMap tests, Status status) {
	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                ExtentTest test = extent.createTest(result.getMethod().getMethodName());

	                test.getModel().setStartTime(getTime(result.getStartMillis()));
	                test.getModel().setEndTime(getTime(result.getEndMillis()));

	                for (String group : result.getMethod().getGroups()) {
	                    test.assignCategory(group);
	                }

	                if (result.getThrowable() != null) {
	                    test.log(status, result.getThrowable());
	                } else {
	                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
	                }
	            }
	        }
	    }

	    private Date getTime(long millis) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(millis);
	        return calendar.getTime();
	    }
}

