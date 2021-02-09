package source.mybatis;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author lla, 2021/2/9  17:17
 * 需要指定拦截那个方法，选择一个合理的方法，对参数修改
 *
 */
@SuppressWarnings("all")
public class C插件 {

    public static void main(String[] args) {

        // 四个对象的接口共有哪些方法可以拦截，合理选择
    }

    /**
     * 只有两个方法，PreparedStatement属于java.sql包，原则上不能改其属性
     */
    private static void 拦截ParameterHandler() {
        // Object getParameterObject();
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
