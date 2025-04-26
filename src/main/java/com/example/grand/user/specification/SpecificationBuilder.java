package com.example.grand.user.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, F> {

    Specification<T> buildSpecification(F filter);

}
