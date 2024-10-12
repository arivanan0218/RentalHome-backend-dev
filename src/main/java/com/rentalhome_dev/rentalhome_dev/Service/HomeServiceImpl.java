package com.rentalhome_dev.rentalhome_dev.Service;

import com.rentalhome_dev.rentalhome_dev.model.Home;
import com.rentalhome_dev.rentalhome_dev.repository.HomeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Home findById(int theId) {
        Optional<Home> result = homeRepository.findById(theId);

        Home theHome = null;

        if (result.isPresent()) {
            theHome = result.get();
        }
        else {
            throw new RuntimeException("Did not find Home Id - " + theId);
        }

        return theHome;
    }

    @Transactional
    @Override
    public Home save(Home theHome) {
        return homeRepository.save(theHome);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        homeRepository.deleteById(theId);
    }
}
