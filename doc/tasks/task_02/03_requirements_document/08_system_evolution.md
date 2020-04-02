<!-- Bitte Unterkapitel mit ### fortführen damit das Dokument nach dem Merge dann bereits sauber gegliedert ist -->
## System evolution

### Soll Zustand
Zur beendigung des Projektes soll eine Webapplikation zur Unterst�tzung von ambulanten Patienten, welche an einer Depression leiden, fertiggestellt sein.

Der behandelnde Therapeut erfasst jeweils den Patienten im System. Sobald der Patient erfasst wurde, kann sich dieser mit einem Browser von verschiedenen Ger�ten aus in das System einloggen.

Dem Patient steht eine Chat-Funktion zur verf�gung um sich mit medizinischem Fachpersonal auszutauschen. In Notf�llen kann der behandelte Therapeut direkt kontaktiert werden oder auch eine allgemeine Notfallnummer (z.B. 143 Dargebotene Hand).

Um dem Patienten die Terminvereinbarung zu vereinfachen, kann eine Terminanfrage direkt �ber das System gestellt werden.

Der Behandelnde Therapeut kann f�r einen Patienten ein Medikament mit der ben�tigten Dosierung, Zeiten der Einnahme und eventuellen weiteren Informationen erfassen. Der Patient erh�lt Erinnerungen f�r die Einnahme der Medikamente.

Der Patient kann ein Tagebuch f�hren, in welcher er seine Stimmung und Tatigkeiten erfasst. Dazu sollen unterschiedliche M�glichkeiten zur verf�gung stehen um dies einfach zu erledigen.

Weiterhin sollen dem Patienten Informationsseiten zur verf�gung gestellt werden, auf welchen er sich �ber seine Krankheit und angrenzende Themen informieren kann. 


### M�gliche Erweiterungen
In einem Weiteren Schritt w�hre es denkbar eine offline version der Applikation anzubieten. Dies w�rde w�re eine weitere Erleichterung f�r den Patienten, wenn er aus bestimmten Gr�nden momentan keinen Internetzugang hat. Dies bedeutet aber, dass teile der Datenbank heruntergeladen werden m�ssten und sp�ter wieder synchronisiert werden. Dies bedeutet ein zus�tzlicher Aufwand der momentan nicht angedacht ist.

Mit einer wachsenden Benutzerbasis k�nnte ein verteiltes System oder load balancing notwendig werden. Deshalb soll bereits beim Design darauf geachtet werden, dass dies erm�glicht wird.

Andere �nderungen oder Erweiterungen sind noch nicht angedacht. Trotzdem soll nach dem open-close Prinzip gearbeitet werden, dies erm�glicht es uns auf Kundenw�nsche einzugehen und Anpassungen oder Erweiterungen unkompliziert umzusetzen.
