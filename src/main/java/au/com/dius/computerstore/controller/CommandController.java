package au.com.dius.computerstore.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import au.com.dius.computerstore.product.type.AppleProductsDetails;
import au.com.dius.computerstore.service.CheckoutService;
import au.com.dius.computerstore.service.PricingRulesService;

public class CommandController {
	
	static Scanner userInput = new Scanner(System.in);
	
	public static void main (String args[]){
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("******************************************************");
		System.out.println("****Welcome to DIUS Computer Store******" );
		System.out.println("******************************************************");
		
		startPurchase();
	}
	
	public static void startPurchase() {
		try {
			System.out.println("");
			System.out.println("");
			System.out.println("Do you want to proceed with the default Rules - [ Y / N ]" );
			System.out.println("");
			if(userInput.nextLine().trim().toUpperCase().equals("Y")) {
				PricingRulesService.initializeDefault();
			} else {
				PricingRulesService.initializeRules();
			}
			
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Please enter the Products in CSV format below " );
		
		AppleProductsDetails.listProducts();
		
		String productListCSV = userInput.nextLine();
		
		List<String> productList = Arrays.asList(productListCSV.split(","))
				.stream()
				.map(code -> code = code.trim().toUpperCase())
				.filter(code -> !code.equals("")).collect(Collectors.toList());
		
//		productList.forEach(System.out::println);
		
		CheckoutService checkout = new CheckoutService(PricingRulesService.getRules());
		
		for(String product : productList) {
			checkout.addProduct(product);
		}
			System.out.println(" TOTAL  :  " + checkout.calculatePrice());
		} catch (Exception  e) {
			System.out.println("WARNING : " + e.getMessage());
		} finally {
			startPurchase();
		}
		
		
		
	}

}
