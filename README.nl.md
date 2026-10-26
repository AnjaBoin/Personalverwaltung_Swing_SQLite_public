[🇩🇪 Deutsch](README.md) | [🇬🇧 English](README.en.md) | 🇳🇱 Nederlands

# Personalverwaltung

Een Java-applicatie voor het beheren van medewerkersgegevens, met een grafische gebruikersinterface (Java Swing) en een SQLite-database.

## Over dit project

Dit project is ontstaan tijdens mijn stage bij **Bruckmann Steuerungstechnik GmbH**. De opdracht was om in Java een personeelsadministratie te programmeren. Het resultaat staat in deze repository.

## Functies

- Overzicht van alle medewerkers in een Swing-tabel
- Nieuwe medewerkers toevoegen (de ID wordt automatisch door de database toegekend)
- Medewerkersgegevens wijzigen
- Medewerkers verwijderen, met een bevestigingsvraag waarin de naam wordt getoond
- Afdeling kiezen via een dropdownmenu
- Aanwezigheidsstatus direct in de tabel wijzigen met een vinkje
- Kleurcodering: aanwezige medewerkers groen, afwezige medewerkers rood
- Blijvende opslag in een SQLite-database; elke wijziging wordt direct opgeslagen

## Projectstructuur

| Klasse                  | Taak                                                                              |
|-------------------------|-----------------------------------------------------------------------------------|
| `Main`                  | Start het programma en opent het hoofdvenster                                     |
| `Mitarbeiter`           | Definieert het object medewerker met alle velden en kolomnamen                    |
| `Verwaltung`            | Regelt het toevoegen, wijzigen, zoeken en verwijderen van medewerkers             |
| `Speicher`              | Leest en schrijft de medewerkersgegevens in de SQLite-database (JDBC)             |
| `Ausgabe`               | Hoofdvenster met Swing-tabel en knoppen                                           |
| `Eingabe`               | Invoervensters (JOptionPane) voor toevoegen, wijzigen en verwijderen              |
| `MitarbeiterTableModel` | Verbindt de medewerkerslijst met de Swing-tabel                                   |
| `Abteilung`             | Bevat de lijst met afdelingen voor het dropdownmenu                               |

## Gebruikte technologieën

- Java
- Java Swing voor de gebruikersinterface
- JDBC en SQLite voor de gegevensopslag
- IntelliJ IDEA
- Git en GitHub

## Vereisten

Het project heeft twee bibliotheken nodig die niet in de repository zitten:

- `sqlite-jdbc` (org.xerial)
- `slf4j-api` (org.slf4j)

In IntelliJ IDEA: **File → Project Structure → Libraries → + → From Maven...** en `org.xerial:sqlite-jdbc` toevoegen. Met de optie "Transitive dependencies" wordt `slf4j-api` automatisch meegeladen.

## Starten

1. Repository klonen:
   ```
   git clone https://github.com/AnjaBoin/Personalverwaltung_Swing_public.git
   ```
2. Project openen in IntelliJ IDEA.
3. De bibliotheken toevoegen (zie Vereisten).
4. `Main` uitvoeren.

De medewerkersgegevens worden opgeslagen in `Daten/personalverwaltung.db`. De database en de tabel worden bij de eerste start automatisch aangemaakt.

## Auteur

**Anja Boin**
Stage bij Bruckmann Steuerungstechnik GmbH
