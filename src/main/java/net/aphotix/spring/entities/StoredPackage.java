package net.aphotix.spring.entities;

import net.aphotix.packages.PackageDefinitionDelta;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The object representation of a package stored in the underlying JPA repository
 *
 * @author Veil (nathan@aphotix.net).
 */
@Entity
public class StoredPackage implements PackageDefinitionDelta {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	@Size(min = 1)
	@ElementCollection
	private List<String> productIds;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<String> getProductIds() {
		return productIds;
	}

	@Override
	public void setProductIds(List<String> ids) {
		productIds = ids;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Allows the direct setting of an id, should only be called when this object has not originated from the JPA
	 * layer and is intended as an updated package
	 *
	 * @param id The id to set for this package
	 */
	public void setId(long id) {
		this.id = id;
	}
}
