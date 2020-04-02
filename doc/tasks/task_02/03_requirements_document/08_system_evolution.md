<!-- Bitte Unterkapitel mit ### fortfÃ¼hren damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## System evolution

### Soll Zustand
Zur beendigung des Projektes soll eine Webapplikation zur Unterstützung von ambulanten Patienten, welche an einer Depression leiden, fertiggestellt sein.

Der behandelnde Therapeut erfasst jeweils den Patienten im System. Sobald der Patient erfasst wurde, kann sich dieser mit einem Browser von verschiedenen Geräten aus in das System einloggen.

Dem Patient steht eine Chat-Funktion zur verfügung um sich mit medizinischem Fachpersonal auszutauschen. In Notfällen kann der behandelte Therapeut direkt kontaktiert werden oder auch eine allgemeine Notfallnummer (z.B. 143 Dargebotene Hand).

Um dem Patienten die Terminvereinbarung zu vereinfachen, kann eine Terminanfrage direkt über das System gestellt werden.

Der Behandelnde Therapeut kann für einen Patienten ein Medikament mit der benötigten Dosierung, Zeiten der Einnahme und eventuellen weiteren Informationen erfassen. Der Patient erhält Erinnerungen für die Einnahme der Medikamente.

Der Patient kann ein Tagebuch führen, in welcher er seine Stimmung und Tatigkeiten erfasst. Dazu sollen unterschiedliche Möglichkeiten zur verfügung stehen um dies einfach zu erledigen.

Weiterhin sollen dem Patienten Informationsseiten zur verfügung gestellt werden, auf welchen er sich über seine Krankheit und angrenzende Themen informieren kann. 


### Mögliche Erweiterungen
In einem Weiteren Schritt währe es denkbar eine offline version der Applikation anzubieten. Dies würde wäre eine weitere Erleichterung für den Patienten, wenn er aus bestimmten Gründen momentan keinen Internetzugang hat. Dies bedeutet aber, dass teile der Datenbank heruntergeladen werden müssten und später wieder synchronisiert werden. Dies bedeutet ein zusätzlicher Aufwand der momentan nicht angedacht ist.

Mit einer wachsenden Benutzerbasis könnte ein verteiltes System oder load balancing notwendig werden. Deshalb soll bereits beim Design darauf geachtet werden, dass dies ermöglicht wird.

Andere Änderungen oder Erweiterungen sind noch nicht angedacht. Trotzdem soll nach dem open-close Prinzip gearbeitet werden, dies ermöglicht es uns auf Kundenwünsche einzugehen und Anpassungen oder Erweiterungen unkompliziert umzusetzen.
