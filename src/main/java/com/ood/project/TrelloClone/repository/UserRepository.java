package com.ood.project.TrelloClone.repository;

import com.ood.project.TrelloClone.model.enitity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUserID(long userID);

    @Override
    UserDetails save(UserDetails userDetails);

    @Override
    List<UserDetails> findAll();
}
