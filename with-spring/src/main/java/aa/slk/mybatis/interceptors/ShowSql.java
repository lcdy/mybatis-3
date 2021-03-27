package aa.slk.mybatis.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;


/**
 * 对四大对象的方法进行拦截
 * type：四大对象之一
 * method：对象的方法
 * args: 方法的参数
 * 这三个属性才能确定唯一的方法
 */
@SuppressWarnings("all")
@Intercepts(@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
))
public class ShowSql implements Interceptor {

    // Invocation：封装了type,method,args。就是对拦截对象的方法的封装
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        assert invocation.getMethod().getName().equals("prepare");
        // Class<? extends StatementHandler> aClass = statementHandler.getClass();
        // Field delegate = aClass.getField("delegate");
        MetaObject metaObject = MetaObject.forObject(
                statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory()
        );
        Object methodName = metaObject.getValue("delegate.mappedStatement.id");
        Object value = metaObject.getValue("delegate.boundSql.sql");
        // 黑 红 绿 黄 蓝 紫 深蓝 白色
        // 字体，背景（0-7）
        // 黑 红 绿 黄 蓝 紫 深蓝 白色
        // 字体，背景（0-7）
        System.out.println("\t\033[31;1m" + methodName.toString() + "\033[0m");
        System.out.println("\t\033[31;1m" + value.toString() + "\033[0m");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // target：配置的方法所属类（type）
        // this: 代理对象，当前类
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
