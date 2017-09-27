package au.com.dius.computerstore.pricingrule.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.pricingrule.Rules;
import au.com.dius.computerstore.product.IProducts;
import au.com.dius.computerstore.product.Product;
import au.com.dius.computerstore.product.type.AppleProductsDetails;

public class FreeProductRule implements Rules{
	
	Map <String, Integer> countMap = new HashMap<>();
	Set <String> registeredProducts = new HashSet<>();

	@Override
	public void registerProduct(String product){
		registeredProducts.add(product);
	}

	@Override
	public IProducts execute(IProducts products, Cart cart) {
		if (registeredProducts.contains(products.getName()))
			cart.getProducts().addAll(getFreeProducts(products));
		return null;
	}
	
//TODO :  return the Free product as configurable w.r.t to the product .
	private Collection<? extends IProducts> getFreeProducts(IProducts products) {
		List<IProducts> freeProducts = new ArrayList<>() ;
		Product free;
		switch (AppleProductsDetails.valueOf(products.getCode())) {
		case ATV : free = new Product(AppleProductsDetails.VGA);
			free.setPrice(0);
			freeProducts.add(free);
			break;
		case IPD : free = new Product(AppleProductsDetails.VGA);
			free.setPrice(0);
			freeProducts.add(free);
			break;
		case MBP : free = new Product(AppleProductsDetails.VGA);
			free.setPrice(0);
			freeProducts.add(free);	
			break;
		
		}
		
		return freeProducts;
	}
}
