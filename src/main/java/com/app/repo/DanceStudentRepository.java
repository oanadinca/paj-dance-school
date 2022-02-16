package com.app.repo;

import com.app.model.DanceStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DanceStudentRepository extends JpaRepository<DanceStudent, Long> {
    Optional<DanceStudent> getByNameAndSurname(String name, String surname);
}
