## Klassendiagramm für Datenbankzugriff
Umgesetzt mit `spring-data`, daher `JpaRepository`

![Klassendiagramm](01_classDiagramm/repositories.svg)

## Model / Entities
Umgesetzt mit JPA, daher entsprechende Typen/Annotations

![Klassendiagramm](01_classDiagramm/models.svg)
### Features

#### Login
![Klassendiagramm](01_classDiagramm/login.svg)

#### Chat
`UnicastProcessor` und `Flux` von Reactive Programming (Project Reactor)
![Klassendiagramm](01_classDiagramm/chat.svg)

#### Anzeige Benutzerinfo
![Klassendiagramm](01_classDiagramm/user_infos_show.svg)

#### Bearbeiten Benutzerinfo
![Klassendiagramm](01_classDiagramm/user_infos_edit.svg)

#### Patient registrieren
![Klassendiagramm](01_classDiagramm/register_patient.svg)

#### Aktivitätstagebuch Übersicht
![Klassendiagramm](01_classDiagramm/activity_diary_overview.svg)

#### Aktivitätstagebuch Eintrag erstellen
![Klassendiagramm](01_classDiagramm/activity_diary_create_entry.svg)

#### Stimmungstagebuch
Da fast genau gleich (bis auf UI Visualisierung) wie Aktivitätstagebuch, wird darauf verzichtet dies noch seperat zu visualisieren.

## Code
Der Code zum Klassendiagramm befindet sich unter https://github.com/soed2020-teamorange/ch.bfh.bti7081.s2020.orange/tree/master/src/main/java/ch/bfh/bti7081/s2020/orange/backend
