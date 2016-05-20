package net.aphotix.products;

import net.aphotix.rest.RestRequester;
import org.springframework.http.HttpHeaders;

import java.util.*;

/**
 * A {@link ProductService} which connects to a rest endpoint to read which products are available at this time.
 *
 * @author Veil (nathan@aphotix.net).
 */
public class RestfulProductService implements ProductService {

	private static final String PRODUCT_ENDPOINT = "https://product-service.herokuapp.com/api/v1";
	private static final String PRODUCT_DETAILS = PRODUCT_ENDPOINT + "/products/%s";
	private static final String PRODUCT_LIST = PRODUCT_ENDPOINT + "/products";

	private static final HttpHeaders AUTH_HEADERS = new HttpHeaders();

	static {
		AUTH_HEADERS.add("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:pass".getBytes()));
	}

	private final RestRequester restRequester;

	/**
	 * Create a new {@link RestfulProductService}
	 *
	 * @param restRequester The requester used to request and map json objects returned from the rest endpoint to their
	 *                      java object counterparts
	 */
	public RestfulProductService(RestRequester restRequester) {
		this.restRequester = restRequester;
	}


	@Override
	public Optional<? extends Product> getProductById(String id) {
		return restRequester.getEntity(String.format(PRODUCT_DETAILS, id), ProductJson.class, AUTH_HEADERS);
	}

	@Override
	public Collection<? extends Product> getAllProducts() {
		return restRequester.getEntity(PRODUCT_LIST, ProductJson[].class, AUTH_HEADERS)
				.map(Arrays::asList).orElse(Collections.emptyList());
	}
}
