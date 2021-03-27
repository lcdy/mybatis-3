package source.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import source.mybatis.domain.Goods;
import source.mybatis.domain.Users;
import source.mybatis.mappers.GoodsMapper;
import source.mybatis.mappers.UsersMapper;

import java.util.ArrayList;
import java.util.Random;

/**
 * 条件: 如果字段存在，拼接该字段
 * <if test='#{user.username} != null'> and username = #{user.username} </if>
 * <p>
 * <p>
 * 选择: 从条件中选择一个
 * SELECT * FROM book WHERE state = 'ACTIVE'
 * <choose>
 * <!-- 书名?存在 -->
 * <when test='#{book.name} != null'> and name = #{book.name.gender} </when>
 * <!-- 书中人物?存在 -->
 * <when test='#{book.role} != null'> and role = #{book.role} </when>
 * <!-- 书名不存在，相关人物不存在，推荐 作家其他书籍 -->
 * <otherwise> and author = #{book.author} </otherwise>
 * </choose>
 * <p>
 * <p>
 * 容错：
 * <!-- where跟多条件: 使用where标签. 如果条件不存在 sql == select * from xx where == 异常 -->
 * <where></where>
 * <p>
 * <!-- 移除所有 prefixOverrides, 插入 prefix  -->
 * <trim prefix="WHERE" prefixOverrides="AND |OR ">
 * ...
 * </trim>
 * <p>
 * <p>
 * set:
 * update users set enabled = 0, `password` = "xcsds" where id = 1;
 * <p>
 * update users
 * <set>
 * <if>enabled = 0,</if>
 * <if>'password' = 'xcsds'</if>
 * </set>
 * where id = #{users.id};
 * 相当于: <trim prefix="set" prefixOverrides=","></set>
 * <p>
 * <p>
 * 迭代, index不用管
 * SELECT * FROM POST P WHERE ID in
 * <foreach collection="# idList" index="index" item="id_" open="(" separator="," close=")">
 * #{id_}
 * </foreach>
 * <p>
 * bind:
 * 创建一个变量，<bind name="pattern" value="'%' + _parameter.getTitle() + '%'" />
 * 变量: pattern = %title%
 * WHERE title LIKE #{pattern}
 */
@SuppressWarnings("all")
public class UseMybatis {

    static SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;


    /**
     * 测试动态SQL
     */
    @Test
    public void dynamicSql() {
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        Users users = usersMapper.findById(1);
        users.setId(1);
        users.setEnabled(0);
        users.setPassword("xxxxxxxxxxxx");
        usersMapper.updateUsersIfNecessary(users);
    }

    /**
     * goods表批量添加
     */
    @Test
    public void insertList() throws InterruptedException {
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        ArrayList<Goods> goodsList = new ArrayList<>();
        Random random = new Random(100);
        for (int i = 0; i < 10; i++) {
            for (int j = 999; j < 1999; j++) {
                Goods list_item = new Goods();
                list_item.setGoodsDesc("desc" + random.nextInt());
                list_item.setGoodsName("name" + random.nextInt());
                goodsList.add(list_item);
            }
            goodsMapper.insertTo(goodsList);
            Thread.sleep(1000);
        }
    }

    @Test
    public void testTranslational() {
        // autoCommit == falst
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        Users users = usersMapper.selectById(1);
        System.out.println(users.getUsername());
        sqlSession.commit();
    }


    @BeforeClass
    public static void beforeClass() throws Exception {
        sqlSessionFactory = MybatisUtils.getSessionFactory();
    }

    @Before
    public void before() throws Exception {
        sqlSession = sqlSessionFactory.openSession(false);
    }

    @After
    public void after() throws Exception {
        sqlSession.commit();
        sqlSession.close();
    }
}
