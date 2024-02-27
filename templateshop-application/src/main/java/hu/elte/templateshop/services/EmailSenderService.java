package hu.elte.templateshop.services;

import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailSenderService {
    private final String from = "ivamat9.a@gmail.com";
    private final String subject = "Your ordered templates";
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private ProductRepository productRepository;

   /* public void sendSimpleEmail(String to, String text) {
        SimpleMailMessage message = getSimpleMailMessage();
        message.setText(text);
        message.setTo(to);
        emailSender.send(message);
    }*/

    public void sendMessageWithAttachment(String to, String[] cartElements) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setSubject(subject);

        helper.setTo(to);
        helper.setText("Dear Customer,\n Here are Your ordered templates :)\n\n");

        List<Product> productsToSend = new ArrayList<>();
        for (String id : cartElements) {
            Product product = productRepository.findById(Integer.parseInt(id)).get();
            productsToSend.add(product);
        }

        for (Product p : productsToSend) {
            helper.addAttachment(p.getName(),  new FileSystemResource(new File("templates/" + p.getFileName() + ".zip")));
        }

        emailSender.send(message);
    }
    
    /*private void addEveryAttachment(MimeMessageHelper helper, List<Integer> shoppingCartId) {
        for (int id : shoppingCartId) {
            if (productRepository.existsById(id)) {
                Product currProd = productRepository.findById(id).get();
                try {
                    helper.addAttachment(currProd.getName(), new FileSystemResource(new File(currProd.getFileName())));
                }
                catch (Exception ex){
                }
            }
        }
    }*/

/*    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(subject);
        return message;
    }*/
}
