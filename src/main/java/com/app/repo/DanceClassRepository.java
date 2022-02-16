package com.app.repo;

import com.app.model.DanceClass;
import com.app.model.DanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanceClassRepository extends JpaRepository<DanceClass, Long> {
    DanceClass findByDanceType(DanceType danceType);

}
