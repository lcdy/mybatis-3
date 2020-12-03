package com.slk.mybatis;

import com.slk.mybatis.config.DruidDataSource;
import com.slk.mybatis.interceptors.InterceptorA;
import com.slk.mybatis.mapper.RoleMapper;
import com.slk.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class AppMain {
  private static SqlSessionFactory sqlSessionFactory;
  private static SqlSession sqlSession;


  public static void main(String[] args) {
    try {
      SqlSession sqlSession = getSqlSessionByConfig();

      // 准备资源
      RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
      System.out.println(mapper.selectById().getRolename());
      System.out.println(sqlSession.getMapper(UserMapper.class).selectById().getUsername());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(sqlSession);
    }
  }

  /*
   * 分析流程：先分析session，在分析事物
   *
   * */

  public static SqlSession getSqlSessionByConfig() throws Exception {

    DataSource dataSource = DruidDataSource.getDataSource();

    // TransactionFactory(DataSource ds, TransactionIsolationLevel level, boolean autoCommit)
    // 连接池，事务隔离级别，是否自动提交（默认false）
    // 如果这里没有配置，Environment会使用默认的配置
    // todo 分析事物
    TransactionFactory transactionFactory = new JdbcTransactionFactory();

    // id环境隔离: 不同的环境给不同的数据库连接池
    // 这里：简单的给三个属性赋值
    Environment environment = new Environment("development", transactionFactory, dataSource);

    // todo 重要
    Configuration configuration = new Configuration(environment);
    configuration.addMappers("com.slk.mybatis.mapper");
    configuration.addInterceptor(new InterceptorA());

    // 关键看build方法
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

    // build方法
    // 单纯的new了一个，给属性赋值
    sqlSessionFactory = sqlSessionFactoryBuilder.build(configuration);


    /*
     *
     * 这里会应用拦截器
     * */
    sqlSession = sqlSessionFactory.openSession();
    System.out.println(sqlSession.toString());
    return sqlSession;
  }

  public static SqlSession getSqlSessionByXML() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    return sqlSessionFactory.openSession();
  }

  public static void close(SqlSession sqlSession) {
    if (sqlSession != null) sqlSession.close();
  }


}
