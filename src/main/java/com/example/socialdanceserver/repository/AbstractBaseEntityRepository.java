package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractBaseEntityRepository extends JpaRepository<AbstractBaseEntity, Integer> {
}
