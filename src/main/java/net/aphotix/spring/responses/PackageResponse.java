package net.aphotix.spring.responses;

import net.aphotix.packages.Package;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Encapsulates a {@link Package} for serialization to json to be sent via web services.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class PackageResponse {

	/** The default currency that package response price values are in */
	private static final String DEFAULT_CURRENCY = "USD";

	private final Long id;

	private final String name;

	private final String description;

	private final Long price;

	private final String currency;

	private final Collection<ProductResponse> products;

	/**
	 * Create a new {@link PackageResponse} with a given currency code
	 *
	 * @param wrapped The Package we're wrapping for serialization
	 * @param currency The currency code this package's price value currently represents
	 */
	public PackageResponse(Package wrapped, String currency) {
		this.id = wrapped.getId();
		this.name = wrapped.getName();
		this.description = wrapped.getDescription();
		this.price = wrapped.getPrice();
		this.products = wrapped.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
		this.currency = currency;
	}

	/**
	 * Create a new {@link PackageResponse} with the default currency code {@link #DEFAULT_CURRENCY}
	 *
	 * @param wrapped The package we're wrapping for serialization
	 */
	public PackageResponse(Package wrapped) {
		this(wrapped, DEFAULT_CURRENCY);
	}

	/**
	 * Get the id of this package
	 *
	 * @return {@link Long} The id of this package
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the name of this package
	 *
	 * @return {@link String} The human readable name of this package
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the description of this package
	 *
	 * @return {@link String} The description describing what's in this package
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the price of this package in the lowest common currency format (e.g. Cents for USD, pence for GBP)
	 *
	 * @see #getCurrency()
	 *
	 * @return {@link Long} The price of this package
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * Get the products included in this package
	 *
	 * @return {@link Collection} A collection of all the products
	 */
	public Collection<ProductResponse> getProducts() {
		return products;
	}

	/**
	 * The currency code that this package's price value is represented in
	 *
	 * @see #getPrice()
	 *
	 * @return {@link String} The currency code (e.g. USD/GBP/EUR)
	 */
	public String getCurrency() {
		return currency;
	}
}
