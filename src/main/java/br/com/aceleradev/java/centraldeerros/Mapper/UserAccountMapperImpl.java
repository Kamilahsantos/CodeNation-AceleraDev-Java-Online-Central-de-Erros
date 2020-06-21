package br.com.aceleradev.java.centraldeerros.Mapper;

import br.com.aceleradev.java.centraldeerros.Entity.UserAccount;
import br.com.aceleradev.java.centraldeerros.DTO.UserAccountDTO;

import org.springframework.stereotype.Component;

@Component
public class UserAccountMapperImpl implements UserAccountMapper{

  @Override
  public UserAccountDTO map(UserAccount userAccount) {
    if (userAccount == null) return null;

    UserAccountDTO userAccountDTO = new UserAccountDTO();
    userAccountDTO.setId(userAccount.getId());
    userAccountDTO.setUsername(userAccount.getUsername());
    userAccountDTO.setEmail(userAccount.getEmail());
    userAccountDTO.setCreatedAt(userAccount.getCreatedAt());

    return userAccountDTO;
  }


}
