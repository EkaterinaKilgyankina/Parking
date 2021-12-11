package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/spots")
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    public List<SpotEntity> getAllSpots() {
        return spotService.getAll();
    }

    @GetMapping("/allFree")
    public List<SpotEntity> getAllFreeSpots() {
        return spotService.getFreeSpots();
    }

    @GetMapping("/allFree/{vacancy}")
    public List<SpotEntity> getAllSpotsByVacancy(@PathVariable boolean vacancy) {
        return spotService.getAllSpotsByVacancy(vacancy);
    }
}
