# Arkkitehtuurikuvaus
## Rakene
Ohjelma koostuu seuraavista kahdesta osasta: Käyttöliittymä (Gui) ja logiikka (logic). Logiikka annetaan parametrina käyttöliittymälle luokan Game muodossa. Yksinkertaistetusti ohjalma toimii näin: Käyttöliittymä ilmoittaa logiikalle pelaajan seuraavan siirron ja sen jälkeen kysyy siltä päivityksen pelitilanteeseen. Tämän jälkeen käyttöliittymä päivittää uuden pelitilanteen näkyväksi pelaajalle.

### Käyttöliittymä
Ohjelmassa on yksinkertainen graafinen käyttöliittymä. Käyttöliittymässä on yksi näkymä. Käyttöliittymä löytyy kansiosta *ui* ja koostuu kahdesta luokasta *ui.Gui* ja *ui.Tile*. Näistä ensimmäinen on ohjelman toiminnallissuuden kannalta merkittävämpi.

Käyttöliittymä koostuu kolmesta osasta. Ylälaidassa on ohje siitä, mitä peli odottaa pelaajan seuraavaksi tekevän. Lisäksi näkymässä on kaksi lautaa, niin kuin laivanupotuksessa yleensäkin. Vasemmanpuoleinen näyttää pelaajan omien laivojen sijainnin. Oikealta pelaaja voi taas nähdä mihin hän on ampunut. Seuraava siirto valitaan klikkaamalla asiaankuuluvaa lautaa.

### Logiikka 
Ohjelmalogiikka löytyy kansiosta *logic*. Alla ohjelman toiminnan kannalta merkittävimmät luokat:
- 


### Yksinkertaistettu luokkarakenne
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/luokkakaavio.png)

## Ohjelman käynnistys ja pelin aloittaminen
Ohjelman toteutuksessa on kolme eri vaihetta. Alustaminen, laivojen sijoittaminen ja itse pelin pelaaminen. Käyttäjälle näistä vaiheista näkyvät kaksi jälkimmäistä. Tässä kohdassa selitetään kaksi ensimmäistä vaihetta. 




### sekvenssikaavio pelin alkuvaiheesta pelin aloituksesta ensimmäisen laivan sijoittamseen
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/alkusekvenssi.png)
