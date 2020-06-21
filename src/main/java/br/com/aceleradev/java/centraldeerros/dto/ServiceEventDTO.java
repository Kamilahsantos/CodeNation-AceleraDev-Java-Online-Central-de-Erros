package br.com.aceleradev.java.centraldeerros.dto;

import br.com.aceleradev.java.centraldeerros.enums.Levels;

import java.time.LocalDateTime;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServiceEventDTO {

  private Long id;
  @Enumerated(EnumType.STRING)
  private Levels levels;
  private String log;
  private String description;
  private String origin;
  private String environment;
  private Long quantity =0L;
  private Boolean isArchived;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public LocalDateTime updatedAt(){
    return LocalDateTime.now();
  }

  public ServiceEventDTO (Boolean isArchived){
    this.isArchived= isArchived;
  }
}
