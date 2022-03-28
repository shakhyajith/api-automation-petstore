package base;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.Reporter;

public abstract class TestBase {
    private ExtentTest test;

    @BeforeClass
    public void createSuite() {
        String testClass = getClass().getName().split("\\.")[1];
        test = Reporter.createTest(testClass);
    }

    @AfterMethod
    public void attachNodes(ITestResult d) {
        Reporter.createNode(test, d.getMethod().getMethodName(), d.getStatus());
    }


    @AfterClass
    public void createReport() {
        Reporter.flush();
    }
}
