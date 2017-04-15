import org.junit.Assert;
import org.junit.Test;

import static Modules.CheckInitializationShip.*;
import static Modules.CheckInitializationShip.checkProducts;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class TestInitializationShip {
    @Test
    public void checkNameTest(){
        Assert.assertEquals(false,checkName(""));
        Assert.assertEquals(true,checkName("Vlad"));
        Assert.assertEquals(true,checkName("123"));
    }

    @Test
    public void checkQuantityTest(){
        Assert.assertEquals(false,checkQuantity(""));
        Assert.assertEquals(false,checkQuantity("123"));
        Assert.assertEquals(false,checkQuantity("-12"));
        Assert.assertEquals(true,checkName("15"));
    }

    @Test
    public void checkTimeTest(){
        Assert.assertEquals(false,checkTime("158"));
        Assert.assertEquals(false,checkTime("-20"));
        Assert.assertEquals(true,checkTime("53"));
        Assert.assertEquals(false,checkTime(""));
    }

    @Test
    public void checkProductsTest(){
        Assert.assertEquals(false,checkProducts("Choose product"));
        Assert.assertEquals(true,checkProducts("Milk"));
        Assert.assertEquals(true,checkProducts("Meat"));
        Assert.assertEquals(true,checkProducts("Bread"));
        Assert.assertEquals(true,checkProducts("Potatoes"));
    }
}
