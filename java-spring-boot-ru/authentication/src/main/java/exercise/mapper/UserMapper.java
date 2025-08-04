package exercise.mapper;

import exercise.dto.UserCreateDTO;
import exercise.dto.UserDTO;
import exercise.model.User;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "passwordDigest", ignore = true) // будем шифровать вручную
    @Mapping(target = "username", source = "email")
    public abstract User map(UserCreateDTO dto);

    @Mapping(target = "username", source = "email")
    @Mapping(target = "passwordDigest", ignore = true)
    public abstract User map(UserDTO dto);

    @Mapping(target = "email", source = "username")
    public abstract UserDTO map(User user);

    /**
     * Обновление сущности User из DTO UserDTO.
     * Пароль обновляется отдельно (если нужно).
     */
    public void update(UserDTO dto, @MappingTarget User entity) {
        if (dto == null) {
            return;
        }
        map(dto, entity);
    }

    protected abstract void map(UserDTO dto, @MappingTarget User entity);

    /**
     * Метод для создания пользователя с хешированием пароля.
     */
    public User mapWithEncodedPassword(UserCreateDTO dto) {
        User user = map(dto);
        if (dto.getPassword() != null) {
            user.setPasswordDigest(passwordEncoder.encode(dto.getPassword()));
        }
        return user;
    }
}