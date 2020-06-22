package br.com.aceleradev.java.centraldeerros.mapper;

import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Component;

import org.mapstruct.MappingTarget;

@Component
public class ServiceEventMapperImpl implements  ServiceEventMapper{

  @Override
  public  ServiceEventDTO map(ServiceEvent serviceEvent) {
    if (serviceEvent == null) return null;

    ServiceEventDTO serviceEventDTO = new ServiceEventDTO();
    serviceEventDTO.setId(serviceEvent.getId());
    serviceEventDTO.setLevels(serviceEvent.getLevels());
    serviceEventDTO.setLog(serviceEvent.getLog());
    serviceEventDTO.setDescription(serviceEvent.getDescription());
    serviceEventDTO.setOrigin(serviceEvent.getOrigin());
    serviceEventDTO.setEnvironment(serviceEvent.getEnvironment());
    serviceEventDTO.setQuantity(serviceEvent.getQuantity());
    serviceEventDTO.setIsArchived(serviceEvent.getIsArchived());
    serviceEventDTO.setCreatedAt(serviceEvent.getCreatedAt());
    if(serviceEvent.getUpdatedAt() != null) serviceEventDTO.setUpdatedAt(serviceEvent.getUpdatedAt());

    return serviceEventDTO;
  }

  @Override
  public List<ServiceEventDTO> toList(List<ServiceEvent> eventList) {
    if (eventList == null) return null;
    return eventList.stream()
      .map(this::map)
      .collect(Collectors.toList());
  }


  @Override
  public ServiceEvent updateEvent(ServiceEventDTO serviceEventDTO, @MappingTarget ServiceEvent serviceEvent) {
    if(serviceEventDTO == null) return null;
    if(serviceEventDTO.getIsArchived() != null) serviceEvent.setIsArchived(serviceEventDTO.getIsArchived());
    if(serviceEventDTO.getLevels() != null) serviceEvent.setLevels(serviceEventDTO.getLevels());
    if(serviceEventDTO.getDescription() !=null) serviceEvent.setDescription(serviceEventDTO.getDescription());
    if(serviceEventDTO.getEnvironment() !=null) serviceEvent.setEnvironment(serviceEventDTO.getEnvironment());
    if(serviceEventDTO.getOrigin() !=null) serviceEvent.setOrigin(serviceEventDTO.getOrigin());
    if(serviceEventDTO.getQuantity() !=0) serviceEvent.setQuantity(serviceEventDTO.getQuantity());
    serviceEvent.setUpdatedAt(serviceEventDTO.updatedAt());

    return serviceEvent;
  }


}
