package me.vanemy.harry.potter.books.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM UserEntity c WHERE c.id = :identifier")
    Boolean existByIdentifier(@Param("identifier") int identifier);
}
