package com.shows.challenge;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.shows.challenge.entities.Show;
import com.shows.challenge.repositories.ShowRepository;
import com.shows.challenge.services.ShowServiceImpl;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChallengeApplicationTests {

  @Autowired private ShowRepository repository;
  @Autowired private ShowServiceImpl services;

  @Test
  public void contextLoads() {}

  @Test
  public void testGetShows() throws Exception {
    List<Show> shows = services.listShows();
    assertThat(shows.size(), is(2));
  }

  @Test
  public void testGetAccount() throws Exception {
    Show show = repository.findById(1L).get();
    List<Show> showsByTitle = repository.findByTitle("Test 2");
    Show showByTitle = showsByTitle.get(0);
    assertThat(show.getId(), is(1L));
    assertThat(show.getTitle(), is("Test"));

    assertThat(showByTitle.getId(), is(2L));
    assertThat(showByTitle.getTitle(), is("Test 2"));

    show = services.getShowById(2L);
    assertThat(show.getId(), is(2L));
    assertThat(show.getTitle(), is("Test 2"));
  }

  @Test
  public void testCreateShow() throws Exception {
    Show show = new Show("Test 99");
    services.addShow(show);
    Long id = show.getId();
    assertThat(id, is(notNullValue()));

    Show again = services.getShowById(id);
    assertThat(again.getId(), is(3L));
    assertThat(again.getTitle(), is("Test 99"));
  }

  @Test
  public void testUpdateShow() throws Exception {
    Show show = services.getShowById(1L);
    String title = "Title updated";
    show.setTitle(title);
    services.updateShow(show);

    Show again = services.getShowById(1L);
    assertThat(again.getTitle(), is("Title updated"));
  }

  @Test
  public void testSortedShows() throws Exception {
    List<Show> shows = repository.findAllByOrderByIdDesc();
    Show show = shows.get(0);

    assertThat(show.getId(), is(2L));
  }
}
