package br.com.aceleradev.java.centraldeerros.dto;

import java.time.LocalDateTime;

import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@Getter
@Setter
public class UserAccountDTO {
  private Long id;
  private String username;
  private String email;
  private LocalDateTime createdAt;

}
