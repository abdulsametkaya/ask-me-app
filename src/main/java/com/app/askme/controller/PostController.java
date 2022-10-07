package com.app.askme.controller;

import com.app.askme.dto.PostDTO;
import com.app.askme.dto.request.PostRequest;
import com.app.askme.dto.request.UpdatePostRequest;
import com.app.askme.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;

    // .../post and .../post?userId={userId}
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam Optional<Long> userId){

        List<PostDTO> posts = postService.getAllPosts(userId);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable Long postId){

        PostDTO post = postService.getOnePostById(postId);

        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createOnePost(@RequestBody PostRequest postRequest){
        PostDTO post = postService.createOnePost(postRequest);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updateOnePost(@PathVariable Long postId,
                                              @RequestBody UpdatePostRequest updatePostRequest){

        PostDTO post = postService.updateOnePostById(postId, updatePostRequest);

        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deleteOnePost(@PathVariable Long postId){

       PostDTO post = postService.deleteOnePostById(postId);

       return ResponseEntity.ok(post);
    }


}
