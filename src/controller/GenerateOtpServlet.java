package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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
 * Servlet implementation class GenerateOtpServlet
 */
@WebServlet({ "/GenerateOtpServlet", "/generate" })
public class GenerateOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateOtpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void sendEmail(String to, String message)
    {
   	 Properties props = new Properties();
     props.put("mail.smtp.host", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     //Establishing a session with required user details
     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("bk8341508414@gmail.com", "Bharath@9807");
         }
     });
     try {
         //Creating a Message object to set the email content
         MimeMessage msg = new MimeMessage(session);
         //Storing the comma seperated values to email addresses
//         String to = "rjagadeeswaran@yahoo.com";
         /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
         addresses in an array of InternetAddress objects*/
         InternetAddress[] address = InternetAddress.parse(to, true);
         //Setting the recepients from the address variable
         msg.setRecipients(Message.RecipientType.TO, address);
         String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
         msg.setSubject("Otp : " + timeStamp);
         msg.setSentDate(new Date());
         msg.setText(message);
         msg.setHeader("XPriority", "1");
        
         Transport.send(msg);
         System.out.println("Mail has been sent successfully");
     } catch (MessagingException mex) {
         System.out.println("Unable to send an email:\n" + mex);
     }
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String to=request.getParameter("email");
		//generate otp
		int temp = (int) (Math.random()*1000000);
		String otp=String.valueOf(temp);
		sendEmail(to, otp);
		PrintWriter out = response.getWriter();
		out.print(otp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
