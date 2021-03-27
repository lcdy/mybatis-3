package source.mybatis.domain;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Goods implements Serializable {

    private Integer id;
    private String goodsName;
    private Integer goodsNum;
    private String goodsDesc;
    private String price;
    private Integer version;

}
