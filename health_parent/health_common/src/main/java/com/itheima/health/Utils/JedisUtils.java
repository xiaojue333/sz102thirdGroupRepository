package com.itheima.health.Utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class JedisUtils {
    // 配置对象
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();


    //1.创建连接池
    private static JedisPool pool = new JedisPool(jedisPoolConfig, "localhost", 6379);


    // 2 提供获取连接的方法
    public static Jedis getConn() {
        Jedis jedis = pool.getResource();
        return jedis;
   


    }


    //3 提供关闭资源的方法
    public static void close(Jedis jedis){
        jedis.close();
    }
}
