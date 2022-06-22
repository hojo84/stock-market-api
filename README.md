# Stock Market API - Tőzsde adatbázis API

## Alkalmazás bemutatása

Az applikáció célja, hogy tőzsdékről, illetve ezen tőzsdéken kereskedett vállalatokról szolgáltat törzsadatokat. Az alkalmazás kereskedési adatokkal való kibővítése fejlesztés alatt áll. 
A dedikált végpontok használatával új tőzsdék, illetve vállalatok hozhatók létre, illetve a már meglévők módosíthatók, lekérdezhetők. Új vállalat létrehozásakor legalább egy tőzsde megadása kötelező.
Mindkét entitásról lekérhető az adatbázisban szereplő összes rekord, illetve azonosító alapján egy-egy adott példány. Az összes rekord lekérdezése esetén az alábbi formátumban jelenik meg a JSON válasz, ahol a zárójelben lévő rész mutatja a rekord azonosítóját, tehát ezt használva tudunk hivatkozni egy bizonyos rekordra az API dokumentáció szerinti végpontoknál:

Budapest Stock Exchange Index (BUD)

Törlés is végezhető azonosító alapján, azonban itt fontos megjegyezni, hogy egy tőzsde törlése esetén az adott tőzsdén jegyzett összes vállalat is törlése kerül, amennyiben az érintett vállalatok más tőzsdén nem jegyzettek. Vállalat törlése esetén a törlés nem terjed ki a jegyző tőzsdére. 
A dedikált végpontok használatával egy adott cég azonosító alapján felvehető (bejegyezhető) további tőzsdére, illetve el is távolítható egy adott tőzsdéről.

Használt technológiák: Spring Boot, JPA Hibernate, Flyway, Docker, Swagger.

Az adatok tárolása PostgreSQL adatbázis-kezelő rendszer használatával történik. A tesztek H2 adatbázist használnak. A futtatási környezet felállítása Docker konténerek használatával valósul meg.

## Telepítési követelmények

* Docker
* Newman

## Shell script fájlok futtatása

IntelliJ IDEA-ból megnyitva az alkalmazást a scriptek futtathatók a script fájl megnyitása után bal oldali margón megjelenő ![img.png](run_icon.png) zöld (futtatás) nyílra kattintva. 

Alternatív opció: parancssorbeli futtatáshoz előbb be kell állítani a megfelelő jogosultságot a következő paranccsal, ahol a <script_neve> helyére illesszük be az adott script fájl nevét:<br>
`chmod 755 <script_neve>.sh`<br>
Majd futtathatjuk az alábbi paranccsal:<br>
`./<script_neve>.sh`

## Alkalmazás futtatása

Az alkalmazás a csatolt `run_application.sh` script futtatásával elindul a háttérben. Indítás után a végpontok a http://localhost:8080 URL-en érhetők el az API dokumentációban foglaltak szerint. 

## Tesztek futtatása

A unit és integrációs tesztek a csatolt `run_tests.sh` script indításával futtathatók.

A Postman tesztek futásához az alkalmazást előbb el kell indítani, majd a csatolt `run_postman_tests.sh` script fájl segítségével lehet a szóban forgó teszteket lefuttatni. A Postman tesztek futtatásához szükséges a Newman nevű alkalmazás megléte. 
Ennek telepítése: lásd csatolt dokumentáció. 

https://learning.postman.com/docs/running-collections/using-newman-cli/installing-running-newman/ 

## Alkalmazás leállítása

Az alkalmazást a csatolt `stop_application.sh` script futtatásával tudjuk leállítani. Ekkor az alkalmazás leállítása után a Docker konténerek nem kerülnek törlésre.

Amennyiben a leállított konténereket is törölni szeretnénk, futtassuk a csatolt `remove_containers.sh` scriptet.

## API dokumentáció

* Az alkalmazás indítása után az API dokumentáció elérhetősége: http://localhost:8080/swagger-ui.html
* Minden végpont JSON formátumban kommunikál.
* Minden végpont szabványos HTTP válaszkódokat használ.
* Az elérhető végpontok, illetve ezek paraméterei és válaszkódjai: lásd API dokumentációban.

## Fejlesztési környezet

Az alkalmazás és a futtatható shell script fájlok fejlesztési környezetének paraméterei:

* Operációs rendszer: Linux (Ubuntu 20.04.4 LTS)<br>
* JDK verzió: openjdk 17.0.3<br>
* Adatbázis: PostgreSQL 14.3<br>