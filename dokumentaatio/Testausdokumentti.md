# Testausdokumentti
Ohjelmaa on testattu sekä JUnit-testeillä automatisoidusti että manuaalisesti. JUnitilla on tehty yksikkö- ja integraatiotestejä. Manuaalisesti on testattu ohjelman yleistä toimivuutta.

## Yksikkö- ja integraatiotestaus
Yksikkö ja integraatiotestit on toteutettu luokissa [GameTest](https://github.com/mazantti/ot-harjoitustyo/blob/master/Laivanupotus/src/test/java/logic/GameTest.java), [MachineTest](https://github.com/mazantti/ot-harjoitustyo/blob/master/Laivanupotus/src/test/java/logic/MachineTest.java), [MoveSelectorTest](https://github.com/mazantti/ot-harjoitustyo/blob/master/Laivanupotus/src/test/java/logic/MoveSelectorTest.java) ja [RuleCheckerTest](https://github.com/mazantti/ot-harjoitustyo/blob/master/Laivanupotus/src/test/java/logic/RuleCheckerTest.java). Merkittävimmät integraatiotestit ovat testiluokassa GameTest olevat testit machineShootsBackProperly() ja playerCanHitMachine(). 


## Testikattavuus
Rivikattavuus on 95% ja haarautumakattavuus on 90%.
![](https://github.com/mazantti/ot-harjoitustyo/blob/master/dokumentaatio/testikattavuusraportti.png)
## Järjestelmätestaus
Järjestelmätestaus on suoritettu manuaalisesti.
