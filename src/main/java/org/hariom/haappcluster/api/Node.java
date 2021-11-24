package org.hariom.haappcluster.api;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public interface Node {

	void initialise(BasicConfig basicConfig);

	NodeType getNodeType();

	NodeStatus getNodeStatus();

	void setNodeType(NodeType nodeType);

	void setNodeStatus(NodeStatus nodeStatus);

	void logAllAttributes();

}
