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
    public Message createMessage(Message message) {
        //SUCCESSFUL IF: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
        //message should be persisted, but will not contain a message_id
        //NOT SUCCESSFUL: the response status should be 400. (Client error)

        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (posted_by, message_text) values (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message.posted_by);
            preparedStatement.setString(2, message.message_text);

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.posted_by, message.message_text, message.time_posted_epoch);
            }//int message_id, int posted_by, String message_text, long time_posted_epoch
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
