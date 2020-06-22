package br.com.aceleradev.java.centraldeerros.response;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CredentialsResponse implements Serializable {

  private static final long serialVersionUID = -8390065503151126865L;

  @NotNull(message = "Please input an username")
  private String username;
  @NotNull(message = "Please input a password")
  private String password;



}
