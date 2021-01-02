package com.mbouhda.reddit.mapper;

import com.mbouhda.reddit.dto.SubRedditDTO;
import com.mbouhda.reddit.model.SubReddit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    SubRedditDTO mapToDTO(SubReddit entity);

  
    SubReddit mapToEntity(SubRedditDTO dto);

}
