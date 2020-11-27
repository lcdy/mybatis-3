package com.slk.mybatis.domain;


public class Role {

  private Long id;
  private String rolename;
  private String roledescribe;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }


  public String getRoledescribe() {
    return roledescribe;
  }

  public void setRoledescribe(String roledescribe) {
    this.roledescribe = roledescribe;
  }

}
