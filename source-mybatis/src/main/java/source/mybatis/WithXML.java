package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import source.mybatis.domain.Users;

import java.io.IOException;

/**
 * @author lla, 2021/2/9  12:26
 *
 * XML方式: dao方法名字什么的要和XML中一一对应
 *
 * NameSpace和权限定类名一致
 * 方法名和标签id一致
 * 参数与返回值类型和parameterType、reusltType对应
 */
public class WithXML {
    public static void main(String[] args) throws IOException {
        // 会话级别，请求完成session结束
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 发送sql
        Users users = sqlSession.selectOne("source.mybatis.mapper.xml.UsersMapper.selectById", 1);
        System.out.println(users);
        // 命中一级缓存(需要支持序列化): 没有执行增删改, statement和参数是完全一样的
        Users users2 = sqlSession.selectOne("source.mybatis.mapper.xml.UsersMapper.selectById", 1);
        System.out.println(users2);
        sqlSession.close();
    }
}
