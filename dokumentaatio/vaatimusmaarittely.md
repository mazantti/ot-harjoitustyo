# vaatimusmäärittely
Sovelluksen tarkoitus on, että käyttäjä voi pelata tietokonetta vastaan laivanupotusta. Laivanupotus on kahden pelaajan vuoropohjainen strategiapeli. Tarkoituksena on upottaa vastustajan laivasto.

Klassisessa laivanupotuksessa on kaksi eri vaihetta. Omien laivojen sijoittaminen ja vastustajan laivojen upottaminen. Siksi myös sovelluksessa tulee olemaan kaksi vaihetta. Pelin alussa pelaaja sijoittaa omat laivansa. Samalla tietokone piilottaa omansa. Kun laivat ovat paikoillaan, alkavat pelaaja ja tietokone upottamaan toistensa laivoja kukin vuorollaan. 

## logiikka
Sovellus muistaa pelitilanteen. Pelilogiikka saa pelaajan siirrot graafisen käyttöliittymän kautta. Tietokone valitsee siirtonsa todennäköisyyslaskennan ja automatisoidun päättelyn avulla. Varsinaista tekoälyä peliin ei tule.

## käyttöliittymä
Pelissä on graafinen käyttöliittymä, joka näyttää suurinpiirtein tältä:
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/kayttoliitymapiirrustus.png)

Tarkoituksena on, että pelaaja valitsee siirtonsa klikkaamalla käytöliittymässä näkyvää pelilautaa. 

## Mahdollisia jatkokehitysideoita
- tehokkaampi tapa tietokoneelle laivojensa kätkentään kuin pelkkä sattuma
- useampia vaikeusasteita
- useampia eri vaihtoehtoja laudan kooksi ja laivojen määräksi
- kaksinpeli
