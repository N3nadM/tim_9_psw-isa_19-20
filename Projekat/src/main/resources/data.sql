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

INSERT INTO TIP_PREGLEDA VALUES ('1', 'Kardiovaskularni');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('1', 'adresa admina KC', 'adminKC@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Centric', 'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('1', '4');
INSERT INTO ADMIN_KLIN_CENTRA VALUES ('1', '1');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('2', 'adresa admina', 'adminKlinike@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Adminic', 'drzava1', 'grad1', 'telefon1');
INSERT INTO USER_AUTHORITY VALUES ('2', '3');
INSERT INTO ADMIN_KLINIKE VALUES ('1', '1', '1', '2');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('3', 'adresa lekara', 'lekar@gmail.com', 'Lekar', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Lekaric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('3', '2');
INSERT INTO LEKAR VALUES ('1', '1', '3', '1');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('4', 'adresa pacijenta', 'pacijent@gmail.com', 'Pacijent', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Pacijentic',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('4', '1');
INSERT INTO PACIJENT VALUES ('1', '2019-11-20', '555333', '4');

INSERT INTO KORISNIK (id, adresa, email, ime, password, prezime, drzava, grad, telefon) VALUES ('5', 'adresa med sestre', 'medsestra@gmail.com', 'Sestra', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Sestric',  'drzava', 'grad', 'telefon');
INSERT INTO USER_AUTHORITY VALUES ('5', '5');
INSERT INTO MEDICINSKA_SESTRA VALUES ('1','1', '5');

INSERT INTO ZAHTEV(ime, prezime, adresa, grad, drzava, telefon, email, password, jbzo, verified) VALUES ('dsads', 'dssadsa', 'dsadsa', 'dsadffds', 'fsdgdf', 'fdssd', 'fdsfds@gmail.com', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', '2132131', False);

INSERT INTO OCENA_KLINIKE VALUES('1', '1 - 1', '1', '1', '1');
INSERT INTO OCENA_KLINIKE VALUES('2', '2 - 1', '2', '2', '1');
INSERT INTO OCENA_KLINIKE VALUES('3', '3 - 1', '2', '3', '1');
INSERT INTO OCENA_KLINIKE VALUES('4', '4 - 1', '3', '4', '1');
INSERT INTO OCENA_KLINIKE VALUES('5', '5 - 1', '3', '5', '1');
INSERT INTO OCENA_KLINIKE VALUES('6', '6 - 1', '4', '6', '1');
INSERT INTO OCENA_KLINIKE VALUES('7', '7 - 1', '4', '7', '1');
INSERT INTO OCENA_KLINIKE VALUES('8', '8 - 1', '5', '8', '1');
INSERT INTO OCENA_KLINIKE VALUES('9', '9 - 1', '5', '9', '1');
INSERT INTO OCENA_KLINIKE VALUES('10', '10 - 1', '5', '10', '1');

INSERT INTO ZDR_KARTON(id, pacijent_id, dioptrija, visina, tezina, krvna_grupa) VALUES ('1', '1', '-1.5', '193', '80', '0+')