package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    // Need a query that deletes a record by Id and returns the number of rows affected
    @Modifying  // Denotes this query as able to modify the data (non-select query)
    @Transactional  // Must be a transaction in order to execute a modifying query
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    Integer deleteByIdAndReturnRowsAffected(@Param("messageId") Integer messageId);

    // Need a query that updates a record's messageText and returns the number of rows affected (identified by Id)
    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    Integer updateTextByIdAndReturnRowsAffected(@Param("messageText") String messageText, @Param("messageId") Integer messageId);

    List<Message> findAllByPostedBy(Integer postedBy);
}
