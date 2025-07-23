package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;

// BEGIN

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class ReferenceMapper {

    @Autowired
    private EntityManager entityManager;

    public <T extends BaseEntity> T resolve(Long id, @TargetType Class<T> type) {
        return entityManager.find(type, id);
    }
}
// END
