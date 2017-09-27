package au.com.dius.computerstore.product;

import au.com.dius.computerstore.product.type.AppleProductsDetails;

public class Product implements IProducts{

	private String code;
	private String name;
	private double price;
	
	public Product (AppleProductsDetails details) {
		this.code = details.getCode();
		this.name = details.getName();
		this.price = details.getPrice();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
		
	}

}
