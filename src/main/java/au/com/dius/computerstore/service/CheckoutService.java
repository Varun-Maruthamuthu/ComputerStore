package au.com.dius.computerstore.service;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.exception.EmptyCartException;
import au.com.dius.computerstore.pricingrule.PricingRules;
import au.com.dius.computerstore.product.IProducts;
import au.com.dius.computerstore.product.Product;
import au.com.dius.computerstore.product.type.AppleProductsDetails;

public class CheckoutService {
	
	private PricingRules pricingRules;
	
	private Cart cart = new Cart();

	public CheckoutService(PricingRules pricingRules) {
		super();
		this.pricingRules = pricingRules;
	}
	
	public void addProduct(String product) {
		addProduct(new Product(AppleProductsDetails.getProductDetails(product)));
	}
	
	public void addProduct(IProducts product) {
		pricingRules.applyRules(product, cart);
		cart.getProducts().add(product);
		
	}
	
	public double calculatePrice() throws EmptyCartException{
		if(cart.getProducts().size() == 0)
			throw new EmptyCartException("Cart is EMPTY !!!!");
			
		
		billReport();
		double total = cart.getProducts()
				.stream()
//				.filter(product -> product.getPrice() > 0.0)
				.mapToDouble(product -> product.getPrice())
				.sum();
		
		cart.empty();
		return total;
	}
	
	public void billReport() {
		
		cart.getProducts()
			.stream()
			.forEach(product -> {
				System.out.printf(" %s  X  %f \n", product.getName(), product.getPrice());
			});
		
		System.out.println("==================================");
	}
	
	public int getProductCount() {
		return cart.getProducts().size();
	}
}
