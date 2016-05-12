一:springMVC集成redis
  1：redis配置文件-redis.config.xml
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p" 
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
      http://www.springframework.org/schema/beans 
      http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
      http://www.springframework.org/schema/tx 
      http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
      http://www.springframework.org/schema/context
      http://www.springframework.org/schema/context/spring-context-3.0.xsd">
    
 	<!-- reids配置 -->
	<bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
		<property name="maxTotal" value="4096"/>
		<property name="maxIdle" value="200"/>
		<property name="maxWaitMillis" value="3000"/>
		<property name="testOnBorrow" value="true" />
		<property name="testOnReturn" value="true" />
	</bean>
	
	<bean id = "shardedJedisPool" class = "redis.clients.jedis.ShardedJedisPool">
		<constructor-arg index="0" ref="poolConfig"/>
		<constructor-arg index="1">
			<list>
				<bean class="redis.clients.jedis.JedisShardInfo">
					<constructor-arg index="0" value="123.206.89.59" type="String"/>
					<!-- shard name -->
					<constructor-arg index="1" value="Shard-1" type="String"/>
					<constructor-arg index="2" value="6379" type="int"/>
					<!-- timeout,default is 2 sec -->
					<constructor-arg index="3" value="2000" type="int"/>
					<!-- weight,default is 1 -->
					<constructor-arg index="4" value="1" type="int"/>
				</bean>
			</list>
		</constructor-arg>
	</bean>
</beans>
   2:spring-context.xml中引入redis-config.xml
     <import resource="redis-config.xml"/>
