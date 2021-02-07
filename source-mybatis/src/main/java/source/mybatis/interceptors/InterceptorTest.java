package source.mybatis.interceptors;

import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.util.Properties;


/**
 * 测试使用拦截器
 */
public class InterceptorTest implements Interceptor {


  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    return null;
  }

  @Override
  public Object plugin(Object target) {

    String simpleName = target.getClass().getSimpleName();
    System.out.println(simpleName + "**********");
    switch (simpleName) {
      case "CachingExecutor":
        CachingExecutor target0 = (CachingExecutor) target;
        System.out.println(target0);
        break;
      case "DefaultParameterHandler":
        DefaultParameterHandler target1 = (DefaultParameterHandler) target;
        Object parameterObject = target1.getParameterObject();
        break;
      case "DefaultResultSetHandler":
        DefaultResultSetHandler target2 = (DefaultResultSetHandler) target;
        break;
      case "RoutingStatementHandler":
        RoutingStatementHandler target3 = (RoutingStatementHandler) target;
        BoundSql boundSql = target3.getBoundSql();
        if (boundSql != null)
          // 黑 红 绿 黄 蓝 紫 深蓝 白色
          // 字体，背景（0-7）
          System.out.println("\033[33;1m" + boundSql.getSql() + "\033[0m");
        break;
    }
    return target;
  }

  @Override
  public void setProperties(Properties properties) {

  }
}
