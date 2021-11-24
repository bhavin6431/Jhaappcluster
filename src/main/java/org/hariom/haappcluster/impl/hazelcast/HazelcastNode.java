package org.hariom.haappcluster.impl.hazelcast;

import java.util.Map;

import org.hariom.haappcluster.api.BasicConfig;
import org.hariom.haappcluster.api.Node;
import org.hariom.haappcluster.api.NodeAttribute;
import org.hariom.haappcluster.api.NodeAttributeService;
import org.hariom.haappcluster.api.NodeDetails;
import org.hariom.haappcluster.api.NodeStatus;
import org.hariom.haappcluster.api.NodeType;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastNode implements Node {

	NodeAttributeService nodeAttributeService;

	public void initialise(BasicConfig basicConfig) {
		Config config = new Config();
		config.setClusterName(basicConfig.getClusterName());
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		hazelcastInstance.getConfig().getMemberAttributeConfig().setAttribute(NodeAttribute.ID.toString(),
				String.valueOf(basicConfig.getNodeId()));
		Map<String, String> attributeMap = hazelcastInstance.getMap(basicConfig.getAttributeStorageName());
		nodeAttributeService = new HazelcastAttributeService(attributeMap, basicConfig.getNodeId());
		NodeDetails nodeDetails = new NodeDetails(nodeAttributeService);
		nodeDetails.setId(basicConfig.getNodeId());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (nodeAttributeService.doesExist(NodeAttribute.TYPE.toString(), NodeType.ACTIVE.toString())) {
			nodeDetails.setType(NodeType.STANDBY);
			nodeDetails.setStatus(NodeStatus.STARTING);
		} else {
			nodeDetails.setType(NodeType.ACTIVE);
			nodeDetails.setStatus(NodeStatus.STARTING);
		}
	}

	public NodeType getNodeType() {
		return NodeType.valueOf(nodeAttributeService.getStringAttribute(NodeAttribute.TYPE.toString()));
	}

	public NodeStatus getNodeStatus() {
		return NodeStatus.valueOf(nodeAttributeService.getStringAttribute(NodeAttribute.STATUS.toString()));
	}

	public void setNodeType(NodeType nodeType) {
		nodeAttributeService.setStringAttribute(NodeAttribute.TYPE.toString(), nodeType.toString());
	}

	public void setNodeStatus(NodeStatus nodeStatus) {
		nodeAttributeService.setStringAttribute(NodeAttribute.STATUS.toString(), nodeStatus.toString());
	}

	@Override
	public void logAllAttributes() {
		nodeAttributeService.logAllAttributes();
	}

}
