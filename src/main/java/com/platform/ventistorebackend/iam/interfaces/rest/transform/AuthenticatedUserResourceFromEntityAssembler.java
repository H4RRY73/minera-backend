package com.platform.ventistorebackend.iam.interfaces.rest.transform;

import com.platform.ventistorebackend.iam.domain.model.aggregates.User;
import com.platform.ventistorebackend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
