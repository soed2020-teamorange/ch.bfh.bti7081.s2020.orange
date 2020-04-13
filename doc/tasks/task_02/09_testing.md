<!-- Bitte Unterkapitel mit ### fortführen damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## Testing

### Unit Tests
Alle business Klassen sollen mit Unit Tests abgedeckt werden. Dabei soll darauf geachtet werden, dass klever getestet wird. Es soll nicht jede Zeile Code abgedeckt werden, da dies in der gegebenen Zeit nicht m�glich sein wird.

Reine Datenklassen, wie sie z.B. im Modell vorkommen werden, werden explizit nicht getestet. Das Potential f�r Schaden bei solchen Klassen ist gering, da sie wenig bis keine Businesslogik enthalten. Somit ist der Aufwand f�r Unit Tests nicht gerechtfertigt.

### Integration Tests
Von allen Entwicklern wird erwartet, dass neue teile der Software integration tested werden, bevor sie in den Main-Branch gemerged werden. Diese Tests sind nicht formgebunden. Jedoch liegt es in der Verantwortung von jedem, das Team nicht durch unzureichend getestete merges zu blockieren.

### Reviews
Reviews sollen immer dann angewendet werden, wenn der Entwickler dies f�r n�tig h�lt. Reviews k�nnen dem gesamten Team oder einer Einzelperson zugewiesen werden. 

### GUI und System Tests
Tests auf der Pr�sentationsebene, und somit des Gesamtsystemes, werden manuell durchgef�hrt. F�r diese Tests besteht kein Test- oder Abnahmeprotokoll, wir arbeiten agil und nicht nach dem V-Modell und somit ist eine devinitive Abnahme f�r ein Projekt dieser Gr�sse nicht n�tig oder zielgerichtet.

Es existieren diverse M�glichkeiten und Tools, um GUI Tests zu automatisieren. Diese setzen aber alle voraus, dass das GUI nur noch minimal angepasst wird. Da wir in einer sehr fr�hen Phase des Projektes sind, sind grundlegende �nderungen der Benutzeroberfl�che zu erwarten. Forerst wird auf eine Automatisierung der GUI Tests verzichtet, jedoch soll diese f�r einen sp�teren Zeitpunkt erm�glicht werden. Deshalb werden allen GUI-Elementen bereits jetzt eindeutige IDs vergeben, was eine sp�tere Automatisierung deutlich vereinfachen wird.