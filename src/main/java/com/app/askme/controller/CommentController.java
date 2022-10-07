package com.app.askme.controller;

import com.app.askme.domain.Comment;
import com.app.askme.dto.request.CommentRequest;
import com.app.askme.dto.request.CommentUpdateRequest;
import com.app.askme.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam Optional<Long> userId,
                                                        @RequestParam Optional<Long> postId) {

       List<Comment> comment = commentService.getAllCommentsWithParam(userId, postId);

        return ResponseEntity.ok(comment);
    }

    @GetMapping(("/{commentId}"))
    public ResponseEntity<Comment> getOneComment(@PathVariable Long commentId) {

        Comment comment = commentService.getOneCommentById(commentId);

        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Comment> createOneComment(@RequestBody CommentRequest commentRequest) {

        Comment comment = commentService.createOneComment(commentRequest);

        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateOneComment(@PathVariable Long commentId,
                                                       @RequestBody CommentUpdateRequest request){

        Comment comment = commentService.updateOneCommentById(commentId, request);

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Comment> deleteOneComment(@PathVariable Long commentId){

        Comment comment = commentService.deleteOneCommentById(commentId);

        return ResponseEntity.ok(comment);

    }


}
