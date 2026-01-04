package com.platform.ventistorebackend.iam.domain.services;

import com.platform.ventistorebackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}

