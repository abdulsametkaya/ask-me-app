package com.app.askme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {

    private Long id;
    private Long postId;
    private Long UserId;


}
