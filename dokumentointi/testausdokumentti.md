# Testausdokumentti

Ohjelman testaus on hoidettu automatisoiduilla JUnit yksikkö- ja integraatiotesteillä jonka lisäksi testausta on suoritettu manuaalisesti järjestelmätasolla. 

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Pakkauksen shootemup.domain luokkia testaavat integraatiotestit [EntitiesTest](https://github.com/jupste/otm-harjoitustyo/blob/master/harjoitustyo/src/test/java/EntitiesTest.java), [GameServiceTest](https://github.com/jupste/otm-harjoitustyo/blob/master/harjoitustyo/src/test/java/GameServiceTest.java), [KeyControllerTest](https://github.com/jupste/otm-harjoitustyo/blob/master/harjoitustyo/src/test/java/KeyControllerTest.java) sekä [ProjectileMakerTest](https://github.com/jupste/otm-harjoitustyo/blob/master/harjoitustyo/src/test/java/ProjectileMakerTest.java). Näiden määrittelemät testit testaavat pelin osien oikeellisuutta sekä kontrollien toimivuutta. EntitiesTest on näistä oikeastaan ainoa yksikkötason testi ja tämä testiluokka testaa yksittäisten olentojen toimivuutta.

### DAO-luokka

DAO-luokan oikeellisuutta testataan DataAccessObjectTest testiluokalla. Tätä testiä varten on luotu tietokanta test.db, jonka avulla tietokannan toimintaa voidaan simuloida testejä varten. 

### Testauskattavuus

![jacoco](https://raw.githubusercontent.com/jupste/otm-harjoitustyo/master/dokumentointi/jacoco.png)

Käyttöliittymää lukuunottamatta sovelluksen rivikattavuus on 87.5% ja haarakattavuus 88.0 %. Puuttellinen haarakattavuus johtuu lähinnä vihollisten liikkumista koskevista case-switch rakenteista. Testaamatta jäi lähinnä tilanteet, joissa vihollinen yrittää liikkua kentän ulkopuolelle. Puuttellinen rivikattavuus puolestaan tulee DAO-olion try-catch rakenteista. DAO-olennon rakenne on tehty siten, että tällaisia poikkeuksia ei esiinny eli ohjelma ei normaalitilanteessa päädy catch rakenteeseen. 

### Järjestelmätestaus

Järjestelmän toimivuutta on testattu Linux-järjestelmällä [käyttöohjeen](https://github.com/jupste/otm-harjoitustyo/tree/master/dokumentointi/kayttoohje.md) kuvaamalla tavalla. 

## Toiminnallisuudet

Määrittelydokumentin listaamat luodut toiminnalisuudet on käyty läpi testeillä. 

## Sovelluksen jääneet laatuongelmat

Dokumentin kirjoituksen ajankohtana ei ole tiedossa mitään laatuongelmia, joita ohjelmassa esiintyisi.
