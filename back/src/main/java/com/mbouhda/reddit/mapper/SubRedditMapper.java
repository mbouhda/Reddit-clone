package com.mbouhda.reddit.mapper;

import com.mbouhda.reddit.dto.SubRedditDTO;
import com.mbouhda.reddit.model.Post;
import com.mbouhda.reddit.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(entity.getPosts()))")
    SubRedditDTO mapToDTO(SubReddit entity);

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddit mapToEntity(SubRedditDTO dto);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }
}
