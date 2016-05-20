package net.aphotix.conversion;

import net.aphotix.packages.Package;
import net.aphotix.rest.RestRequester;

import java.util.Currency;
import java.util.Optional;

/**
 * A currency converter which uses the Fixer IO rest service to retrieve the most up to date currency conversion rates
 * in order to perform price conversion.
 *
 * http://fixer.io/
 *
 * @author Veil (nathan@aphotix.net).
 */
public class FixerIOCurrencyConverter implements CurrencyConverter {

	private static final String REST_URL = "http://api.fixer.io/latest?base=USD&symbols=%s";

	private final RestRequester restRequester;

	/**
	 * Create a new {@link FixerIOCurrencyConverter}
	 *
	 * @param restRequester The rest requester used to map json objects to their class counterparts
	 */
	public FixerIOCurrencyConverter(RestRequester restRequester) {
		this.restRequester = restRequester;
	}

	@Override
	public Package convertCurrency(Package packageToConvert, Currency currency) {
		final Optional<CurrencyRates> rates = restRequester.getEntity(String.format(REST_URL, currency.getCurrencyCode()),
				CurrencyRates.class);

		return rates.map(CurrencyRates::getRates)
				.map(m -> m.get(currency.getCurrencyCode()))
				.<Package>map(rate -> new CurrencyConvertedPackage(packageToConvert, rate))
				.orElse(packageToConvert);
	}
}
