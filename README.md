# Trio-Opdracht Programmeren &amp; Databases, Netflix Statistix
## Informatie

Gemaakt door: 
* Jan Belterman (`.jjabelte@avans.nl`)
* Floris Botermans (`.f.botermans@avans.nl1`)
* Chris Boer (`.ckboer@avans.nl`)

Klas: 23IVT1A1 

## Installatie instructies

1. Download de laatste release [hier](https://github.com/lVlrChris/TrioNetflix/releases).

2. Extract de zip.

3. Open het project in IntelliJ.

4. In (File > Project Structure > Project settings > Project) selecteer jouw java JDK 1.8.

5. In Modules selecteer de src folder en markeer als Sources.

6. In Libraries, klik +, Java en dan de volgende file: ..\sqljdbc_6.2\enu\mssql-jdbc-6.2.2.jre8.jar.

LET OP: The volgende stap zal een database genaamd 'Netflix' overschrijven.

7. Run de ..\src\database\CreateNetflixDB.sql query in Microsoft SQL Management Studio 17 om de database aan te maken.

8. Build en Run het project.
