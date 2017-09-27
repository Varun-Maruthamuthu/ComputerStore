package au.com.dius.computerstore.pricingrule;

import au.com.dius.computerstore.cart.Cart;
import au.com.dius.computerstore.product.IProducts;

public interface Rules {

	public IProducts execute(IProducts products, Cart cart);
	public void registerProduct(String product);

}
