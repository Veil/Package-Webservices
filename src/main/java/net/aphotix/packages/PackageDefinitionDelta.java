package net.aphotix.packages;

import java.util.List;

/**
 * A {@link PackageDefinition} which allows modification and can be used as a delta when updating definitions
 *
 * @author Veil (nathan@aphotix.net).
 */
public interface PackageDefinitionDelta extends PackageDefinition {

	/**
	 * Set the product ids associated with this definition
	 *
	 * @param ids The ids to set
	 */
	public void setProductIds(List<String> ids);

	/**
	 * Set the name of the package this definition represents
	 *
	 * @param name The name of the package
	 */
	public void setName(String name);

	/**
	 * Set the description of the package this definition represents
	 *
	 * @param description The description of the package
	 */
	public void setDescription(String description);

}
