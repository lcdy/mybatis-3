package source.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import source.mybatis.domain.Users;
import source.mybatis.mapper.UsersMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lla, 2021/2/7  16:45
 */
public class ACache {
    static InputStream resourceAsStream;
    static SqlSessionFactoryBuilder factoryBuilder;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession sqlSession;

    public static void before() throws IOException {
        resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        factoryBuilder = new SqlSessionFactoryBuilder();

        // 生命周期贯穿整个程序
        sqlSessionFactory = factoryBuilder.build(resourceAsStream);
    }

    public static void main(String[] args) throws IOException {
        before();
        // 生命周期：一次会话
        sqlSession = sqlSessionFactory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        Users users1 = usersMapper.selectById(1);
        System.out.println(users1);
        // 会调用DefaultSqlSession.selectList()这个方法
        Users users = sqlSession.selectOne("source.mybatis.domain.Users.selectOne");
        System.out.println(users);
        after();
    }

    public static void after() throws IOException {

        if (sqlSession != null) {
            sqlSession.close();
        }
        if (resourceAsStream != null) {
            resourceAsStream.close();
        }
    }
}
