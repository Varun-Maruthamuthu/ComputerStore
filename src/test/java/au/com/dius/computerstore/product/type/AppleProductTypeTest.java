package au.com.dius.computerstore.product.type;

import org.junit.Assert;
import org.junit.Test;

import au.com.dius.computerstore.exception.InvalidProductCodeException;

public class AppleProductTypeTest {

    @Test
    public void whenProductCodeIsGivenThenReturnAppleProductsDetails() {
    	Assert.assertEquals(AppleProductsDetails.valueOf("ATV").getName(), AppleProductsDetails.ATV.getName());
    	Assert.assertEquals(AppleProductsDetails.valueOf("IPD").getName(), AppleProductsDetails.IPD.getName());
    	Assert.assertEquals(AppleProductsDetails.valueOf("MBP").getName(), AppleProductsDetails.MBP.getName());
    	Assert.assertEquals(AppleProductsDetails.valueOf("VGA").getName(), AppleProductsDetails.VGA.getName());

    }

    @Test (expected = InvalidProductCodeException.class)
    public void whenGetValueThenCorrectValue() {
    	AppleProductsDetails.getProductDetails("AVT1");
    }

}