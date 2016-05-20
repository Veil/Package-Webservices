package net.aphotix.packages;

import net.aphotix.products.Product;
import net.aphotix.products.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An implementation of a Package which lazily intializes products and price values, that is, the fields for price and
 * products are only populated when the {@link #getPrice()} and {@link #getProducts()} methods are called. Further calls
 * to those methods will not repopulate, only the first call.
 *
 * @author Veil (nathan@aphotix.net).
 */
class LazyProductFetchingPackage implements Package {

	private final PackageDefinition definition;
	private final ProductService productService;

	private List<Product> products;

	private long totalPrice = -1;

	/**
	 * Create a new {@link LazyProductFetchingPackage}
	 *
	 * @param definition The package definition to create the package object from
	 * @param productService The product service to look up products from
	 */
	public LazyProductFetchingPackage(PackageDefinition definition, ProductService productService) {
		this.definition = definition;
		this.productService = productService;
	}

	@Override
	public long getId() {
		return definition.getId();
	}

	@Override
	public String getName() {
		return definition.getName();
	}

	@Override
	public String getDescription() {
		return definition.getDescription();
	}

	@Override
	public Collection<Product> getProducts() {

		if (products == null) {
			products = new ArrayList<>();
			definition.getProductIds().forEach(id -> productService.getProductById(id).ifPresent(products::add));
		}

		return products;
	}

	@Override
	public long getPrice() {

		if (totalPrice == -1) {
			totalPrice = this.getProducts().stream()
					.map(Product::getPrice)
					.reduce(Long::sum)
					.orElse(0L);
		}

		return totalPrice;
	}
}
