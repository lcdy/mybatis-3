package source.mybatis.mapper;

import source.mybatis.domain.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
  @Select("select * from role where id = 2")
  Role selectById();
}
