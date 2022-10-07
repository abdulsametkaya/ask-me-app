package com.app.askme.dto.mapper;

import com.app.askme.domain.Comment;
import com.app.askme.dto.request.CommentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentRequestToComment(CommentRequest commentRequest);

}
