package hu.webuni.airportij.web;

import hu.webuni.airportij.dto.AirportDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AirportTLController {

    // dto kat tárol egyenlőre memóriában
    private List<AirportDto> allAirports=new ArrayList<>();

    {
        allAirports.add(new AirportDto(1,"Ferenc Liszt Airport","BUD"));
    }

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/airports")
    public String listAirports(Map<String, Object> model) {
        model.put("airports", allAirports);
        model.put("newAirport", new AirportDto()); // az airports htmlből érkező új adatot teszi ide bele
        return "airports";

    }

    @PostMapping("/airports")
    public String addAirport(AirportDto airport) {
        allAirports.add(airport);
        return "redirect:airports";
    }
}
