package org.hariom.haappcluster.impl.sample;

import org.hariom.haappcluster.api.BasicConfig;
import org.hariom.haappcluster.impl.hazelcast.HazelcastAttributeService;
import org.hariom.haappcluster.impl.hazelcast.HazelcastNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(HazelcastAttributeService.class);

	public static void main(String args[]) {
		LOG.info("Starting node {}", System.getProperty("nodeId"));
		HazelcastNode node = new HazelcastNode();
		BasicConfig config = new BasicConfig("haappcluster", (short) 9091, "attributeStorage",
				Short.valueOf(System.getProperty("nodeId")));
		node.initialise(config);
		node.logAllAttributes();
	}
}
