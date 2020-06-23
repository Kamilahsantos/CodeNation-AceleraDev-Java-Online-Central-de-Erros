package br.com.aceleradev.java.centraldeerros.useraccount;

import br.com.aceleradev.java.centraldeerros.dto.UserAccountDTO;
import br.com.aceleradev.java.centraldeerros.entity.UserAccount;
import br.com.aceleradev.java.centraldeerros.mapper.UserAccountMapper;
import br.com.aceleradev.java.centraldeerros.mock.ServiceEventMocks;
import br.com.aceleradev.java.centraldeerros.service.UserAccountService;
import br.com.aceleradev.java.centraldeerros.auth.TokenAuthenticationService;
import br.com.aceleradev.java.centraldeerros.controller.UserAccountController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static br.com.aceleradev.java.centraldeerros.mock.ServiceEventMocks.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;


@WebMvcTest({UserAccountController.class, UserAccountMapper.class})
@ContextConfiguration
@WebAppConfiguration
@Import(TokenAuthenticationService.class)
public class UserAccountControllerTest {

  @MockBean
  private UserAccountService userAccountService;

  @InjectMocks
  ServiceEventMocks serviceEventMocks = new ServiceEventMocks();

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  private UserAccountDTO createAccount(UserAccount userAccount) {
    UserAccountDTO userAccountDTO = new UserAccountDTO();
    userAccountDTO.setId(userAccount.getId());
    userAccountDTO.setUsername(userAccount.getUsername());
    userAccountDTO.setEmail(userAccount.getEmail());
    userAccountDTO.setCreatedAt(userAccount.getCreatedAt());

    return userAccountDTO;

  }

  @Test
  @WithMockUser
  public void shouldReturnaNewUserAccountById() throws Exception {
    final UserAccount user = new UserAccount("kamila.santos", "54321", "teste@teste.com");
    final UserAccountDTO userAccountDTO = createAccount(user);
    when(userAccountService.findById(user.getId())).thenReturn(Optional.of(user));
    String token = TokenAuthenticationService.create(user.getUsername());
    this.mockMvc.perform(get("/api/v1/account/1")
      .header("Authorization", "Bearer " + token))
      .andDo((print()))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(asJsonString(userAccountDTO))));
  }


}
