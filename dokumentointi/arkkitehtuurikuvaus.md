# Arkkitehtuurikuvaus
## Ohjelman rakenne

## Käyttöliittymä

Ohjelman päämenu ladataan aina ohjelman käynnistyessä. Tässä näkymässä on kolme nappia, jotka kaikki luovat uuden 
Scene-olion ja lataavat sen stageen. "Hiscores"-nappi vie käyttäjän hiscores taulukkoon, johon ladataan parhaiden pelaajien pisteet. "Instructions"-nappi puolestaan vie käyttäjän pelin käyttöohjeeseen. Näistä molemmista näkymistä voi palata päämenuun "Exit"-nappia painamalla. "Start the slaugter"-nappi korvaa päämenun napit tekstikentällä ja "READY"- napilla. Kun pelaaja on laittanut nimensä nimikenttään, voi hän aloittaa pelin painamalla "READY"-nappia. Kun peli alkaa stageen vaihdetaan uusi scene, jossa on pane-olento. Paneen sijoitetaan aluksi pelaajahahmo ja yksi vihollinen. Viholliset liikkuvat satunnaisesti ja pelaaja voi liikkua nuolinäppäimiä käyttäen ja ampua WASD näppäimillä.

## Sovelluslogiikka

Ohjelman osien suhdetta kuvaava luokkakaavio:

![luokkakaavio](https://yuml.me/c8860a70.png)

## Päätoiminnalisuudet

Seuraavaksi kuvataan sovelluksen keskeisten toimintojen päätoiminnalisuus sekvenssikaavioilla.

![kaynnistys](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/kaynnistys.png)
Kun ohjelma käynnistetään , main metodi luo uuden ScreenLoader olennon, jonka kautta suurin osa ohjelman toiminnallisuudesta tapahtuu. ScreenLoader saa yhdeksi parametrikseen main metodissa käytetyn Stage- olennon, sillä sen täytyy vaihtaa ruutua Pane- ja BorderPane-olentojen välillä.

![hiscore](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/Hiscore-taulukon_noutaminen.png)

Kun luodaan ScreenLoader-olio, luodaan samalla automaattisesti myös tietokantoja hallinnoiva DatabaseManager. Kun käyttäjä painaa Hiscores-nappia, ScreenLoader lataa uuden ruudun. Samalla Screenloader-olio saa DatabaseManagerilta sen tietokannan sisällön ObservableList-muodossa, jonka se lisää ruudulle. 
Kun käyttäjä painaa Clear-nappia, lähettää ScreenLoader DatabaseManagerille käskyn, että se tyhjää tietokantansa. 
Jos käyttäjä painaa Exit-nappia, palautuu ohjelma alkuperäiseen ruutuun. 

![introduction](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/Hiscore-taulukon_noutaminen.png)
Käyttöohjeen sisältävän sivun lataaminen tapahtuu hyvin pitkälti samalla periaattella, kuin hiscore sivun lataaminen.