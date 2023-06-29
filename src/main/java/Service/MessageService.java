package Service;

import Model.Message;
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
        //SUCCESSFUL IF: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
        //message should be persisted, but will not contain a message_id
        //NOT SUCCESSFUL: the response status should be 400. (Client error)

        if(!message.message_text.isEmpty() && message.getMessage_text().length() < 255 && message.posted_by != 0) {
            return messageDAO.createMessage(message);
        }
        return null;
    }
    
}
