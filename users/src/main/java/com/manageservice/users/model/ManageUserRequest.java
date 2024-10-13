package com.manageservice.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@NoArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class ManageUserRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "User details missing")
    private @Valid User user;
}
