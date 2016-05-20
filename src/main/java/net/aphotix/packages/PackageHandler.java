package net.aphotix.packages;

import net.aphotix.products.ProductService;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Veil (nathan@aphotix.net).
 */
public final class PackageHandler {

	private final PackageStore store;
	private final ProductService productService;

	public PackageHandler(PackageStore store, ProductService productService) {
		this.store = store;
		this.productService = productService;
	}

	public Package getPackageById(long id) throws PackageNotFoundException {
		return store.findById(id)
				.map(definition -> new LazyProductFetchingPackage(definition, productService))
				.orElseThrow(PackageNotFoundException::new);
	}

	public Package addPackage(PackageDefinition newPackage) {

		if (hasValidProducts(newPackage)) {
			return new LazyProductFetchingPackage(store.save(newPackage), productService);
		} else {
			throw new IllegalArgumentException("All products provided in a package must exist!");
		}
	}

	public void removePackage(long packageId) {
		store.delete(packageId);
	}

	public void updatePackage(PackageDefinitionDelta definition) throws PackageNotFoundException {
		final PackageDefinition existing = store.findById(definition.getId()).orElseThrow(PackageNotFoundException::new);

		if (definition.getProductIds() == null) {
			definition.setProductIds(existing.getProductIds());
		} else if (hasValidProducts(definition)) {
			throw new IllegalArgumentException("All products provided in a package must exist!");
		}

		if (definition.getDescription() == null) {
			definition.setDescription(existing.getDescription());
		}

		if (definition.getName() == null) {
			definition.setName(existing.getName());
		}

		store.save(definition);
	}

	public Collection<Package> listAllPackages() {
		return store.findAll()
				.stream()
				.map(definition -> new LazyProductFetchingPackage(definition, productService))
				.collect(Collectors.toList());
	}

	private boolean hasValidProducts(PackageDefinition newPackage) {
		return newPackage.getProductIds().stream().allMatch(id ->
				productService.getProductById(id).isPresent());
	}

}
