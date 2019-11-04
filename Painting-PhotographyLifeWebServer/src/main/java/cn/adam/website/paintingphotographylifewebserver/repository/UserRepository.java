package cn.adam.website.paintingphotographylifewebserver.repository;

import cn.adam.website.paintingphotographylifewebserver.modle.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);
}
