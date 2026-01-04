package com.platform.ventistorebackend.iam.domain.model.queries;

import com.platform.ventistorebackend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}

