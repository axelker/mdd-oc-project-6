package com.openclassrooms.mdd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.openclassrooms.mdd.dto.response.ThemeResponse;
import com.openclassrooms.mdd.model.ThemeEntity;

/**
 * Mapper for converting between {@link ThemeEntity} and {@link ThemeResponse}.
 * <p>
 * This interface uses MapStruct to automatically map fields between the entity 
 * and the response DTO, particularly handling subscription status.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface ThemeMapper {

    /**
     * Singleton instance of the mapper.
     */
    ThemeMapper INSTANCE = Mappers.getMapper(ThemeMapper.class);
    
    /**
     * Converts a {@link ThemeEntity} to a {@link ThemeResponse} DTO.
     * <p>
     * The mapping applies the following transformation:
     * <ul>
     *     <li>{@code isSubscribed} ← {@code sub} (boolean subscription status)</li>
     * </ul>
     * </p>
     *
     * @param entity the theme entity to convert.
     * @param sub    the subscription status (true if the user is subscribed, false otherwise).
     * @return a {@link ThemeResponse} DTO populated with the theme data.
     */
    @Mapping(target = "isSubscribed", source = "sub")
    ThemeResponse toDto(ThemeEntity entity, boolean sub);
}
