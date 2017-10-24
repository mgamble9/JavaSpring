package com.gamble.admin_dashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.admin_dashboard.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByName(String string);

    List<Role> findAll();

}
