package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.Type;
import mk.finki.ukim.mk.lab.model.exceptions.*;
import mk.finki.ukim.mk.lab.repository.InDatabase.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.InDatabase.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.implementation.BalloonServiceImpl;
import mk.finki.ukim.mk.lab.service.implementation.ManufacturerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaveBalloonTest {

    @Mock
    private BalloonRepository balloonRepository;
    @Mock
    private ManufacturerRepository manufacturerRepository;

    private BalloonService balloonService;
    private ManufacturerService manufacturerService;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

        Manufacturer manufacturer = new Manufacturer("Adidas", "11th Avenue Street");
        Balloon balloon = new Balloon("Red Balloon", "This is red balloon",
                manufacturer, Type.Arrow);

        Mockito.when(this.manufacturerRepository.save(Mockito.any(Manufacturer.class))).thenReturn(manufacturer);
        Mockito.when(this.balloonRepository.save(Mockito.any(Balloon.class))).thenReturn(balloon);

        this.balloonService = Mockito.spy(new BalloonServiceImpl(this.balloonRepository));
        this.manufacturerService = Mockito.spy(new ManufacturerServiceImpl(this.manufacturerRepository));
    }

    @Test
    public void testSuccessAdd() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");
        Balloon balloon = this.balloonService.save("Red Balloon", "This is red balloon", null
                    ,manufacturer ,Type.Arrow).get();

        Mockito.verify(this.manufacturerService).save("Adidas", "11th Avenue Street");
        Mockito.verify(this.balloonService).save("Red Balloon", "This is red balloon", null,
                manufacturer, Type.Arrow);


        Assert.assertNotNull("Balloon is null", balloon);
        Assert.assertEquals("balloon name do not mach", "Red Balloon", balloon.getName());
        Assert.assertEquals("balloon description do not mach", "This is red balloon", balloon.getDescription());
        Assert.assertEquals("balloon type do not mach", Type.Arrow, balloon.getType());
        Assert.assertEquals("balloon manufacturer do not mach", "Adidas", balloon.getManufacturer().getName());
    }


    @Test
    public void testNullName() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");

        Assert.assertThrows("BalloonNullNameException expected",
                BalloonNullNameException.class,
                () -> this.balloonService.save(null, "This is red balloon", null,
                        manufacturer, Type.Arrow));
        Mockito.verify(this.balloonService).save(null, "This is red balloon", null,
                manufacturer, Type.Arrow);
    }

    @Test
    public void testEmptyName() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");

        Assert.assertThrows("BalloonEmptyNameException expected",
                BalloonEmptyNameException.class,
                () -> this.balloonService.save("", "This is red balloon", null,
                        manufacturer, Type.Arrow));
        Mockito.verify(this.balloonService).save("", "This is red balloon", null,
                manufacturer, Type.Arrow);
    }


    @Test
    public void testNullDescription() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");

        Assert.assertThrows("BalloonNullDescriptionException expected",
                BalloonNullDescriptionException.class,
                () -> this.balloonService.save("Red Balloon", null, null,
                        manufacturer, Type.Arrow));
        Mockito.verify(this.balloonService).save("Red Balloon", null, null,
                manufacturer, Type.Arrow);
    }

    @Test
    public void testEmptyDescription() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");

        Assert.assertThrows("BalloonEmptyDescriptionException expected",
                BalloonEmptyDescriptionException.class,
                () -> this.balloonService.save("Red Balloon", "", null,
                        manufacturer, Type.Arrow));
        Mockito.verify(this.balloonService).save("Red Balloon", "", null,
                manufacturer, Type.Arrow);
    }

    @Test
    public void testManufacturerNotFound() {

        Manufacturer manufacturer = null;

        Assert.assertThrows("ManufacturerNotFoundException expected",
                ManufacturerNotFoundException.class,
                () -> this.balloonService.save("Red Balloon", "This is red balloon", null,
                        manufacturer, Type.Arrow));
        Mockito.verify(this.balloonService).save("Red Balloon", "This is red balloon", null,
                manufacturer, Type.Arrow);
    }

    @Test
    public void testNullType() {

        Manufacturer manufacturer = this.manufacturerService.save("Adidas", "11th Avenue Street");

        Assert.assertThrows("BalloonTypeNullException expected",
                BalloonTypeNullException.class,
                () -> this.balloonService.save("Red Balloon", "This is red balloon", null,
                        manufacturer, null));
        Mockito.verify(this.balloonService).save("Red Balloon", "This is red balloon", null,
                manufacturer, null);
    }

}
