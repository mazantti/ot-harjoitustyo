# Laivanupotus

Sovellus on tietokoneversio klassisesta pelistä laivanupotus. Sovellus mahdollistaa pelaamisen tietokonetta vastaan. 

## release
[Laivanupotus](https://github.com/mazantti/ot-harjoitustyo/releases/tag/Lopullinen)

## komentorivitoiminnot

- testit:
```
mvn test
```
- testikattavuusraportti (löytyy tiedostosta  *target/site/jacoco/index.html*):   
```
mvn jacoco:report
```
- suoritettavan jar:in generointi (ilmestyy hakemistoon *target*):
```
mvn package
```
- checkstyle (*target/site/checkstyle.html*):
```
mvn jxr:jxr checkstyle:checkstyle
```
- javaDoc (target/site/apidocs/index.html):
```
mvn javadoc:javadoc
```

## dokumentaatio
[käyttöohje](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[vaatimusmäärittely](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[arkkitehtuuri](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[testausdokumentti](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/Testausdokumentti.md)

[todennäköisyyslaskentaa](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/todennakoisyyslaskenta.md) (Selvennys siitä, miten tietokone valitsee siirtonsa.)
