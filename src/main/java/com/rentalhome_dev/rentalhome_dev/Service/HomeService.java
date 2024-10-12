package com.rentalhome_dev.rentalhome_dev.Service;

import com.rentalhome_dev.rentalhome_dev.model.Home;

import java.util.List;

public interface HomeService {
    List<Home> findAll();

    Home findById(int theId);

    Home save(Home theHome);

    void deleteById(int theId);
}

