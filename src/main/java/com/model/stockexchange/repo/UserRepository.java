package com.model.stockexchange.repo;

import com.model.stockexchange.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsEntity, Long> {

  @Query("select user from UserDetailsEntity user where user.username =?1")
  UserDetailsEntity findByUsername(String s);
}
