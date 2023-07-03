package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        accountService = new AccountService();
        messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        //for creating a message:
        app.post("/messages", this::postCreateMessageHandler);
        //delete message by message id
        app.delete("/messages/{message_id}", this::deleteMessageByMessageIdHandler);
        //get all messages for user
        app.get("/accounts/{account_id}/messages", this::getAllMessagesForUserHandler);
        //get all messages
        app.get("/messages", this::getAllMessagesHandler);
        //get a message by message id
        app.get("/messages/{message_id}", this::getMessageByMessageIdHandler);
        //update a message by message id
        app.patch("messages/{message_id}", this::updateMessageByMessageIdHandler);
        //user login
        app.post("login", this::postUserLogin);
        //user registration
        app.post("register", this::postUserRegistration);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //SUCCESSFUL IF: message_text is not blank, under 255 chars, and posted_by refers to a real/existing user
    //message should be persisted, but will not contain a message_id
    //NOT SUCCESSFUL: the response status should be 400. (Client error)
    private void postCreateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        Message message = mapper.readValue(context.body(), Message.class);
        
        Message createdMessage = messageService.createMessage(message);
        
        if(createdMessage != null) {
            context.json(mapper.writeValueAsString(createdMessage));
        } else {
            context.status(400);
        }
    }

    //delete message by message_id
    private void deleteMessageByMessageIdHandler(Context context) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	int message_id = Integer.parseInt(context.pathParam("message_id"));
    	
    	Message deletedMessage = messageService.deleteMessageByMessageId(message_id);
    	
    	if(deletedMessage != null) {
    		context.json(mapper.writeValueAsString(deletedMessage));
    	} else {
    		mapper.writeValueAsString("");
    	}
    }
    //

    //retreieve all messages for user
    private void getAllMessagesForUserHandler(Context context) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	List<Message> messages = messageService.getAllMessagesForUser(Integer.parseInt(context.pathParam("account_id")));
    	
    	if(messages != null) {
    		context.json(mapper.writeValueAsString(messages));
    	} else {
    		mapper.writeValueAsString("");
    	}
    }
    //

    //retrieve all messages
    private void getAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }
    
    //retrieve a message by message id
    private void getMessageByMessageIdHandler(Context context) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	Message message = messageService.getMessageByMessageId(Integer.parseInt(context.pathParam("message_id")));
    	
    	if(message != null) {
    		context.json(mapper.writeValueAsString(message));
    	} else {
    		mapper.writeValueAsString("");
    	}
    	
    }

    //update message_text
    private void updateMessageByMessageIdHandler(Context context) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	int message_id = Integer.parseInt(context.pathParam("message_id"));
    	String newText = mapper.readValue(context.body(), Message.class).getMessage_text();
    	//debugging:
    	//System.out.println("newText: " + newText);
    	
    	Message updatedMessage = messageService.updateMessagebyMessageId(message_id, newText);
    	
    	if(updatedMessage != null) {
    		context.json(mapper.writeValueAsString(updatedMessage));
    	} else {
    		context.status(400);
    	}
    }
    //

    //user login, NOT FINISHED
    private void postUserLogin(Context context) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Account account = mapper.readValue(context.body(), Account.class);
    	
    	Account accountLoggedIn = accountService.login(account);
    	
    	if(accountLoggedIn != null) {
    		context.json(mapper.writeValueAsString(accountLoggedIn));	
    	} else {
    		context.status(401);
    	}
    }
    //

    //user registration
    private void postUserRegistration(Context context) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Account account = mapper.readValue(context.body(), Account.class);
    	//debugging:
    	//System.out.println("controller file, account obj: " + account);
    	
    	Account createdAccount = accountService.userRegistration(account);
    	
    	if(createdAccount != null) {
    		context.json(mapper.writeValueAsString(createdAccount));
    	} else {
    		context.status(400);
    	}
    }
    // 
}
