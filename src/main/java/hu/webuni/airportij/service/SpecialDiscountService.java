package hu.webuni.airportij.service;

import hu.webuni.airportij.config.AirportConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialDiscountService implements DiscountService{

    @Autowired
    AirportConfigProperties config;

    @Override
    public int getDiscountPercent(int totalPrice) {

        //return totalPrice > 10000 ? 15 : 10 ;

//		return totalPrice > limit ? specialPercent : defaultPercent ;
        return totalPrice > config.getDiscount().getSpecial().getLimit()
                ? config.getDiscount().getSpecial().getPercent()
                : config.getDiscount().getDef().getPercent();
    }
}
