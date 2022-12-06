package hu.webuni.airportij.service;

import hu.webuni.airportij.config.AirportConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultDiscountService implements DiscountService{

    @Autowired
    AirportConfigProperties config;

    @Override
    public int getDiscountPercent(int totalPrice) {

        //return 10;
        return config.getDiscount().getDef().getPercent();
    }
}
