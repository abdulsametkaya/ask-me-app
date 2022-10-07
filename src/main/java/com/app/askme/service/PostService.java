package com.app.askme.service;

import com.app.askme.domain.Post;
import com.app.askme.dto.PostDTO;
import com.app.askme.dto.UserDTO;
import com.app.askme.dto.mapper.PostMapper;
import com.app.askme.dto.mapper.UserMapper;
import com.app.askme.dto.request.PostRequest;
import com.app.askme.dto.request.UpdatePostRequest;
import com.app.askme.exceptions.BadRequestException;
import com.app.askme.exceptions.messages.ErrorMessage;
import com.app.askme.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    PostRepository postRepository;

    UserService userService;

    UserMapper userMapper;

    PostMapper postMapper;


    public List<PostDTO> getAllPosts(Optional<Long> userId) {

        List<Post> posts = null;

        if (userId.isPresent()){
            posts = postRepository.findByUserId(userId.get());

            return postMapper.postsToPostDtoss(posts);
        }else {
            posts = postRepository.findAll();

            return postMapper.postsToPostDtoss(posts);
        }

    }

    public PostDTO getOnePostById(Long postId) {

        Post post= postRepository.findById(postId).orElseThrow(() ->
                new BadRequestException(String.format(ErrorMessage.POST_NOT_FOUND_MESSAGE)));

        return postMapper.postToPostDto(post);
    }

    public PostDTO createOnePost(PostRequest postRequest) {

        Post post = new Post();

        UserDTO user= userService.getOneUser(postRequest.getUserId());

        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        post.setUser(userMapper.userDtoToUser(user));
        postRepository.save(post);

       return postMapper.postToPostDto(post);
    }

    public PostDTO updateOnePostById(Long postId, UpdatePostRequest updatePostRequest) {

        Optional<Post> post = postRepository.findById(postId);
        Post foundPost = null;

        if (post.isPresent()) {
            foundPost = post.get();

            foundPost.setTitle(updatePostRequest.getTitle());
            foundPost.setText(updatePostRequest.getText());
            postRepository.save(foundPost);

            return postMapper.postToPostDto(foundPost);
        }else {
            throw new BadRequestException(String.format(ErrorMessage.POST_NOT_FOUND_MESSAGE));
        }
    }


    public PostDTO deleteOnePostById(Long postId) {

        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()){
            postRepository.deleteById(postId);

            return postMapper.postToPostDto(post.get());
        }else {
            throw new BadRequestException(String.format(ErrorMessage.POST_NOT_FOUND_MESSAGE));
        }
    }

}
