package source.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import source.mybatis.domain.Users;

import java.util.List;

/**
 * @author lla, 2021/2/9  12:23
 * <p>
 * XML方式: 方法名字什么的要和XML中一一对应
 * <p>
 * NameSpace和权限定类名一致
 * 方法名和标签id一致
 * 参数与返回值类型和parameterType、reusltType对应
 */
public interface UsersMapper {

    @Select("select * from users where id = #{id}")
    Users selectById(@Param("id") Integer id);

    @Select("<script>\t" +
            "select * from users\t" +
            "<where>\t" +
            "and id = #{id}\t" +
            "</where>\t" +
            "</script>")
    Users findById(@Param("id") Integer id);

    @Select("select * from users")
    List<Users> findAll();

    /**
     * "password = #{users.password},",
     * "enabled = #{users.enabled}",
     *
     * @param users
     */

    @Update({"<script>",
            "update users u",
            "  <set>",
            "    <if test='#{users.password} != null'>password=#{users.password},</if>",
            "    <if test='#{users.enabled} != null'>enabled=#{users.enabled},</if>",
            "  </set>",
            "where id=#{users.id}",
            "</script>"})
    void updateUsersIfNecessary(@Param("users") Users users);


    @Select("select * from users where username = #{users.username}")
    Users findLike(@Param("users") Users users);


    @Update({"<script>",
            "update Author ",
            "  <set>",
            "    <if test='username != null'>username=#{username},</if>",
            "    <if test='password != null'>password=#{password},</if>",
            "    <if test='email != null'>email=#{email},</if>",
            "    <if test='bio != null'>bio=#{bio}</if>",
            "  </set>",
            "where id=#{id}",
            "</script>"})
    void testBuilder();
}
