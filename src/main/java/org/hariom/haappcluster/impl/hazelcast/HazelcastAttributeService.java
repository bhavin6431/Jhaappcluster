package org.hariom.haappcluster.impl.hazelcast;

import java.util.Map;
import java.util.TreeMap;

import org.hariom.haappcluster.api.NodeAttribute;
import org.hariom.haappcluster.api.NodeAttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Bhavin Pandya
 *
 */
public class HazelcastAttributeService implements NodeAttributeService {
	private final Map<String, String> attributeMap;
	private static final String KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR = "//#";
	private short nodeId;
	private static final Logger LOG = LoggerFactory.getLogger(HazelcastAttributeService.class);

	public HazelcastAttributeService(Map<String, String> attributeMap, short nodeId) {
		this.attributeMap = attributeMap;
		this.nodeId = nodeId;
	}

	public String getStringAttribute(String attributeName) {
		return getStringValue(attributeName);
	}

	private String getStringValue(String attributeName) {
		return attributeMap.entrySet().stream().filter(e -> e.getValue().equals(generateKey(attributeName)))
				.map(Map.Entry::getValue).findFirst().orElse(null);
	}

	public void setStringAttribute(String attributeName, String attributeValue) {
		attributeMap.put(generateKey(attributeName), attributeValue);
	}

	public String getStringAttribute(Short nodeId, String attributeName) {
		return attributeMap.entrySet().stream().filter(e -> e.getValue().equals(generateKey(nodeId, attributeName)))
				.map(Map.Entry::getValue).findFirst().orElse(null);
	}

	public void setStringAttribute(Short nodeId, String attributeName, String attributeValue) {
		attributeMap.put(generateKey(nodeId, attributeName), attributeValue);
	}

	private String generateKey(short nodeId, String attributeName) {
		return String.valueOf(nodeId) + KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR + attributeName;
	}

	private String generateKey(String attributeName) {
		return String.valueOf(nodeId) + KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR + attributeName;
	}

	private String getNodeId(String attributeValue) {
		return attributeValue.substring(0, attributeValue.indexOf(KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR));
	}

	private String getKey(String attributeValue) {
		return attributeValue.substring(attributeValue.indexOf(KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR)
				+ KEY_NODE_ID_AND_NODE_ATTRIBUTE_SEPARATOR.length(), attributeValue.length());
	}

	public void logAllAttributes() {
		LOG.info("{}", String.format("%50s", "Membership Attributes"));
		LOG.info("+{}+{}+{}+", "-".repeat(10), "-".repeat(40), "-".repeat(40));
		LOG.info("|{}|{}|{}|", String.format("%-10s", "NODE ID"), String.format("%-40s", "KEY"),
				String.format("%-40s", "VALUE"));
		LOG.info("+{}+{}+{}+", "-".repeat(10), "-".repeat(40), "-".repeat(40));
		TreeMap<String, String> treeMap = new TreeMap<String, String>(attributeMap);
		String prevNode = "";
		int count = 0;
		for (String key : treeMap.keySet()) {
			if (!prevNode.equals(getNodeId(key)) && count > 0) {
				LOG.info("+{}+{}+{}+", "-".repeat(10), "-".repeat(40), "-".repeat(40));
			}
			if (key.contains(NodeAttribute.TYPE.toString()) || key.contains(NodeAttribute.STATUS.toString())) {
				LOG.info("|{}|{}|{}|", String.format("%-10s", getNodeId(key)), String.format("%-40s", getKey(key)),
						String.format("%-40s", treeMap.get(key)));
			}

			prevNode = getNodeId(key);
			count++;
		}
		LOG.info("+{}+{}+{}+", "-".repeat(10), "-".repeat(40), "-".repeat(40));
	}

	@Override
	public boolean doesExist(String attributeName, String attributeValue) {
		return attributeMap.entrySet().stream()
				.filter(e -> e.getKey().contains(attributeName) && e.getValue().contains(attributeValue)).count() > 0;
	}

}
