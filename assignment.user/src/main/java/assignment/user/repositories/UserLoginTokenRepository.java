package assignment.user.repositories;

import org.springframework.data.repository.CrudRepository;

import assignment.user.models.UserLoginToken;


public interface UserLoginTokenRepository extends CrudRepository<UserLoginToken, Long> {
    UserLoginToken findByToken(String token);
}
