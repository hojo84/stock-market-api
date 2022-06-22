# Alkalmazás futtatása

Az alkalmazást a csatolt `run_app_with_env_variables.sh` script fájl futtatásával lehet indítani, mely létrehozza a futtatáshoz szükséges környezeti változókat.

## API Végpontok

* Minden végpont JSON formátumban kommunikál.

| HTTP kérés | erőforrás                               | leírás                                                                                                                                                           | válasz kód |
|------------|-----------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| `GET`      | `/exchanges`                            | visszaadja az adatbázisban tárolt összes tőzsdék neveit azonosítójukkal együtt                                                                                   | 200        |
| `GET`      | `/exchanges/{id}`                       | visszatér a megadott id-val rendelkező tőzsdével, ha az létezik, 404-as hiba, ha nem                                                                             | 200, 404   |
| `POST`     | `/exchanges`                            | a _request body_-ban kapott JSON formátumú `ExchangeDTO`-ot menti le az adatbázisba, (visszatér a lementett entitással)                                          | 200, 400   |
| `PUT`      | `/exchanges/{id}`                       | a _request body_-ban kapott `ExchangeDTO`-ot felülírja az adatbázisban. (ha rossz `{id}`-ra küldték: 404-as hiba)                                                | 200, 404   |
| `DELETE`   | `/exchanges/{id}`                       | törli az adatbázisból a megadott azonosítójú tőzsdét az adott tőzsdén kereskedett vállalatokkal együtt, ha a vállalat más tőzsdén nem kereskedett                | 204        |
| `GET`      | `/exchanges/{id}/companies`             | visszaadja az adott `{id}`-val rendelkező tőzsdén kereskedett vállalatokat                                                                                       | 200        |
| `GET`      | `/exchanges/{id}/companies/{companyId}` | visszaadja az adott `{id}`-val rendelkező tőzsdén az adott `{companyId}`-val kereskedett vállalatot                                                              | 200        |
| `GET`      | `/companies`                            | visszaadja az adatbázisban tárolt összes vállalatot                                                                                                              | 200        |
| `GET`      | `/companies/{id}`                       | visszaadja a megadott id-val rendelkező vállalatot. Ha nincs ilyen, _404-as HTTP kóddal_ válaszol.                                                               | 200, 404   |
| `POST`     | `/companies`                            | _request body_-jában kapott JSON formátumú új varázslót, `CreateCompanyDTO`-ot menti az adatbázisba, (visszatér a lementett entitással)                          | 200, 400   |
| `PUT`      | `/companies/{id}`                       | a _request body_-ban kapott `UpdateCompanyDTO`-ban lévő adatok alapján felülírja a megfelelő rekordot az adatbázisban. (ha rossz `{id}`-ra küldték: 404-as hiba) | 200, 404   |
| `DELETE`   | `/companies/{id}`                       | törli az adatbázisból a megadott id-jú céget (a tőzsdét nem).                                                                                                    | 204        |
| `PUT`      | `/companies/{id}/listings/{exchangeId}` | hozzáadja az adott `{id}`-val rendelkező vállalatot az adott `{exchangeId}`-val rendelkező tőzsdéhez                                                             | 200        |
| `DELETE`   | `/companies/{id}/listings/{exchangeId}` | törli az adott `{id}`-val rendelkező vállalatot az adott `{exchangeId}`-val rendelkező tőzsdéről (tőzsdei kivezetés)                                             | 204        |
