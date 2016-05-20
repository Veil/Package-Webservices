package net.aphotix.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The json representation of a product used in deserializing a restful json response
 *
 * @author Veil (nathan@aphotix.net).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductJson implements Product {

	private String id;
	private String name;
	@JsonProperty("usdPrice")
	private Long usdPrice;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	@JsonProperty("usdPrice")
	public long getPrice() {
		return usdPrice;
	}
}
