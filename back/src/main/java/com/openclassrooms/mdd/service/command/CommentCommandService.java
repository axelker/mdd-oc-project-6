package com.openclassrooms.mdd.service.command;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.dto.response.CommentResponse;
import com.openclassrooms.mdd.mapper.CommentMapper;
import com.openclassrooms.mdd.repository.CommentRepository;

@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentCommandService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentResponse create(CommentRequest comment) {
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(comment)));
    }
}
