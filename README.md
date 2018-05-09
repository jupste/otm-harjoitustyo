# ShootEmUp peli
Pelissä pelaaja liikuttaa mustaa neliötä nuolinäppäimillä. Pelaaja voi käyttämällä WASD näppäimiä ampua vihollisia  joiden tuhoamisesta pelaaja saa pisteitä. Jos pelaaja osuu viholliseen niin peli loppuu. Pelin lopuksi pelaajan tulos tallennetaan hiscores-taulukkoon. 

## Releaset
[Viikko 5](https://github.com/jupste/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/jupste/otm-harjoitustyo/releases/tag/viikko6)

[Lopullinen release](https://github.com/jupste/otm-harjoitustyo/releases/tag/viikko6)
## Dokumentointi

[Vaatimusmäärittely](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/tyoaika.md)

[Käyttöohje](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuurikuvaus.md)

[Testausdokumentti](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

## Komentorivitoiminnot

# Ohjelman käynnistäminen
Ennen ohjelman suoritusta tulee siirtyä oikeaan hakemistoon. Tämä tapahtuu juuressa kirjoittamalla komennot:
> cd harjoitustyo


Tämän jälkeen ohjelman voi suorittaa komennolla 
> mvn compile exec:java -Dexec.MainClass=shootemup.ShootEmUp

# Ohjelman testaus

Testauksen voi suorittaa komennolla
> mvn test

Testikattavuus puolestaan luodaan komennolla
> mvn jacoco:report

Kattavuus löytyy hakemistosta target/site/jacoco/index.html

# JavaDoc
Javadoc luodaan komennolla 
> mvn javadoc:javadoc

JavaDoc löytyy hakemistosta target/site/apidocs/index.html

# Checkstylen generoiminen

Checkstyle luodaan komennolla 
> mvn jxr:jxr checkstyle:checkstyle

Dokumentti löytyy hakemistosta target/site/checkstyle.html

# .jar tiedoston generoiminen

jar-tiedoston generoiminen onnistuu komennolla
> mvn package

se löytyy hakemistosta target/ShootEmUp-1.0.jar