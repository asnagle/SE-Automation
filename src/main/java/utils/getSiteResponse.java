package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;


public class getSiteResponse {
	public static void validateLogin(WebDriver driver, String actualMsg, ExtentTest testrep) {
		
		
		String pageTitle = driver.getTitle();

        String ADMIN_TITLE = "Dashboard / nopCommerce administration";
        String WAIT_TITLE = "Just a moment...";
        String LOGIN_TITLE = "nopCommerce demo store. Login";

        String invalidUser = "Login was unsuccessful. Please correct the errors and try again. No customer account found".trim().replaceAll("\\s+", " ");
        String invalidPassword = "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect".trim().replaceAll("\\s+", " ");
        String humanVerification = "Verify you are human by completing the action below.".trim().replaceAll("\\s+", " ");
        String waitTime = "Verifying you are human. This may take a few seconds.".trim().replaceAll("\\s+", " ");

        Log.info("Title is: " + pageTitle);
        Log.info("Actual Message: " + actualMsg);

        if (ADMIN_TITLE.equals(pageTitle) && actualMsg.equals(ADMIN_TITLE)) {
            testrep.pass("Login Successful");
            Log.info("Test completed Successfully ");
        } else if (WAIT_TITLE.equals(pageTitle) && actualMsg.equals(humanVerification)) {
            testrep.warning("Bot verification required: " + humanVerification);
        } else if (WAIT_TITLE.equals(pageTitle) && actualMsg.equals(waitTime)) {
            testrep.warning("Bot verification in progress: " + waitTime);
        } else if ((WAIT_TITLE.equals(pageTitle) || LOGIN_TITLE.equals(pageTitle) || ADMIN_TITLE.equals(pageTitle))
                && actualMsg.equals(invalidUser)) {
            testrep.fail("Invalid username: " + invalidUser);
            throwFailure("Test failed due to Invalid Username/Account.");
        } else if ((WAIT_TITLE.equals(pageTitle) || LOGIN_TITLE.equals(pageTitle) || ADMIN_TITLE.equals(pageTitle))
                && actualMsg.equals(invalidPassword)) {
            testrep.fail("Invalid password: " + invalidPassword);
            throwFailure("Test failed due to Invalid Password.");
        } else {
            testrep.warning("Unrecognized login result. Manual check may be required.");
        }
    }

    public static void throwFailure(String message) {
        Log.info(message);
        Assert.fail(message);
        throw new AssertionError("Marking test as failed: " + message);
    }
    
    public static void validateInvalidLogin(WebDriver driver, String actualMsg, ExtentTest testrep) {
    	String pageTitle = driver.getTitle();

        String ADMIN_TITLE = "Dashboard / nopCommerce administration";
        String WAIT_TITLE = "Just a moment...";
        String LOGIN_TITLE = "nopCommerce demo store. Login";

        String invalidUser = "Login was unsuccessful. Please correct the errors and try again. No customer account found".trim().replaceAll("\\s+", " ");
        String invalidPassword = "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect".trim().replaceAll("\\s+", " ");
        String humanVerification = "Verify you are human by completing the action below.".trim().replaceAll("\\s+", " ");
        String waitTime = "Verifying you are human. This may take a few seconds.".trim().replaceAll("\\s+", " ");

        Log.info("Title is: " + pageTitle);
        Log.info("Actual Message: " + actualMsg);
        
        if (ADMIN_TITLE.equals(pageTitle) && actualMsg.equals(ADMIN_TITLE)) {
//          testrep.pass("Login Successful");
			Log.info("Test completed Successfully ");
			testrep.fail("Valid Credentials Entered..");
			Assert.fail("Test failed due to Valid Credentials.");
		} else if (WAIT_TITLE.equals(pageTitle) && actualMsg.equals(humanVerification)) {
			testrep.warning("Bot verification required: " + humanVerification);
		} else if (WAIT_TITLE.equals(pageTitle) && actualMsg.equals(waitTime)) {
			testrep.warning("Bot verification in progress: " + waitTime);
		} else if ((WAIT_TITLE.equals(pageTitle) || LOGIN_TITLE.equals(pageTitle) || ADMIN_TITLE.equals(pageTitle))
				&& actualMsg.equals(invalidUser)) {
			Log.info("Test completed Successfully ");
			testrep.pass("Invalid username: " + invalidUser);
			Log.info("Expected login to fail due to Invalid Username/Account.");
		} else if ((WAIT_TITLE.equals(pageTitle) || LOGIN_TITLE.equals(pageTitle) || ADMIN_TITLE.equals(pageTitle))
				&& actualMsg.equals(invalidPassword)) {
			Log.info("Test completed Successfully ");
			testrep.pass("Invalid password: " + invalidPassword);
			Log.info("Expected login to fail due to Invalid Password.");
		} else {
			testrep.warning("Unrecognized login result. Manual check may be required.");
		}
    }


}
