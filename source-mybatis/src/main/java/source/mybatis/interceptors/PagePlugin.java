package source.mybatis.interceptors;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author lla, 2021/2/9  15:52
 * <p>
 * 在parameterHandler中也可以获取boundSql
 */
@SuppressWarnings("all")
@Intercepts(@Signature(
        // type = StatementHandler.class,
        type = ParameterHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
))
public class PagePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // Invocation：封装了type,method,args。就是对拦截对象的方法的封装
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        assert invocation.getMethod().getName().equals("prepare");
        MetaObject metaObject = MetaObject.forObject(
                statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory()
        );
        Object methodName = metaObject.getValue("delegate.mappedStatement.id");
        Object value = metaObject.getValue("delegate.boundSql.sql");
        System.out.println("\t\033[31;1m" + methodName.toString() + "\033[0m");
        System.out.println("\t\033[31;1m" + value.toString() + "\033[0m");
        metaObject.setValue("delegate.boundSql.sql", value.toString()+" limit 1");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
