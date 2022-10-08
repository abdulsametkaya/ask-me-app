package com.app.askme.controller;

import com.app.askme.domain.Like;
import com.app.askme.dto.request.LikeRequest;
import com.app.askme.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
public class LikeController {

    LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Like>> getAllComments(@RequestParam Optional<Long> postId) {

        List<Like> like = likeService.getAllLikesWithParam(postId);
        return ResponseEntity.ok(like);
    }

    @GetMapping(("/{likeId}"))
    public ResponseEntity<Like> getOneComment(@PathVariable Long likeId) {

        Like like = likeService.getOneLikeById(likeId);

        return ResponseEntity.ok(like);
    }

    @PostMapping
    public ResponseEntity<Like> createOneComment(@RequestBody LikeRequest likeRequest) {

        Like like = likeService.createOneLike(likeRequest);

        return ResponseEntity.ok(like);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Like> deleteOneLike(@PathVariable Long likeId){

        Like like = likeService.deleteOneLikeById(likeId);

        return ResponseEntity.ok(like);

    }

}
