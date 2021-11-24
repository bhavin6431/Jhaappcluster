package org.hariom.haappcluster.impl.hazelcast;

import org.hariom.haappcluster.api.ClusterInfoService;
import org.hariom.haappcluster.api.NodeAttribute;
import org.hariom.haappcluster.api.NodeType;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public class HazelcastClusterService implements ClusterInfoService {

	HazelcastAttributeService hazelcastAttributeService;

	public HazelcastClusterService(HazelcastAttributeService hazelcastAttributeService) {
		this.hazelcastAttributeService = hazelcastAttributeService;
	}

	@Override
	public boolean isActiveNodeInCluster() {
		return hazelcastAttributeService.doesExist(NodeAttribute.TYPE.toString(), NodeType.ACTIVE.toString());
	}

}
