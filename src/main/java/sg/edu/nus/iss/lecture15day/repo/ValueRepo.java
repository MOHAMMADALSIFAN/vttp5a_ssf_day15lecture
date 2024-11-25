package sg.edu.nus.iss.lecture15day.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.lecture15day.Util.Util;

@Repository
public class ValueRepo {
  @Autowired
  @Qualifier(Util.template01)
  RedisTemplate <String,String> template;

  public void createValue(String key,String value){
    template.opsForValue().set(key, value);
  }
  
  public String readvalue(String key){
   return template.opsForValue().get(key);
  }

  public Boolean deleteValue(String key){
   return template.delete(key);
  }

  //slide 26 - only works for key with integer value
  public void incrementValue(String key){
     template.opsForValue().increment(key);
  }
  public void decrementValue(String Key){
    template.opsForValue().decrement(Key);
  }
  public void incrementByValue(String Key,Integer value){
    template.opsForValue().increment(Key, value);
  }

  public void decrementByValue(String Key, Integer Value){
    template.opsForValue().decrement(Key, Value);
  }

  //Slide 28
  public Boolean checkExists(String Key){
    return template.hasKey(Key);
  }

}
