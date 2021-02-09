package source.mybatis.mapper.anno;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import source.mybatis.domain.Roles;
import org.apache.ibatis.annotations.Select;

@Mapper
@CacheNamespace
public interface RolesMapper {
    @Select("select * from role where id = #{id}")
    Roles selectById(@Param("id") Integer id);
}
