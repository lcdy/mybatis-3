package source.mybatis.interceptors;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import source.mybatis.Page;

import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Properties;

/**
 * @author lla, 2021/2/9  20:58
 */
@SuppressWarnings("all")
@Intercepts(@Signature(
        type = ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class}
))
public class ParameterHandler插件 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultParameterHandler parameterHandler = (DefaultParameterHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(
                parameterHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory()
        );
        // 修改sql没用的，字符串是拷贝的。parameterHandler中的bound.sql和statementHandler中的bound.sql不一样
         String value = (String) metaObject.getValue("boundSql.sql");
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
