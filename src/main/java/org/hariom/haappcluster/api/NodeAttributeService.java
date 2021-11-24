package org.hariom.haappcluster.api;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public interface NodeAttributeService {

	String getStringAttribute(String attributeName);

	void setStringAttribute(String attributeName, String attributeValue);

	String getStringAttribute(Short nodeId, String attributeName);

	void setStringAttribute(Short nodeId, String attributeName, String attributeValue);

	void logAllAttributes();

	boolean doesExist(String attributeName, String attributeValue);

}
