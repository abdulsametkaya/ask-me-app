package com.app.askme.service;

import com.app.askme.domain.Comment;
import com.app.askme.domain.Post;
import com.app.askme.domain.User;
import com.app.askme.dto.request.CommentRequest;
import com.app.askme.dto.request.CommentUpdateRequest;
import com.app.askme.exceptions.BadRequestException;
import com.app.askme.exceptions.messages.ErrorMessage;
import com.app.askme.repository.CommentRepository;
import com.app.askme.repository.PostRepository;
import com.app.askme.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    CommentRepository commentRepository;
    UserRepository userRepository;
    PostRepository postRepository;

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

        List<Comment> comment = null;

        if (userId.isPresent() && postId.isPresent()) {
            comment = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            comment = commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comment = commentRepository.findByPostId(postId.get());
        } else {
            comment = commentRepository.findAll();
        }

        return comment;
    }


    public Comment getOneCommentById(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new BadRequestException(String.format(ErrorMessage.COMMENT_NOT_FOUND_MESSAGE)));

        return comment;
    }

    public Comment createOneComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId()).orElseThrow(()->
                new BadRequestException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE)));
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(()->
                new BadRequestException(String.format(ErrorMessage.POST_NOT_FOUND_MESSAGE)));

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setText(commentRequest.getText());
        commentRepository.save(comment);

        return comment;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            foundComment.setText(request.getText());
            commentRepository.save(foundComment);

            return foundComment;
        } else {
            throw new BadRequestException(String.format(ErrorMessage.COMMENT_NOT_FOUND_MESSAGE));
        }


    }

    public Comment deleteOneCommentById(Long commentId) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            commentRepository.deleteById(commentId);

            return comment.get();
        } else {
            throw new BadRequestException(String.format(ErrorMessage.COMMENT_NOT_FOUND_MESSAGE));
        }
    }

}
