package au.com.dius.computerstore.service;

import java.util.Scanner;

import au.com.dius.computerstore.pricingrule.PricingRules;
import au.com.dius.computerstore.pricingrule.rules.BulkDiscountRule;
import au.com.dius.computerstore.pricingrule.rules.FreeProductRule;
import au.com.dius.computerstore.pricingrule.rules.ThreeForTwoRule;
import au.com.dius.computerstore.product.type.AppleProductsDetails;

public class PricingRulesService {
	
	private static PricingRules rules;
	static Scanner userInput = new Scanner(System.in);
	
	
	public static PricingRules getRules(){
		return rules;
	}
	
	public static void initializeDefault() {
		rules = new PricingRules();
		ThreeForTwoRule rule1 = new ThreeForTwoRule();
		rule1.registerProduct(AppleProductsDetails.ATV.getName());
		
		BulkDiscountRule rule2 = new BulkDiscountRule();
		rule2.registerProduct(AppleProductsDetails.IPD.getName());
		rule2.setThreshold(4);
		rule2.setDiscountPrice(100.00);
		
		FreeProductRule rule3 = new FreeProductRule();
		rule3.registerProduct(AppleProductsDetails.MBP.getName());
		
		rules.addRule(rule1);
		rules.addRule(rule2);
		rules.addRule(rule3);
		
	}
	
	
	public static void initializeRules() {
		rules = new PricingRules();
		String code ;
		ThreeForTwoRule rule1 = new ThreeForTwoRule();
		System.out.println("Enter the Product code to register ThreeForTwo deal ");
		AppleProductsDetails.listProducts();
		code = userInput.nextLine();
		rule1.registerProduct(AppleProductsDetails.getProductDetails(code.trim()).getName());
		
		BulkDiscountRule rule2 = new BulkDiscountRule();
		rule2.setThreshold(4);
		rule2.setDiscountPrice(100.00);
		System.out.println("Enter the Product code to register BulkDiscount deal of 100 $ less");		
		AppleProductsDetails.listProducts();
		code = userInput.nextLine();
		rule2.registerProduct(AppleProductsDetails.getProductDetails(code.trim()).getName());
		
		FreeProductRule rule3 = new FreeProductRule();
		System.out.println("Enter the Product code to register Free VGA deal");		
		AppleProductsDetails.listProducts();
		code = userInput.nextLine();
		rule3.registerProduct(AppleProductsDetails.getProductDetails(code.trim()).getName());
		
		rules.addRule(rule1);
		rules.addRule(rule2);
		rules.addRule(rule3);
		
	}
	
	
	
}
