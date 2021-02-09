package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import source.mybatis.domain.Roles;
import source.mybatis.mapper.anno.RolesMapper;

import java.io.IOException;

/**
 * @author lla, 2021/2/9  12:26
 *
 * sql使用注解的方式放到dao的方法上面
 */
public class WithInterface {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 代理对象，其实是一个xx工厂
        RolesMapper rolesMapper = sqlSession.getMapper(RolesMapper.class);
        Roles roles = rolesMapper.selectById(1);
        System.out.println(roles);
    }
}
