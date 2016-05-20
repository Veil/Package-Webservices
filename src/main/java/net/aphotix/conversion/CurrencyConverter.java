package net.aphotix.conversion;

import net.aphotix.packages.Package;

import java.util.Currency;

/**
 * A converter which will convert package's to their currency converted counterparts
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface CurrencyConverter {

	/**
	 * Converts a package into a package with it's equivalent price in the currency provided.
	 *
	 * @param packageToConvert The package to convert
	 * @param currency The currency to convert the package's price value to
	 *
	 * @return {@link Package} The converted package, or the same package that was passed in if no conversion could be
	 * performed to the currency provided.
	 */
	public Package convertCurrency(Package packageToConvert, Currency currency);

}
