package com.example.chat.persistence.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Row Data Gateway */
@Repository
public class ConversationGateway {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addConversation(Conversation conversation) {
        var sql = """ 
                INSERT INTO CONVERSATIONS (contact_id, name)
                VALUES (?,?)
                """;
        return jdbcTemplate.update(sql,conversation.contact_id(),conversation.name());
    }

    public int deleteConversation(int id) {
        var sql = """ 
                DELETE FROM CONVERSATIONS
                WHERE contact_id=?
                """;
        return jdbcTemplate.update(sql,id);
    }

    public Optional<Conversation> selectConversationById(int contact_id) {
        var sql = """ 
                SELECT *
                FROM CONVERSATIONS
                WHERE contact_id = ?
                """;
        return jdbcTemplate.query(sql,new ConversationMapper(),contact_id).stream().findFirst();
    }

    public int updateNameConversation(Conversation conversation) {
        var sql = """ 
                UPDATE CONVERSATIONS
                SET name = ?
                WHERE contact_id = ?
                """;
        return jdbcTemplate.update(sql,conversation.name(),conversation.contact_id());
    }
}
