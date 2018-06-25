package com.shows.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Show {
  // Required
  private String title;

  @Column(name = "originalairdate")
  private LocalDate originalAirDate;
  // Optional
  private String description;
  private String duration; // Duration
  private String rating; // Enum
  private String keywords; //List

  @Id
  @Column(name = "id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Show() {}

  public Show(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getOriginalAirDate() {
    return originalAirDate;
  }

  public void setOriginalAirDate(LocalDate originalAirDate) {
    this.originalAirDate = originalAirDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Show show = (Show) o;
    return Objects.equals(getId(), show.getId())
        && Objects.equals(getTitle(), show.getTitle())
        && Objects.equals(getDuration(), show.getDuration())
        && Objects.equals(getOriginalAirDate(), show.getOriginalAirDate());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getTitle(), getDuration(), getOriginalAirDate());
  }

  @Override
  public String toString() {
    return "Show{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", duration='"
        + duration
        + '\''
        + ", originalAirDate="
        + originalAirDate
        + ", rating='"
        + rating
        + '\''
        + ", keywords="
        + keywords
        + '}';
  }
}
