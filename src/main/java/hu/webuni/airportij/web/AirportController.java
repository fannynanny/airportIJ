package hu.webuni.airportij.web;

import hu.webuni.airportij.dto.AirportDto;
import hu.webuni.airportij.service.NonUniqueIataException;
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

    private Map<Long, AirportDto> airports=new HashMap<>();

    {
        airports.put(1L, new AirportDto(1,"abc","XYZ"));
        airports.put(2L, new AirportDto(2,"def","UVW"));
    }

    @GetMapping
    public List<AirportDto> getAll(){
        return new ArrayList<>(airports.values());
    }

    @GetMapping("/{id}")
    public AirportDto getById(@PathVariable long id) {
        AirportDto airportDto = airports.get(id);
        if(airportDto != null)
            return airportDto;
        else
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //egyszerűsítünk-Hibakezelés - lásd fentebb
    /*public ResponseEntity<AirportDto> getById(@PathVariable long id) {
        //public AirportDto getById(@PathVariable long id) {
        AirportDto airportDto = airports.get(id);
        if(airportDto != null)
            return ResponseEntity.ok(airportDto);
        else
            return ResponseEntity.notFound().build();
    }*/

    @PostMapping
    //public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto, BindingResult errors) { //a valid annotáció hibáit beleteszi a bindingresult errorsba
    public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto) {
        checkUniqueIata(airportDto.getIata());
        airports.put(airportDto.getId(), airportDto);
        return airportDto;
        //NonUniqueIataException bevezetése után- fentebb
       /* airports.put(airportDto.getId(), airportDto);
        return airportDto;*/
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id,@RequestBody AirportDto airportDto) {
        if(!airports.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        checkUniqueIata(airportDto.getIata());
        airportDto.setId(id);
        airports.put(id, airportDto);
        return ResponseEntity.ok(airportDto);
    }

    private void checkUniqueIata(String iata) {
        Optional<AirportDto> airportWithSameIata =airports.values().stream()
                .filter(a -> a.getIata().equals(iata))
                .findAny();
        if(airportWithSameIata.isPresent())
            throw new NonUniqueIataException(iata);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable long id) {
        airports.remove(id);
    }
}
