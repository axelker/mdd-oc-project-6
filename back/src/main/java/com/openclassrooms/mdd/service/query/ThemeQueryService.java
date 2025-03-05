package com.openclassrooms.mdd.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.mapper.ThemeMapper;
import com.openclassrooms.mdd.repository.ThemeRepository;

@Service
public class ThemeQueryService {
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;
    private final SubscriptionQueryService subscriptionQueryService ;

    ThemeQueryService(ThemeRepository themeRepository,ThemeMapper themeMapper,SubscriptionQueryService subscriptionQueryService) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
        this.subscriptionQueryService = subscriptionQueryService;
    }


    public List<ThemeResponse> findAll(Long userId,Boolean subscribed) {
        List<Long> themeIdsSub = subscriptionQueryService.getThemeIdsUserSub(userId);
        return themeRepository.findThemesBySubscriptionStatus(userId,subscribed).stream()
                .map(theme -> themeMapper.toDto(theme, themeIdsSub.contains(theme.getId())))
                .toList();
        
    }
    
}
