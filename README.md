# Evolutionary Computing
### By Micha de Groot, Nima Motamed, Maximilian Filtenborg and Stijn Verdenius

Repository for the optimisation assignment


## Voorstel opzet:

Om snel nieuwe implementanties te testen is de structuur nu opgezet met interfase classes.
Deze vormen de bouwplannen/method-contracts voor hoe elk los onderdeel van een EA eruit moet zien. 
Deze onderdelen zijn: parent selection, recombination, mutation, natural selection en termination.
Voor elk van deze onderdelen is er dus een interfaceclass die bepaald welke functies op zijn minst geimplementeerd moeten worden.
Uiteraard kan aan de losse implementaties altijd meer functionaliteit toegevoegd worden. 
De reden om dit te doen is dat er snel geswitched kan worden tussen verschillende implementaties van EA.
Je kan namelijk in player50.java bepalen welke implementatie je gaat toepassen.
Stel je wilt het algoritme dat je hebt gebouwd even testen zonder mutatie, dan verander je 1 string in de configuratie. 
Idem dito als je wil stoppen met het EA na 100 beurten ipv na het maximaal aantal evaluaties; je hangt er gewoon een andere terminator in.

Het echte werkt van de EA gebeurt in de player50.java en de population.java classes. 
Population.java verwacht een interface object van alle onderdelen (: mutatie, .. etc.)  en roept dan een van de functies aan die gespecificeerd zijn in dit interface.
Dit zorgt ervoor dat elke implementatie van zon interface doorgegeven kan worden met een andere implementatie van die afgesproken functies.
Hierdoor hoeft er niet veel veranderd te worden aan population.java zodra die eenmaal staat.

Neem de terminators als voorbeeld: je hebt terminator.java; de interface class. De andere zijn daar implementaties van.
Dit betekent dat ze alle functies die gedefineerd staan in de interface class moeten implementeren en @overridden.
Echter is dit niet het geval bij de 3 specifieke terminators die er bestaan op het moment dat ik dit schrijf.
De reden hiervoor is dat ze alle 3 de empty terminator extenden en dus alle functies die ze zelf niet implementeren uit empty terminator gehaald worden.
Als ik nu basis functionaliteit van bijv de functie addgeneration wil aanpassen hoef ik dat niet in alle files apart te doen, en toch kan in population.java de functie addgeneration gevonden worden omdat hij in de interfacde staat. 
Zo kom je ook nooit op een methodnotfoundexception.

in player50.java zit de complete EA structuur zoals gedefineerd in het boek (dat stuk pseudocode), in population.java zit vervolgens de loop uit die structuur.
individual en genoType zijn gewoon objectclasses om data bij te houden voor fenotype en genotype respectievelijk.
Momenteel is ons genotype nog identiek aan fenotype maar als we dat willen aanpassen dan kan dat zo gemakkelijk

Wat kan je aanpassen? alles! Zorg alleen dat alle methoden die in de interface staan ook geimplementeerd en ge@override zijn. 
Als je meer functies wil toevoegen kan dat prima, ook subclasses of whatever. echter als je het returntype of inkomende parameters wil aanpassen van een methode die @override is van de interface moet je die in ALLE implementaties daarnaartoe aanpassen.



### Compilation and testing commands from the tutorial 
#### Compile

todo: bijwerken

javac -cp .:contest.jar player50.java  
jar cmf MainClass.txt submission.jar player50.class  
#### Testing
java -jar testrun.jar -submission=player50 -evaluation=SphereEvaluation -seed=1  
Add local path if required:  
export LD\_LIBRARY\_PATH=~/path/to/repo/Evolutionary\_Computing/src
