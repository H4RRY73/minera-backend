package com.platform.ventistorebackend.iam.interfaces.rest.transform;

import com.platform.ventistorebackend.iam.domain.model.commands.SignInCommand;
import com.platform.ventistorebackend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}