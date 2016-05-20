package net.aphotix.spring.config;

import net.aphotix.conversion.CurrencyConverter;
import net.aphotix.conversion.FixerIOCurrencyConverter;
import net.aphotix.products.ProductService;
import net.aphotix.products.RestfulProductService;
import net.aphotix.rest.RestRequester;
import net.aphotix.rest.RestTemplateRequester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Simple configuration for dependency injected objects
 *
 * @author Veil (nathan@aphotix.net).
 */
@Configuration
public class BeanConfig {

	@Bean
	public ProductService productService(RestRequester restRequester) {
		return new RestfulProductService(restRequester);
	}

	@Bean
	public RestRequester restRequester() {
		return new RestTemplateRequester();
	}

	@Bean
	public CurrencyConverter currencyConverter(RestRequester restRequester) {
		return new FixerIOCurrencyConverter(restRequester);
	}

}
