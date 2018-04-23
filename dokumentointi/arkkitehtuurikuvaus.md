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

![hiscore](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/Hiscore-taulukon_noutaminen.png)