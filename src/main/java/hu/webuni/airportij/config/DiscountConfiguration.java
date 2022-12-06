package hu.webuni.airportij.config;

import hu.webuni.airportij.service.DefaultDiscountService;
import hu.webuni.airportij.service.DiscountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class DiscountConfiguration {

    @Bean
    public DiscountService discountService() {
        return new DefaultDiscountService();

    }
}
