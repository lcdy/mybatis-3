package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import source.mybatis.domain.Roles;
import source.mybatis.mappers.anno.RolesMapper;

/**
 * @author lla, 2021/2/7  16:45
 * <p>
 * 同一session共享一级缓存
 */
public class AB缓存设计 {

    public static void main(String[] args) throws Exception {

        SqlSessionFactory sessionFactory = MybatisUtils.getSessionFactory();
        SqlSession sqlSession = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();

        RolesMapper rolesMapper = sqlSession.getMapper(RolesMapper.class);
        Roles roles = rolesMapper.selectById(1);

        // 只有会话提交或commit之后才会刷新二级缓存
        // 返回对象支持序列化
        sqlSession.commit();
        RolesMapper rolesMapper2 = sqlSession2.getMapper(RolesMapper.class);
        Roles roles2 = rolesMapper2.selectById(1);
        sqlSession2.commit();

        /*
         * BaseExecutor(perpetualCache)
         * 一级缓存：session级别比较简单，直接对perpetualCache对象操作
         *
         * TranslationalCache(perpetualCache)
         * 二级缓存sqlSessionFactory级别级别设计很复杂:
         * 和事务相关, translationCache.get ==> xx ==>xx ==> xx ==> xx ==> xx ==> perpetualCache.get
         *
         * cacheExecutor先从二级获取，再从一个获取缓存，然后查询数据库
         * */

        sqlSession.close();
        sqlSession2.close();
    }
}