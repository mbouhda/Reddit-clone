package com.mbouhda.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubRedditDTO {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
