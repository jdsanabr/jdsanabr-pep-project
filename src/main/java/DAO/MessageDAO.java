package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*; //(Connection, PreparedStatement, ResultSet)
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public Message createMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (posted_by, message_text) values (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.posted_by);
            preparedStatement.setString(2, message.message_text);

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()) {
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.posted_by, message.message_text, message.time_posted_epoch);
            }//int message_id, int posted_by, String message_text, long time_posted_epoch
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message deleteMessageByMessageId(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
        	//change: added '*' to sql string
            String sql = "DELETE FROM message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            Message deleteThisMessage = getMessageByMessageId(id);
            
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            
            return deleteThisMessage;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    public List<Message> getAllMessagesForUser(int account_id) {
    	Connection connection = ConnectionUtil.getConnection();
    	
    	List<Message> messages = new ArrayList<>();
    	
    	try {
    		String sql = "SELECT * FROM message WHERE posted_by = ?";
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()) {
                //int message_id, int posted_by, String message_text, long time_posted_epoch
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));

                messages.add(message);
            }
    	} catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	
        return messages;
    }

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();

        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                //int message_id, int posted_by, String message_text, long time_posted_epoch
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));

                messages.add(message);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    public Message getMessageByMessageId(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                //int message_id, int posted_by, String message_text, long time_posted_epoch
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));

                return message;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void updateMessageByMessageId(int id, String newText) {
    	Connection connection = ConnectionUtil.getConnection();
    	
    	try {
    		String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		
    		preparedStatement.setString(1, newText);
    		preparedStatement.setInt(2, id);
    		
    		preparedStatement.executeUpdate();
    	} catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
}
