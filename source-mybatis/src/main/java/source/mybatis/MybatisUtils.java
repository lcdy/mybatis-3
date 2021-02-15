package source.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import source.mybatis.domain.Users;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lla, 2021/2/9  12:39
 */
public class MybatisUtils {

    static InputStream resourceAsStream;
    static SqlSessionFactoryBuilder factoryBuilder;
    static SqlSessionFactory sqlSessionFactory;
    static SqlSession sqlSession;

    public static SqlSessionFactory getSessionFactory() throws IOException {
        if (sqlSessionFactory == null) {
            resourceAsStream = Resources.getResourceAsStream("mybatisConfig.xml");
            factoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = factoryBuilder.build(resourceAsStream);
            resourceAsStream.close();
        }
        return sqlSessionFactory;
    }

    /**
     * 测试一级缓存
     * @return SqlSession
     * @throws IOException x
     */
    public static SqlSession getSqlSession() throws IOException {
        if (sqlSession == null){
            sqlSession = getSessionFactory().openSession();
        }
        return sqlSession;
    }

    public static SqlSession getSqlSession(Boolean sameSession) throws IOException {
        getSqlSession();
        if (sameSession) {
            return sqlSession;
        } else {
            return getSessionFactory().openSession();
        }
    }

    public static Configuration getConfiguration() throws IOException {
        if (sqlSessionFactory == null) {
            getSessionFactory();
        }
        return sqlSessionFactory.getConfiguration();
    }
}
