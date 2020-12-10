package com.mbouhda.reddit.service;

import com.mbouhda.reddit.dto.SubRedditDTO;
import com.mbouhda.reddit.mapper.SubRedditMapper;
import com.mbouhda.reddit.model.SubReddit;
import com.mbouhda.reddit.repository.SubRedditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubRedditService {

    private final SubRedditRepository repository;
    private final SubRedditMapper mapper;

    @Transactional
    public SubRedditDTO save(SubRedditDTO dto) {
        SubReddit saveResult = repository.save(mapper.mapToEntity((dto)));
        dto.setId(saveResult.getId());
        return dto;
    }

}
