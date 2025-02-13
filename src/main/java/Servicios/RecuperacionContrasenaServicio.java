package Servicios;

public class RecuperacionContrasenaServicio {
	
	/*
	// Método para enviar correos
	public void enviarCorreo(String correoDestinatario, String asunto, String mensaje) throws MessagingException {
	    JavaMailSender mailSender = configurarServidorSMTP();
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	    helper.setTo(correoDestinatario);
	    helper.setSubject(asunto);
	    helper.setText(mensaje, false); // false = texto plano
	    helper.setFrom("bonsaisur@gmail.com");

	    mailSender.send(mimeMessage);
	    System.out.println("[Correo enviado a " + correoDestinatario + "]");
	}

	// Configuración del servidor SMTP (solo si no inyectas JavaMailSender desde Spring)
	private JavaMailSender configurarServidorSMTP() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("bonsaisur@gmail.com");
	    mailSender.setPassword("msprjeksnbhekmjc");

	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.starttls.required", "true");

	    mailSender.setJavaMailProperties(props);
	    return mailSender;
	}
	
	*/
}
