package com.skyllx.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyllx.rental.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> { }
