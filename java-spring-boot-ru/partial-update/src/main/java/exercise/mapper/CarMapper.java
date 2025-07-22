package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarUpdateDTO;
import exercise.dto.CarDTO;
import exercise.model.Car;

// BEGIN
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = JsonNullableMapper.class
)
public interface CarMapper {

    CarDTO toDTO(Car car);

    Car toEntity(CarCreateDTO carCreateDTO);

    void update(CarUpdateDTO carUpdateDTO, @MappingTarget Car car);

    default CarDTO map(Car car) {
        return toDTO(car);
    }

    default Car map(CarCreateDTO dto) {
        return toEntity(dto);
    }
}
// END
