package com.javaee;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-09-25 09:37
 */
public class JedisEx {

    JedisPool pool;

    public JedisEx() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxTotal(10);
        pool = new JedisPool(poolConfig, "192.168.247.132",
                6379,2000,"1qaz!QAZ");
    }

    public void addZset(String key, String number, Double score) {
        try (Jedis jedis = pool.getResource()) {
            jedis.zadd(key, score, number);
        }
    }


    public static void main(String[] args) {
        String key = "contact";
        JedisEx jedisEx = new JedisEx();
        try (Jedis jedis = jedisEx.pool.getResource()) {
            jedis.watch(key);
            Transaction transaction = jedis.multi();
            transaction.zadd(key, 0.0, "xiaqing12");
            transaction.zadd(key, 0.0, "xiaqing12");
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Object> list = transaction.exec();
        }

    }
}
