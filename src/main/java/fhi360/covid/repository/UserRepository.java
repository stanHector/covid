package fhi360.covid.repository;

import fhi360.covid.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users getByPasscode(String passcode);

    Users findByPasscode(@NotBlank String passcode);
}
