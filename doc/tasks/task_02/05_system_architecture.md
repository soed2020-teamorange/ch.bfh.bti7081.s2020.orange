<!-- Bitte Unterkapitel mit ### fortführen damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## System architecture

![Systemarchitektur](resources/05_system_architecture.jpg "Systemarchitektur")

Bei der System Architektur ist zu beachten, dass sich diese noch ändern kann. Während des technischen Refinement der Stories, kann es noch zu Änderungen kommen. Dies ist auch dem geschuldet, da vorgeschriebene Technologien/Einschränkungen, noch nicht bekannt sind. 

### Frontend
Das Frontend der Applikation wird eine Webapplikation (HTML, CSS, JS) sein, welche über Laptops, Computer und Smartphones über einen modernen Browser abrufbar ist. Der Aufruf hier geschieht über das HTTP-Protokoll mit einer SSL Verschlüsselung. Damit die Applikation überall identisch ist und die gleichen Funktionen bietet, wird ein responsive Design erstellt.
Das Frontend kann mit Java (bspw. Vaadin) oder auch Angular umgesetzt werden. Das Frontend wird entsprechend auf einem Webserver zur Verfügung gestellt. 

### Backend
Die Business Logik wird in Java geschrieben. Jegliche Daten werden nur im Backend auf der Datenbank abgelegt und nicht auf einem Endgerät des Kunden. Installationen sind keine notwendig. Zu prüfen ist hier, ob ein Framework wie bspw. Spring verwendet wird. 

### Datenbank
Bei der Datenbank ist zu beachten, dass eine persistente Datenbank verwendet wird und nicht beispielsweise eine In-Memory-Datenbank. Die Daten müssen auch nach einem Neustart oder Absturz verfügbar sein. Die Datenbank ist bei dieser Applikation zentral und muss somit hochverfügbar sein. Auch zu beachten ist, dass Daten in der Datenbank datenschutztechnisch kritisch sind und somit hohen Sicherheitsanforderungen unterliegt.
