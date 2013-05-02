package com.ombillah.ecom4j.webapp.springmvc;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ombillah.ecom4j.domain.CartItem;
import com.ombillah.ecom4j.domain.Product;
import com.ombillah.ecom4j.domain.ShoppingCart;
import com.ombillah.ecom4j.service.ProductService;


/**
 * Controller to handle Cart funtionality.
 * @author Oussama M Billah
 *
 */
@Controller
@SessionAttributes("shoppingCart")
public class CartController {
	
	@Resource(name="productService")
	private ProductService productService;

	
	@ModelAttribute("shoppingCart")
	public ShoppingCart getShoppingCart() {
		return new ShoppingCart();
	}
	@RequestMapping(value = "/viewcart.do",  method = RequestMethod.GET)
	public String showCart(@ModelAttribute("shoppingCart") ShoppingCart cart) {
		return "shoppingCart";
	}
	
	@RequestMapping(value = "/addtocart.do",  method = RequestMethod.POST)
	public String addProductToCart(
			@ModelAttribute("shoppingCart") ShoppingCart cart,
			@RequestParam(value="productId", required=true) final Long productId) {
		
		CartItem item = cart.getCartItem(productId);
		
		if(item != null){
			cart.removeFromCart(item);
			item.setQuantity(1);
		}else {
			Product product = productService.getProductDetails(productId);
			item = new CartItem(product, 1);
		}
		
		cart.addToCart(item);
		return "shoppingCart";
	}
	
	
}
