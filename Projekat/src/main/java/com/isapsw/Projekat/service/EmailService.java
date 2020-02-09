package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Pregled;
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
import java.util.Date;

@Service
public class EmailService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PregledService pregledService;

    @Async
    public void sendConfirmationAsync(String email) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        System.out.println(email);

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
    public void sendEmailPacijentuZakazan(Korisnik korisnik, Pregled pregled) throws  MailException,MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        String msgHtml = "<h5>Postovani " + korisnik.getIme() + "</h5><p>Zakazan vam je pregled za " + pregled.getDatumPocetka()  + " <p>";

        message.setContent(msgHtml, "text/html");
        mimeMessageHelper.setTo(korisnik.getEmail());
        mimeMessageHelper.setSubject("Obavestenje o zakazanom pregledu");
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
    }

    @Async
    public void sendOsobljePregledRezervacijaSale(String email, Date termin, String nazivSale) throws MailException, InterruptedException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + "Novi pregled je dodat u Vas radni kalendar:" + "<br/>" +
                "Termin: " + termin.toString() + "<br/>" +
                "Sala: " + nazivSale + "</p>", "text/html");
        mimeMessageHelper.setTo("neskexx@gmail.com");
        mimeMessageHelper.setSubject("Rezervisanje sale za Pregled");
        mimeMessageHelper.setFrom("neskexx@gmail.com");

        javaMailSender.send(message);
    }

    @Async
    public void sendPacijentPregledRezervacijaSale(String email, Date termin, String nazivKlinike, String adresaKlinike, String nazivSale) throws MailException, InterruptedException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + "Rezervisana je sala za Vas novi pregled:" + "<br/>" +
                "Klinika: " + nazivKlinike + "<br/>" +
                "Adresa klinike: " + adresaKlinike + "<br/>" +
                "Termin: " + termin.toString() + "<br/>" +
                "Sala: " + nazivSale + "</p>", "text/html");
        mimeMessageHelper.setTo("neskexx@gmail.com");
        mimeMessageHelper.setSubject("Rezervisanje sale za Pregled");
        mimeMessageHelper.setFrom("neskexx@gmail.com");

        javaMailSender.send(message);
    }

    @Async
    public void sendOsobljeOperacijaRezervacijaSale(String email, Date termin, String nazivSale) throws MailException, InterruptedException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + "Nova operacija je dodata u Vas radni kalendar:" + "<br/>" +
                "Termin: " + termin.toString() + "<br/>" +
                "Sala: " + nazivSale + "</p>", "text/html");
        mimeMessageHelper.setTo("neskexx@gmail.com");
        mimeMessageHelper.setSubject("Rezervisanje sale za Operaciju");
        mimeMessageHelper.setFrom("neskexx@gmail.com");

        javaMailSender.send(message);
    }

    @Async
    public void sendPacijentOperacijaRezervacijaSale(String email, Date termin, String nazivKlinike, String adresaKlinike, String nazivSale) throws MailException, InterruptedException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + "Rezervisana je sala za Vasu operaciju:" + "<br/>" +
                "Klinika: " + nazivKlinike + "<br/>" +
                "Adresa klinike: " + adresaKlinike + "<br/>" +
                "Termin: " + termin.toString() + "<br/>" +
                "Sala: " + nazivSale + "</p>", "text/html");
        mimeMessageHelper.setTo("neskexx@gmail.com");
        mimeMessageHelper.setSubject("Rezervisanje sale za Operaciju");
        mimeMessageHelper.setFrom("neskexx@gmail.com");

        javaMailSender.send(message);
    }

    @Async
    public void sendOdsustvoOdbijanje(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Odbijanje zahteva za odsustvo");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendOdsustvoPrihvatanje(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Prihvatanje zahteva za odsustvo");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendOdmorOdbijanje(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Odbijanje zahteva za godisnji odmor");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendOdmorPrihvatanje(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Prihvatanje zahteva za godnisnji odmor");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendAdminuZakazivanjePregledaOperacije(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Zakazivanje pregleda/operacije od strane lekara");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }

    @Async
    public void sendAdminuZakazivanjePregledaOperacijePacijent(String email, String msg) throws MailException, InterruptedException, MessagingException {
        System.out.println("Saljem email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        message.setContent("<p>" + msg + "</p>", "text/html");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Zakazivanje pregleda od strane pacijenta");
        mimeMessageHelper.setFrom("vesna.svrkota997@gmail.com");

        javaMailSender.send(message);

        System.out.println("Email je poslat");
    }
}