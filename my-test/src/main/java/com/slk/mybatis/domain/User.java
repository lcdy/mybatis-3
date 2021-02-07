package com.slk.mybatis.domain;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data  // getter setter
@ToString
public class User implements Serializable {
  private Long id;
  private String username;
  private Long age;
  private String password;

}
