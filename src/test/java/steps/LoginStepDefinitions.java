package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;

public class LoginStepDefinitions {
    WebDriver driver;
    String username;
    String password;

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        // Code to navigate to the home page
        driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/");
    }

    @When("User navigates to Login Page")
    public void user_navigates_to_login_page() {
        // Code to navigate to the login page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
    }

    @Then("User enters {string} and {string}")
    public void user_enters_username_and_password(String username, String password) throws IOException {
        this.username=username;
        this.password=password;
        //Either get data from the excel or data provider in cucumber feature file
//        getData();
        // Code to enter username and password
        driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"input-password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
    }

    @Then("User should get logged in")
    public void user_should_get_logged_in() {
        // Code to verify successful login
        String expectedPageTitle = "My Account";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle,actualPageTitle);
    }

    @And("Message displayed Login Successfully")
    public void message_displayed_login_successfully() {// Code to verify login success message
        WebElement myAccount = driver.findElement(By.xpath("//*[@id=\"content\"]/h2[1]"));
        if(myAccount.isDisplayed()){
            System.out.println("Login Successful!");
        }
        driver.quit();
    }

    public static void getData() throws IOException {
        FileInputStream file = new FileInputStream("utils\\Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int noOfRows = sheet.getLastRowNum();
        int noOfCols = sheet.getRow(0).getLastCellNum();

        for(int row=1;row<=noOfRows;row++){
            ArrayList<String> credentials = new ArrayList<>();
            XSSFRow currRow = sheet.getRow(row);
            for(int cell=0;cell<noOfCols;cell++){
                credentials.add(currRow.getCell(cell).toString());
            }
        }
        workbook.close();
        file.close();
    }
}

