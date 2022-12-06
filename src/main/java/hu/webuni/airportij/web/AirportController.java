package hu.webuni.airportij.web;

import hu.webuni.airportij.dto.AirportDto;
import hu.webuni.airportij.mapper.AirportMapper;
import hu.webuni.airportij.model.Airport;
import hu.webuni.airportij.service.AirportService;
import hu.webuni.airportij.service.NonUniqueIataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @Autowired
    AirportMapper airportMapper;

    @GetMapping
    public List<AirportDto> getAll(){
// itt most ahhoz, hogy AirportDto kat adjunk vissza az airportService en végig kellene iterálni az airportokat kigettelgetni és besettelgetni az airportdto ba
// ezt fogja megcsinálni helyettünk a MapStruct - mapstruct.org
        /*airportService.findAll();
        return null;*/
        return airportMapper.airportsToDtos((airportService.findAll()));
    }

    @GetMapping("/{id}")
    public AirportDto getById(@PathVariable long id) {
        Airport airport = airportService.findById(id);
        
        if(airport != null)
            return airportMapper.airportToDto(airport);
        else
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    //public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto, BindingResult errors) { //a valid annotáció hibáit beleteszi a bindingresult errorsba
    public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto) {
        Airport airport = airportService.save(airportMapper.dtoToAirport(airportDto));
       // checkUniqueIata(airportDto.getIata());
       // airports.put(airportDto.getId(), airportDto);
        return airportMapper.airportToDto(airport);
        //NonUniqueIataException bevezetése után- fentebb
        //airports.put(airportDto.getId(), airportDto);
        //return airportDto;
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id,@RequestBody AirportDto airportDto) {
        if(!airports.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        checkUniqueIata(airportDto.getIata());
        airportDto.setId(id);
        airports.put(id, airportDto);
        return ResponseEntity.ok(airportDto);
    }



    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable long id) {
        airports.remove(id);
    }*/
}
