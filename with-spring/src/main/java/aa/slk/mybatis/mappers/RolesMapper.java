package aa.slk.mybatis.mappers;

import aa.slk.mybatis.domain.Roles;
import aa.slk.mybatis.interceptors.Page;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.impl.PerpetualCache;

import java.util.List;

@CacheNamespace(implementation = PerpetualCache.class)  // 全注解生效
public interface RolesMapper {
    @Select("select * from roles where id = #{id}")
    Roles selectById(@Param("id") Integer id);

    @Select("select * from roles where id > #{id} limit #{limit}")
    List<Roles> selectListTwoParameter(@Param("id") Integer id, @Param("limit") Integer limit);

    @Select("select * from roles")
    List<Roles> selectListPageable(@Param("page") Page page);
}
