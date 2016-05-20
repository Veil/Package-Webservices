package net.aphotix.conversion;

import java.util.Map;

/**
 * Encapsulates currency rates received from a restful json api
 *
 * @author Veil (nathan@aphotix.net).
 */
class CurrencyRates {

	private String base;

	private String date;

	private Map<String, Double> rates;

	/**
	 * Get the base currency all the rates returned are measured against
	 *
	 * @return {@link String} The base currency code
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Get the date that base currency rate was updated
	 *
	 * @return {@link String} The date last updated
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Get all the rates that were returned as part of the restful request
	 *
	 * @return {@link Map} A map of currency rates returned
	 */
	public Map<String, Double> getRates() {
		return rates;
	}
}
