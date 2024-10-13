package com.manageservice.users.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor()
@ToString(callSuper = true)
@SuperBuilder
public class User {

    @NotNull(message="username cannot be null")
    private String username;

    @NotNull(message="firstname cannot be null")
    private String firstname;

    @NotNull(message="lastname cannot be null")
    private String lastname;

    @NotNull(message = "email cannot be null")
    private String user_email;

    @NotNull(message = "phone num cannot be null")
    private String user_phone_num;
}
