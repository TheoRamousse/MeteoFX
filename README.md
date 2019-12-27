# MeteoFX


English :

The goal of this project is to create a graphical software to simulate temperatures detected by diferents types of sensors.
These sensors will generate temperature according to diferents algorithms configurables by users.
The main page of the software is made of two parts : a master and a detail. On the right, there is the list of sensors and on the left, there is the detail of the selected sensor.

The user will be able to :

- Create sensors that he will have to configure
- Modify existing sensors
- See temperatures simulated by sensors with a digital format
- See the current weather thanks to a simuled cam associated to sensors
- Load and save sensors
- See the sensor tree (a sensor can be made of others sensors)

Sensors can be configurate according to :
- The name
- The refresh frequency in sconds (from 1 to 60 seconds)
- The temperature generation algorithm
- Others elements needed to configurate the algorithm

Others developers will be able to add some new algorithms. Each algorithm will be linked to a view. This view correspond to the configuration elements that the user will have to complete during the sensor creation







French :

Ce projet consiste à créer une application graphique qui permet de simuler les températures relevées par différents types de capteurs de températures.
Ces capteurs généreront donc des températures en fonction de différents algorithmes paramétrables par l'utilisateur.
La page principale de l'application se présente sous le format d'un master/detail avec à droite la liste des capteurs et à gauche le détail du capteur sélectionné.

L'utilisateur pourra ainsi :

- Créer des capteurs qu'il devra configurer
- Modifier la configuration des capteurs
- Supprimer les capteurs
- Voir les températures au format digital relevées en temps réel
- Voir le temps qu'il fait grâce aux webcams simulées, associées aux capteurs
- Sauvegarder et charger les capteurs
- Voir l'arborescence des capteurs ( certains capteurs génèrent des valeurs en fonction d'autres capteurs)

Les capteurs sont configurables selon :

- Leur nom
- La fréquence de rafraîchissement en secondes (de 1 seconde à 60 secondes)
- L'alogrithme de génération des températures
- D'autres éléments de configuration requis par l'algorithme de génération des températures

Les développeurs pourront ainsi mettre à jour l'application en ajoutant des algorithmes de génération de températures.
A ces alogrithmes, seront associés des vues qui correspondront aux éléments de configuration supplémentaires que l'utilisateur devra renseigner lors de la création du capteur.