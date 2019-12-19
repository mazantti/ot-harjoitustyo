# Sovelluksessa käytetty todennäköisyyslaskenta
"Vastustaja", jota vastaan pelaaja pelaa käyttää hyödykseen yksinkertaista todennäköisyyslaskentaa siirtojensa valitsemiseen. Tässä dokumentissa selitetään luokkien *ShipPlacer* ja *MoveSelector* toiminta menemättä kovin tarkasti toteutuksen yksityiskohtiin.

## ShipPlacer
*ShipPlacer*-luokka on käytössä pelin alkupuolella, kun tietokone kätkee laivansa. Kätkeminen perustuu yksinkertaisesti tasajakaumaan. Metodi *placeShips* käy läpi kaikki laivat ja vuorollaan arpoo niiden sijainnin. Arpominen tapahtuu seuraavasti: Ensin arvotaan ehdokas laivan keulan sijainniksi. Jokaisen ruudun todennäköisyys on yhtä suuri. Ehdokkaan kelvollisuus laivan sijoituspaikaksi tarkistetaan luokan *RuleChecker* toiminnallisuuden avulla. Kun löytyy  kelvollinen ehdokas laivan sijainniksi, arvotaan sen jälkeen laivan perän sijainti. Taas RuleCheckerin avulla tarkastetaan, että arvottu sijainti on sääntöjen mukainen. Tätä jatketaan kunnes kaikki laivat on sijoitettu.

On teoriassa mahdollista, että ylläoleva menetelmä jää ikuiseen luuppiin jos jollekin laivalle ei enää löydy laillista paikkaa. Tästä syystä toteutukseen on lisätty varmuuden vuoksi mittari, joka laskee montako kertaa käsiteltävää laivaa on yritetty sijoittaa. Jos ennalta määritelty raja ylittyy (joka on laudan ruutujen määrä), koko laivojen sijoittamisprosessi aloitetaan alusta.

Tämä menetelmä laivojen piilottamiseksi ei ole optimaalinen, sillä tietokone ei aktiivisesti pyri hyödyntämään sääntöjä omaksi edukseen. Paremman menetelmän toteuttaminen olisi kuitenkin monimutkaista, joten siihen ei ollut aikaa.

## MoveSelector
*MoveSelector* valitsee, mitä ruutuja tietokone ampuu. Luokka antaa seuraavan siirron kun sen metodia *nextMove* kutsutaan. Metodille annetaan parametriksi tiedot siitä, että osuiko edellinen siirto ja upottiko se laivan. Tämä ilmaistaan kahden boolean-muuttujan avulla. Palautusarvon laskentamenetelmä riipuu suuresti pelitilanteesta. Jos *MoveSelector* ei ole löytänyt laivaa, se käyttää tonennäköisyyslaskentaa seuraavan siirron määrittämiseen. Jos taas laiva on löytynyt (mutta ei uponnut), niin valitaan seuraava siirto suoraviivaisesti laivan seuraavien mahdollisten ruutujen joukosta. Tätä ei tarvinne selittää sen tarkemmin.

### Todennäköisyyteen perustuva siirtojen valinta
MoveSelector pitää muistissaan kahta taulukkoa, joihin se tallettaa tietonsa tilanteesta. Ensimmäinen taulukko on nimeltään *target* ja siihen tallennetaan tieto aiemmista siirroista. Toinen taulukko on *chances*, joka sisältää todennäköisyysjakauman, jonka perusteella arvotaan seuraava siirto. Molemmat taulukot päivitetään joka vuorolla.

**Taulun *target* päivitäminen**
Tieto siitä, että osuiko edellinen siirto laivaan merkitään tauluun vuoron alussa. Jos edellinen siirto upotti laivan, merkitään myös laivan viereiset ruudut ammutuiksi. 

**Taulun *chances* päivittäminen** 
Taulu *chances* päivitetään seuraavasti. jokaiselle ruudulle annetaan numeroarvoksi se luku, kuinka monella eri tavalla jokin laiva voi olla tässä ruudussa. Tämän luvun laskemiseksi käytetään taulun *target* tietoja ja luokan *RuleChecker* toiminnallissuksia. 

**Seuraavan siirron valinta**
Seuraava siirto arvotaan taulun *chances* sisältämän jakauman perusteella.

**Huomioita todennäköisyysjakaumasta**
Käytetty jakauma ei vastaa todellisuutta, siinä, että se ei anna oikeaa todennäköisyyttä sille, että ruudussa on laiva. Tarkan arvon laskeminen olisi aivan liian vaativaa ajallisesti, joten toteutuksessa on tyydytty yksinkertaisempaan jakaumaan.

