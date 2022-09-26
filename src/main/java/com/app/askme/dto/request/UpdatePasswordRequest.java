package com.app.askme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotNull(message = "Please provide your password")
    private String password;

}