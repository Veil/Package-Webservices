package net.aphotix.spring.controllers;

import net.aphotix.packages.Package;
import net.aphotix.conversion.CurrencyConverter;
import net.aphotix.packages.PackageHandler;
import net.aphotix.packages.PackageNotFoundException;
import net.aphotix.products.ProductService;
import net.aphotix.spring.responses.ErrorResponse;
import net.aphotix.spring.responses.PackageResponse;
import net.aphotix.spring.entities.StoredPackage;
import net.aphotix.spring.repositories.PackageRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A controller used to manage various packages stored in a repository through various C.R.U.D. operations.
 *
 * @author Veil (nathan@aphotix.net).
 */
@RestController
@RequestMapping("/packages")
public class PackagesRestController {

	private static final String ID_PATH = "/{id}";

	private final PackageRepository repository;
	private final ProductService productService;
	private final CurrencyConverter converter;

	@Inject
	public PackagesRestController(PackageRepository repository, ProductService productService, CurrencyConverter converter) {
		this.repository = repository;
		this.productService = productService;
		this.converter = converter;
	}

	/**
	 * List all the packages currently residing in the underlying repository
	 *
	 * @return {@link List} A list of packages
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<PackageResponse> getAllPackages() {
		final PackageHandler handler = new PackageHandler(repository, productService);
		return handler.listAllPackages().stream().map(PackageResponse::new).collect(Collectors.toList());
	}

	/**
	 * Get a package matching the id provided
	 *
	 * @param id The id of the package to return
	 *
	 * @return {@link PackageResponse} A response holding the package's information
	 *
	 * @throws PackageNotFoundException If the id does not map to a package in the repository
	 */
	@RequestMapping(value = ID_PATH, method = RequestMethod.GET)
	public PackageResponse getPackage(@PathVariable long id) throws PackageNotFoundException {
		final PackageHandler handler = new PackageHandler(repository, productService);
		return new PackageResponse(handler.getPackageById(id));
	}

	/**
	 * Get a package matching the id provided with its value converted to currency code provided using the latest
	 * conversion rates. If the currency code supplied is not recognised, USD is used.
	 *
	 * @param id The id of the package to return
	 * @param currencyCode The currency to convert the price to
	 *
	 * @return {@link PackageResponse} A response holding the package's information
	 *
	 * @throws PackageNotFoundException If the ide does not map to a package in the repository
	 */
	@RequestMapping(value = ID_PATH + "/{currencyCode}", method = RequestMethod.GET)
	public PackageResponse getPackageInCurrency(@PathVariable long id, @PathVariable String currencyCode) throws PackageNotFoundException {
		final PackageHandler handler = new PackageHandler(repository, productService);
		final Package packageToConvert = handler.getPackageById(id);

		try {
			return new PackageResponse(converter.convertCurrency(packageToConvert, Currency.getInstance(currencyCode)),
					currencyCode);
		} catch (IllegalArgumentException e) {
			return new PackageResponse(packageToConvert);
		}
	}

	/**
	 * Delete a package matching the id provided
	 *
	 * @param id The id of the package to delete
	 *
	 * @return {@link ResponseEntity} A response indicating the successful deletion of the package
	 *
	 * @throws EmptyResultDataAccessException If no package exists
	 */
	@RequestMapping(value = ID_PATH, method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePackage(@PathVariable long id) throws EmptyResultDataAccessException {
		final PackageHandler handler = new PackageHandler(repository, productService);
		handler.removePackage(id);
		return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
	}

	/**
	 * Create a new package with the provided details
	 *
	 * @param request The requested details of the new package
	 * @param result The result of how the requested package details bound to the request object
	 *
	 * @return {@link ResponseEntity} A response indicating the successful creation of the package including a Location
	 * header of where this package can be found
	 *
	 * @throws IllegalArgumentException If the request package details do not bind to the request object properly
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<?> createPackage(@RequestBody @Valid StoredPackage request, BindingResult result)
			throws IllegalArgumentException {

		if (result.hasErrors()) {
			throw new IllegalArgumentException("New package does not have the required fields");
		}

		final PackageHandler handler = new PackageHandler(repository, productService);
		final Package createdPackage = handler.addPackage(request);
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
				.path(ID_PATH)
				.buildAndExpand(createdPackage.getId())
				.toUri());
		return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
	}

	/**
	 * Update an existing package with the information provided
	 *
	 * @param id The id of the package to update
	 * @param request The information to update the package with
	 *
	 * @return {@link ResponseEntity} A response indicating the success of the update
	 *
	 * @throws PackageNotFoundException If the id provided does not match a package in the repository
	 */
	@RequestMapping(value = ID_PATH, method = RequestMethod.POST)
	public ResponseEntity<?> updatePackage(@PathVariable long id, @RequestBody StoredPackage request) throws PackageNotFoundException {
		final PackageHandler handler = new PackageHandler(repository, productService);
		request.setId(id);
		handler.updatePackage(request);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
	}

	/**
	 * Handles missing packages gracefully
	 *
	 * @return {@link ErrorResponse} Encapsulates an error message indicating what happened
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({PackageNotFoundException.class, EmptyResultDataAccessException.class})
	public ErrorResponse notFoundExceptionHandler() {
		return new ErrorResponse("No such package");
	}

	/**
	 * Handles illegal package creation requests gracefully
	 *
	 * @return {@link ErrorResponse} Encapsulates an error messages indicating what happened
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResponse badPackageFormatHandler() {
		return new ErrorResponse("Package requires a name, description and at least one product! " +
				"A list of valid products can be found at: https://product-service.herokuapp.com/api/v1/products");
	}

}
