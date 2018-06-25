package com.shows.challenge.services;

import com.shows.challenge.entities.Show;
import com.shows.challenge.repositories.ShowRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowServiceImpl extends AbstractService{

  @Autowired private ShowRepository repository;

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public Page<Show> listShows(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public List<Show> listShows(){
    return repository.findAll();
  }

  public void addShow(Show newShow) {
    repository.save(newShow);
  }

  public Show getShowByTitle(String title) {
    List<Show> shows = repository.findByTitle(title);
    return shows.get(0);
  }

  public Show getShowById(Long id) {
    return repository.getOne(id);
  }

  public void updateShow(Show show) {
    repository.save(show);
  }

  public boolean isShowInDB(Show show) {
    Show checkShow =
        repository.findByTitleAndAndOriginalAirDate(show.getTitle(), show.getOriginalAirDate());

    return checkShow != null ? true : false;
  }
}
