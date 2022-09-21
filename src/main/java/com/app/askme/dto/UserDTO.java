package com.app.askme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    private String eMail;

    private String password;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDTO {

        private Long id;

        private String userName;

        private String eMail;

        private String password;
    }
}
