package au.com.dius.computerstore.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.computerstore.exception.EmptyCartException;
import au.com.dius.computerstore.pricingrule.PricingRules;
import au.com.dius.computerstore.pricingrule.rules.BulkDiscountRule;
import au.com.dius.computerstore.pricingrule.rules.FreeProductRule;
import au.com.dius.computerstore.product.type.AppleProductsDetails;

import static au.com.dius.computerstore.product.type.AppleProductsDetails.ATV;
import static au.com.dius.computerstore.product.type.AppleProductsDetails.IPD;
import static au.com.dius.computerstore.product.type.AppleProductsDetails.MBP;
import static au.com.dius.computerstore.product.type.AppleProductsDetails.VGA;


public class CheckoutServiceTest {
	
	private CheckoutService  checkoutService;
	private PricingRulesService  pricingService;
	
	@Before
	public void setup() {
		pricingService.initializeDefault();
		checkoutService = new CheckoutService(pricingService.getRules());
	}
	
	@Test
	public void testCalculateTotal() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(MBP.getCode());
		checkoutService.addProduct(VGA.getCode());
		Assert.assertEquals(5, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals(2089.48, total, 2);
	}
	
	
	@Test
	public void testTwoForThreeRuleTotal() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		Assert.assertEquals(3, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals(ATV.getPrice() *2 , total, 2);
	}
	
	
	@Test
	public void testBulkPurchaseDiscountBeforeThresholdTotal() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		
		Assert.assertEquals(3, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
//		3 < threshold .. so no discount
		
		Assert.assertEquals(IPD.getPrice() * 3, total, 2);
	}
	
	@Test
	public void testBulkPurchaseDiscountTotal() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		
		Assert.assertEquals(4, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		
//		4 == threshold .. so  discount available !!
		Assert.assertEquals((IPD.getPrice()-100) * 4, total, 2);
	}
	
	@Test
	public void testBulkPurchaseDiscountAferThresholdTotal() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		
		Assert.assertEquals(6, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
//		6 > threshold .. so  discount available !!
		Assert.assertEquals((IPD.getPrice()-100) * 6, total, 2);
	}
	
	@Test
	public void testFreeProductTotalandProductCount() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		
		checkoutService.addProduct(ATV.getCode());
		Assert.assertEquals(1, checkoutService.getProductCount());
		Assert.assertEquals(ATV.getPrice(), checkoutService.calculatePrice(), 2);

		checkoutService.addProduct(IPD.getCode());
		Assert.assertEquals(1, checkoutService.getProductCount());
		Assert.assertEquals(IPD.getPrice(), checkoutService.calculatePrice(), 2);
		
		checkoutService.addProduct(VGA.getCode());
		Assert.assertEquals(1, checkoutService.getProductCount());
		Assert.assertEquals(VGA.getPrice(), checkoutService.calculatePrice(), 2);
		
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(MBP.getCode());
		Assert.assertEquals(2, checkoutService.getProductCount());
		Assert.assertEquals(MBP.getPrice(), checkoutService.calculatePrice(), 2);
	}
	
	
	@Test
	public void testSequnetialProductsOrderInCart() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(IPD.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(MBP.getCode());
		checkoutService.addProduct(MBP.getCode());
		checkoutService.addProduct(MBP.getCode());
		checkoutService.addProduct(MBP.getCode());
		checkoutService.addProduct(VGA.getCode());
		checkoutService.addProduct(VGA.getCode());
		checkoutService.addProduct(VGA.getCode());
		checkoutService.addProduct(VGA.getCode());
		
		
		Assert.assertEquals(20, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
	}
	
	
	@Test
	public void testDynamicPricingRulesUpdateforIPD() throws EmptyCartException {
		checkoutService = new CheckoutService(pricingService.getRules());
		
		checkoutService.addProduct(IPD.getCode());
		Assert.assertEquals(1, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals(IPD.getPrice(), total, 2);
		
//		Update the pricing rules to add IPD also to provide free VGA
		PricingRules rules = pricingService.getRules();
		FreeProductRule rule = new FreeProductRule();
		rule.registerProduct(AppleProductsDetails.IPD.getName());
		rules.addRule(rule);
		checkoutService = new CheckoutService(rules);
		
		checkoutService.addProduct(IPD.getCode());
		Assert.assertEquals(2, checkoutService.getProductCount());
		total = checkoutService.calculatePrice();
		Assert.assertEquals(IPD.getPrice(), total, 2);
	}
	
	@Test
	public void testDynamicPricingRulesUpdateforATV() throws EmptyCartException {
//		Update the pricing rules to add ATV also to provide free VGA
		PricingRules rules = pricingService.getRules();
		FreeProductRule rule = new FreeProductRule();
		rule.registerProduct(AppleProductsDetails.ATV.getName());
		rules.addRule(rule);
		checkoutService = new CheckoutService(rules);
		
		checkoutService.addProduct(ATV.getCode());
		Assert.assertEquals(2, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals(ATV.getPrice(), total, 2);
	}
		
	@Test
	public void testBulkDiscountPricingRulesUpdateforATV() throws EmptyCartException {
//		Update the pricing rules to add ATV also for Bulk discount
//		PricingRules rules = pricingService.getRules();
		PricingRules rules = new PricingRules();
		BulkDiscountRule rule = new BulkDiscountRule();
		rule.registerProduct(AppleProductsDetails.ATV.getName());
		rule.setDiscountPrice(10.00);
		rule.setThreshold(4);
		rules.addRule(rule);
		checkoutService = new CheckoutService(rules);
		
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		checkoutService.addProduct(ATV.getCode());
		
		Assert.assertEquals(4, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals((ATV.getPrice() - 10.00) * 4  , total, 2);
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInputException() throws EmptyCartException {
//		Update the pricing rules to add ATV also for Bulk discount
		checkoutService = new CheckoutService(pricingService.getRules());
		
		checkoutService.addProduct("ATV1");
		
		Assert.assertEquals(4, checkoutService.getProductCount());
		double total = checkoutService.calculatePrice();
		Assert.assertEquals((ATV.getPrice() - 10.00) * 4  , total, 2);
		
	}
	
	
	@Test(expected = EmptyCartException.class)
	public void testEmptyCartException() throws EmptyCartException {
//		Update the pricing rules to add ATV also for Bulk discount
		checkoutService = new CheckoutService(pricingService.getRules());
		checkoutService.calculatePrice();
		
	}
}
