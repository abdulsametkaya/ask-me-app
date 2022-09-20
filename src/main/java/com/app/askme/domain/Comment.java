package com.app.askme.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Long postId;

    private Long UserId;

    @Lob
    @Column(columnDefinition = "text")
    String text ;



}
