Feature: Fonctionnalités du configurateur de la model S
	Scenario: Vérification de l'url et du prix de LOA
		Given je suis sur la page de la model S
        When je clique sur sur le bouton commander
		Then l'url doit être "https://www.tesla.com/fr_fr/models/design"
		And le prix de LOA est de "847 € /mois"


	Scenario Outline: Verification des caractéristiques du model 
		Given je suis sur la page de configuration de la model S
		When je selectionne le modele "<model>"
		Then le prix de la LOA est de "<prixLOA>"
		And l'économie du carburant estimé est de "<economieEstime>"
		And le prix total avec achat est de "<prixTotal>"
		Examples:
			| model		| prixLOA		| economieEstime	| prixTotal	|
			| Plaid+	| 1 559 € /mois	| 108 € /mois 		| 168 398 €	|
			# | Plaid 	| 1 203 € /mois	| 108 € /mois 		| 134 495 €	|

	
	Scenario: Verification augmentation prix
		Given je suis sur la page de configuration de la model S
		When je descends et je selectionne autonomie
		Then le prix augmente de "89€/mois"

