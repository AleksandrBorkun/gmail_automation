package com.epam.gmail.utils;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class MailAPI {
	

	public static void deleteAllMailIn(String login, String password, String folderName){
	 
	    Properties props = new Properties();
	    props.setProperty("mail.store.protocol", "imaps");
	    props.setProperty("mail.imaps.host", "imap.gmail.com");
	    props.setProperty("mail.imaps.port", "993");
	    props.setProperty("mail.imaps.ssl.enable", "true");
	    
		
		try {
			Session session = Session.getInstance(props, null);
		    Store store = session.getStore();
		    store.connect(login, password);
		    Folder sent = store.getFolder(folderName); // Get folder by name
		    sent.open(Folder.READ_WRITE);
		    Message[] messageList = sent.getMessages();
	        for (Message m : messageList) {
	        m.setFlag(Flags.Flag.DELETED, true);
	        }
	        
	        sent.close(true);
	        store.close();
	       
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}

}
