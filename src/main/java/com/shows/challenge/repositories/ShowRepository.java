package com.shows.challenge.repositories;

import com.shows.challenge.entities.Show;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
  List<Show> findAllByOrderByIdDesc();

  List<Show> findByTitle(String title);

  Page findAll(Pageable pageable);

  List<Show> findAll();

  Show findByTitleAndAndOriginalAirDate(String title, LocalDate airDate);
}
