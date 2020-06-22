package br.com.aceleradev.java.centraldeerros.entity;

import br.com.aceleradev.java.centraldeerros.enums.Levels;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "serviceevent")
public class ServiceEvent {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(name = "level", length = 30)
  @NotNull(message = "Please input a level")
  @Enumerated(EnumType.STRING)
  private Levels levels;

  @Column(name = "log", length = 255, nullable = false)
  @NotNull(message = "Please input a log")
  @Size(max = 255)
  private String log;

  @Column(name = "description", length = 255, nullable = false)
  @NotNull(message = "Please input a description")
  @Size(max = 255)
  private String description;

  @Column(name = "origin", length = 100, nullable = false)
  @NotNull(message = "Please input an origin")
  @Size(max = 100)
  private String origin;

  @Column(name = "environment", length = 100, nullable = false)
  @NotNull(message = "Please input an environment")
  @Size(max = 100)
  private String environment;

  @Column
  @Min(value = 0L, message = "Quantity can't be negative")
  private Long quantity = 0L;

  @Column
  private Boolean isArchived;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "created_at", updatable = false)
  @CreatedDate
  @CreationTimestamp
  private LocalDateTime createdAt;

  public ServiceEvent(Long id, Levels levels, String log, String description, String origin, String environment) {
  }
}
