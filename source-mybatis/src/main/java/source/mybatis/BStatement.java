package source.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import source.mybatis.domain.Users;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lla, 2021/2/7  16:45
 * 每执行一次sql都会创建一个statementHandler对象
 * statementHandler: 参数，返回结果
 */
public class BStatement {
    static InputStream resourceAsStream;
    static SqlSessionFactoryBuilder factoryBuilder;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession sqlSession;

    public static void main(String[] args) throws IOException {
        try {
            before();
            sqlSession = sqlSessionFactory.openSession();
            Users users = sqlSession.selectOne("source.mybatis.domain.Users.selectOne");
            System.out.println(users);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            after();
        }
    }


    public static void before() throws IOException {
        resourceAsStream = Resources.getResourceAsStream("mybatisConfig.xml");
        factoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = factoryBuilder.build(resourceAsStream);
    }

    public static void after() throws IOException {
        if (sqlSession != null) sqlSession.close();
        if (resourceAsStream != null) resourceAsStream.close();
    }
}
