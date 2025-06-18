import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DiscountTest {
    @Test

    public void test() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=111111&first_name=Test&last_name=Test&email=test%40test.com&password1=Test&password2=Test");
        String email = driver.findElement(
                By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        driver.get("https://sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        driver.get("https://sharelane.com/cgi-bin/add_to_cart.py?book_id=10");
        driver.get("https://sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("2000");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        String price = driver.findElement(By.xpath("//td[text()='10.00']")).getText();
        String discountPercent = driver.findElement(By.xpath("//b[text()='6']")).getText();
        String discountMoney = driver.findElement(By.xpath("//td[text()='1200.0']")).getText();
        String total = driver.findElement(By.xpath("//td[text()='21200.00']")).getText();
        softAssert.assertEquals(price, "10.00");
        softAssert.assertEquals(discountPercent, "6");
        softAssert.assertEquals(discountMoney, "1200.0");
        softAssert.assertEquals(total, "21200.00");
        driver.quit();
        softAssert.assertAll();
    }
}