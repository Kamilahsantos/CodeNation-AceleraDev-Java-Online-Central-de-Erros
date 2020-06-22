package br.com.aceleradev.java.centraldeerros.mapper;

import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;
import br.com.aceleradev.java.centraldeerros.dto.ServiceEventDTO;

import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceEventMapper {

  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "levelEnum", target = "levelEnum"),
    @Mapping(source = "log", target = "log"),
    @Mapping(source = "description", target = "description"),
    @Mapping(source = "origin", target = "origin"),
    @Mapping(source = "environment", target = "environment"),
    @Mapping(source = "quantity", target = "quantity"),
    @Mapping(source = "isArchived", target = "isArchived"),
    @Mapping(source = "createdAt", target = "createdAt"),
    @Mapping(source = "updatedAt", target = "updatedAt")
  })
  ServiceEventDTO map(ServiceEvent serviceEvent);

  List<ServiceEventDTO> toList(List<ServiceEvent> serviceEventList);


  ServiceEvent updateEvent(ServiceEventDTO serviceEventDTO, @MappingTarget ServiceEvent serviceEvent);
}
