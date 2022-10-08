package com.app.askme.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeRequest {

    private Long postId;
    private Long userId;

}
