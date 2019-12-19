### Sovelluksessa käytetty todennäköisyyslaskenta
"Vastustaja", jota vastaan pelaaja pelaa käyttää hyödykseen yksinkertaista todennäköisyyslaskentaa siirtojensa valitsemiseen.
Tässä dokumentissa selitetään luokkien ShipPlacer ja MoveSelector toiminta menemättä liian tarkasti toteutuksen yksityiskohtiin.

## ShipPlacer
ShipPlacer-luokka on käytössä pelin alkupuolella, kun tietokone kätkee laivansa. Kätkeminen perustuu yksinkertaisesti
tasajakaumaan. Metodi placeShips käy läpi kaikki laivat ja vuorollaan arpoo niiden sijainnin. Arpominen tapahtuu seuraavasti: 
Ensin arvotaan ehdokas laivan keulan sijainniksi. Ehdokkaan kelvollisuus laivan sijoituspaikaksi tarkistetaan luokan RuleChecker
toiminnallisuuden avulla. Kun löytyy  kelvollinen ehdokas laivan sijainniksi, arvotaan sen jälkeen laivan perän sijainti. Taas
RuleCheckerin avulla tarkastetaan, että arvottu 
