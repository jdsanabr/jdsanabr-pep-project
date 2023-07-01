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

    //create a message
    public Message createMessage(Message message) {
        //REQ: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
        //message should be persisted, but will not contain a message_id

        //posted_by ??
        if(!message.message_text.isEmpty() && message.getMessage_text().length() < 255) {
            return messageDAO.createMessage(message);
        }
        return null;
    }
    //

    //delete message by message_id
    public Message deleteMessageByMessageId(int message_id) {
    	if(messageDAO.getMessageByMessageId(message_id) != null) {
    		return messageDAO.deleteMessageByMessageId(message_id);
    	} else {
    		return null;
    	}
    }
    //

    //retrieve all messages for user
    public List<Message> getAllMessagesForUser(int account_id) {
    	return messageDAO.getAllMessagesForUser(account_id);
    }
    //

    //retrieve all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    
    
    //retrieve a message by message id
    public Message getMessageByMessageId(int id) {
    	return messageDAO.getMessageByMessageId(id);
    }
    //

    //update message_text
    public Message updateMessagebyMessageId(int id, String newText) {
    	//REQ: successful if and only if the message id already exists
    	//and the new message_text is not blank
    	//and is not over 255 characters.
    	Message message = getMessageByMessageId(id);
    	
    	if(message != null
    			&& !message.message_text.isEmpty()
    			&& !newText.isEmpty()
    			&& newText.length() < 255) {
    		messageDAO.updateMessageByMessageId(id, newText);
    		return messageDAO.getMessageByMessageId(id);
    	} else {
    		return null;
    	}
    }
    //
    
}
