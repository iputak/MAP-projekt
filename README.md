# MAP-projekt
Projekt iz predmeta "Mobilne aplikacije"

## Taxivodstvo 
"Taxivodstvo" je ime aplikacije kojoj je ideja olakšati pračenje podataka vezane uz promet i vozače u taxi poduzećima.

## O aplikaciji

Mobilna aplikacija "Taxivodstvo" sastoji se od stranica: prijava, početna, smjena, izvješća, vozači i novi vozač/ica.
Svi podatci su zapisani u realtime bazi Firebase platforme.

### Prijava

Prilikom pokretanja aplikacije, prikazuje se pozdravna slika s kratkim trajanjem. Nakon završetka se prikazuje forma za prijavu u koju se unose podatci.
Nakon unosa, pritiskom na dugme "Prijava", provjeravaju se uneseni podatci s podatcima u bazi. Nakon potrde, otvara se 'Početna' stranica.

### Početna

Stranica 'Početna' se sastoji od pozdravne poruke, zapisom današnjih podataka te mogučnostima otvaranja kalkulatora i kalendara.

### Smjena

Na stranici 'Smjena' se nalaze forme preko kojih se unose podatci o smjeni. 
Podatci o smjeni su: 
* datum početka,
* dnevna/noćna smjene,
* početni i završni kilometri i
* promet.

### Izvješća

Otvaranjem stranice 'Izvješća', prikazuje se graf podataka smjena upisanih u zadnjih tjedan dana. 
Graf se sastoji od dana i ukupnog prometa koji su grafički obrađeni.

### Vozači 

Na stranici 'Vozači' prikazani su svi vozači s osnovnim podatcima. Pritiskom na individualnog vozača, otvara se nova personalizirana stranica vozača s opširnijim podatcima o njemu koji su uneseni u formama stracice 'Novi vozač/ica'.

### Novi vozač/ica
Na stranici 'Novi vozač/ica' se nalaze forme preko kojih se unose podatci o vozaču ili vozačici. 
Podatci o vozaču/ici su: 
* ime i prezime,
* spol,
* adresa,
* kontakt broj i e-mail,
* korisnički podatci za prijavu u sutav i 
* odabir ranga - VOZAČ ili VODITELJ.
