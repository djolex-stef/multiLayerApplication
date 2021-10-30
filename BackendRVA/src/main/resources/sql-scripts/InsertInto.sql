INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (nextval('kredit_seq'), 'Dinarski do 12 meseci', 'din12m', 'Dinarski gotovinski krediti sa rokom otplate do 12 meseci i fiksnom kamatnom stopom');
INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (nextval('kredit_seq'), 'Dinarski i devizni gotovinski', 'ddg', 'Dinarski i devizni gotovinski krediti sa dužim vremenskim rokom otplate, do 83 meseca');
INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (nextval('kredit_seq'), 'Krediti za refinansiranje', 'kf', 'Kredite za refinansiranje možete dobiti ukoliko ste klijent Banke, ali i kao fizičko lice koje nema otvoren tekući račun kod Komercijalne banke.');
INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (nextval('kredit_seq'), 'Krediti obezbeđeni hipotekom', 'koh', 'Krediti sa deviznom klauzulom, dužim vremenskim sa rokom otplate, do 83 meseca, obezbeđeni hipotekom');
INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (nextval('kredit_seq'), 'Krediti sa pologom depozita', 'kpd', 'Krediti sa dužim vremenskim sa rokom otplate, do 83 meseca, sa pologom dinarskog ili deviznog depozita');

INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Djordje', 'Stefanovic', 077842412, 1);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Nevena', 'Nikolic', 12345679, 2);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Nikola', 'Nikolic', 16725680, 2);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Djordje', 'Djordjevic', 12341122, 3);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Lazar', 'Lazarevic', 99215152, 3);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Marko', 'Markovic', 19521782, 4);
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (nextval('klijent_seq'), 'Jovan', 'Jovanovic', 515717157, 5);

INSERT INTO tip_racuna(id, naziv, oznaka)
VALUES (nextval('tip_racuna_seq'), 'Tekući račun', 'tr');
INSERT INTO tip_racuna(id, naziv, oznaka, opis)
VALUES (nextval('tip_racuna_seq'), 'Dinarski račun', 'dinr', 'Dinarski račun možete koristiti za lične uplate kao i za izvršavanje transakcija.');
INSERT INTO tip_racuna(id, naziv, oznaka, opis)
VALUES (nextval('tip_racuna_seq'), 'Devizni račun', 'devr', 'Na devizni račun možete uplatiti sledeće vrste valuta: EUR, AUD, CAD, DKK, JPY, NOK, RUB, SEK, GBP, USD i CNY.');
INSERT INTO tip_racuna(id, naziv, oznaka, opis)
VALUES (nextval('tip_racuna_seq'), 'Žiro račun', 'žr', 'Na žiro račun se prima honorar ili naknada od povremenog posla.');

INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(nextval('racun_seq'), 'Aktiv račun', 'ar', 'Aktiv tekući račun namenjen je zaposlenima sa redovnim mesečnim primanjima, kao i ostalim fizičkim licima koji žele da postanu klijenti Komercijalne banke. Mesečna naknada iznosi 310,00  dinara.', 1, 1);
INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(nextval('racun_seq'), 'Klasik račun', 'kr', 'Klasik tekući račun namenjen je penzionerima. Mesečna naknada iznosi 199,00 dinara.', 1, 2);
INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(nextval('racun_seq'), 'Premijum račun', 'pr', 'Premijum tekući račun je posebno kreiran u cilju zadovoljenja potreba klijenata, koji koristite najveći broj pogodnosti premijum segmenta. Mesečna naknada iznosi 610,00 dinara.', 4, 3);
INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(nextval('racun_seq'), 'Start račun', 'sr', 'Start tekući račun je posebno kreiran u skladu sa potrebama mladih, starosti od 18 do 27 godina. Bez troškova mesečnog održavanja.', 3, 4);
INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(nextval('racun_seq'), 'Osnovni račun', 'or', 'Dinarski račun bez dodatne indeksacije. Sredstva se polažu bez vremenskog ograničenja. Mesečna naknada iznosi 200,00 dinara.', 2, 5);





/* Proba */
INSERT INTO klijent(id, ime, prezime, broj_lk, kredit)
VALUES (-100, 'Test', 'Test',51521, 5);


INSERT INTO kredit(id, naziv, oznaka, opis)
VALUES (-100, 'Test', 'Test', 'Test');


INSERT INTO tip_racuna(id, naziv, oznaka, opis)
VALUES (-100, 'Test', 'Test', 'Test');


INSERT INTO racun(id, naziv, oznaka, opis, tip_racuna, klijent)
VALUES(-100,'Test','Test','Test',1,1);
