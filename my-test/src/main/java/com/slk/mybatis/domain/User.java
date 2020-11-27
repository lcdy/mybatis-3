package com.slk.mybatis.domain;


import lombok.Data;
import lombok.ToString;

@Data  // getter setter
@ToString
public class User {
  private Long id;
  private String username;
  private Long age;
  private String password;

}
