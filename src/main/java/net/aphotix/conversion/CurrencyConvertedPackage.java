package net.aphotix.conversion;

import net.aphotix.packages.Package;
import net.aphotix.products.Product;

import java.util.Collection;

/**
 * A package which will convert a provided package's price into the new price using the currency conversion rate provided
 *
 * @author Veil (nathan@aphotix.net).
 */
class CurrencyConvertedPackage implements Package {

	private final Package wrapped;
	private final long price;

	/**
	 * Create a new {@link CurrencyConvertedPackage}
	 *
	 * @param wrapped The package we're converting
	 * @param currencyRate The currency conversion rate
	 */
	public CurrencyConvertedPackage(Package wrapped, double currencyRate) {
		this.wrapped = wrapped;
		this.price = (long) (wrapped.getPrice() * currencyRate);
	}

	@Override
	public long getId() {
		return wrapped.getId();
	}

	@Override
	public String getName() {
		return wrapped.getName();
	}

	@Override
	public String getDescription() {
		return wrapped.getDescription();
	}

	@Override
	public Collection<Product> getProducts() {
		return wrapped.getProducts();
	}

	@Override
	public long getPrice() {
		return price;
	}
}
