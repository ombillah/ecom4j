package com.ombillah.ecom4j.webapp.springmvc;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.ombillah.ecom4j.utils.Constants;


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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		for (GrantedAuthority role : roles) {
			if(StringUtils.equals(role.getAuthority(), Constants.GUEST_ROLE)) {
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}
		
		
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
		return showCart(cart);
	}
	
	@RequestMapping(value = "/removefromcart.do", method = RequestMethod.POST)
	public String removeProductFromCart(
			@RequestParam(value="productId", required=true) final Long productId,
			@ModelAttribute("shoppingCart") ShoppingCart cart) {
		
		CartItem item = cart.getCartItem(productId);
		cart.removeFromCart(item);
		
		return "cartContent";
	}
	
	@RequestMapping(value = "/updateitemquantity.do", method = RequestMethod.POST)
	public String updateQuantity(
			@RequestParam(value="productId", required=true) final Long productId,
			@RequestParam(value="quantity", required=true) final Long quantity,
			@ModelAttribute("shoppingCart") ShoppingCart cart) {
		
		CartItem item = cart.getCartItem(productId);
		item.setQuantity(quantity);
		cart.removeFromCart(item);
		cart.addToCart(item);
		
		return "cartContent";
	}
	
	@RequestMapping(value = "/updateshipping.do", method = RequestMethod.POST)
	public String updateShipping(
			@RequestParam(value="shippingOption", required=true) final String shippingOption,
			@ModelAttribute("shoppingCart") ShoppingCart cart) {
		
		cart.setShippingOption(shippingOption);
		if(StringUtils.equals(cart.getShippingOption(), Constants.STANDARD_SHIPPING)) {
			cart.setShippingCost(0);
		} else {
			cart.setShippingCost(Constants.EXPRESS_SHIPPING_COST);
		}
		return "cartContent";
	}
}
