<!-- Bitte Unterkapitel mit ### fortfÃ¼hren damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## Testing

###Unit Tests
Alle Business Klassen sollen mit Unit Tests abgedeckt werden. Dabei soll darauf geachtet werden, dass klever getestet wird. Es soll nicht jede Zeile Code abgedeckt werden, da dies in der gegebenen Zeit nicht möglich sein wird.

Reine Datenklassen, wie sie z.B. im Modell vorkommen werden, werden explizit nicht getestet. Das Potential für Schaden bei solchen Klassen ist gering, da sie wenig bis keine Businesslogik enthalten. Somit ist der Aufwand für Unit Tests nicht gerechtfertigt.

###Integration Tests
Von allen Entwicklern wird erwartet, dass neue teile der Software integration tested werden bevor sie in den Main-Branch gemerged werden. Dazu ist kein Abnahmeprotokoll erforderlich. Jedoch liegt es in der Verantwortung von jedem, das Team nicht durch unzureichend getestete merges zu blockieren.

###Reviews
Reviews sollen immer dann angewendet werden, wenn der Entwickler dies für nötig hält. Reviews können dem ganzen Team oder einer Einzelperson zugewiesen werden. 

###GUI Tests
Tests auf der Präsentationsebene werden manuell durchgeführt. Für diese Tests besteht kein Test- oder Abnahmeprotokoll, wir arbeiten agil und nicht nach dem V-Modell und somit ist eine devinitive Abnahme für ein Projekt dieser Grösse nicht nötig oder Zielgerichtet.

Es existieren diverse Möglichkeiten und Tools GUI Tests zu automatisieren. Diese setzen aber alle voraus, dass das GUI nur noch minimal angepasst wird. Da wir in einer sehr frühen Phase des Projektes sind, sind grundlegende Änderungen der Benutzeroberfläche zu erwarten. Forerst wird auf eine Automatisierung der GUI Tests verzichtet, jedoch soll diese für einen späteren Zeitpunkt ermöglicht werden. Deshalb werden allen GUI-Elementen bereits jetzt eindeutige IDs vergeben, was eine spätere Automatisierung deutlich vereinfachen wird.