package au.com.dius.computerstore.cart;

import java.util.ArrayList;
import java.util.List;

import au.com.dius.computerstore.product.IProducts;

public class Cart {
	private List<IProducts> productsInCart = new ArrayList<>();
	
	
	public List<IProducts>  getProducts() {
		return productsInCart;
	}
	
	public void empty() {
		productsInCart = new ArrayList<>();
	}

}
