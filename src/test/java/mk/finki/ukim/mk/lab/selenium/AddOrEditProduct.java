package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditProduct extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement type;
    private WebElement submit;

    public AddOrEditProduct(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage addProduct(WebDriver driver, String name, String description, String manufacturer, String type) {
        get(driver, "/balloons/add-balloon");
        AddOrEditProduct addOrEditProduct = PageFactory.initElements(driver, AddOrEditProduct.class);
        addOrEditProduct.name.sendKeys(name);
        addOrEditProduct.description.sendKeys(description);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
        addOrEditProduct.type.click();
        addOrEditProduct.type.findElement(By.xpath("//option[. = '" + type + "']")).click();

        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

}
