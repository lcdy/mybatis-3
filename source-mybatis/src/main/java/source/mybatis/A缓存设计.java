package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import source.mybatis.domain.Users;

import java.io.IOException;

/**
 * @author lla, 2021/2/7  16:45
 */
public class A缓存设计 {

    public static void main(String[] args) throws IOException {

        // 生命周期：一次会话
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        // Users users1 = usersMapper.selectById(1);
        // System.out.println(users1);
        // 会调用DefaultSqlSession.selectList()这个方法
        // 执行sql
        Users users = sqlSession.selectOne("source.mybatis.domain.Users.selectById", 1);
        System.out.println(users);
    }
}