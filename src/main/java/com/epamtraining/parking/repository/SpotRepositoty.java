package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepositoty extends JpaRepository <SpotEntity, Long> {

    List<SpotEntity> findAllByVacancyIsTrue();

    List<SpotEntity> findAllByVacancy(boolean isFree);
}
