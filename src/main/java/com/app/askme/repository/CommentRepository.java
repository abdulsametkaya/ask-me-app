package com.app.askme.repository;

import com.app.askme.domain.Comment;
import com.app.askme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}