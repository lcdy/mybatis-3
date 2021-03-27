package source.mybatis;

import lombok.Data;
import lombok.ToString;

/**
 * @author lla, 2021/2/9  21:42
 */
@Data
@ToString
public class Page {
    // 数据库起始id
    private final Integer starterId = 0;
    // 每页条数
    private Integer size;
    // 当前页：默认从第一页开始
    private Integer pageNum;

    public Page(Integer size, Integer pageNum) {
        // 默认每页两条数据
        this.size = size == null?2:size;
        this.pageNum = pageNum;
    }
}
