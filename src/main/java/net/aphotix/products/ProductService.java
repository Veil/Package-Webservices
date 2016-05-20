package net.aphotix.products;

import java.util.Collection;
import java.util.Optional;

/**
 * A service used to locate and retrieve currently available products
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface ProductService {

	/**
	 * Get a product by its id
	 *
	 * @param id The id of the product to find
	 *
	 * @return {@link Optional} An optional indicating the possible existence of a product, can be empty
	 */
	public Optional<? extends Product> getProductById(String id);

	/**
	 * Get all the products currently available
	 *
	 * @return {@link Collection} A collection of all available products
	 */
	public Collection<? extends Product> getAllProducts();

}
