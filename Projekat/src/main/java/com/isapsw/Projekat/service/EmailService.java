package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendConfirmationAsync(String email) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        Zahtev zahtev = zahtevRepository.findZahtevByEmail(email);

        zahtev.setVerified(true);

        zahtevRepository.save(zahtev);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        String msgHtml = "<h5>Postovani " + zahtev.getIme() + "</h5><p>Kako biste aktivirali svoj korisnicki nalog posetite sledeci <a href=\"http://localhost:8080/api/users/confirm/" + zahtev.getEmail() + "\">link</a>. Hvala na poverenju.<p>";

        message.setContent(msgHtml, "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Aktivacija korisnickog naloga");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendDeniedAsync(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Odbijanje aktivacije korisnickog naloga");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }
}