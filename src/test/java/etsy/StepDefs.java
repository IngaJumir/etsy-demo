package etsy;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class StepDefs {
    @Given("^I am on the home page$")
    public void i_am_on_the_home_page(){
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get("http://etsy.com");
        
    }
    
    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String search){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search_query']")));
        element.sendKeys(search + Keys.ENTER);
    }
    
    @Then("^I should see the results$")
    public void i_should_see_the_results(){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }
    
    @Then("^I should see more results$")
    public void i_should_see_more_results(){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }
    
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
