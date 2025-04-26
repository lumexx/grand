package com.example.grand.user.specification;


import com.example.grand.email.domain.EmailData_;
import com.example.grand.phone.domain.PhoneData_;
import com.example.grand.user.domain.User;
import com.example.grand.user.domain.User_;
import com.example.grand.user.dto.request.UserFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

@Component
public class UserSpecificationBuilder implements SpecificationBuilder<User, UserFilter> {
    @Override
    public Specification<User> buildSpecification(UserFilter filter) {
        Specification<User> specification = null;

        if (Objects.nonNull(filter.getDateOfBirth())) {
            specification = Specification.where(specification)
                    .and(getGreaterThanOrEqualToSpecification(User_.DATE_OF_BIRTH, filter.getDateOfBirth()
                            .atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        if (StringUtils.isNotEmpty(filter.getName())) {
            specification = Specification.where(specification)
                    .and(getLikeSingleSpecification(User_.NAME, "%" + filter.getName() + "%"));
        }

        if (StringUtils.isNotEmpty(filter.getEmail())) {
            specification = Specification.where(specification)
                    .and(getEqualWithLeftJoinSingleSpecification(User_.EMAILS, EmailData_.EMAIL, filter.getEmail()));
        }

        if (StringUtils.isNotEmpty(filter.getPhone())) {
            specification = Specification.where(specification)
                    .and(getEqualWithLeftJoinSingleSpecification(User_.PHONES, PhoneData_.PHONE, filter.getPhone()));
        }

        return specification;
    }

    private Specification<User> getEqualWithLeftJoinSingleSpecification(String joinTableField, String fieldName,
                                                                        Object value) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join(joinTableField, JoinType.LEFT);
            return criteriaBuilder.equal(join.get(fieldName), value);
        };
    }

    private Specification<User> getLikeSingleSpecification(String fieldName, String pattern) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(criteriaBuilder.toString(root.get(fieldName))),
                        pattern.toLowerCase());
    }

    private Specification<User> getGreaterThanOrEqualToSpecification(String fieldName, Instant pattern) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), pattern);
    }

}


