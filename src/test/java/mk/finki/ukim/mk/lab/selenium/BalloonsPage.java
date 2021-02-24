package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonsPage extends AbstractPage {

    @FindBy(css = "tr[class=balloon]")
    private List<WebElement> balloonRows;

    @FindBy(css = ".delete-balloon")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".order-balloon")
    private List<WebElement> orderButtons;

    @FindBy(css = ".edit-balloon")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-balloon-btn")
    private List<WebElement> addProductButton;

    public BalloonsPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public void assertElements(int balloonsNumber, int orders, int deleteButtons, int editButtons, int addButtons) {
        Assert.assertEquals("rows do not match", balloonsNumber, this.getBalloonRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add-to-order do not match", orders, this.getOrderButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddProductButton().size());
    }
}

