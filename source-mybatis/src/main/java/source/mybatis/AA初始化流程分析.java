package source.mybatis;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import source.mybatis.dbConfig.DruidDataSource;
import source.mybatis.domain.Roles;
import source.mybatis.mappers.anno.RolesMapper;

import javax.sql.DataSource;

/**
 * ProcessAnalysis
 * Configuration: 对应xml中`<configuration>`
 * Environment: 对应xml中`<environments>`
 * configuration.addMappers(packageName): 对应xml中`<mappers>`
 */
@SuppressWarnings("ALL")
public class AA初始化流程分析 {
    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();
        DataSource dataSource = DruidDataSource.getDataSource();

        // 环境初始化
        Environment environment = new Environment("dev", new JdbcTransactionFactory(), dataSource);
        configuration.setEnvironment(environment);

        // 指定mapper位置, xml和anno都支持
        configuration.addMappers("source.mybatis.mappers");
        configuration.setLogImpl(Slf4jImpl.class);

        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        // java配置的方式和xml一样
        SqlSessionFactory sqlSessionFactory = factoryBuilder.build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 从getMapper分析: 基于JDK动态为mapper接口创建代理对象
        // 涉及到笔记重要的对象: MapperRegistry MapperProxyFactory MapperProxy(实现了InvocationHandler)
        // 具体的处理逻辑在MapperProxy的invoke方法中。在SQL执行流程中分析
        RolesMapper rolesMapper = sqlSession.getMapper(RolesMapper.class);
        Roles roles = rolesMapper.selectById(1);
        Roles role1 = rolesMapper.selectById(1);
        System.out.println(roles);
    }
}
