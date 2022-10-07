package com.app.askme.service;

import com.app.askme.domain.Like;
import com.app.askme.dto.LikeDTO;
import com.app.askme.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {

    LikeRepository likeRepository;

    //public List<LikeDTO> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
    //}

    //public LikeDTO getOneLikeById(Long likeId) {
    //}

//    public List<> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
    //  }


}
