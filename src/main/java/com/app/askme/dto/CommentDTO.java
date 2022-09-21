package com.app.askme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long postId;
    private Long UserId;
    String text ;


}
