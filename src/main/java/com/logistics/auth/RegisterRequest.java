package com.logistics.auth;


import com.logistics.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String documentType;
  private String documentNumber;
  private String names;
  private String lastNames;
  private String address;
  private String cellPhone;
  private String email;
  private String phone;
  private String password;
  private Role role;


}
