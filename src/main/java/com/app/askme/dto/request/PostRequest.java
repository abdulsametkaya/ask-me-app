package com.app.askme.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    @Size(max = 30)
    private String title;

    @Size(max = 250)
    private String text;

    @NotNull(message = "Please provide a user")
    private Long userId;

}
