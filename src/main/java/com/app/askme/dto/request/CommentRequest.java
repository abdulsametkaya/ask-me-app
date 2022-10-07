package com.app.askme.dto.request;

import com.app.askme.domain.Post;
import com.app.askme.domain.User;
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
