package com.slk.mybatis.config;

import com.slk.mybatis.mapper.RoleMapper;
import com.slk.mybatis.mapper.UserMapper;
import lombok.Data;
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

@Data
public class SqlSessionConfig {

  private static SqlSessionFactory sqlSessionFactory = null;
  private static SqlSession sqlSession = null;

  public static void main(String[] args) {
    try {
      SqlSession sqlSession = getSqlSessionByConfig();
      System.out.println(sqlSession.getMapper(RoleMapper.class).selectById().getRolename());
      System.out.println(sqlSession.getMapper(UserMapper.class).selectById().getUsername());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(sqlSession);
    }
  }


  public static SqlSession getSqlSessionByConfig() throws Exception {

    DataSource dataSource = DruidDataSource.getDataSource();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    configuration.addMappers("com.slk.mybatis.mapper");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    return sqlSessionFactory.openSession();
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
