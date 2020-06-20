package br.com.aceleradev.java.centraldeerros.Entity;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;


@Data
@Entity
@Table(name="useraccount")
@EntityListeners(AuditingEntityListener.class)
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(name = "username", length = 100, nullable = false)
  @NotNull(message = "Please input an username")
  @Size(max = 100)
  private String username;

  @Column(name = "password", length = 100, nullable = false)
  @NotNull(message = "Please input a password")
  @Size(max = 100)
  @JsonProperty(access = JsonProperty.Access.READ_WRITE)
  private String password;

  @Column(name = "email", length = 100, nullable = false)
  @Email(message = "E-mail isn't valid")
  @NotNull(message = "Please input an e-mail")
  @Size(max = 100)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  @CreationTimestamp
  private LocalDateTime createdAt;

  @JsonCreator
  public UserAccount(@NotNull @Size(max = 100)@JsonProperty("username") String username, @NotNull @Size(max = 100) @JsonProperty("password") String password, @Email @NotNull @Size(max = 100) @JsonProperty("email") String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public UserAccount() {
  }


}
