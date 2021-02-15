package source.mybatis;

import org.apache.ibatis.session.*;
import source.mybatis.domain.Roles;
import source.mybatis.interceptors.PagePlugin;
import source.mybatis.mapper.anno.RolesMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author lla, 2021/2/9  17:17
 * 需要指定拦截那个方法，选择一个合理的方法，对参数修改
 *
 */
@SuppressWarnings("all")
public class C插件 {
    public static void main(String[] args) throws IOException {
        // 四个对象的接口共有哪些方法可以拦截，合理选择
        Configuration configuration = MybatisUtils.getConfiguration();
        configuration.addInterceptor(new PagePlugin());
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RolesMapper mapper = sqlSession.getMapper(RolesMapper.class);
        // System.out.println(mapper.selectById(1));
        // List<Roles> roles = mapper.selectListTwoParameter(10, 2);
        /*
        *
        * -- 起始页码为1的两种写法
        * >= (num -1)  * size + 起始id limit size;
        * > ((num -1)  * size) + 起始id - 1  limit size;
        *
        * -- 起始页码为0
        * >= num*size + 起始id limit size;
        * >  num * size + 起始id - 1  limit size;
        *
        * */
        // (num - 1)*2+1, id应该是6,7,8
        List<Roles> roles = mapper.selectListPageable(new Page(5, 4));
        for (Roles role : roles) {
            System.out.println(role);
        }
    }

    /**
     * 只有两个方法，PreparedStatement属于java.sql包，原则上不能改其属性
     */
    private static void 拦截ParameterHandler() {
        // Object getParameterObject(); 不能用
        // void setParameters(PreparedStatement ps) throws SQLException;
    }
    /**
     * 只能修改result
     */
    private static void 拦截ResultHandler() {
        // void handleResult(ResultContext<? extends T> resultContext);
    }

    /**
     * 一般选择拦截statementHandler
     */
    private static void 拦截StatementHandler() {
        // Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;
        // void parameterize(Statement statement) throws SQLException;
        // void batch(Statement statement)
        // int update(Statement statement)
        // <E> List<E> query(Statement statement, ResultHandler resultHandler)
        // <E> Cursor<E> queryCursor(Statement statement)
        // BoundSql getBoundSql();
        // ParameterHandler getParameterHandler();
    }

    /**
     * 能拦截的东西有很多
     */
    private static void 拦截Executor() {
        // int update(MappedStatement ms, Object parameter) throws SQLException;
        // <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;
        // <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
        // <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException;
        // List<BatchResult> flushStatements() throws SQLException;
        // void commit(boolean required) throws SQLException;
        // void rollback(boolean required) throws SQLException;
        // CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);
        // boolean isCached(MappedStatement ms, CacheKey key);
        // void clearLocalCache();
        // void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);
        // Transaction getTransaction();
        // void close(boolean forceRollback);
        // boolean isClosed();
        // void setExecutorWrapper(Executor executor);
    }
}
