package au.com.dius.computerstore.pricingrule;

import java.util.HashSet;
import java.util.Set;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.product.IProducts;

public class PricingRules {

	Set <Rules> rules = new HashSet<>();
	
	public PricingRules() {
		
	}
	
	public PricingRules(Set <Rules> rule) {
		this.rules.addAll(rule) ;
	}
	
	public void addRule(Rules rule) {
		rules.add(rule);
	}
	
	public IProducts applyRules(IProducts product, Cart cart) {
		
		for( Rules rule : rules) {
			apply(rule, product, cart);
		}
			
		return product;
	}

	private IProducts apply(Rules rule, IProducts product, Cart cart) {
//		rule.execute(product);
		rule.execute(product, cart);
		return product;
	}
	
}
