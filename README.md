# Vektorgrafik

Ett ritprogram byggt med Java och Swing där du kan skapa och redigera vektorfigurer på en rityta.

## Funktioner

- Rita cirklar, rektanglar, linjer, trianglar och femhörningar samt lägga till text.
- Markera, avmarkera, flytta, ändra storlek på och ta bort figurer.
- Välja röd, blå eller svart färg och justera linjebredden.
- Skapa återanvändbara egna figurer från markerade objekt.
- Ångra och göra om ändringar med knapparna `<<` och `>>`.
- Exportera och importera ritytan i programmets CSV-format.
- Skriva ut ritytan.

## Kom igång

### Krav

- JDK 21 eller senare.
- Apache Maven installerat och tillgängligt som `mvn`.
- En grafisk skrivbordsmiljö för att köra Swing-programmet.

### Bygg och starta

Klona projektet och gå till projektmappen:

```sh
git clone https://github.com/isakenglund/vektorgrafik.git
cd vektorgrafik
```

Bygg projektet:

```sh
mvn clean package
```

Starta programmet:

```sh
java -jar target/vektorgrafik-1.0-SNAPSHOT.jar
```

Du kan även öppna projektets `pom.xml` i IntelliJ IDEA, välja JDK 21 och köra huvudklassen `view.ShapeApp`.

## Användning

1. Välj en figur i menyn **Shapes** och rita med musen på den vita ritytan.
2. Välj ett redigeringsläge i **Modes**, exempelvis **Mark**, **Move** eller **Resize**.
3. Använd färgknapparna och `-`/`+` för att välja stil. Markerade figurer får också den valda stilen.
4. Lägg till text med **Add text**. För egna figurer markerar du objekt och väljer **Manage Custom Shapes → Add marked shapes to custom tool**. Figuren blir tillgänglig i **Custom Shapes** under den aktuella sessionen.
5. Spara eller läs in figurer via **File → Export canvas** respektive **Import canvas**.

Vid import och export anger du filnamnet **utan `.csv`**, eftersom programmet lägger till filändelsen. Relativa sökvägar utgår från mappen där programmet startades. Import lägger till figurer på den befintliga ritytan.

Filen [`test canvas.csv`](test%20canvas.csv) innehåller en exempelritning. Starta programmet från projektmappen och ange `test canvas` i importdialogen för att öppna den.

## Tester

Kör testerna med Maven:

```sh
mvn test
```

Testkoden finns i `src/test/java` och innehåller tester för cirklars konstruktion, kloning och träffkontroll.

## Projektstruktur

```text
src/main/java/
├── controller/       Hantering av mus, filer och redigeringslägen
├── model/            Figurer, stilar och visitor-klasser
└── view/             Swing-fönster och rityta
src/test/java/        Enhetstester
.github/workflows/    GitHub Actions för bygge och release
pom.xml              Maven-konfiguration
test canvas.csv      Exempelritning
```

Programmet är uppdelat i modell, vy och controller. Verktyg och redigeringslägen hanteras av klasser i `controller.states`.

## GitHub Actions

- **Java CI** kör `mvn --batch-mode verify` för pushar och pull requests mot grenen `maven` och laddar upp den byggda JAR-filen som en artefakt.
- **Release** bygger projektet när en tagg som börjar med `v` pushas och bifogar JAR-filen till en GitHub-release.
