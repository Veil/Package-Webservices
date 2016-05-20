package net.aphotix.rest;

import org.springframework.http.HttpHeaders;

import java.util.Optional;

/**
 * A general requester used to request resources from a json endpoint and map them to a java object counterpart
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface RestRequester {

	/**
	 * Get an entity found at the provided url
	 *
	 * @param requestUrl The url to make the get request to
	 * @param clazz The class to map the response to
	 * @param <T> The type of object to map the response to
	 *
	 * @return {@link Optional} Contains the potential response returned by the endpoint, can be empty
	 */
	public <T> Optional<T> getEntity(String requestUrl, Class<T> clazz);

	/**
	 * Get an entity found at the provided url
	 *
	 * @param requestUrl The url to make the get request to
	 * @param clazz The class to map the response to
	 * @param headers Any extra headers needed to be sent with the request
	 * @param <T> The type of object to map the response to
	 *
	 * @return {@link Optional} Contains the potential response returned by the endpoint, can be empty
	 */
	public <T> Optional<T> getEntity(String requestUrl, Class<T> clazz, HttpHeaders headers);

}
