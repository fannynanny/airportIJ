package hu.webuni.airportij.mapper;

import hu.webuni.airportij.dto.AirportDto;
import hu.webuni.airportij.model.Airport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    // lista ami airportdto kat gyárt
    List<AirportDto> airportsToDtos(List<Airport> airports);

    AirportDto airportToDto(Airport airport);

    Airport dtoToAirport(AirportDto airportDto);

}
