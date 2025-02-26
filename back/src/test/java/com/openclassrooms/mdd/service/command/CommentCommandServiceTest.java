package com.openclassrooms.mdd.service.command;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.mdd.dto.request.CommentRequest;
import com.openclassrooms.mdd.mapper.CommentMapper;
import com.openclassrooms.mdd.model.CommentEntity;
import com.openclassrooms.mdd.repository.CommentRepository;
import com.openclassrooms.mdd.service.command.CommentCommandService;

@ExtendWith(MockitoExtension.class)
public class CommentCommandServiceTest {
    @Mock
    private CommentMapper commentMapper;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentCommandService commentCommandService;

    @Test
    void createCommentSuccess() {
        CommentRequest messageRequest = CommentRequest.builder()
                .user_id(1L)
                .article_id(2L)
                .message("test message")
                .build();

        CommentEntity messageEntity = CommentEntity.builder()
                .message("test message")
                .build();

        when(commentMapper.toEntity(messageRequest)).thenReturn(messageEntity);
        when(commentRepository.save(messageEntity)).thenReturn(messageEntity);

        commentCommandService.createComment(messageRequest);
        verify(commentRepository, times(1)).save(messageEntity);
    }

}
