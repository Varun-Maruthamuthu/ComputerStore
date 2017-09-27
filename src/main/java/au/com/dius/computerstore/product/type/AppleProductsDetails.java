package au.com.dius.computerstore.product.type;

import java.util.Arrays;

import au.com.dius.computerstore.exception.InvalidProductCodeException;

public enum AppleProductsDetails {
	IPD("IPD", "Super iPad", 549.99),
	MBP("MBP", "MacBook Pro", 1399.99),
	ATV("ATV", "Apple TV", 109.50),
	VGA("VGA", "VGA adapter", 30.00)
	;
	
	private String code;
	private String name;
	private double price;

	AppleProductsDetails(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public static AppleProductsDetails getProductDetails(String productCode) {
		try {
		return valueOf(productCode);
		} catch (Exception e) {
			throw new InvalidProductCodeException(e.getMessage());
		}
	}
	
	public static void listProducts() {
	Arrays.asList(AppleProductsDetails.values()).stream()
	.forEach(action -> {
		System.out.println(action.getName() +" ==> "+ action.getCode());
		});
	System.out.println("");
	System.out.println("");
	}
}
