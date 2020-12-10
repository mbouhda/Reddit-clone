package com.mbouhda.reddit.controller;

import com.mbouhda.reddit.dto.SubRedditDTO;
import com.mbouhda.reddit.service.SubRedditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
public class SubRedditController {

    private final SubRedditService service;

    @PostMapping
    public ResponseEntity<SubRedditDTO> save(@RequestBody SubRedditDTO dto) {
        SubRedditDTO save = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

}
