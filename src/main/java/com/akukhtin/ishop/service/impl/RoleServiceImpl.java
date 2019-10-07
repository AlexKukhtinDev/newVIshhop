package com.akukhtin.ishop.service.impl;

import com.akukhtin.ishop.dao.RoleDao;
import com.akukhtin.ishop.lib.Inject;
import com.akukhtin.ishop.lib.Service;
import com.akukhtin.ishop.model.Role;
import com.akukhtin.ishop.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Optional<Role> create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Optional<Role> get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public Optional<Role> update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
