INSERT INTO ZAHTEV(ime, prezime, adresa, grad, drzava, telefon, email, password, jbzo, verified) VALUES
    ('dsads', 'dssadsa', 'dsadsa', 'dsadffds', 'fsdgdf', 'fdssd', 'fdsfds@gmail.com', 'fdsfsd', '2132131', False);
INSERT INTO AUTHORITY VALUES ('1', 'ROLE_PACIJENT');
INSERT INTO AUTHORITY VALUES ('2', 'ROLE_LEKAR');
INSERT INTO AUTHORITY VALUES ('3', 'ROLE_AK');
INSERT INTO AUTHORITY VALUES ('4', 'ROLE_AKC');
INSERT INTO KORISNIK VALUES ('1', 'adresa admina KC', 'adminKC@gmail.com', 'Admin', 'adminkc', 'Centric');
INSERT INTO USER_AUTHORITY VALUES ('1', '4');
INSERT INTO KORISNIK VALUES ('2', 'adresa admina', 'adminKlinike@gmail.com', 'Admin', '$2a$10$feiotRqclqYZ7aJnytCDSedhWmzztzhuwyujbVfVucV4nbmMRNnwa', 'Adminic');
INSERT INTO ADMIN_KLIN_CENTRA VALUES ('1', '1');
INSERT INTO USER_AUTHORITY VALUES ('2', '3');
INSERT INTO KLINIKA VALUES ('1', 'adresa klinike', 'Naziv klinike', 'opis klinike', '1');
INSERT INTO ADMIN_KLINIKE VALUES ('1', '1', '1', '2');
INSERT INTO KORISNIK VALUES ('3', 'adresa lekara', 'lekar@gmail.com', 'Lekar', 'lekar', 'Lekaric');
INSERT INTO USER_AUTHORITY VALUES ('3', '2');
INSERT INTO LEKAR VALUES ('1', '1', '3');