package com.vauen.yann.utils.redis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisManager {

	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/**
	* @Title: ttl  
	* @Description: TODO(实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @return    参数  
	* @return long    返回类型  
	* @throws
	 */
	public long ttl(String key) {
		return redisTemplate.getExpire(key);
	}
	/**
	* @Title: expire  
	* @Description: TODO(实现命令：expire 设置过期时间，单位秒)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param timeout    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void expire(String key, long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	/**
	* @Title: incr  
	* @Description: TODO(实现命令：INCR key，增加key一次)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param delta
	* @param @return    参数  
	* @return long    返回类型  
	* @throws
	 */
	public long incr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}
	/**
	* @Title: keys  
	* @Description: TODO(实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param pattern
	* @param @return    参数  
	* @return Set<String>    返回类型  
	* @throws
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	/**
	* @Title: del  
	* @Description: TODO(实现命令：DEL key，删除一个key)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}
	/**
	* @Title: set  
	* @Description: TODO(实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param value    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	* @Title: set  
	* @Description: TODO(实现命令：SET key value EX seconds，设置key-value和超时时间（秒）)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param value
	* @param @param timeout    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}
	/**
	* @Title: get  
	* @Description: TODO(实现命令：GET key，返回 key所关联的字符串值。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}
	/**
	* @Title: hset  
	* @Description: TODO(实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param field
	* @param @param value    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}
	/**
	* @Title: hget  
	* @Description: TODO(实现命令：HGET key field，返回哈希表 key中给定域 field的值)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param field
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}
	/**
	* @Title: hdel  
	* @Description: TODO(实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param fields    参数  
	* @return void    返回类型  
	* @throws
	 */
	public void hdel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}
	/**
	* @Title: hgetall  
	* @Description: TODO(实现命令：HGETALL key，返回哈希表 key中，所有的域和值。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @return    参数  
	* @return Map<Object,Object>    返回类型  
	* @throws
	 */
	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	* @Title: lpush  
	* @Description: TODO( 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param value
	* @param @return    参数  
	* @return long    返回类型  
	* @throws
	 */
	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}
	/**
	* @Title: lpop  
	* @Description: TODO(实现命令：LPOP key，移除并返回列表 key的头元素。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String lpop(String key) {
		return (String)redisTemplate.opsForList().leftPop(key);
	}
	/**
	* @Title: rpush  
	* @Description: TODO(实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param key
	* @param @param value
	* @param @return    参数  
	* @return long    返回类型  
	* @throws
	 */
	public long rpush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}
}
