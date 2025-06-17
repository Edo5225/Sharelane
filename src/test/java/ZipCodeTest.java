import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ZipCodeTest {
    @Test
    public void test() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();

        WebElement firstName = driver.findElement(By.cssSelector("[name=first_name]"));
        Assert.assertTrue(firstName.isDisplayed(), "Мы не на странице регистрации");

        driver.findElement(By.cssSelector("[name=first_name]")).sendKeys("Dd");
        driver.findElement(By.cssSelector("[name=last_name]")).sendKeys("Aa");
        driver.findElement(By.cssSelector("[name=email]")).sendKeys("D.@gmail.com");
        driver.findElement(By.cssSelector("[name=password1]")).sendKeys("D!aa1");
        driver.findElement(By.cssSelector("[name=password2]")).sendKeys("D!aa1");

        driver.findElement(By.cssSelector("[value=Register]")).click();

        String confirmation_message = driver.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        Assert.assertEquals(confirmation_message, "Account is created!");

        driver.quit();

    }

}
