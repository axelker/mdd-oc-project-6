package com.openclassrooms.mdd.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.mapper.ThemeMapper;
import com.openclassrooms.mdd.repository.ThemeRepository;

/**
 * Service for querying theme data.
 * <p>
 * This service provides functionality for fetching themes, including retrieving all available themes
 * and checking whether a user is subscribed to each theme.
 * </p>
 */
@Service
public class ThemeQueryService {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;
    private final SubscriptionQueryService subscriptionQueryService;

    /**
     * Constructs an instance of {@code ThemeQueryService} with required dependencies.
     *
     * @param themeRepository         the repository for retrieving theme data.
     * @param themeMapper             the mapper for converting theme entities to DTOs.
     * @param subscriptionQueryService the service for managing user subscriptions.
     */
    ThemeQueryService(ThemeRepository themeRepository,
                      ThemeMapper themeMapper,
                      SubscriptionQueryService subscriptionQueryService) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    /**
     * Retrieves all themes, filtered by subscription status for a specific user.
     * <p>
     * This method fetches themes and checks whether the user is subscribed to each theme.
     * If the {@code subscribed} parameter is {@code true}, only themes the user is subscribed to are returned.
     * If {@code subscribed} is {@code false}, only themes the user is not subscribed to are returned.
     * </p>
     *
     * @param userId    the ID of the user.
     * @param subscribed the subscription status to filter themes.
     * @return a list of {@link ThemeResponse} DTOs representing themes.
     */
    public List<ThemeResponse> findAll(Long userId, Boolean subscribed) {
        List<Long> themeIdsSub = subscriptionQueryService.getThemeIdsUserSub(userId);
    
        return themeRepository.findThemesBySubscriptionStatus(userId, subscribed).stream()
                .map(theme -> themeMapper.toDto(theme, themeIdsSub.contains(theme.getId())))
                .toList();
    }
}
