package com.taiga.user.service.usermanagement.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	public UserEntity findByEmail(String email);
}
