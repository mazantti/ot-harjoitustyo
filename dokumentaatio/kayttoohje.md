# Käyttöohje
Lataa tiedosto [Laivanupotus.jar](https://github.com/mazantti/ot-harjoitustyo/releases/tag/Lopullinen).

### Pelin käynnistäminen
Peli käynnistyy komennolla 
```
java -jar Laivanupotus.jar
```
### Pelaaminen
Peli alkaa pelaajan laivojen sijoittamisella. Sijoita laivasi vasemmalle laudalle klikkaamalla. Vasemmassa yläkulmassa lukee, kuinka pitkää laivaa olet sijoittamassa. Laiva sijoitetaan seuraavasti: Ensin klikkaa ruutua, johon haluat laivan "keulan". Sen jälkeen klikkaa ruutua, johon haluat "perän". Peli ei anna sijoittaa laivoja sääntöjen vastaisesti. 

Kun kaikki laivat ovat paikoillaan, alkaa seuraava vaihe välittömästi. Peli kehottaa pelaajaa valitsemaan ruudun, johon hän haluaa ampua. Valitse ruutu klikkaamalla oikean puoleista lautaa. Pelaaja ei näe minne tietokone ampuu, paitsi jos tietokone osuu pelaajan laivaan. Peli ilmoittaa, kun pelaaja joko voittaa tai häviää pelin.

## Säännöt

### laivojen sijoittaminen
Pelin alussa sijoitetaan laivat. Laivojen pituudet ovat seuraavat: 5, 4, 3, 3, 2 ja 2. Laivat eivät saa koskea toisiinsa. Tämä sisältää myös kulmittaisen kosketuksen. Samoin laivojen päällekkäisyys on kielletty.

### Laivojen upottaminen
Laiva on uponnut kun sen jokaiseen ruutuun on ammuttu. Pelin voittaa se, joka upottaa ensin vastustajan laivat. Tässä versiossa ei saa jatkaa vuoroaan jos osuu vastustajan laivaan. 
