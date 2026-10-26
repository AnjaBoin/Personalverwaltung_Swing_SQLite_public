[🇩🇪 Deutsch](README.md) | 🇬🇧 English | [🇳🇱 Nederlands](README.nl.md)

# Personalverwaltung

A Java application for managing employee data, with a graphical user interface (Java Swing) and an SQLite database.

## About this project

This project was created during my internship at **Bruckmann Steuerungstechnik GmbH**. The assignment was to develop an employee management system in Java. This repository contains the result.

## Features

- Overview of all employees in a Swing table
- Add new employees (IDs are assigned automatically by the database)
- Edit employee data
- Delete employees, with a confirmation prompt showing the employee's name
- Select a department from a dropdown menu
- Change attendance status directly in the table via a checkbox
- Colour coding: present employees in green, absent employees in red
- Permanent storage in an SQLite database; every change is saved immediately

## Project structure

| Class                   | Purpose                                                                  |
|-------------------------|--------------------------------------------------------------------------|
| `Main`                  | Starts the program and opens the main window                             |
| `Mitarbeiter`           | Defines the employee object with all fields and column names             |
| `Verwaltung`            | Handles adding, editing, searching and deleting employees                |
| `Speicher`              | Reads and writes employee data in the SQLite database (JDBC)             |
| `Ausgabe`               | Main window with Swing table and buttons                                 |
| `Eingabe`               | Input dialogs (JOptionPane) for adding, editing and deleting employees   |
| `MitarbeiterTableModel` | Connects the employee list to the Swing table                            |
| `Abteilung`             | Contains the list of departments for the dropdown menu                   |

## Technologies

- Java
- Java Swing for the user interface
- JDBC and SQLite for data storage
- IntelliJ IDEA
- Git and GitHub

## Requirements

The project needs two libraries that are not included in the repository:

- `sqlite-jdbc` (org.xerial)
- `slf4j-api` (org.slf4j)

In IntelliJ IDEA: **File → Project Structure → Libraries → + → From Maven...** and add `org.xerial:sqlite-jdbc`. With the option "Transitive dependencies" enabled, `slf4j-api` is downloaded automatically.

## Getting started

1. Clone the repository:
   ```
   git clone https://github.com/AnjaBoin/Personalverwaltung_Swing_public.git
   ```
2. Open the project in IntelliJ IDEA.
3. Add the libraries (see Requirements).
4. Run `Main`.

Employee data is stored in `Daten/personalverwaltung.db`. The database and table are created automatically on first start.

## Author

**Anja Boin**
Internship at Bruckmann Steuerungstechnik GmbH
