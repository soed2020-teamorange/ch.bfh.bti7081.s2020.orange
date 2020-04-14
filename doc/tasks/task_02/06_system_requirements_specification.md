<!-- Bitte Unterkapitel mit ### fortführen damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## System requirements specification

### Funktionale Anforderungen

Nr. | Beschreibung | Use Case
-----|--------|-------------
01 | Ein Patient kann über das App einen freien Termin beim Therapeuten suchen | Use-Case-01
02 | Ein zuvor gesuchter und freier Termin, kann beim Therapeuten angefragt werden | Use-Case-01
03 | Dem Patienten wird eine Informationsseite angezeigt, bei welcher er Informationen zu seiner Diagnose findet. | Use-Case-02
04 | Der Benutzer kann seine Stimmung in regelmässigen Abständen erfassen (Bild, Text, ...) | Use-Case-03
05 | Der Benutzer kann seine erfassen Stimmungen analysieren | Use-Case-03
06 | Ein Therapeut kann einen neuen Benutzer erstellen. Der Patient selbst soll keine Registrierung durchführen können. Ihm wird das Login schlussendlich überreicht | Use-Case-04
07 | Ein bestehender Patient kann bearbeitet werden | Use-Case-04
08 | Ein bestehender Patient kann gelöscht werden | Use-Case-04
09 | Ein Patient sowie Therapeut kann sich bei der App einloggen | Vorraussetzung für alle Use-Cases
10 | Ein vom Patienten angefragter Termin kann durch den Therapeuten bestätigt werden | Use-Case-05, Use-Case-01
11 | Der Therapeut kann dem Patienten ein neues Medikament zur Einnahme hinzufügen | Use-Case-06 
12 | Der Therapeut kann die Einnahme des Medikaments bearbeiten | Use-Case-06 
13 | Ähnlich wie bei der Stimmung erfassen, kann der Patient seine Tätigkeiten in regelmässigen Abständen erfassen | Use-Case-07, Use-Case-03
14 | Ähnlich wie bei der Stimmung erfassen, kann der Patient seine Tätigkeiten analysieren | Use-Case-07, Use-Case-03
15 | Dem Benutzer wird eine Benachrichtigung angezeigt, dass er ein Medikament, welches ihm der Arzt eingetragen hat, einnehmen muss  | Use-Case-08, Use-Case-06
16 | Ein Patient kann die Einnahme des Medikamentes bestätigen  | Use-Case-08, Use-Case-06
17 | Der Patient muss seinen Therauepten im Notfall anrufen können | Use-Case-09
18 | Der Patient muss einen Notruf-Button haben, bei welchem er den Notfall (Zentrale) kontaktieren kann | Use-Case-10
19 | Dem Patienten sowie Therapeuten steht ein Chat zur Verfügung, bei welchen sie sich unterhalten/beraten können | Use-Case-11
20 | Dem Patienten steht eine Liste seiner/seines Therapeuten zur Verfügung. | Use-Case-11, Use-Case-09
21 | Dem Therapeuten steht eine Liste seiner Patienten zur Verfügung. | Use-Case-11, Use-Case-04


### Nicht-Funktionale Anforderungen

Nr. | Art | Beschreibung
-----|--------|--------
01 | Sicherheit/Datenschutz | Benutzer können Daten nur sehen, wenn sie eingeloggt sind.
02 | Sicherheit/Datenschutz | Benutzer können nur ihre eigenen Daten sehen.
03 | Sicherheit/Datenschutz | Benutzer sehen nur Sachen, zu welchen sie auch die Berechtigungen dafür haben.
04 | Sicherheit/Datenschutz | Daten werden sicher und konsistent auf der Datenbank gespeichert. Bei einem Ausfall sind alle Daten mit dem aktuellsten Stand noch verfügbar.
05 | Sicherheit/Datenschutz | Die Applikation hält sich an die DSGVO.
06 | Benutzerfreundlichkeit | Nebst IT-affinen Personen sollen auch unerfahrene Benutzer die Applikation einfach bedienen können.
07 | Benutzerfreundlichkeit | Die App ist für das Handy sowie Laptop, Tablet, etc. implementiert (responsive Design)
08 | Benutzerfreundlichkeit | Eingabefelder sollen validiert werden und dem Benutzer entsprechend gekennzeichnet werden
09 | Benutzerfreundlichkeit | Barrierefreiheit, für Menschen mit Behinderung, muss gegeben sein
10 | Entwicklung | Es gibt eine Möglichkeit, dem Benutzer Push-Benachrichtigungen zu schicken. 
11 | Entwicklung | Grössere Änderungen am Programmcode müssen von einer zweiten Person angesehen und freigegeben werden.
12 | Entwicklung | Sourcecode sowie Dokumentation wird in die Versionsverwaltung eingecheckt. 
13 | Verfügbarkeit | Die Applikation soll unterbruchsfrei durchgehend verfügbar sein.
14 | Verfügbarkeit | Die Applikation soll mit den gängisten und neusten Webbrowser (Evergreen browsers) ausführbar sein.
15 | Performance | Die Applikation soll in Seiten und Daten performant und ohne Wartezeit laden. Das bedeutet, dass keine Wartezeit von mehr als 3 Sekunden auftritt.
16 | Rechtliches | Das System hält sich an die 2 Gesetztestexte<ul><li>Data Protection Act that governs the confidentiality of personal information.</li><li>Mental Health Act that governs the compulsory detention of patients deemed to be a danger to themselves or others.</li><li>Vorschriften swissmedic</li></ul>
