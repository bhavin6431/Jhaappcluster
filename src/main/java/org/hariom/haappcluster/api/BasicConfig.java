package org.hariom.haappcluster.api;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public class BasicConfig {
	private final String clusterName;
	private final short clusterPort;
	private final String attributeStorageName;
	private final short nodeId;

	public String getClusterName() {
		return clusterName;
	}

	public short getClusterPort() {
		return clusterPort;
	}

	public String getAttributeStorageName() {
		return attributeStorageName;
	}

	public short getNodeId() {
		return nodeId;
	}

	public BasicConfig(String clusterName, short clusterPort, String attributeStorageName, short nodeId) {
		super();
		this.clusterName = clusterName;
		this.clusterPort = clusterPort;
		this.attributeStorageName = attributeStorageName;
		this.nodeId = nodeId;
	}

}
