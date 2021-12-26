package com.epamtraining.parking.service;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.BookedSpot;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.impl.SpotServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpotServiceImplTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    SpotRepository spotRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    SpotServiceImpl spotService;
    @Captor
    ArgumentCaptor<SpotEntity> spotEntityCaptor;
    @Captor
    ArgumentCaptor<BookingEntity> bookingEntityArgumentCaptor;

    @Test
    public void getAllSpotsTest() {
        when(spotRepository.findAll()).thenReturn(Collections.emptyList());
        verify(userRepository, times(1)).findAll();
        assertEquals(Collections.emptyList(), spotService.getAll());
    }

    @Test
    public void createSpotTest() {
        SpotRequest request = new SpotRequest();
        request.setLocation("200");
        when(spotRepository.save(any())).thenAnswer(e -> e.getArgument(0));
        spotService.createSpot(request);
        verify(spotRepository, times(1)).save(spotEntityCaptor.capture());
        SpotEntity entity = spotEntityCaptor.getValue();
        assertEquals(request.getLocation(), entity.getLocation());
    }


    @Test
    public void deleteSpotTest() {
        SpotEntity entity = mock(SpotEntity.class);
        entity.setLocation("201");
        when(spotRepository.getById(any())).thenReturn(entity);
        spotService.deleteSpot(0L);
        verify(spotRepository, times(1)).delete(entity);
    }


    @Test
    public void getFreeSpotsForTimePeriodTest() {
        //TODO какие проверки?!
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMinutes(30);
        when(spotRepository.findAll()).thenReturn(List.of(new SpotEntity()));
        when(bookingRepository.findAll()).thenReturn(List.of(new BookingEntity().setBookingTo(LocalDateTime.now()).setBookingFrom(LocalDateTime.now().plusMinutes(30))));
        spotService.getFreeSpotsForTimePeriod(from, to);
        verify(spotRepository, times(1)).findAll();
        verify(bookingRepository, times(1)).findAll();
    }



    @Test
    public void getAllBookedSpotsTest() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMinutes(30);
        BookingEntity entity = mock(BookingEntity.class);
        entity.getCarEntity()
                .setNumber("M000")
                .setUser(new UserEntity().setId(0L));
        List<BookingEntity> bookings = new ArrayList<>();
        bookings.add(entity);

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(userRepository.findById(bookings.stream().findFirst().get().getCarEntity().getUser().getId())).thenReturn(Optional.of(new UserEntity()));
        spotService.getAllBookedSpots(from,to);
        verify(bookingRepository,times(1)).findAll();
        verify(userRepository,times(1)).findById(0l);


    }

    @Test
    public void getExceptionWhenFromIsBeforeTo() {
        LocalDateTime from = LocalDateTime.now().plusMinutes(30);
        LocalDateTime to = LocalDateTime.now();
        assertThrows(ApplicationException.class, () -> spotService.getFreeSpotsForTimePeriod(from, to));
    }

}



