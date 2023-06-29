package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*; //(Connection, PreparedStatement, ResultSet)
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    /*
     * Create New Message:
     * 
     * As a user, I should be able to submit a new post
     * on the endpoint POST localhost:8080/messages.
     * The request body will contain a JSON representation of a message,
     * which should be persisted to the database, but will not contain a message_id.
    */
    public void createMessage(Message message) {
        //SUCCESSFUL IF: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
        //message should be persisted, but will not contain a message_id

        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (message_text, posted_by) values (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message.message_text);
            preparedStatement.setInt(2, message.posted_by);

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        //NOT SUCCESSFUL: the response status should be 400. (Client error)

        // new Message();
    }

    /*
     * Delete a Message Given Message Id:
     * 
     * As a User, I should be able to submit a DELETE request
     * on the endpoint DELETE localhost:8080/messages/{message_id}
    */
    public void deleteMessageByMessageId(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "DELETE FROM message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //is there a preparedStatement for deleting?

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Message> retrieveAllMessagesForUser() {
        //
        return null;
    }

    public List<Message> retrieveAllMessages() {
        //
        return null;
    }

    public Message retrieveMessageByMessageId(int id) {
        //
        return null;
    }

    public void updateMessageText(int id, Message m) { //will need int id as well?
        //
    }
    
}
