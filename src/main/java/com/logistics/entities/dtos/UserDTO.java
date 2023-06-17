package com.logistics.entities.dtos;

import com.logistics.entities.Role;
import lombok.Data;

import java.util.List;
@Data
public class UserDTO {

  private String nickname;


  private String password;


  private String names;


  private String lastNames;


  private Long documentNumber;


  private String documentType;


  private Long cellPhone;


  private Long phone;

  private String email;


  private Role role;

}
