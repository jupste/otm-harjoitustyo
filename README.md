# ShootEmUp peli

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

> cd OTM-harjoitustyo

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

# .jar tiedoston generoiminen

jar-tiedoston generoiminen onnistuu komennolla
> mvn package

se löytyy hakemistosta target/site/apidocs/index.html