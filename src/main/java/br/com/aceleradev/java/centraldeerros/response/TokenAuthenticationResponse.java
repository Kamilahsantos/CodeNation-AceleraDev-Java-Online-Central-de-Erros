package br.com.aceleradev.java.centraldeerros.response;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class TokenAuthenticationResponse implements Serializable{

  private static final long serialVersionUID = 8271473625181457022L;

  private String token;


}
