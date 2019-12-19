# Arkkitehtuurikuvaus
## Rakene
Ohjelma koostuu seuraavista kahdesta osasta: Käyttöliittymä (Gui) ja logiikka (logic). Logiikka annetaan parametrina käyttöliittymälle luokan Game muodossa. Yksinkertaistetusti ohjalma toimii näin: Käyttöliittymä ilmoittaa logiikalle pelaajan seuraavan siirron ja sen jälkeen kysyy siltä päivityksen pelitilanteeseen. Tämän jälkeen käyttöliittymä päivittää uuden pelitilanteen näkyväksi pelaajalle.


### Yksinkertaistettu luokkarakenne
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/luokkakaavio3.png)


### Käyttöliittymä
Ohjelmassa on yksinkertainen graafinen käyttöliittymä. Käyttöliittymässä on yksi näkymä. Käyttöliittymä löytyy kansiosta *ui* ja koostuu kahdesta luokasta *ui.Gui* ja *ui.Tile*. Näistä ensimmäinen on ohjelman toiminnallissuuden kannalta merkittävämpi.

Käyttöliittymän näkymä koostuu kolmesta osasta. Ylälaidassa on ohje siitä, mitä peli odottaa pelaajan seuraavaksi tekevän. Lisäksi näkymässä on kaksi lautaa, niin kuin laivanupotuksessa yleensäkin. Vasemmanpuoleinen näyttää pelaajan omien laivojen sijainnin. Oikealta pelaaja voi taas nähdä mihin hän on ampunut. Seuraava siirto valitaan klikkaamalla asiaankuuluvaa lautaa.
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/kayttoliittyma.png)

### Logiikka 
Sovelluslogiikka löytyy kansiosta *logic*. Alla ohjelman toiminnan kannalta merkittävimmät luokat:

- *logic.Game* hallitsee pelitilannetta
- *logic.RuleChecker* sisältää metodeja eri asioiden sääntöjenmukaisuuden tarkastamiseen
- *logic.Machine* "vastustaja"
- *logic.MoveSelector* valitsee Machinen seuraavan siirron
- *logic.ShipPlacer* valitsee Machinen laivojen sijainnin

## Ohjelman käynnistys ja pelin aloittaminen
Ohjelman toteutuksessa on kolme eri vaihetta. Alustaminen, laivojen sijoittaminen ja itse pelin pelaaminen. Käyttäjälle näistä vaiheista näkyvät kaksi jälkimmäistä. Tässä kohdassa selitetään kaksi ensimmäistä vaihetta. 

### sekvenssikaavio pelin alkuvaiheesta pelin aloituksesta ensimmäisen laivan sijoittamseen
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/alkusekvenssi.png)

Luettavuuden vuoksi kaavio on hieman yksinkertaistettu. Siinä ei esimerkiksi näy, että machine ulkoistaa laivojen sijoittamisen luokalle ShipPlacer. 

## Pelin jatkuminen (laivojen upottaminen)
Seuraavassa vaiheessa pelaaja ja tietokone kumpikin pyrkivät upottamaan toistensa laivat. Käytännössä pelaaja valitsee seuraavan siirtonsa klikkaamalla oikeanpuoleista lautaa. Toteutuksen tasolla käyttöliittymä *Gui* antaa siirron luokalle *Game* tämän metodin *insertCommand* avulla. Tämän jälkeen *Game* kysyy machinen seuraavan siirron. Machine ulkoistaa siirron päättämisen luokalle *MoveSelector*. *MoveSelector* valitsee siirron luokan *RuleChecker* toimintoja ja [todennäköisyyslaskentaa](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/todennakoisyyslaskenta.md) hyödyntämällä. Tämän jälkeen *Gui* kysyy luokalta *Game* uuden tilanteen ja päivittää sen pelaajan nähtäväksi. Tämä jatkuu kunnes jommankumman kaikki laivat ovat upotettuja, jolloin peli ilmoittaa voittajan. 

### yksinkertaistettu kuvaus vuoroparin tapahtumista
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/nextMove.png)


