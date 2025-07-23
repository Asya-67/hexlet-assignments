package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Named;

// BEGIN
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReferenceMapper {
    @Autowired
    protected EntityManager entityManager;

    @Named("resolve")
    public <T extends BaseEntity> T resolve(Long id, @TargetType Class<T> type) {
        if (id == null) {
            return null;
        }
        return entityManager.find(type, id);
    }
}
// END
