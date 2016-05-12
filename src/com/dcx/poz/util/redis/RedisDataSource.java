package com.dcx.poz.util.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {

	/**
	 * 取得redis的客户端，可以执行命令了
	 * @description 
	 * @author dcx
	 * @date 2016年3月8日 下午3:41:36
	 * @return
	 */
	public abstract ShardedJedis getRedisClient();
	/**
	 * 将资源返还给pool
	 * @description 
	 * @author dcx
	 * @date 2016年3月8日 下午3:41:43
	 * @param shardedJedis
	 */
    public void returnResource(ShardedJedis shardedJedis);
    /**
     * 出现异常后，将资源返还给pool
     * @description 
     * @author dcx
     * @date 2016年3月8日 下午3:41:53
     * @param shardedJedis
     * @param broken
     */
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
