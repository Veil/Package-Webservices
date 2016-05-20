package net.aphotix.packages;

import net.aphotix.packages.PackageDefinition;

import java.util.List;
import java.util.Optional;

/**
 * @author Veil (nathan@aphotix.net).
 */
public interface PackageStore {

	public Optional<? extends PackageDefinition> findById(long id);

	public PackageDefinition save(PackageDefinition definition);

	public void delete(Long id);

	public <S extends PackageDefinition> List<S> findAll();

}
