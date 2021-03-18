package source.mybatis.mappers.anno;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.impl.PerpetualCache;
import source.mybatis.Page;
import source.mybatis.domain.Roles;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@CacheNamespace(implementation = PerpetualCache.class)  // 全注解生效
public interface RolesMapper {
    @Select("select * from roles where id = #{id}")
    Roles selectById(@Param("id") Integer id);

    @Select("select * from roles where id > #{id} limit #{limit}")
    List<Roles> selectListTwoParameter(@Param("id") Integer id, @Param("limit")Integer limit);

    @Select("select * from roles")
    List<Roles> selectListPageable(@Param("page") Page page);
}
