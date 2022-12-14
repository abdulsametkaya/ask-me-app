package com.app.askme.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {

    @Size(max = 50)
    @NotNull(message = "Please provide your username")
    private String username;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 20)
    @NotNull(message = "Please provide your email")
    private String email;

}
