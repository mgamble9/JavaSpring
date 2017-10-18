package com.gamble.login_and_reg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.login_and_reg.models.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByName(String string);

    List<Role> findAll();

}
