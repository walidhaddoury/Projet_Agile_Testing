Feature: Fonctionnalités de ma page d'accueil
	Scenario: Vérification du titre et de la description
		Given je suis sur la homepage
		Then le titre doit être "Voitures électriques, énergie solaire et propre | Tesla France"
		And la description contient "Tesla accélère la transition mondiale vers une énergie durable en proposant des véhicules électriques, des panneaux solaires et des solutions intégrées d'énergie renouvelable pour les particuliers et les entreprises."
		
	Scenario Outline: Vérification des punchlines
		Given je suis sur la homepage
		Then le titre h1 doit être "<Titre>"
		Examples:
			| Titre 									|
			| Model 3									|
			| Model S									|
			| Model X									|
			| Model Y									|
			| Systèmes d'énergie solaire et Powerwalls	|

	Scenario Outline: Vérification des liens du menu
		Given je suis sur la homepage
		When je clique sur "<Titre>"
		Then le lien renvoi vers "<Link>"
		Examples:
			| Titre 	| Link									|
			| Model S	| https://www.tesla.com/fr_fr/models	|
			| Model 3	| https://www.tesla.com/fr_fr/model3	|
			| Model X	| https://www.tesla.com/fr_fr/modelx	|
			| Model Y	| https://www.tesla.com/fr_fr/modely	|
			| Powerwall	| https://www.tesla.com/fr_fr/powerwall	|
			| Recharge	| https://www.tesla.com/fr_fr/charging	|

	Scenario: Vérification du burger menu
		Given je suis sur la homepage
		When je clique sur le burger menu
		Then le burger menu contient le lien "Véhicules Disponibles"
		And le burger menu contient le lien "Véhicules D'occasion"
		And le burger menu contient le lien "Reprise"
		And le burger menu contient le lien "Cybertruck"
		And le burger menu contient le lien "Roadster"
		And le burger menu contient le lien "Énergie"
		And le burger menu contient le lien "Essais"
		And le burger menu contient le lien "Flottes et Entreprises"
		And le burger menu contient le lien "Nous trouver"
		And le burger menu contient le lien "Événements"
		And le burger menu contient le lien "Assistance"
