package br.com.aceleradev.java.centraldeerros.event;

import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;
import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.entity.UserAccount;
import br.com.aceleradev.java.centraldeerros.auth.TokenAuthenticationService;
import br.com.aceleradev.java.centraldeerros.controller.ServiceEventController;
import br.com.aceleradev.java.centraldeerros.mapper.ServiceEventMapper;
import br.com.aceleradev.java.centraldeerros.mock.ServiceEventMocks;
import br.com.aceleradev.java.centraldeerros.repository.ServiceEventRepository;
import br.com.aceleradev.java.centraldeerros.service.ServiceEventService;
import static br.com.aceleradev.java.centraldeerros.mock.ServiceEventMocks.asJsonString;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.hamcrest.Matchers.containsString;



@WebMvcTest({ServiceEventController.class, ServiceEventMapper.class})
@ContextConfiguration
@AutoConfigureMockMvc
@WebAppConfiguration
@Import(TokenAuthenticationService.class)
public class ServiceEventControllerTest {

  @MockBean
  private ServiceEventService serviceEventService;

  @InjectMocks
  ServiceEventMocks serviceEventMocks = new ServiceEventMocks();


  @Mock
  ServiceEventMapper serviceEventMapper;

  @Mock
  ServiceEventRepository serviceEventRepository;

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

  @Test
  @WithMockUser
  public void shouldCreateaNewServiceEvent() throws Exception {
    final UserAccount user = new UserAccount("kamila.santos", "12345", "teste@teste.com");
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    final ServiceEventDTO serviceEventDTO = serviceEventMocks.createDTO();
    String token = TokenAuthenticationService.create(user.getUsername());
    when(serviceEventService.save(any(ServiceEvent.class))).thenReturn(serviceEvent);
    mockMvc.perform(post("/api/v1/event")
      .header("Authorization", "Bearer "+ token)
      .contentType(MediaType.APPLICATION_JSON)
      .content(asJsonString(serviceEventDTO))
      .characterEncoding("utf-8")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(content().string(containsString(asJsonString(serviceEvent))));

  }

  @Test
  @WithMockUser
  public void shouldReturnaServiceEventById() throws Exception {
    final UserAccount user = new UserAccount("Kamila.oliveira", "123456", "teste2@teste.com");
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    final ServiceEventDTO serviceEventDTO = serviceEventMocks.createDTO();
    String token = TokenAuthenticationService.create(user.getUsername());
    when(serviceEventService.findById(serviceEvent.getId())).thenReturn(serviceEventDTO);
    mockMvc.perform(get("/api/v1/event/2")
      .header("Authorization", "Bearer "+ token))
      .andDo((print()))
      .andExpect(status().isOk())
      .andExpect(content().string(containsString(asJsonString(serviceEventDTO))));

  }



  @Test
  @WithMockUser
  public void shouldDeleteaServiceEvent() throws Exception {
    final UserAccount user = new UserAccount("kamila.fatima", "54321", "teste3@teste.com");
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    String token = TokenAuthenticationService.create(user.getUsername());
    mockMvc.perform(delete("/api/v1/event/2")
      .header("Authorization", "Bearer "+ token)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());
  }



}
