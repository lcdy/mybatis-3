package source.mybatis.mapper.anno;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import source.mybatis.Page;
import source.mybatis.domain.Roles;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@CacheNamespace
public interface RolesMapper {
    @Select("select * from roles where id = #{id}")
    Roles selectById(@Param("id") Integer id);

    @Select("select * from roles where id > #{id} limit #{limit}")
    List<Roles> selectListTwoParameter(@Param("id") Integer id, @Param("limit")Integer limit);

    @Select("select * from roles")
    List<Roles> selectListPageable(@Param("page") Page page);
}
