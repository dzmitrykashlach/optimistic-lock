package com.version.optimisticlock;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class City {
  @Version
  private int version;

  @Id
  @GeneratedValue
  @Getter
  @Setter
  @Column(name = "city_id")
  private int id;

  @Getter
  @Setter
  private String city;

  @Getter
  @Setter
  private String countryId;

  @Getter
  @Setter
  private String lastUpdate;
}
