package com.app.askme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Size(max = 50)
    @NotNull(message = "Please provide your username")
    private String userName;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 20)
    @NotNull(message = "Please provide your email")
    private String eMail;

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotNull(message = "Please provide your password")
    private String password;
}
