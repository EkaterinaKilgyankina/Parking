package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/spots")
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllSpots() {
        return spotService.getAll();
    }

    @GetMapping("/allFree")
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllFreeSpots() {
        return spotService.getFreeSpots();
    }

    @GetMapping("/allBooked")
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllBookedSpots() {
        return spotService.getAllBookedSpots();
    }

}
