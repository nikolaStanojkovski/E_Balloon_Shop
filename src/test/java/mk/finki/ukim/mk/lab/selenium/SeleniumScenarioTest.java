package mk.finki.ukim.mk.lab.selenium;


import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enums.Role;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;


    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m1", "m1");
            m2 = manufacturerService.save("m2", "m2");

            regularUser = this.userService.registerUser("nikola.stanojkovski", "ns", "ns", Role.ROLE_USER);
            adminUser = this.userService.registerUser("admin", "admin", "admin", Role.ROLE_ADMIN);

            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {

        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(0, 0, 0, 0, 0);

        // ...

    }

}

