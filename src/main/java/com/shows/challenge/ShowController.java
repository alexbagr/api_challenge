package com.shows.challenge;

import com.shows.challenge.entities.Show;
import com.shows.challenge.services.ShowServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ShowController {
  @Autowired ShowServiceImpl services;

  //  @ResponseBody
  //  @RequestMapping(value = "/showAll", method = RequestMethod.GET)
  //  public String getAllShows(
  //      @PageableDefault(size = 10, sort = "id") Pageable pageable, Model model) {
  //
  //    Page<Show> page = services.listShows(pageable);
  //    List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
  //    if (sortOrders.size() > 0) {
  //      Sort.Order order = sortOrders.get(0);
  //      model.addAttribute("sortProperty", order.getProperty());
  //      model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
  //    }
  //    model.addAttribute("page", page);
  //
  //    for (Show show : page) {
  //      System.out.println(show);
  //    }
  //    return "show-page";
  //  }

  @ResponseBody
  @RequestMapping(value = "/showAll", method = RequestMethod.GET)
  public ResponseEntity<List<Show>> getAllShows() {

    List<Show> shows = services.listShows();
    if (shows.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(shows, HttpStatus.OK);
  }

  @RequestMapping(
      value = "/addShow",
      method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> createShow(@RequestBody Show show, UriComponentsBuilder ucBuilder) {

    if (services.isShowInDB(show)) {
      System.out.println("A show with title " + show.getTitle() + " already exist");
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    services.addShow(show);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/show/{id}").buildAndExpand(show.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/show/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Show> updateShow(@PathVariable("id") long id, @RequestBody Show show) {

    Show showToUpdate = services.getShowById(id);

    // change to Optional

    if (showToUpdate == null) {
      System.out.println("Could not find show with id " + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    showToUpdate.setDescription(show.getDescription());
    showToUpdate.setDuration(show.getDuration());
    showToUpdate.setRating(show.getRating());

    services.updateShow(showToUpdate);
    return new ResponseEntity<>(showToUpdate, HttpStatus.OK);
  }
}
