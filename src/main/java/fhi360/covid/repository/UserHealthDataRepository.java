package fhi360.covid.repository;

import fhi360.covid.model.UserHealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHealthDataRepository extends JpaRepository<UserHealthData, Long> {
    List<UserHealthData> findUserHealthDataByUserId(Long id);

}