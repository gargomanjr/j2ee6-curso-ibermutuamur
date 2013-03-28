package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Mail", urlPatterns="/Mail")
public class Ejemplo_Mail extends HttpServlet {
	
    @Resource(name="mail/Prueba")
    private Session session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Mail() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMail( request,  response);
	}

	
	private void sendMail(HttpServletRequest request, HttpServletResponse response){
        try {    
        	
        	String recipient = "ajosegmartinez@gmail.com";
        	String mailer = "AntonioJoseGarcia@ibermutuamur.es";
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient, false));
            message.setSubject("Test Message from ConfirmerBean");
            DateFormat dateFormatter = DateFormat
                    .getDateTimeInstance(DateFormat.LONG,
                    DateFormat.SHORT);
            Date timeStamp = new Date();

            String messageText = "Thank you for your order." + "\n"
                    + "We received your order on "
                    + dateFormatter.format(timeStamp) + ".";
            message.setText(messageText);

            message.setHeader("X-Mailer", mailer);
            message.setSentDate(timeStamp);
            // Send message
            Transport.send(message);
        	
        	//------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMail(request,response);
	}

}
