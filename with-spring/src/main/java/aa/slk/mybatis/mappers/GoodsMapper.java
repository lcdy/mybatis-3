package aa.slk.mybatis.mappers;

import aa.slk.mybatis.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace  // 二级缓存需要手动提交才生效
public interface GoodsMapper {
    @Select("select * from Goods where id = #{id}")
    Goods selectById(@Param("id") Integer id);

    @Select("select * from Goods")
    List<Goods> selectList();

    @Delete({
            "",
            "",
            "",
            "",
            ""})
    void deleteList(List<Goods> goodsList);


    /**
     * 注意#{}的用法，collection 不要·#{}, 异常
     */
    @Insert({
            "<script>",
            "insert into goods (goods_desc, goods_name) values",
            "<foreach collection='goodsList' index='index' item='item' separator=','>",
            " (#{item.goodsDesc}, #{item.goodsName}) ",
            "</foreach>",
            "</script>"
    })
    void insertTo(@Param("goodsList") List<Goods> goodsList);
}
