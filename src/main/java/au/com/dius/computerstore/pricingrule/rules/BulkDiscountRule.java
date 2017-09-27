package au.com.dius.computerstore.pricingrule.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.pricingrule.Rules;
import au.com.dius.computerstore.product.IProducts;

public class BulkDiscountRule implements Rules{

	Map <IProducts, Integer> countMap = new HashMap<>();
	Set <String> registeredProducts = new HashSet<>();
	
	int threshold;
	double discountPrice;
	
	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	
	
	@Override
	public void registerProduct(String product){
		registeredProducts.add(product);
	}

	@Override
	public IProducts execute(IProducts products, Cart cart) {
		
		if(countMap.containsKey(products.getName())) {
			countMap.put(products, countMap.get(products).intValue()+1);
			} else if(registeredProducts.contains(products.getName())){
				countMap.put(products, 1);
			}
		
		long count = countMap
				.entrySet()
				.stream()
				.filter(entryset -> registeredProducts.contains(products.getName()))
				.mapToInt(mapper -> mapper.getValue())
				.sum(); 
		
		
		if(count == threshold) {
			cart.getProducts()
			.stream()
			.filter( cartProduct -> cartProduct.getName().equals(products.getName()) && products.getPrice() > 100.00)
			.forEach(product -> {product.setPrice(product.getPrice() - discountPrice);});
			products.setPrice(products.getPrice() - discountPrice);
			
		} else if( count > threshold ) {
			products.setPrice(products.getPrice() - discountPrice);
		}
		
		return products;
	}

	
}
