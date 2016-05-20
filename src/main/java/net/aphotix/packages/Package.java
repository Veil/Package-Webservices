package net.aphotix.packages;

import net.aphotix.products.Product;

import java.util.Collection;

/**
 * Represents a package in the package service system
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface Package {

	/**
	 * The id of this package
	 *
	 * @return {@literal long} The unique id of this package
	 */
	public long getId();

	/**
	 * Get the human readable name of this package
	 *
	 * @return {@link String} The name of this package
	 */
	public String getName();

	/**
	 * Get the description of this package
	 *
	 * @return {@link String} The package description
	 */
	public String getDescription();

	/**
	 * Get all the products currently in this package
	 *
	 * @return {@link Collection} The products in this package
	 */
	public Collection<Product> getProducts();

	/**
	 * Get the price of this package in the lowest common format for the currently in-use currency code (e.g. Cents/Pence)
	 *
	 * @return {@literal long} The price of this package
	 */
	public long getPrice();

}
