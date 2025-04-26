package com.example.grand.common.domain.listener;

import com.example.grand.common.domain.BaseEntity;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class BaseEntityListener {

	@PrePersist
	public void onPersist(Object entityObject) {
		BaseEntity entity = (BaseEntity) entityObject;
		if (Objects.isNull(entity.getUuid())) {
			entity.setUuid(UUID.randomUUID());
		}
	}

}
