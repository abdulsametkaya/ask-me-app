package com.app.askme.service;

import com.app.askme.domain.Like;
import com.app.askme.domain.Post;
import com.app.askme.domain.User;
import com.app.askme.dto.request.LikeRequest;
import com.app.askme.exceptions.BadRequestException;
import com.app.askme.exceptions.messages.ErrorMessage;
import com.app.askme.repository.LikeRepository;
import com.app.askme.repository.PostRepository;
import com.app.askme.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {

    LikeRepository likeRepository;
    UserRepository userRepository;
    PostRepository postRepository;

    public List<Like> getAllLikesWithParam(Optional<Long> postId) {

        List<Like> likes = null;

        if (postId.isPresent()) {
            likes = likeRepository.findByPostId(postId.get());
        } else {
            likes = likeRepository.findAll();
        }
        return likes;
    }

    public Like getOneLikeById(Long likeId) {

        Like like = likeRepository.findById(likeId).orElseThrow(() ->
                new BadRequestException(String.format(ErrorMessage.LIKE_NOT_FOUND_MESSAGE)));

        return like;
    }

    public Like createOneLike(LikeRequest likeRequest) {
        User user = userRepository.findById(likeRequest.getUserId()).orElseThrow(()->
                new BadRequestException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE)));
        Post post = postRepository.findById(likeRequest.getPostId()).orElseThrow(()->
                new BadRequestException(String.format(ErrorMessage.POST_NOT_FOUND_MESSAGE)));

        Like like = new Like();

        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);

        return like;
    }

    public Like deleteOneLikeById(Long likeId) {

        Optional<Like> like = likeRepository.findById(likeId);

        if (like.isPresent()) {
            likeRepository.deleteById(likeId);

            return like.get();
        } else {
            throw new BadRequestException(String.format(ErrorMessage.COMMENT_NOT_FOUND_MESSAGE));
        }
    }



}
