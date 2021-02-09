package source.mybatis.mapper.xml;

import source.mybatis.domain.Users;

/**
 * @author lla, 2021/2/9  12:23
 *
 * XML方式: 方法名字什么的要和XML中一一对应
 *
 * NameSpace和权限定类名一致
 * 方法名和标签id一致
 * 参数与返回值类型和parameterType、reusltType对应
 */
public interface UsersMapper {
    Users selectById(Integer id);
}
