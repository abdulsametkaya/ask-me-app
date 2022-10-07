package com.app.askme.dto.mapper;

import com.app.askme.domain.Post;
import com.app.askme.dto.PostDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    List<PostDTO> postsToPostDtos(List<Post> post);

    PostDTO postToPostDto(Post post);

    Post postDtoToPost(PostDTO postDTO);

}
