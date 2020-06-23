package br.com.aceleradev.java.centraldeerros.mock;

import br.com.aceleradev.java.centraldeerros.enums.Levels;
import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceEventMocks {

  private Long id = 1L;
  private Levels levels = Levels.valueOf("DEBUG");
  private String log = "NullPointerException";
  private String description = "is an exception thrown by Java when a program tries to access a memory object that has not been instantiated";
  private String origin = "127.0.0.1";
  private String environment = "production";

  public ServiceEventDTO createDTO() {
    ServiceEventDTO serviceEventDTO = new ServiceEventDTO();
    serviceEventDTO.setId(id);
    serviceEventDTO.setLevels(levels);
    serviceEventDTO.setLog(log);
    serviceEventDTO.setDescription(description);
    serviceEventDTO.setOrigin(origin);
    serviceEventDTO.setEnvironment(environment);
    serviceEventDTO.setQuantity(3L);
    return serviceEventDTO;
  }

  public ServiceEvent create() {
    ServiceEvent serviceEvent = new ServiceEvent();
    serviceEvent.setId(id);
    serviceEvent.setLevels(levels);
    serviceEvent.setLog(log);
    serviceEvent.setDescription(description);
    serviceEvent.setOrigin(origin);
    serviceEvent.setEnvironment(environment);
    serviceEvent.setQuantity(3L);
    return serviceEvent;
  }

  public List<ServiceEventDTO> DTOList() {
    List<ServiceEventDTO> eventDTOList = new ArrayList<>();
    for (long i = 0; i<=30; i++) {
      long l = i + 1;
      ServiceEventDTO serviceEventDTO = createDTO();
      eventDTOList.add(serviceEventDTO);
    }
    return eventDTOList;
  }

  public List<ServiceEvent> List() {
    List<ServiceEvent> serviceEvents = new ArrayList<>();
    for (long i = 0; i<=30; i++) {
      long l = i + 1;
      ServiceEvent serviceEvent = create();
      serviceEvents.add(serviceEvent);
    }
    return serviceEvents;
  }



  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
