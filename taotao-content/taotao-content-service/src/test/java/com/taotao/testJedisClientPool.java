package com.taotao;

import com.taotao.content.jedis.service.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2018/4/20.
 */
public class testJedisClientPool {

    @Test
    public void testJedisClientPool(){
        //初始化Spring容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        //从容器中获取JedisClient对象
        JedisClient jedisClient = (JedisClient) ctx.getBean("jedisClientCluster");
        //使用JedisClient操作Redis
        String result = jedisClient.get("name");
        System.out.println(result);
    }
}
