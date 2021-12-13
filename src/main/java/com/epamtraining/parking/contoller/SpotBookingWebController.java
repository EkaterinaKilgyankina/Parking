package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.SpotBooking;
import com.epamtraining.parking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class SpotBookingWebController {
    private final BookingService bookingService;

    @Autowired
    public SpotBookingWebController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String getBookings(@RequestParam(value = "from", required = false) String fromDateString,
                              @RequestParam(value = "to", required = false) String toDateString, Model model){
        LocalDateTime fromDate = LocalDateTime.now().plusDays(10);
        LocalDateTime toDate = fromDate.plusDays(4);
        List<SpotBooking> spotBookings = this.bookingService.getSpotBookingsForTimePeriod();
        model.addAttribute("spotBookings", spotBookings);
        return "booking";
    }
}
