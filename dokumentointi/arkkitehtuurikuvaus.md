# Arkkitehtuurikuvaus
## Ohjelman rakenne

## Käyttöliittymä

Ohjelman päämenu ladataan aina ohjelman käynnistyessä. Tässä näkymässä on kolme nappia, jotka kaikki luovat uuden 
Scene-olion ja lataavat sen stageen. "Hiscores"-nappi vie käyttäjän hiscores taulukkoon, johon ladataan parhaiden pelaajien pisteet. "Instructions"-nappi puolestaan vie käyttäjän pelin käyttöohjeeseen. Näistä molemmista näkymistä voi palata päämenuun "Exit"-nappia painamalla. "Start the slaughter"-nappi korvaa päämenun napit tekstikentällä ja "READY"- napilla. Kun pelaaja on laittanut nimensä nimikenttään, voi hän aloittaa pelin painamalla "READY"-nappia. Kun peli alkaa stageen vaihdetaan uusi scene, jossa on pane-olento. Paneen sijoitetaan aluksi pelaajahahmo ja yksi vihollinen. Viholliset liikkuvat satunnaisesti ja pelaaja voi liikkua nuolinäppäimiä käyttäen ja ampua WASD näppäimillä.

## Sovelluslogiikka

Ohjelman osien suhdetta kuvaava luokkakaavio:

![luokkakaavio](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/luokkakaavio.png)

Peli muodostuu pitkälti ScreenLoader ja GameUpdate olioiden varaan. ScreenLoader vastaa menujen vaihdoista ja pelialustan staattisista elementeistä kun taas GameUpdate-olio vastaa pelialustan elementtien liikkeistä. GameUpdate olennon kautta tapahtuu myös kaikki näppäimistökomennot. Molemmilla olioilla on pääsy samaan DatabaseManager-olioon, joka vastaa pelin tulosten tallentamisesta. 
## Päätoiminnalisuudet

Seuraavaksi kuvataan sovelluksen keskeisten toimintojen päätoiminnalisuus sekvenssikaavioilla.

![kaynnistys](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/kaynnistys.png)

Kun ohjelma käynnistetään , main metodi luo uuden ScreenLoader olennon, jonka kautta suurin osa ohjelman toiminnallisuudesta tapahtuu. ScreenLoader saa yhdeksi parametrikseen main metodissa käytetyn Stage- olennon, sillä sen täytyy vaihtaa ruutua Pane- ja BorderPane-olentojen välillä.

![hiscore](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/hiscore.png)

Kun luodaan ScreenLoader-olio, luodaan samalla automaattisesti myös tietokantoja hallinnoiva DatabaseManager. Kun käyttäjä painaa Hiscores-nappia, ScreenLoader lataa uuden ruudun. Samalla Screenloader-olio saa DatabaseManagerilta sen tietokannan sisällön ObservableList-muodossa, jonka se lisää ruudulle. 
Kun käyttäjä painaa Clear-nappia, lähettää ScreenLoader DatabaseManagerille käskyn, että se tyhjää tietokantansa. 
Jos käyttäjä painaa Exit-nappia, palautuu ohjelma alkuperäiseen ruutuun. 

![introduction](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/ohjeet.png)

Käyttöohjeen sisältävän sivun lataaminen tapahtuu hyvin pitkälti samalla periaattella, kuin hiscore sivun lataaminen.

![pelaaminen](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/pelaaminen.png)

Varsinainen peli alkaa kun pelaaja painaa Start-nappia. Tällöin valikko muuttuu siten, että siihen ilmestyy tekstikenttä sekä uusi Ready-nappula. Jos Ready-nappulaa painaa, ilman että tekstikentässä on tekstiä, näyttää se virheilmoituksen. Kun pelaaja painaa Ready-nappia ei-tyhjällä tekstikentällä, luo ScreenLoader-olio uuden peliä varten käytettävän Pane-olion ja siirtää ohjelman hallinnan GameUpdate-oliolle. Kun pelaaja painaa nuolinäppäintä, GameUpdaten KeyController-olio käsittelee sen siten, että pelaajan hahmo liikkuu oikeaan suuntaan. Jos pelaaja puolestaan painaa WASD-näppäintä, KeyController käskee ProjectileMakerin luomaan ammus, joka kulkee WASD näppäimen osoittamaan suuntaan.

## Tietojen pysyväistallennus

Ohjelma käyttää SQLite kirjastoa tietojen pysyväistallennukseen. Tallennuksesta vastaa DatabaseManager niminen DAO-olento. Pelin loputtua DatabaseManager tallentaa pelaajan nimen ja pisteet hiscore taulukkoon. Mikäli pelaajan nimellä on jo tietue tietokannassa, päivitetään piste attribuuttiin uusi arvo, mikäli uusi arvo on vanhaa parempi. Käyttäjä voi myös tarkastella muiden pelaajien tuloksia päävalikosta painamalla "Hiscores"-nappia. Tällöin DataManager olio palauttaa SQL tiedostoon tallennetut tietueet ja ScreenLoader olio asettaa ne taulukkoon. "Clear"-nappia painaessa DatabaseManager tyhjentää tietokannan kaikista tietueista. 

![tallentaminen](https://github.com/jupste/otm-harjoitustyo/blob/master/dokumentointi/tallentaminen.png)