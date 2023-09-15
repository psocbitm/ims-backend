package com.wf.training.ims.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wf.training.ims.model.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

    //Custom Method to fetch record/object based on email field - non id field.
    public Optional<Dealer> findByEmail(String email);
}
