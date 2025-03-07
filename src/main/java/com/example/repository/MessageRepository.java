package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    // Need a query that deletes a record and returns the number of rows affected
    @Modifying  // Denotes this query as able to modify the data (non-select query)
    @Transactional  // Must be a transaction in order to execute a modifying query
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    Integer deleteByIdAndReturnRowsAffected(@Param("messageId") Integer messageId);
}
