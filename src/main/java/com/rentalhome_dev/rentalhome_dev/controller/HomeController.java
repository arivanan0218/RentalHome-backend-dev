package com.rentalhome_dev.rentalhome_dev.controller;

import com.rentalhome_dev.rentalhome_dev.Service.HomeService;
import com.rentalhome_dev.rentalhome_dev.model.Home;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/homes")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public List<Home> findAll() {
        return homeService.findAll();
    }

    // add mapping for GET /homes/{homeId}

    @GetMapping("/homes/{homeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public Home getHome(@PathVariable int homeId) {
        Home theHome = homeService.findById(homeId);

        if(theHome == null) {
            throw new RuntimeException("Home id not found: " + homeId);
        }

        return theHome;
    }

    // add mapping for POST /homes - add new home

    @PostMapping("/homes")
    @PreAuthorize("hasRole('client_admin')")
    public Home addHome(@RequestBody Home theHome) {

        theHome.setId(0);

        Home dbHome = homeService.save(theHome);

        return dbHome;

    }

    @PutMapping("/homes/{homeId}")
    @PreAuthorize("hasRole('client_admin')")
    public Home updateHome(@PathVariable int homeId, @RequestBody Home updatedHome) {
        // Retrieve the existing home from the database
        Home dbHome = homeService.findById(homeId);

        // Check if the home with given homeId exists
        if (dbHome == null) {
            throw new RuntimeException("Home id not found: " + homeId);
        }

        // Update the existing home entity with the new data
        dbHome.setImage(updatedHome.getImage());
        dbHome.setTitle(updatedHome.getTitle());
        dbHome.setPrice(updatedHome.getPrice());
        dbHome.setCapacity(updatedHome.getCapacity());
        dbHome.setBody(updatedHome.getBody());
        dbHome.setAddress(updatedHome.getAddress());
        // Update other properties as needed

        // Save the updated home entity back to the database
        Home savedHome = homeService.save(dbHome);

        // Return the updated home entity
        return savedHome;
    }



    // add mapping for DELETE /homes/{homeId} - delete the home

    @DeleteMapping("/homes/{homeId}")
    @PreAuthorize("hasRole('client_admin')")
    public String deleteHome(@PathVariable int homeId) {

        Home tempHome = homeService.findById(homeId);

        if(tempHome == null) {
            throw new RuntimeException("Home id not found : " + homeId);
        }

        homeService.deleteById(homeId);

        return "deleted home id : " + homeId;
    }
}
