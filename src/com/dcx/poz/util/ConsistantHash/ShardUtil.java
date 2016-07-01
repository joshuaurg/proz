package com.dcx.poz.util.ConsistantHash;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ShardUtil {

	private TreeMap<Long, ServerInfo> nodes; // 虚拟节点
    private List<ServerInfo> shards; // 真实机器节点
    private final int NODE_NUM = 100; // 每个机器节点关联的虚拟节点个数

    public ShardUtil(List<ServerInfo> shards) {
        super();
        this.shards = shards;
        init();
    }

    private void init() { // 初始化一致性hash环
        nodes = new TreeMap<Long,ServerInfo>();
        for (int i = 0; i != shards.size(); ++i) { // 每个真实机器节点都需要关联虚拟节点
            final ServerInfo shardInfo = shards.get(i);

            for (int n = 0; n < NODE_NUM; n++)
                // 一个真实机器节点关联NODE_NUM个虚拟节点
                nodes.put(MurMurHash.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
        }
    }

    public ServerInfo getShardInfo(String key) {
        SortedMap<Long, ServerInfo> tail = nodes.tailMap(MurMurHash.hash(key)); // 沿环的顺时针找到一个虚拟节点
        if (tail.size() == 0) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey()); // 返回该虚拟节点对应的真实机器节点的信息
    }
}
