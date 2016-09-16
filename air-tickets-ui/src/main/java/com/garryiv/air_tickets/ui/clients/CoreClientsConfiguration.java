package com.garryiv.air_tickets.ui.clients;

import com.garryiv.air_tickets.api.flight.FlightService;
import com.garryiv.air_tickets.api.reservation.ReservationService;
import com.garryiv.air_tickets.api.user.UserService;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients
@Configuration
public class CoreClientsConfiguration {

    @FeignClient("core")
    public interface UserClient extends UserService {
    }

    @FeignClient("core")
    public interface FlightClient extends FlightService {
    }

    @FeignClient("core")
    public interface ReservationClient extends ReservationService {
    }
}
