package au.com.dius.computerstore.pricingrule.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.pricingrule.Rules;
import au.com.dius.computerstore.product.IProducts;

public class ThreeForTwoRule implements Rules{
	
	Map <String, Integer> countMap = new HashMap<>();
	Set <String> registeredProducts = new HashSet<>();

	@Override
	public void registerProduct(String product){
		registeredProducts.add(product);
	}

	@Override
	public IProducts execute(IProducts products, Cart cart) {
		
		if(countMap.containsKey(products.getName())) {
			countMap.put(products.getName(), countMap.get(products.getName()).intValue()+1);
			} else if(registeredProducts.contains(products.getName())){
				countMap.put(products.getName(), 1);
			}
		
		long count = countMap
				.entrySet()
				.stream()
				.filter(entryset -> registeredProducts.contains(products.getName()))
				.mapToInt(mapper -> mapper.getValue())
				.sum(); 
		
		
		if(count == 3) {
			products.setPrice(0);
			countMap.put(products.getName(), 0);
		}
		return products;
	}
}
