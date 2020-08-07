package base;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;

	public static String escapeUrl = "http://everestnational-qa/ESCAPE/Default.aspx";
	public static String userid = "tseelam";
	public static String password = "Tulsi@30";

	public static String dburl = "jdbc:sqlserver://enicdb-dev\\ENIC_QA;databaseName=EscapeDB;integratedSecurity=true";
	public static String dbUserName = "";
	public static String dbPassword = "";

	public static SimpleDateFormat htmlfolderFormat = new SimpleDateFormat("dd-MMM-yyyy") ;
	public static SimpleDateFormat htmlfileFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss") ;
	public static String htmlfolderName ;
	public static String htmlfileName ;
	public static long htmlfileNumber ;
	
	@BeforeSuite
	public void setUp() {
		htmlfolderName=	htmlfolderFormat.format(new Date());
		htmlfileName=htmlfileFormat.format(new Date());
		htmlfileNumber=System.currentTimeMillis();
		
		String htmlfpath = System.getProperty("user.dir")+"/HtmlReports/WebServiceAutomationReport"+htmlfileName+htmlfileNumber + ".html";

		htmlReporter = new ExtentHtmlReporter(htmlfpath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows 8 Server R2");
		extent.setSystemInfo("Host Name", "Rajeev Singh");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Rajeev Singh");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("WebService Automation POC Report");
		htmlReporter.config().setReportName("WebService Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

		driver = launchChrome();
		minimize();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	public static WebDriver launchChrome() {
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("test-type");
		Options.addArguments("disable-infobars");
		Options.addArguments("start-maximized");
		Options.addArguments("--js-flags=--expose-gc");
		Options.addArguments("--enable-precise-memory-info");
		Options.addArguments("--disable-popup-blocking");
		Options.addArguments("--disable-default-apps");
		return driver = new ChromeDriver(Options);
	}

	public static void launchTestNgReport() throws Exception {
		maximize();
		String tesngreportfile = "file:///D:/Selenium/workspace/Ws%20Testing/test-output/emailable-report.html";
		driver.get(tesngreportfile);
		screenshot();
		Thread.sleep(5000);
	}

	public static void launchExtentReport() throws Exception {
		String extentreprotfile = "file:///"+System.getProperty("user.dir")+"/HtmlReports/WebServiceAutomationReport"+htmlfileName+htmlfileNumber+".html";
		driver.get(extentreprotfile);
		screenshot();
	}
	
	
	public static void screenshot() throws Exception {
		
		
		try {
			SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss") ;
			SimpleDateFormat folderName = new SimpleDateFormat("yyyy-MMM-dd") ;
			
			long fileno = System.currentTimeMillis();
			
			String fpath = System.getProperty("user.dir")+"/Screenshot/"+folderName.format(new Date())+"/Pic "+fileName.format(new Date())+" "+fileno + ".png";
			
			File dfile = new File(fpath);
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, dfile);
			test.addScreenCaptureFromPath(fpath);
			} 
			catch (Exception e) {
			//e.printStackTrace(); 
				System.out.println("Exception occured during screenshot");
			}

			}

	public static void minimize() {
		driver.manage().window().setSize(new Dimension(10,10));
	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void verifyInEscape(String SubmissionNumberbr) throws Exception {
		
		System.out.println("\n Escape webportal Verification Started.....");
		
		maximize();
		driver.get(escapeUrl);
		screenshot();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[contains(@name,'ctl00$ContentPlaceHolder1$')][@type='text']")).sendKeys(userid);
		screenshot();
		driver.findElement(By.xpath("//input[contains(@name,'ctl00$ContentPlaceHolder1$')][@type='password']")).sendKeys(password);
		screenshot();
		driver.findElement(By.xpath("//*[@id='LoginButton']")).click();
		screenshot();
		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_tbSubmissionNumber']")).sendKeys(SubmissionNumberbr);
		screenshot();
		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btnSearch']")).click();

		if (driver.findElement(By.xpath("//*[contains(text(),'Search Criteria - All Business Segments')]")).isDisplayed()) 
		{
			System.out.println(SubmissionNumberbr + " Submission number found in Escape Portal");
		} else {
			System.out.println(SubmissionNumberbr + " Submission number not found in Escape Portal");
		}
		Thread.sleep(5000);
		screenshot();
		minimize();
	} // verify in escape close

	
public static void verifyInDatabase(String SubmissionNumberbr) throws ClassNotFoundException, SQLException {
	
	System.out.println("\n Escape Database Verification Started.....");
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(dburl, dbUserName, dbPassword);

		Statement stmt = con.createStatement();
		String query = "select Submission_Nbr from SUB_Submission order by Submission_Nbr desc";
		ResultSet res = stmt.executeQuery(query);

		boolean flag = false;
		String actualSubmissionNumberbr="";
		
		try {
			while (res.next()) 
			{
				actualSubmissionNumberbr=res.getString(1);
				if (SubmissionNumberbr.equals(actualSubmissionNumberbr)) 
				{
				flag = true;
				break;
				}
			}// end of while loop
		} catch (Exception e) {
			e.printStackTrace();
		} // end of catch method
		finally {
			if (flag) {
				System.out.println("\nGiven Submission Number -> "+SubmissionNumberbr+" matches with " + actualSubmissionNumberbr + " in Escape Database");
			} else {
				System.out.println("\nGiven Submission Number -> " + SubmissionNumberbr + " Not Found in the Escape Database");
			}
			// Close DB connection
			if (con != null) {
				con.close();
			}
		} // end of finally block

	} // end of method
	
	
	@AfterSuite
	public void tearDown() throws Exception {
		extent.flush();
		launchTestNgReport();
		launchExtentReport();
	}
}