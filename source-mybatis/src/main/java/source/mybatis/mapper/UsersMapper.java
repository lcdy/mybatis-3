package source.mybatis.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import source.mybatis.domain.Users;

/**
 * @author lla, 2021/2/7  17:02
 */
@CacheNamespace
public interface UsersMapper {
    @Select("select * from users where id = #{id}")
    Users selectById(@Param("id") Integer id);
}
