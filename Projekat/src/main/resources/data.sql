INSERT INTO AUTHORITY VALUES ('1', 'ROLE_PACIJENT');
INSERT INTO AUTHORITY VALUES ('2', 'ROLE_LEKAR');
INSERT INTO AUTHORITY VALUES ('3', 'ROLE_AK');
INSERT INTO AUTHORITY VALUES ('4', 'ROLE_AKC');
INSERT INTO AUTHORITY VALUES ('5', 'ROLE_MED_SESTRA');

INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('1', 'Novi Sad, Zmajognjenavuka', 'Nikako ovde', 'opis klinike', '1.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('2', 'Beograd, Nusiceva 3', 'Pravo mesto', 'opis klinike', '2.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('3', 'Novi Sad, Jablanicka 22', 'Haha lose fore', 'opis klinike', '2.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('4', 'Begec, jama 01', 'Samo nogama napred', 'opis klinike', '3.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('5', 'James Bond, 007', 'Jednom udjes nikad ne izadjes', 'opis klinike', '3.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('6', 'Paracin, Puskinova 32', 'Crveni krst', 'opis klinike', '4.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('7', 'Zmajevo, kod semafora', 'Sve naj', 'opis klinike', '4.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('8', 'Venac, toranj 1', 'Carna', 'opis klinike', '5.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('9', 'Bogojevo, Radnicka 104', 'Sjajna', 'opis klinike', '5.0');
INSERT INTO KLINIKA (id, adresa, naziv, opis, ocena) VALUES ('10', 'Knić, Narodnog Fronta 2', 'Lepa klinika', 'opis klinike', '5.0');

INSERT INTO SALA (id, datum_kreiranja, sala_identifier, klinika_id) VALUES ('1', '2019-11-30','1 - 1', '1');

INSERT INTO SALA (id, datum_kreiranja, sala_identifier, klinika_id) VALUES ('2', '2019-11-30','1 - 2', '1');

INSERT INTO TIP_PREGLEDA(id, naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id) VALUES ('1', 'Kardiovaskularni', 1200, 1300, 25, '1');
INSERT INTO TIP_PREGLEDA(id, naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id) VALUES ('2', 'Fizijatrijski', 1600, 12200, 66, '1');
INSERT INTO TIP_PREGLEDA(id, naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id) VALUES ('3', 'Fizijatrijski', 1600, 12200, 66, '2');
INSERT INTO TIP_PREGLEDA(id, naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id) VALUES ('4', 'Fizijatrijski', 1600, 12200, 66, '3');
INSERT INTO TIP_PREGLEDA(id, naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id) VALUES ('5', 'Fizijatrijski', 1600, 12200, 66, '9');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('1', 'adresa admina KC', 'adminKC@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Centric', 'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('1', '4');
INSERT INTO ADMIN_KLIN_CENTRA VALUES ('1', '1');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('2', 'adresa admina', 'adminKlinike@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Adminic', 'drzava1', 'grad1', 'telefon1');
INSERT INTO USER_AUTHORITY VALUES ('2', '3');
INSERT INTO ADMIN_KLINIKE VALUES ('1', '1', '1', '2');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('3', 'adresa lekara', 'lekar@gmail.com', 'Lekar', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('3', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('1', '1', '3', '1', '07:30', '20:00', 3.4);

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES (12, 'lekariceva 12', 'lekar123@gmail.com', 'Mile', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('12', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('2', '2', '12', '1', '07:00', '21:00', 4.5);

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES (13, 'lekariceva 12', 'lekar1235@gmail.com', 'Micko', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('13', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('3', '5', '13', '9', '08:00', '21:00', 4.0);

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES (14, 'lekariceva 12', 'lekarMicko@gmail.com', 'Micko', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('14', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('4', '2', '14', '2', '08:00', '17:00', 5.0);

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES (15, 'lekariceva 12', 'lekarMilivoje@gmail.com', 'Milivoje', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('15', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('5', '4', '15', '3', '08:00', '17:00', 2.1);

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES (16, 'lekariceva 12', 'lekarrrMilivoje@gmail.com', 'Milivojee1', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('16', '2');
INSERT INTO LEKAR(id, tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena) VALUES ('6', '4', '16', '1', '08:00', '17:00', 2.1);


INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('4', 'adresa pacijenta', 'pacijent@gmail.com', 'Pacijent', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Pacijentic',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('4', '1');
INSERT INTO PACIJENT VALUES ('1', '2019-11-20', '555333', '4');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('5', 'adresa med sestre', 'medsestra@gmail.com', 'Sestra', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Sestric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('5', '5');
INSERT INTO MEDICINSKA_SESTRA VALUES ('1','1', '5');

INSERT INTO ZAHTEV(ime, prezime, adresa, grad, drzava, telefon, email, password, jbzo, verified) VALUES ('dsads', 'dssadsa', 'dsadsa', 'dsadffds', 'fsdgdf', 'fdssd', 'fdsfds@gmail.com', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', '2132131', False);

-- INSERT INTO OCENA_KLINIKE (id, oc_klinike_identifier, ocena, klinika_id, korisnik_id) VALUES('1', '1-4', '1', '1', '4');
-- INSERT INTO OCENA_LEKARA (id, oc_lekara_identifier, ocena, lekar_id, korisnik_id) VALUES('1', '2-4', '5', '2', '4');
-- INSERT INTO OCENA_KLINIKE VALUES('2', '2-1', '2', '2', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('3', '3-1', '2', '3', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('4', '4-1', '3', '4', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('5', '5-1', '3', '5', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('6', '6-1', '4', '6', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('7', '7-1', '4', '7', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('8', '8-1', '5', '8', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('9', '9-1', '5', '9', '1');
-- INSERT INTO OCENA_KLINIKE VALUES('10', '10-1', '5', '10', '1');

INSERT INTO ZDR_KARTON(id, pacijent_id, dioptrija, visina, tezina, krvna_grupa) VALUES ('1', '1', '-1.5', '193', '80', '0+');
INSERT INTO LEK(id, naziv, sadrzaj, sifra) VALUES('1', 'Brufen 600mg', 'metan, propan, butan gutam', 'BrT6');
INSERT INTO ALERGIJE_PACIJENATA VALUES('1','1');

INSERT INTO Dijagnoza VALUES ('1', '2019-1-28 11:21:22', 'Lud', 'Pop');
INSERT INTO DIJAGNOZE_PACIJENATA VALUES ('1', '1');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('8', 'neka adresa', 'peraperic@gmail.com', 'Pera', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Pacijent',  'drzava', 'Beograd', '33333213');
INSERT INTO USER_AUTHORITY VALUES ('8', '1');
INSERT INTO PACIJENT VALUES ('2', '2019-11-22', '666777', '8');

INSERT INTO ZDR_KARTON(id, pacijent_id, dioptrija, visina, tezina, krvna_grupa) VALUES ('2', '2', '-1.35', '153', '40', '0+');
INSERT INTO LEK(id, naziv, sadrzaj, sifra) VALUES('2', 'Alergen 600mg', 'bitces alergican', 'Al234');
INSERT INTO ALERGIJE_PACIJENATA VALUES('2','2');

INSERT INTO RECEPT VALUES ('1', '2019-12-28 11:21:22', '2019-11-28 11:21:22', 'true', '2', '1');
INSERT INTO RECEPTI_PACIJENTA VALUES ('1','1');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('9', 'tamo daleko', 'email@gmail.com', 'Email', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'emajil',  'drzava', 'Novi Sad', '543543543');
INSERT INTO USER_AUTHORITY VALUES ('9', '1');
INSERT INTO PACIJENT VALUES ('3', '2019-04-13', '654654', '9');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('10', 'ulica 54', 'trtrtr@gmail.com', 'Trtrtr', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'rttrt',  'drzava', 'Nis', '7864554');
INSERT INTO USER_AUTHORITY VALUES ('10', '1');
INSERT INTO PACIJENT VALUES ('4', '2019-11-07', '543543', '10');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('11', 'adresaaaa', 'ijasam@gmail.com', 'IJaSamPacijent', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'IJaSamPrezime',  'drzava', 'Subotica', '654765');
INSERT INTO USER_AUTHORITY VALUES ('11', '1');
INSERT INTO PACIJENT VALUES ('5', '2019-12-20', '123123', '11');

INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (1, 'Odlicno ste bolesni', '2019-12-12 15:50:00', '2019-12-12 16:50:00', '2', '2', '2', '1', '0', '2019-11-30');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (2, 'Bit ces unet', '2019-12-25 15:50:00', '2019-12-25 16:50:00','1', '2', '2', '2', '0', '2019-11-30');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (3, 'Odlicno ste bolesni', '2019-11-12 15:51:00', '2019-11-12 16:51:00', '2', '2', '2', '1', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (4, 'Svaka cast', '2019-11-11 07:10:00', '2019-11-11 16:52:00', '2', '2', '2', '1', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (5, 'Bit ce unet', '2019-12-1 15:51:00', '2019-12-1 16:31:00','2', '2', '2', '1', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (6, 'Bit ce unet', '2019-12-25 16:51:00', '2019-12-25 20:00:00','1', '1', '1', '1', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (7, 'Bit ce unet', '2019-12-25 07:30:00', '2019-12-25 16:51:00','1', '1', '1', '1', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (8, 'Odlicno ste bolesni', '2019-12-25 16:52:00', '2019-12-25 21:00:00', '2', '2', '2', '2', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (9, 'Svaka cast', '2019-12-25 07:10:00', '2019-12-25 16:52:00', '2', '2', '2', '2', '0', '2019-11-6');
INSERT INTO PREGLED (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, sala_id, pacijent_id, popust, datum_kreiranja) VALUES (10, 'Svaka cast', '2019-11-25 07:10:00', '2019-11-25 16:52:00', '2', '2', '2', '2', '0', '2019-11-6');


INSERT INTO OPERACIJA (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, sala_id, pacijent_id, datum_kreiranja, tip_pregleda_id) VALUES (1,'Bit ce unet', '2019-12-1 15:51:00', '2019-12-1 16:41:00', 'Operacija jea', '1', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, sala_id, pacijent_id, datum_kreiranja, tip_pregleda_id) VALUES (2,'Bit ce unet', '2019-12-11 15:51:00', '2019-12-11 17:51:00','Operacija jea', '1', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (id, izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, sala_id, pacijent_id, datum_kreiranja, tip_pregleda_id) VALUES (3,'Operisan od zivota...', '2019-3-11 15:51:00', '2019-3-11 16:51:00', 'Operacija jea', '1', '1', '2019-11-6', 1);

INSERT INTO PRISUTNI_LEKARI VALUES (1, 1);
INSERT INTO PRISUTNI_LEKARI VALUES (1, 2);
INSERT INTO PRISUTNI_LEKARI VALUES (2, 1);
INSERT INTO PRISUTNI_LEKARI VALUES (3, 2);

INSERT INTO PACIJENTI_KLINIKE VALUES ('4', '1');
INSERT INTO PACIJENTI_KLINIKE VALUES ('2', '2');
INSERT INTO PACIJENTI_KLINIKE VALUES ('4', '3');
INSERT INTO PACIJENTI_KLINIKE VALUES ('5', '3');
INSERT INTO PACIJENTI_KLINIKE VALUES ('1', '4');
INSERT INTO PACIJENTI_KLINIKE VALUES ('1', '5');
INSERT INTO PACIJENTI_KLINIKE VALUES ('5', '2');
INSERT INTO PACIJENTI_KLINIKE VALUES ('1', '3');
INSERT INTO PACIJENTI_KLINIKE VALUES ('3', '2');
INSERT INTO PACIJENTI_KLINIKE VALUES ('6', '4');
INSERT INTO PACIJENTI_KLINIKE VALUES ('1', '1');
INSERT INTO PACIJENTI_KLINIKE VALUES ('1', '2');
