package net.aphotix.spring.responses;

import net.aphotix.products.Product;

/**
 * @author Veil (nathan@aphotix.net).
 */
public class ProductResponse {

	private final String name;

	private final String id;

	public ProductResponse(Product wrapped) {
		this.name = wrapped.getName();
		this.id = wrapped.getId();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
