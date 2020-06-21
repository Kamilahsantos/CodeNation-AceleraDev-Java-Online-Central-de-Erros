package br.com.aceleradev.java.centraldeerros.Mapper;

import br.com.aceleradev.java.centraldeerros.DTO.UserAccountDTO;
import br.com.aceleradev.java.centraldeerros.Entity.UserAccount;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserAccountMapper {
  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "username", target = "username"),
    @Mapping(source = "email", target = "email"),
    @Mapping(source = "createdAt", target = "createdAt")
  })
  UserAccountDTO map(UserAccount userAccount);
}
