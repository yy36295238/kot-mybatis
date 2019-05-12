# kot-mybatis
mybatis 样例 工程
> 依赖项目：[https://github.com/yy36295238/kotmybatis-spring-boot-starter](https://github.com/yy36295238/kotmybatis-spring-boot-starter/)

### 更新内容
- 2019-05-12 
> 增加驼峰注解: **@ToCamel**，可以把返回结果为List<Map>和Map类型中的key转成驼峰类型
  
  
  ```java
  
  // Mapper
 public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where user_name = #{userName}")
    List<Map<String, Object>> findListByUserNameForMap(@Param("userName") String userName);

    @Select("select * from user where user_name = #{userName}")
    Map<String, Object> findByUserNameForMap(@Param("userName") String userName);
}
```

```java
// Service
@Service
public class UserService extends MapperManagerServiceImpl<User> {

    @Autowired
    private UserMapper userMapper;

    @ToCamel
    public List<Map<String, Object>> findListByUserNameForMap(String userName) {
        return userMapper.findListByUserNameForMap(userName);
    }

    @ToCamel
    public Map<String, Object> findByUserNameForMap(String userName) {
        return userMapper.findByUserNameForMap(userName);
    }
}
```

```java
// AOP
@Aspect
@Component
public class ToCamelWrapper {

    @Pointcut("@annotation(com.kot.kotmybatis.annotation.ToCamel)")
    public void toCamelPointcut() {
    }

    @Around("toCamelPointcut()")
    public Object checkParams(ProceedingJoinPoint pdj) throws Throwable {
        if (pdj.proceed() instanceof List) {
            return Tools.toCamel((List<Map<String, Object>>) pdj.proceed());
        }
        if (pdj.proceed() instanceof Map) {
            return Tools.toCamel((Map<String, Object>) pdj.proceed());
        }
        return pdj.proceed();

    }

}
```
  #### 结果
```
{realName=lisi, password=123, **userStatus**=0, **createTime**=2019-05-13 04:34:00.0, updateTime=2019-05-13 04:34:26.0, createUser=0, id=1, userName=lisi, activation=0}
```
