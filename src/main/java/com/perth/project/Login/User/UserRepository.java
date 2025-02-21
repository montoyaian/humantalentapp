package com.perth.project.Login.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User userFail set userFail.failedAttemps = userFail.failedAttemps + 1 where userFail.username = :username")
    void incrementFailedTrys(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("update User u set u.blockedAccount = true where u.username = :username")
    void blockAccount(@Param("username") String username);

    @Query("select u.failedAttemps from User u where u.username = :username")
    int getFailedTrys(@Param("username") String username);

    @Query("select u.blockedAccount from User u where u.username = :username")
    boolean isAccountBlocked(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("update User u set u.blockedAccount = false, u.failedAttemps = 0 where u.username = :username")
    void unblockUser(@Param("username") String username);
}
