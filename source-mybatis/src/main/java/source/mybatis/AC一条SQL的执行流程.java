package source.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import source.mybatis.domain.Users;
import source.mybatis.mappers.anno.RolesMapper;

import java.io.IOException;
import java.util.Collection;

/**
 * @author lla, 2021/2/7  16:45
 * <p>
 * 分析sql的执行流程
 * <p>
 * <p>
 * 每执行一次sql都会创建一个statementHandler对象
 * statementHandler: (parameterHandler，resultHandler: 依赖typeHandler)
 * parameterHandler: parameterValue+BoundSql
 */
@SuppressWarnings("all")
public class AC一条SQL的执行流程 {
    public static void main(String[] args) throws Exception {
        什么是MappedStatement();
        执行示例sql();
    }


    /*
     * mappedStatement是一个字符串、是一个方法的全限定类名。
     * 包含sql内容，sql类型，参数和返回值的类型
     * */
    public static void 什么是MappedStatement() throws Exception {
        SqlSessionFactory sessionFactory = MybatisUtils.getSessionFactory();
        Configuration configuration = sessionFactory.getConfiguration();
        // 注册一个插件
        // configuration.addInterceptor(new PagePlugin());
        Collection<String> mappedStatementNames = configuration.getMappedStatementNames();
        for (String mappedStatement : mappedStatementNames) {
            System.out.println(mappedStatement);
        }

        // 测试获取sql
        MappedStatement mappedStatement = configuration.getMappedStatement("source.mybatis.mappers.xml.UsersMapper.selectById");
        SqlSource sqlSource = mappedStatement.getSqlSource();
        // Object parameterObject
        BoundSql boundSql = sqlSource.getBoundSql(ParamNameResolver.wrapToMapIfCollection(1, null));
        String sql = boundSql.getSql();
    }

    private static void 执行示例sql() throws Exception {

        SqlSession sqlSession = MybatisUtils.getSessionFactory().openSession();

        Users users = sqlSession.selectOne("source.mybatis.mapper.xml.UsersMappers.selectById", 1);
        sqlSession.getMapper(RolesMapper.class);
        System.out.println(users);
    }

    /*
    * 原生jdbc: Statement stmt = createStatement();
    * mybatis: Statement stmt = handler.prepare();
    *
    * parameterHandler能操作的只有
    *
    * */
    private static void statementHandler的作用(){

    }
}
