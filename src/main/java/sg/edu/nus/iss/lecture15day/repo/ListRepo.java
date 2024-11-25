package sg.edu.nus.iss.lecture15day.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.lecture15day.Util.Util;
import sg.edu.nus.iss.lecture15day.config.RedisConfig;


@Repository
public class ListRepo {
  
  @Autowired
  @Qualifier(Util.template01)
  RedisTemplate<String,String> template;

  //slide 30-34 
  public void leftPush (String key,String Value){
    template.opsForList().leftPush(key, Value);
  }

  public void rightPush(String Key, String Value){
    template.opsForList().rightPush(Key, Value);
  }

  public void leftPop(String key, String Value){
    template.opsForList().leftPop(key);
  }

  public void rightPop(String key, String Value){
    template.opsForList().rightPop(key);
  }

  public String indexPosition(String key, Integer index){
    return template.opsForList().index(key, index);
  }

  public Long listSize(String key){
    return template.opsForList().size(key);
  }

  public List<String> getList(String key){
    List<String> list = template.opsForList().range(key, 0, -1);
    return list;
  }

  public void remove(String key, Integer count,String value){
       /*
         * Removes elements equal to the value:
         * - count > 0: Remove `count` occurrences from the head (left).
         * - count < 0: Remove `count` occurrences from the tail (right).
         * - count == 0: Remove all occurrences of the value.
         */
    template.opsForList().remove(key, count, value);
  }


  public Boolean delete(String Key){
    return template.delete(Key);
  }
}
