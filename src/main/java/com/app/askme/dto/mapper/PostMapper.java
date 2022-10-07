package com.app.askme.dto.mapper;

import com.app.askme.domain.Post;
import com.app.askme.domain.User;
import com.app.askme.dto.PostDTO;
import com.app.askme.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    List<PostDTO> postsToPostDtoss(List<Post> user);

    PostDTO postToPostDto(Post post);

    Post postDtoToPost(PostDTO postDTO);

}
