package br.com.aceleradev.java.centraldeerros.repository;

import br.com.aceleradev.java.centraldeerros.entity.UserAccount;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

  Optional<UserAccount> findById(Long id);

  @Query("select a from UserAccount a where a.email= ?1")
  Optional<UserAccount> findByEmail(String email);
  Optional<UserAccount> findByUsername(String username);


}
