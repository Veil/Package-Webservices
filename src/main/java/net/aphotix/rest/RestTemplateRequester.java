package net.aphotix.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * A {@link RestRequester} which uses Spring's {@link RestTemplate} to make requests to json endpoints and map their
 * responses to java objects
 *
 * @author Veil (nathan@aphotix.net).
 */
public class RestTemplateRequester implements RestRequester {

	private static final HttpHeaders EMPTY_HEADERS = new HttpHeaders();

	@Override
	public <T> Optional<T> getEntity(String requestUrl, Class<T> clazz) {
		return getEntity(requestUrl, clazz, EMPTY_HEADERS);
	}

	@Override
	public <T> Optional<T> getEntity(String requestUrl, Class<T> clazz, HttpHeaders headers) {

		try {
			final RestTemplate template = new RestTemplate();
			final ResponseEntity<T> product = template.exchange(requestUrl, HttpMethod.GET,
					new HttpEntity<String>(headers), clazz);
			return Optional.of(product.getBody());
		} catch (HttpClientErrorException e) {
			return Optional.empty();
		}
	}
}
