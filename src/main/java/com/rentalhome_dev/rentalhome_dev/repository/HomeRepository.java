package com.rentalhome_dev.rentalhome_dev.repository;

import com.rentalhome_dev.rentalhome_dev.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {

}
