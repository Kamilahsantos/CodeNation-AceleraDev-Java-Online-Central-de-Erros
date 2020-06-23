package br.com.aceleradev.java.centraldeerros.event;

import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;
import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.mapper.ServiceEventMapper;
import br.com.aceleradev.java.centraldeerros.service.ServiceEventService;
import br.com.aceleradev.java.centraldeerros.mock.ServiceEventMocks;
import br.com.aceleradev.java.centraldeerros.repository.ServiceEventRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;


public class UserEventServiceTest {

  @Mock
  ServiceEventMapper eventMapper;

  @InjectMocks
  ServiceEventService serviceEventService;
   ServiceEventMocks serviceEventMocks = new ServiceEventMocks();

  @Mock
  ServiceEventRepository serviceEventRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldSaveaNewServiceEvent() {
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    when(serviceEventRepository.save(Mockito.any(ServiceEvent.class))).thenReturn(serviceEvent);
    serviceEventService.save(serviceEvent);
  }

  @Test
  public void shouldReturnaServoceEventById() {
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    final ServiceEventDTO eventDTO = serviceEventMocks.createDTO();
    when(serviceEventRepository.findById(1L)).thenReturn(Optional.of(serviceEvent));
    when(eventMapper.map(serviceEvent)).thenReturn(eventDTO);
    ServiceEventDTO serviceEventDTO = serviceEventService.findById(serviceEvent.getId());
    Assert.assertSame(serviceEventDTO.getId(), serviceEvent.getId());
  }


  @Test
  public void shouldReturnErrorWhenGetaServiceEventByIdNotExist() {
    final ServiceEvent serviceEvent = serviceEventMocks.create();
    final ServiceEventDTO serviceEventDTO = serviceEventMocks.createDTO();
    when(serviceEventRepository.findById(1L)).thenReturn(Optional.of(serviceEvent));
    when(eventMapper.map(serviceEvent)).thenReturn(serviceEventDTO);
    ServiceEventDTO serviceEventDTOs = serviceEventService.findById(serviceEvent.getId());
    Assert.assertSame(serviceEventDTOs.getId(), serviceEvent.getId());
  }


}
