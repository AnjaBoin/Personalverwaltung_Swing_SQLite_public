🇩🇪 Deutsch | [🇬🇧 English](README.en.md) | [🇳🇱 Nederlands](README.nl.md)

# Personalverwaltung

Eine Java-Anwendung zur Verwaltung von Mitarbeiterdaten mit grafischer Oberfläche (Java Swing) und SQLite-Datenbank.

## Über dieses Projekt

Dieses Projekt ist im Rahmen meines Praktikums bei der **Bruckmann Steuerungstechnik GmbH** entstanden. Die Aufgabe bestand darin, eine Personalverwaltung in Java zu programmieren. Das Ergebnis ist in diesem Repository zu finden.

## Funktionen

- Übersicht aller Mitarbeiter in einer Swing-Tabelle
- Neue Mitarbeiter anlegen (die ID wird automatisch von der Datenbank vergeben)
- Mitarbeiterdaten ändern
- Mitarbeiter löschen, mit Sicherheitsabfrage und Anzeige des Namens
- Abteilung über ein Dropdown-Menü auswählen
- Anwesenheitsstatus direkt in der Tabelle per Checkbox ändern
- Farbliche Kennzeichnung: anwesende Mitarbeiter grün, abwesende rot
- Dauerhafte Speicherung in einer SQLite-Datenbank; jede Änderung wird sofort gespeichert

## Projektstruktur

| Klasse                  | Aufgabe                                                                        |
|-------------------------|--------------------------------------------------------------------------------|
| `Main`                  | Startet das Programm und öffnet das Hauptfenster                               |
| `Mitarbeiter`           | Definiert das Objekt Mitarbeiter mit allen Feldern und Spaltennamen            |
| `Verwaltung`            | Regelt das Hinzufügen, Ändern, Suchen und Löschen von Mitarbeitern             |
| `Speicher`              | Liest und schreibt die Mitarbeiterdaten in der SQLite-Datenbank (JDBC)         |
| `Ausgabe`               | Hauptfenster mit Swing-Tabelle und Buttons                                     |
| `Eingabe`               | Eingabedialoge (JOptionPane) zum Anlegen, Ändern und Löschen von Mitarbeitern  |
| `MitarbeiterTableModel` | Verbindet die Mitarbeiterliste mit der Swing-Tabelle                           |
| `Abteilung`             | Enthält die Liste der Abteilungen für das Dropdown-Menü                        |

## Verwendete Technologien

- Java
- Java Swing für die Benutzeroberfläche
- JDBC und SQLite für die Datenspeicherung
- IntelliJ IDEA
- Git und GitHub

## Voraussetzungen

Das Projekt benötigt zwei Bibliotheken, die nicht im Repository enthalten sind:

- `sqlite-jdbc` (org.xerial)
- `slf4j-api` (org.slf4j)

In IntelliJ IDEA: **File → Project Structure → Libraries → + → From Maven...** und `org.xerial:sqlite-jdbc` hinzufügen. Mit der Option „Transitive dependencies" wird `slf4j-api` automatisch mitgeladen.

## Starten

1. Repository klonen:
   ```
   git clone https://github.com/AnjaBoin/Personalverwaltung_Swing_public.git
   ```
2. Projekt in IntelliJ IDEA öffnen.
3. Die Bibliotheken hinzufügen (siehe Voraussetzungen).
4. `Main` ausführen.

Die Mitarbeiterdaten werden in `Daten/personalverwaltung.db` gespeichert. Die Datenbank und die Tabelle werden beim ersten Start automatisch angelegt.

## Autorin

**Anja Boin**
Praktikum bei der Bruckmann Steuerungstechnik GmbH
