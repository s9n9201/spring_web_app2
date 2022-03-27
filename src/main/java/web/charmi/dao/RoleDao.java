package web.charmi.dao;

import web.charmi.entity.Role;
import web.charmi.entity.enumRole;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> findByName(enumRole name);
}
