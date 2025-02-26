package com.openclassrooms.mdd.service.command;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.openclassrooms.mdd.exception.UnauthorizedActionException;
import com.openclassrooms.mdd.model.SubscriptionEntity;
import com.openclassrooms.mdd.model.ThemeEntity;
import com.openclassrooms.mdd.model.UserEntity;
import com.openclassrooms.mdd.repository.SubscriptionRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;
import com.openclassrooms.mdd.repository.UserRepository;

@Service
public class SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    public SubscriptionCommandService(SubscriptionRepository subscriptionRepository,
                                    UserRepository userRepository,
                                    ThemeRepository themeRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }

    public void subscribe(Long themeId, Long userId) {
         UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        ThemeEntity theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id: " + themeId));

        boolean alreadySubscribed = subscriptionRepository.existsByUserIdAndThemeId(userId, themeId);
        if (alreadySubscribed) {
            throw new IllegalStateException("User is already subscribed to this theme.");
        }

        SubscriptionEntity sub = SubscriptionEntity.builder()
                .user(user)
                .theme(theme)
                .build();

        subscriptionRepository.save(sub);
    }

    public void unSubscribe(Long subscriptionId, Long userId) {
        SubscriptionEntity sub = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found with id: " + subscriptionId));

        if (!sub.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("You are not authorized to delete this subscription.");
        }

        subscriptionRepository.deleteById(subscriptionId);
    }
}
