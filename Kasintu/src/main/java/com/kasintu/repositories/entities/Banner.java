package com.kasintu.repositories.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banners")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Banner {

    @Id
    @Column(name = "banner_id")
    private String bannerID;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany
    @JoinTable(
            name = "banners_pull_rates",
            joinColumns = @JoinColumn(name = "banner_id"),
            inverseJoinColumns =@JoinColumn(name = "pull_rate_id")
    )
    private List<PullRate> pullRates;
}
