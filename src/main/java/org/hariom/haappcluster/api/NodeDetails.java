package org.hariom.haappcluster.api;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public class NodeDetails {

	private NodeAttributeService nodeAttributeService;

	public NodeDetails(NodeAttributeService nodeAttributeService) {
		this.nodeAttributeService = nodeAttributeService;
	}

	public short getId() {
		return Short.parseShort(this.nodeAttributeService.getStringAttribute(NodeAttribute.ID.toString()));
	}

	public void setId(short id) {
		this.nodeAttributeService.setStringAttribute(NodeAttribute.ID.toString(), String.valueOf(id));
	}

	public NodeStatus getStatus() {
		return NodeStatus.valueOf(this.nodeAttributeService.getStringAttribute(NodeAttribute.STATUS.toString()));
	}

	public void setStatus(NodeStatus status) {
		this.nodeAttributeService.setStringAttribute(NodeAttribute.STATUS.toString(), status.toString());
	}

	public NodeType getType() {
		return NodeType.valueOf(this.nodeAttributeService.getStringAttribute(NodeAttribute.TYPE.toString()));
	}

	public void setType(NodeType type) {
		this.nodeAttributeService.setStringAttribute(NodeAttribute.TYPE.toString(), type.toString());
	}

}
