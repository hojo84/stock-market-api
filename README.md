# Stock Market API - Tőzsde adatbázis API

## Alkalmazás bemutatása

Az applikáció tőzsdékről, illetve ezen tőzsdéken kereskedett vállalatokról szolgáltat törzsadatokat. 
A dedikált végpontok használatával új tőzsdék, illetve vállalatok hozhatók létre, illetve a már meglévők módosíthatók. Új vállalat létrehozásakor legalább egy tőzsde megadása kötelező.
Mindkét entitásról lekérhető az adatbázisban szereplő összes rekord, illetve azonosító alapján egy-egy adott példány. 
Törlés is végezhető azonosító alapján, azonban itt fontos megjegyezni, hogy egy tőzsde törlése esetén az adott tőzsdén jegyzett összes vállalat is törlése kerül, amennyiben az érintett vállalatok más tőzsdén nem jegyzettek. Vállalat törlése esetén a törlés nem terjed ki a jegyző tőzsdére. 
A dedikált végpontok használatával egy adott cég azonosító alapján felvehető (bejegyezhető) további tőzsdére, illetve el is távolítható egy adott tőzsdéről.

## Alkalmazás futtatása

Az alkalmazás a csatolt `run_application.sh` script futtatásával indítható. Indítás után a végpontok a http://localhost:8080 URL-en érhetők el az API dokumentációban foglaltak szerint. 

## API dokumentáció

* Az alkalmazás indítása után az API dokumentáció elérhetősége: http://localhost:8080/swagger-ui.html
* Minden végpont JSON formátumban kommunikál.
* Minden végpont szabványos HTTP válaszkódokat használ.
* Az elérhető végpontok, illetve ezek paraméterei és válaszkódjai: lásd API dokumentációban.
