package com.akukhtin.ishop.dao;

import com.akukhtin.ishop.model.Role;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> create(Role role);

    Optional<Role> get(Long id);

    Optional<Role> update(Role role);

    void delete(Long id);
}
