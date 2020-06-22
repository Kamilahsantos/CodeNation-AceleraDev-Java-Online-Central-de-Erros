package br.com.aceleradev.java.centraldeerros.rules;

import br.com.aceleradev.java.centraldeerros.entity.ServiceEvent;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.domain.Specification;

public class BooleanEventRule implements Specification<ServiceEvent>  {

  private static final long serialVersionUID = -1490145734676071686L;
  private String columnName;
  private Boolean columnValue;

  public BooleanEventRule(String name, Boolean value) {
    this.columnName = name;
    this.columnValue = value;
  }

  @Override
  public Predicate toPredicate(Root<ServiceEvent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    if (this.columnValue == null) {
      return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
    return criteriaBuilder.equal(root.get(this.columnName), this.columnValue);
  }


}
