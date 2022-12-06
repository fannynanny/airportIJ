package hu.webuni.airportij.config;

import hu.webuni.airportij.service.DiscountService;
import hu.webuni.airportij.service.SpecialDiscountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdDiscountConfiguration {

    @Bean
    public DiscountService discountService() {

        return new SpecialDiscountService();
    }
}
