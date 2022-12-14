package com.app.askme.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequest {

    private Long postId;

    private Long userId;

    private String text;


}
