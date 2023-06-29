package Service;

import Model.Message;

import java.util.List;

import DAO.MessageDAO;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO m) {
        messageDAO = m;
    }

    public Message createMessage(Message message) {
        //REQ: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
        //message should be persisted, but will not contain a message_id

        //posted_by ??
        if(!message.message_text.isEmpty() && message.getMessage_text().length() < 255) {
            return messageDAO.createMessage(message);
        }
        return null;
    }

    //delete message by message_id
    //
    //

    //retreieve all messages for user
    //
    //

    //retrieve all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    //retrieve message by message_id
    //
    //

    //update message_text
    //
    //

    //user login
    //
    //

    //user reg
    //
    //
    
}
