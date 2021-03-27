package aa.slk.mybatis.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer enabled;

    public Users(Integer id, String password, Integer enabled) {
        this.id = id;
        this.password = password;
        this.enabled = enabled;
    }

    public Users() {
    }

    public Users(Integer id, String username, String password, Integer enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
