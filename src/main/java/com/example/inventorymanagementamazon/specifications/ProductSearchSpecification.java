package com.example.inventorymanagementamazon.specifications;

import com.example.inventorymanagementamazon.dto.ProductSearchCriteria;
import com.example.inventorymanagementamazon.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class ProductSearchSpecification implements Specification<Product> {

    ProductSearchCriteria productSearchCriteria;
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(productSearchCriteria.getName())){
            log.info("add product Name to search criteria");
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+productSearchCriteria.getName()+"%"));
        }
        if (Objects.nonNull(productSearchCriteria.getBrandId())){
            log.info("add brand to search criteria");

            predicates.add(criteriaBuilder.equal(root.get("name"), productSearchCriteria.getBrandId()));
        }if (!productSearchCriteria.getIds().isEmpty()){
            log.info("add categories to search criteria");

            predicates.add(root.get("id").in(productSearchCriteria.getIds()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
