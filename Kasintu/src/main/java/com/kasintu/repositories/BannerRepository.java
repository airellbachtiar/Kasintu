package com.kasintu.repositories;

import com.kasintu.repositories.entities.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, String>
{
}
