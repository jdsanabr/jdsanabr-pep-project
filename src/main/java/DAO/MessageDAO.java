package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*; //(Connection, PreparedStatement, ResultSet)
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public void createMessage() {
        // new Message();
    }

    public void deleteMessageByMessageId(int id) {
        //
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
