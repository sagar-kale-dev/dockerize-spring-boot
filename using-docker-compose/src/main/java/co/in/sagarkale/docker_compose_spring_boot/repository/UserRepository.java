package co.in.sagarkale.docker_compose_spring_boot.repository;

import co.in.sagarkale.docker_compose_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}