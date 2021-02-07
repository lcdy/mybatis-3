package source.mybatis.mapper;


import source.mybatis.domain.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;


@CacheNamespace// 虽然默认开启二级缓存，但是这里仍然需要添加这个注解
public interface UserMapper {
  @Select("select * from user where id = 10")
  User selectById();
}
