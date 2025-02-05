package com.art.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.art.dao.ArtworkDao;
import com.art.dao.CartDao;
import com.art.dao.CartItemDao;
import com.art.dao.UserDao;
import com.art.dto.CartDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Artwork;
import com.art.pojos.CartEntity;
import com.art.pojos.CartItem;
import com.art.pojos.User;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartRepository;
    
    @Autowired
    private ArtworkDao artworkDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> mapper.map(cart, CartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addCart(CartDto cartDTO) {
    	
        CartEntity cart = new CartEntity();
        cart.setCartId(cartDTO.getCartId());
        User user = userDao.findById(cartDTO.getCustId()).get();
        cart.setCustomer(user);
        CartEntity savedCart = cartRepository.save(cart);
        return "New Cart added with id " + savedCart.getCartId();
    }

    @Override
    public String deleteCart(Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return "Cart deleted";
        }
        throw new ResourceNotFoundException("Invalid Cart ID!");
    }

    @Override
    public CartDto getCartDetails(Long cartId) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Cart ID"));
        return mapper.map(cart, CartDto.class);
    }

    @Override
    public String updateCart(Long cartId, CartDto cartDTO) {
        if (cartRepository.existsById(cartId)) {
        	CartEntity cart = new CartEntity();
            cart.setCartId(cartDTO.getCartId());
            User user = userDao.findById(cartDTO.getCustId()).get();
            cart.setCustomer(user);
            cartRepository.save(cart);
            return "Update success";
        }
        throw new ResourceNotFoundException("Cart doesn't exist!");
    }

	@Override
	public List<CartItem> addToCart(Long userId, Long artworkId, int quantity) {
		// Step 1: Retrieve the customer’s cart or create a new one if it doesn't exist
        CartEntity cart = cartRepository.findByCustomer_UserId(userId);
		/*
		 * if (cart == null) { // Create a new CartEntity if the customer doesn't have
		 * one cart = new CartEntity(); cart.setCustomer(new User(custId)); // Assuming
		 * User constructor accepts customer ID cartEntityRepository.save(cart); }
		 */
        
        // Step 2: Retrieve the artwork
        Artwork artwork = artworkDao.findById(artworkId)
                                            .orElseThrow(() -> new RuntimeException("Artwork not found"));

        // Step 3: Create a CartItem and add it to the cart
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setArtwork(artwork);
        cartItem.setQuantity(quantity);
        
        // Step 4: Save the CartItem
        cartItemDao.save(cartItem);

        // Step 5: Return the updated list of CartItems for the customer
        return cartItemDao.findByCart(cart); // Fetch all items for this cart
	}
    
	/*
	 * @Override public List<CartItem> removeFromCart(Long userId, Long artworkId) {
	 * // Retrieve the customer's cart CartEntity cart =
	 * cartRepository.findByCustomer_UserId(userId); if (cart == null) { throw new
	 * RuntimeException("Cart not found for customer: " + userId); }
	 * 
	 * // Find the cart item by cart ID and artwork ID CartItem cartItem =
	 * cartItemDao.findByCartAndArtwork_ArtId(cart, artworkId) .orElseThrow(() ->
	 * new RuntimeException("Item not found in cart"));
	 * 
	 * // Remove the CartItem cartItemDao.delete(cartItem);
	 * 
	 * // Return the updated list of CartItems for the customer after removal return
	 * cartItemDao.findByCart(cart); // Fetch all remaining items for this cart }
	 */
	
	
	
	@Override
	public List<CartItem> removeFromCart(Long userId, Long artworkId) {
	    // Retrieve the customer's cart
	    CartEntity cart = cartRepository.findByCustomer_UserId(userId);
	    if (cart == null) {
	        throw new RuntimeException("Cart not found for customer: " + userId);
	    }

	    // Find all matching cart items
	    List<CartItem> cartItems = (List<CartItem>) cartItemDao.findByCartAndArtwork_ArtId(cart, artworkId);
	    
	    if (cartItems.isEmpty()) {
	        throw new RuntimeException("Item not found in cart");
	    }

	    // Remove only the first (or last) item
	    CartItem itemToRemove = cartItems.get(0); // Change index if needed (e.g., `get(cartItems.size() - 1)` for newest)
	    cartItemDao.delete(itemToRemove);

	    // Return the updated cart list
	    return cartItemDao.findByCart(cart);
	}


	@Override
	public List<CartItem> getCartListByUser(Long userId) {
		// Step 1: Retrieve the customer’s cart or create a new one if it doesn't exist
        CartEntity cart = cartRepository.findByCustomer_UserId(userId);
        // Step 5: Return the updated list of CartItems for the customer
        return cartItemDao.findByCart(cart); // Fetch all items for this cart
	}
}
