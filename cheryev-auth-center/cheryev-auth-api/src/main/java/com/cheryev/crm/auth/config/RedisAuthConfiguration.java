package com.cheryev.crm.auth.config;

import com.cheryev.crm.auth.pojo.BaseModuleResourcesVO;
import com.cheryev.crm.auth.pojo.BaseRoleVO;
import com.cheryev.crm.auth.utils.RedisObjectSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by fp295 on 2018/4/12.
 * Redis配置类
 */
@Configuration
public class RedisAuthConfiguration {


    @Value("${redis.server.pool.size}")
    private int poolSize;

    @Value("${redis.server.url}")
    private String url;

    @Value("${redis.server.port}")
    private int port;

    @Value("${redis.server.key}")
    private String key;

    @Value("${redis.server.ssl}")
    private boolean useSsl;

    @Value("${redis.server.pool.idle.min:5}")
    private int poolMinIdle;

    @Value("${redis.server.pool.idle.max:2000}")
    private int poolMaxIdle;

    @Value("${redis.server.pool.wait.max:5000}")
    private int maxWaitMillisOnCall;

    @Value("${redis.server.pool.evictionrun.testnum:3}")
    private int numTestPerEvictionRun;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //开启空闲资源监测
        poolConfig.setTestWhileIdle(true);
        //设置，做空闲资源检测时，每次的采样数
        poolConfig.setNumTestsPerEvictionRun(Math.min(numTestPerEvictionRun, 3));

        //设置资源池允许的最小空闲连接数
        poolConfig.setMinIdle(Math.min(poolMinIdle, poolMaxIdle));
        //设置资源池允许的最大空闲连接数
        poolConfig.setMaxIdle(Math.max(poolMinIdle, poolMaxIdle));
        //设置：当资源池用尽后，调用者必须等待
        poolConfig.setBlockWhenExhausted(true);
        //设置，资源池连接用尽后，调用者的最大等待时间（毫秒）
        poolConfig.setMaxWaitMillis(Math.min(maxWaitMillisOnCall, 5000));

        poolConfig.setMaxTotal(poolSize);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        //单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(url);
        //设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(0);
        //设置密码
        if(!StringUtils.isEmpty(key)) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(key));
        }
        //设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);

        //获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(poolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();

        //单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, BaseRoleVO> baseRoleTemplate() {
        RedisTemplate<String, BaseRoleVO> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, BaseModuleResourcesVO> baseModelTemplate() {
        RedisTemplate<String, BaseModuleResourcesVO> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}
