package net.aphotix.spring.repositories;

import net.aphotix.packages.PackageStore;
import net.aphotix.spring.entities.StoredPackage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository used by spring to generate a concrete class to access the underlying repository, mapping StoredPackages
 * from their database counterparts
 *
 * Note: Warnings are suppressed because implementations of this interface are auto-generated, to prevent the JDK
 * throwing errors at compile time, overrides of both PackageStore and JpaRepository are found here, which generate
 * warnings, however they can be safely ignored as the class generator spring provides is clever enough to generate
 * a sensible type safe concrete class
 *
 * @author Veil (nathan@aphotix.net).
 */
@SuppressWarnings("unchecked")
public interface PackageRepository extends JpaRepository<StoredPackage, Long>, PackageStore {

	/**
	 * Find a stored package by the id provided
	 *
	 * @param id The id of the package to find
	 *
	 * @return {@link Optional} An object representing the fact that the storedpackage looked for may or may not exist
	 */
	public Optional<StoredPackage> findById(long id);

	/**
	 * Saves the package provided returning the newly saved version with any autogenerated values populated
	 *
	 * @param definition The package definition to save
	 *
	 * @return {@link StoredPackage} The saved version of the package
	 */
	public StoredPackage save(StoredPackage definition);

	/**
	 * Delete the package matching the provided id
	 *
	 * @param id The id of the package to delete
	 *
	 * @throws EmptyResultDataAccessException If no package with that id exists
	 */
	public void delete(Long id) throws EmptyResultDataAccessException;

	/**
	 * Get all the packages currently residing in the underlying data store
	 *
	 * @return {@link List} A list of all packages in the data store, can be empty if none exist
	 */
	public List<StoredPackage> findAll();

}
