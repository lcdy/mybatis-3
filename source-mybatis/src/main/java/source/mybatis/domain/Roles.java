package source.mybatis.domain;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Roles implements Serializable {

  private Long id;
  private String roleName;
  private String roleDescribe;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDescribe() {
    return roleDescribe;
  }

  public void setRoleDescribe(String roleDescribe) {
    this.roleDescribe = roleDescribe;
  }

}
