package com.shows.challenge.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShowWithBuilder {
  // Required
  private String title;

  @Column(name = "originalairdate")
  private LocalDate originalAirDate;
  // Optional
  private String description;
  private String duration; // Duration
  private String rating; // Enum
  private String keywords;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private ShowWithBuilder(ShowBuilder builder) {
    this.title = builder.title;
    this.originalAirDate = builder.originalAirDate;
    this.description = builder.description;
    this.duration = builder.duration;
    this.rating = builder.rating;
    this.keywords = builder.keywords;
  }

  public ShowWithBuilder() {};

  public Long getId() {
    return id;
  }


  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getDuration() {
    return duration;
  }

  public LocalDate getOriginalAirDate() {
    return originalAirDate;
  }

  public String getRating() {
    return rating;
  }

  public String getKeywords() {
    return keywords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShowWithBuilder show = (ShowWithBuilder) o;
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

  public static class ShowBuilder {
    // Required
    private final String title;
    private final LocalDate originalAirDate;

    // Optional
    private String description = "Empty";
    private String duration = "Empty";
    private String rating = "Empty";
    private String keywords = "Empty";

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ShowBuilder(String title, LocalDate originalAirDate) {
      this.title = title;
      this.originalAirDate = originalAirDate;
    }

    public ShowBuilder description(String val) {
      description = val;
      return this;
    }

    public ShowBuilder duration(String val) {
      duration = val;
      return this;
    }

    public ShowBuilder rating(String val) {
      rating = val;
      return this;
    }

    public ShowBuilder keyWords(String val) {
      keywords = val;
      return this;
    }

    public ShowBuilder id(Long val){
      id = val;
      return this;
    }

    public ShowWithBuilder build() {
      return new ShowWithBuilder(this);
    }
  }
}
