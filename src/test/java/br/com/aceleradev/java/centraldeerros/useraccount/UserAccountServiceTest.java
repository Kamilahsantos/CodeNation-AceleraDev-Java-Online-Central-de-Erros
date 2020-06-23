package br.com.aceleradev.java.centraldeerros.useraccount;

import br.com.aceleradev.java.centraldeerros.entity.UserAccount;
import br.com.aceleradev.java.centraldeerros.repository.UserAccountRepository;
import br.com.aceleradev.java.centraldeerros.service.UserAccountService;

import org.springframework.security.crypto.password.PasswordEncoder;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



import java.util.Optional;


public class UserAccountServiceTest {

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  UserAccountService userAccountService;

  @Mock
  UserAccountRepository userAccountRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldReturnAccountById() {
    UserAccount user = new UserAccount("kamila.oliveira", "123", "testanto@teste.com");
    Mockito.<Optional<UserAccount>>when(userAccountRepository.findById(1L)).thenReturn(Optional.of(user));
    Optional<UserAccount> account = userAccountService.findById(1L);
    Assert.assertTrue(account.isPresent());
    Assert.assertEquals("kamila.oliveira", user.getUsername());
  }

}
