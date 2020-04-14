<!-- Bitte Unterkapitel mit ### fortführen damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## System requirements specification

### Funktionale Anforderungen

Nr. | Beschreibung | Use Case
-----|--------|-------------
01 | Neuen Termin erfassen: Ein Patient kann über das App einen neuen Termin mit dem Arzt oder Therapeuten erstellen | Use-Case-01
02 | Informationsseite: Dem Patienten wird eine Informationsseite angezeigt, bei welcher er Informationen zu seiner Diagnose findet. | Use-Case-02
03 | Stimmung erfasssen: Der Benutzer kann seine Stimmung in regelmässigen Abständen erfassen (Bild, Text, ...) | Use-Case-03
03 | Stimmung erfasssen: Der Benutzer kann seine erfassen Stimmungen analysieren | Use-Case-03
04 | Neuen Patienten im System registrieren: Ein Therapeut kann einen neuen Benutzer erstellen. Der Patient selbst soll keine Registrierung durchführen können. Ihm wird das Login schlussendlich überreicht | Use-Case-04
05 | Login: Ein Patient sowie Therapeut kann sich bei der App einloggen | Vorraussetzung für alle Use-Cases
06 | Therapeut bestätigt Patiententermin: Ein vom Patienten angefragter Termin kann durch den Therapeuten bestätigt werden | Use-Case-05, Use-Case-01
07 | Therapeut fügt neues Medikament für den Patienten hinzu: Der Therapeut kann dem Patienten ein neues Medikament zur Einnahme hinzufügen | Use-Case-06 
07 | Eintrag ins Tätigskeitsbuch: Ähnlich wie bei der Stimmung erfassen, kann der Patient seine Tätigkeiten in regelmässigen Abständen erfassen | Use-Case-07, Use-Case-03
07 | Eintrag ins Tätigskeitsbuch: Ähnlich wie bei der Stimmung erfassen, kann der Patient seine Tätigkeiten analysieren | Use-Case-07, Use-Case-03
09 | Erinnerung an die Medikamenteneinnahme: Dem Benutzer wird eine Benachrichtigung angezeigt, dass er ein Medikament, welches ihm der Arzt eingetragen hat, einnehmen muss  | Use-Case-08, Use-Case-06
09 | Erinnerung an die Medikamenteneinnahme: Ein Patient kann die Einnahme des Medikamentes bestätigen  | Use-Case-08, Use-Case-06
10 | Im Notfall einen Therapeuten anrufen: Der Patient muss seinen Therauepten im Notfall anrufen können | Use-Case-09
11 | Notruf wählen: Der Patient muss einen Notruf-Button haben, bei welchem er den Notfall (Zentrale) kontaktieren kann | Use-Case-10
12 | Chatfunktion mit Fachperson: Dem Patienten sowie Therapeuten steht ein Chat zur Verfügung, bei welchen sie sich unterhalten/beraten können | Use-Case-11


### Nicht-Funktionale Anforderungen

Nr. | Art | Beschreibung
-----|--------|--------
01 | Sicherheit/Datenschutz | Benutzer können Daten nur sehen, wenn sie eingeloggt sind.
02 | Sicherheit/Datenschutz | Benutzer können nur ihre eigenen Daten sehen.
03 | Sicherheit/Datenschutz | Daten werden sicher und konsistent auf der Datenbank gespeichert. Bei einem Ausfall sind alle Daten mit dem aktuellsten Stand noch verfügbar.
04 | Sicherheit/Datenschutz | Die Applikation hält sich an die DSGVO.
05 | Benutzerfreundlichkeit | Nebst IT-affinen Personen sollen auch unerfahrene Benutzer die Applikation einfach bedienen können.
06 | Benutzerfreundlichkeit | Die App ist für das Handy sowie Laptop, Tablet, etc. implementiert (responsive Design)
06 | Benutzerfreundlichkeit | Eingabefelder sollen validiert werden und dem Benutzer entsprechend gekennzeichnet werden
06 | Entwicklung | Grössere Änderungen am Programmcode müssen von einer zweiten Person angesehen und freigegeben werden.
07 | Verfügbarkeit | Die Applikation soll unterbruchsfrei durchgehend verfügbar sein.
08 | Performance | Die Applikation soll in Seiten und Daten performant und ohne Wartezeit laden. Das bedeutet, dass keine Wartezeit von mehr als 3 Sekunden auftritt.
09 | Rechtliches | Das System hält sich an die 2 Gesetztestexte<ul><li>Data Protection Act that governs the confidentiality of personal information.</li><li>Mental Health Act that governs the compulsory detention of patients deemed to be a danger to themselves or others.</li></ul>
