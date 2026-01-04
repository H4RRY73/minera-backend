package com.platform.ventistorebackend.iam.interfaces.rest.transform;

import com.platform.ventistorebackend.iam.domain.model.entities.Role;
import com.platform.ventistorebackend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
