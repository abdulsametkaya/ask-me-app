package com.app.askme.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Data
public class Post {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Long UserId;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    String text;



}
