package com.openclassrooms.mdd.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mdd.model.UserEntity;

/**
 * Repository interface for managing {@link UserEntity} persistence.
 * <p>
 * This interface extends {@link CrudRepository} to provide basic CRUD operations
 * for the {@code users} table. Custom query methods are included for retrieving users
 * by email or username.
 * </p>
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email of the user.
     * @return an {@link Optional} containing the user if found, otherwise empty.
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user.
     * @return an {@link Optional} containing the user if found, otherwise empty.
     */
    Optional<UserEntity> findByUsername(String username);
}
