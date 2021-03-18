package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import source.mybatis.mappers.anno.RolesMapper;

import java.io.IOException;

/**
 * @author lla, 2021/2/12  18:52
 * RolesMapper的代理对象是如何产生的？
 * 找到存放代理对象的那个map，一步一步开始分析
 */
@SuppressWarnings("all")
public class D代理对象 {
    public static void main(String[] args) throws Exception {
        SqlSession sqlSession = MybatisUtils.getSessionFactory().openSession();

        /*
        * MapperProxy implements InvocationHandler
        * new MapperProxy(sqlSession, mapperInterface)
        * 代理对象 = MapperProxyFactory.newInterface(classLoadeer, interface, new MapperProxy(sqlSession, mapperInterface))
        *
        * 方法拦截：应该是调用sqlSession里面的方法
        * selectAnno == method.getAnnocation
        * sqlSession.select(selectAnno.value)
        * 走缓存，走cachingExecutor -> simpleExcutor -> baseExecutor -> xxx
        * */
        RolesMapper rolesMapper = sqlSession.getMapper(RolesMapper.class);
        System.out.println(rolesMapper.selectById(1));
    }
}
