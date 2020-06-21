package br.com.aceleradev.java.centraldeerros.service;

import br.com.aceleradev.java.centraldeerros.repository.UserAccountRepository;
import br.com.aceleradev.java.centraldeerros.response.CredentialsResponse;
import br.com.aceleradev.java.centraldeerros.entity.UserAccount;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserAccountService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  UserAccountRepository userAccountRepository;

  public boolean isEmpty() {
    return (userAccountRepository.count() == 0);
  }

  public boolean hasAccount(CredentialsResponse credentialsResponse) {
    Optional<UserAccount> existingAccount = userAccountRepository.findByUsername(credentialsResponse.getUsername());
    if(existingAccount.isPresent()) {
      return passwordEncoder.matches(credentialsResponse.getPassword(), existingAccount.get().getPassword());
    }
    return false;
  }

  public Optional<UserAccount> findByEmail (String email){
    return userAccountRepository.findByEmail(email);
  }

  public Optional<UserAccount> findById(Long id) {
    return userAccountRepository.findById(id);
  }

  public UserAccount save(UserAccount userAccount) {
    userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
    return userAccountRepository.save(userAccount);
  }





}
