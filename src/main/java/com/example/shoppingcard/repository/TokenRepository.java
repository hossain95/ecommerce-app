package com.example.shoppingcard.repository;

import com.example.shoppingcard.model.AuthenticationToken;
import com.example.shoppingcard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    @Query("select u from AuthenticationToken u where u.user =?1")
    public AuthenticationToken getTokenByUserId(User user);
}
