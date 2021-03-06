INSERT INTO AUTHORITY VALUES ('1', 'ROLE_PACIJENT');
INSERT INTO AUTHORITY VALUES ('2', 'ROLE_LEKAR');
INSERT INTO AUTHORITY VALUES ('3', 'ROLE_AK');
INSERT INTO AUTHORITY VALUES ('4', 'ROLE_AKC');
INSERT INTO AUTHORITY VALUES ('5', 'ROLE_MED_SESTRA');

INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Novi Sad, Zmaj Ognjena Vuka', 'Nikako ovde', 'opis klinike', '1.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Dzordza Vasingtona 17', 'Pravo mesto', 'opis klinike', '2.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Novi Sad, Bulevar cara Lazara 77', 'Haha lose fore', 'opis klinike', '2.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Novi Sad, Bulevar Slobodana Jovanovica 13', 'Samo nogama napred', 'opis klinike', '3.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Omladinskih brigada 104', 'Jednom udjes nikad ne izadjes', 'opis klinike', '3.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Nehruova 53', 'Crveni krst', 'opis klinike', '4.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Ustanicka 16a', 'Sve naj', 'opis klinike', '4.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Kraljice Jelene 22', 'Carna', 'opis klinike', '5.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Beograd, Bojanska 16', 'Sjajna', 'opis klinike', '5.0');
INSERT INTO KLINIKA (adresa, naziv, opis, ocena) VALUES ('Novi Sad, Cara Dusana 26', 'Lepa klinika', 'opis klinike', '5.0');

INSERT INTO SALA (datum_kreiranja, sala_identifier, naziv, klinika_id, aktivna) VALUES ('2019-11-30','1 - 1', 'Sala za preglede 1', '1', true);
INSERT INTO SALA (datum_kreiranja, sala_identifier, naziv, klinika_id, aktivna) VALUES ('2019-11-30','1 - 2', 'Sala za preglede 2', '1', true);

INSERT INTO TIP_PREGLEDA(naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id, aktivan) VALUES ('Kardiovaskularni', 1200, 1300, 25, '1', true );
INSERT INTO TIP_PREGLEDA(naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id, aktivan) VALUES ('Fizijatrijski', 1600, 12200, 66, '1', true);
INSERT INTO TIP_PREGLEDA(naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id, aktivan) VALUES ('Fizijatrijski', 1600, 12200, 66, '2', true);
INSERT INTO TIP_PREGLEDA(naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id, aktivan) VALUES ('Fizijatrijski', 1600, 12200, 66, '3', true);
INSERT INTO TIP_PREGLEDA(naziv, cena_pregleda, cena_operacije, minimalno_trajanje_min, klinika_id, aktivan) VALUES ('Fizijatrijski', 1600, 12200, 66, '9', true );

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa admina KC', 'adminKC@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Centric', 'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('1', '4');
INSERT INTO ADMIN_KLIN_CENTRA(korisnik_id) VALUES ('1');

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa admina', 'admin@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Adminic', 'drzava1', 'grad1', 'telefon1');
INSERT INTO USER_AUTHORITY VALUES ('2', '3');
INSERT INTO ADMIN_KLINIKE(klinika_id, korisnik_id) VALUES ('1', '2');

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa lekara', 'lekar@gmail.com', 'Lekar', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('3', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('1', '3', '1', '07:00', '22:00', 3.4, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa pacijenta', 'neskexx@gmail.com', 'Pacijent', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Pacijentic',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('4', '1');
INSERT INTO PACIJENT(datum_kreiranja, jbzo, korisnik_id) VALUES ('2019-11-20', '555333', '4');
INSERT INTO ZDR_KARTON(pacijent_id, dioptrija, visina, tezina, krvna_grupa, version) VALUES ('1', '-1.5', '193', '80', '0+', 0);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa med sestre', 'medsestra@gmail.com', 'Sestra', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Sestric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('5', '5');
INSERT INTO MEDICINSKA_SESTRA(korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena) VALUES ('5', '1', '06:59', '22:00');

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


INSERT INTO LEK(naziv, sadrzaj, sifra) VALUES('Brufen 600mg', 'metan, propan, butan gutam', 'BrT6');
INSERT INTO ALERGIJE_PACIJENATA VALUES('1','1');

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('neka adresa', 'peraperic@gmail.com', 'Pera', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Pacijent',  'drzava', 'Beograd', '33333213');
INSERT INTO USER_AUTHORITY VALUES ('6', '1');
INSERT INTO PACIJENT(datum_kreiranja, jbzo, korisnik_id) VALUES ('2019-11-22', '666777', '6');
INSERT INTO ZDR_KARTON(pacijent_id, dioptrija, visina, tezina, krvna_grupa, version) VALUES ('2', '-1.35', '153', '40', '0+', 0);

INSERT INTO LEK(naziv, sadrzaj, sifra) VALUES('Alergen 600mg', 'bitces alergican', 'Al234');
INSERT INTO ALERGIJE_PACIJENATA VALUES('2','2');

INSERT INTO DIJAGNOZA(datum_kreiranja, naziv, sifra) VALUES ('2019-1-28 11:21:22', 'Lud', 'Pop');
INSERT INTO DIJAGNOZE_PACIJENATA VALUES ('1', '1');
INSERT INTO LEK_ZA_DIJAGNOZU(lek_id, dijagnoza_id) VALUES ('1', '1');
INSERT INTO LEK_ZA_DIJAGNOZU(lek_id, dijagnoza_id) VALUES ('2', '1');

INSERT INTO RECEPT(datum_isticanja, datum_izdavanja, overen, medicinska_sestra_id, lek_id, zdr_karton_id) VALUES ('2019-12-28 11:21:22', '2019-11-28 11:21:22', 'false', '1', '2', '1');
-- INSERT INTO RECEPTI_PACIJENTA VALUES ('1','1');

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('tamo daleko', 'email@gmail.com', 'Email', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'emajil',  'drzava', 'Novi Sad', '543543543');
INSERT INTO USER_AUTHORITY VALUES ('7', '1');
INSERT INTO PACIJENT(datum_kreiranja, jbzo, korisnik_id) VALUES ('2019-04-13', '654654', '7');
INSERT INTO ZDR_KARTON(pacijent_id, dioptrija, visina, tezina, krvna_grupa, version) VALUES ('3', '-1.5', '193', '80', '0+', 0);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('ulica 54', 'trtrtr@gmail.com', 'Trtrtr', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'rttrt',  'drzava', 'Nis', '7864554');
INSERT INTO USER_AUTHORITY VALUES ('8', '1');
INSERT INTO PACIJENT(datum_kreiranja, jbzo, korisnik_id) VALUES ('2019-11-07', '543543', '8');
INSERT INTO ZDR_KARTON(pacijent_id, dioptrija, visina, tezina, krvna_grupa, version) VALUES ('4', '-1.35', '153', '40', '0+', 0);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresaaaa', 'ijasam@gmail.com', 'IJaSamPacijent', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'IJaSamPrezime',  'drzava', 'Subotica', '654765');
INSERT INTO USER_AUTHORITY VALUES ('9', '1');
INSERT INTO PACIJENT(datum_kreiranja, jbzo, korisnik_id) VALUES ('2019-12-20', '123123', '9');
INSERT INTO ZDR_KARTON(pacijent_id, dioptrija, visina, tezina, krvna_grupa, version) VALUES ('5', '-1.35', '153', '40', '0+', 0);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 12', 'lekar123@gmail.com', 'Mile', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('10', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('2', '10', '1', '07:00', '22:00', 4.5, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 12', 'lekar1235@gmail.com', 'Micko', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('11', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('5', '11', '9', '07:00', '22:00', 4.0, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 12', 'lekarMicko@gmail.com', 'Micko', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('12', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('2', '12', '2', '07:00', '22:00', 5.0, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 12', 'lekarMilivoje@gmail.com', 'Milivoje', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('13', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('4', '13', '3', '07:00', '22:00', 2.1, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 12', 'lekarrrMilivoje@gmail.com', 'Milivojee1', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('14', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('2', '14', '1', '07:00', '22:00', 2.1, true);

INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('adresa med sestre', 'medsestra2@gmail.com', 'Sestrica', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Sestricevic',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('15', '5');
INSERT INTO MEDICINSKA_SESTRA(korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena) VALUES ('15', '1', '07:00', '22:00');


INSERT INTO KORISNIK (adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('lekariceva 13', 'lekarcic@gmail.com', 'Lekarcic', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaricccccacca',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('16', '2');
INSERT INTO LEKAR(tip_pregleda_id, korisnik_id, klinika_id, pocetak_radnog_vremena,  kraj_radnog_vremena, ocena, aktivan) VALUES ('2', '16', '1', '07:00', '22:00', 2.1, true);


INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Odlicno ste bolesni', '2019-12-12 15:50:00', '2019-12-12 16:50:00', '2', '2', '2', '2', '1', '0', '2', '0', '2019-11-30',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Bit ces unet', '2019-12-27 15:50:00', '2019-12-27 16:50:00','1', '2', '1', '2', '2', '0', '2', '0', '2019-11-30',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Odlicno ste bolesni', '2019-11-12 15:51:00', '2019-11-12 16:51:00', '2', '2', '2', '2', '1', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Svaka cast', '2019-11-11 07:10:00', '2019-11-11 16:52:00', '2', '2', '1', '2', '1', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Bit ce unet', '2019-12-1 15:51:00', '2019-12-1 16:31:00','2', '2', '2', '2', '1', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Bit ce unet', '2019-12-27 16:51:00', '2019-12-27 20:00:00','1', '1', '1', '1', '1', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Bit ce unet', '2019-12-25 07:30:00', '2019-12-25 16:51:00','1', '1', '2', '1', '1', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Odlicno ste bolesni', '2019-12-25 16:52:00', '2019-12-25 21:00:00', '2', '2', '1', '2', '2', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Svaka cast', '2019-12-25 07:10:00', '2019-12-25 16:52:00', '2', '2', '2', '2', '2', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Svaka cast', '2019-11-25 07:10:00', '2019-11-25 16:52:00', '2', '2', '1', '2', '2', '0', '2', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-01-04 17:53:00', '2020-01-04 18:52:00', '2', '2', '2', '2', '2', '0', '0', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-09 18:50:00', '2020-02-09 19:52:00', '1', '1', '1', '2', '1', '0', '0', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi',  '2020-01-21 18:10:00', '2020-01-21 19:55:00', '1', '6', '1', '1', '1', '0', '0', '0', '2019-11-6',0);

INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-10 21:00:00', '2020-02-10 22:00:00', '2', '1', '2', '2', '1', '0', '0', '0', '2019-11-6',0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-10 08:06:00', '2020-02-10 09:12:00', '2', '2', '2', '2', '1', '0', '0', '0', '2019-11-6', 0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, sala_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-10 21:00:00', '2020-02-10 22:06:00', '1', '1', '1', '1', '2', '0', '0', '0', '2019-11-6',0);

INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-10 21:00:00', '2020-02-10 22:00:00', '2', '2', null, '1', '0', '0', '0', '2019-11-6', 0);
INSERT INTO PREGLED (izvestaj, datum_pocetka, datum_zavrsetka, tip_pregleda_id, lekar_id, medicinska_sestra_id, pacijent_id, popust, stanje, vrsta, datum_kreiranja, version) VALUES ('Novi', '2020-02-10 07:00:00', '2020-02-10 8:06:00', '2', '2', null, '1', '0', '0', '0', '2019-11-6', 0);

INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, sala_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Bit ce unet', '2019-12-27 16:51:00', '2019-12-27 20:00:00', 'Operacija jea', '1', '1', '1', '2', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, sala_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Bit ce unet', '2019-12-27 15:51:00', '2019-12-27 16:41:00', 'Operacija jea', '1', '1', '1', '2', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, sala_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Bit ce unet', '2019-12-11 15:51:00', '2019-12-11 17:51:00','Operacija jea', '2', '1', '1', '2', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, sala_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Operisan od zivota...', '2020-02-10 21:00:00', '2020-02-10 22:00:00', 'Operacija jea', '1', '1', '1', '0', '1', '2019-11-6', 1);
INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, sala_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Operisan od zivota...', '2020-02-10 21:00:00', '2020-02-10 22:00:00', 'Operacija jea', '1', '2', '1', '0', '1', '2019-11-6', 1);

INSERT INTO OPERACIJA (izvestaj, datum_pocetka, datum_zavrsetka, tip_operacije, medicinska_sestra_id, pacijent_id, stanje, vrsta, datum_kreiranja, tip_pregleda_id) VALUES ('Operisan od zivota...', '2020-02-10 21:00:00', '2020-02-10 22:00:00', 'Operacija jea', null, '1', '0', '1', '2019-11-6', 2);

INSERT INTO PRISUTNI_LEKARI VALUES (6, 2);

INSERT INTO PRISUTNI_LEKARI VALUES (1, 1);
INSERT INTO PRISUTNI_LEKARI VALUES (1, 2);
INSERT INTO PRISUTNI_LEKARI VALUES (2, 1);
INSERT INTO PRISUTNI_LEKARI VALUES (3, 2);
INSERT INTO PRISUTNI_LEKARI VALUES (4, 2);
INSERT INTO PRISUTNI_LEKARI VALUES (5, 1);

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

