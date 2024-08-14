# Projet Tic-Tac-Toe Android (420-DM2-ID 2024-01-28)

## Introduction
Complétez votre premier projet : un jeu de Tic-Tac-Toe en Kotlin. Vous serez responsable du design et du code source.

## Objectifs
Voici les objectifs visés par le projet :
- Créer une interface simple pour le jeu.
- Récupérer et afficher certaines données.
- Développer la logique du jeu.
- Faire apparaître des images.
- Détecter les victoires et les parties nulles.

## Temps Alloué
4 jours ouvrables.

## Particularités du Projet
Cette version du jeu se joue à deux joueurs. Lors de l'ouverture de l’application, les noms des deux joueurs doivent être fournis dans des zones de saisie. L'application n'acceptera pas de commencer le jeu tant que les deux noms ne sont pas validés. Une fois validés, les zones de saisie sont désactivées et le bouton pour commencer la partie devient fonctionnel. Les noms et les pointages des joueurs sont affichés, et la partie peut commencer.

Le joueur 1 possède les 'X' et débute la première partie. Si trois symboles identiques appartenant au même joueur sont alignés correctement, l’application déclare le gagnant, augmente son score et offre la possibilité de recommencer une nouvelle partie.

## Éléments Clés de l'Application
- L'interface doit être adéquate, démontrant une maîtrise des différents layouts XML.
- La partie ne commence que si deux noms de joueurs valides et non vides sont entrés.
- Les zones de saisie pour les noms deviennent inaccessibles pendant la partie.
- Le premier joueur utilise les symboles 'X' et le second les symboles 'O'.
- La grille de jeu est un tableau de 3 rangées et 3 colonnes.
- Les symboles s'affichent sous forme d'images dans les cases du jeu.
- Le jeu identifie le gagnant par son nom et augmente son score.
- Une partie nulle est détectée si toutes les cases sont occupées sans combinaison gagnante.
- L’application propose de continuer avec les mêmes joueurs ou de retourner au début pour entrer de nouveaux joueurs.

## Informations Supplémentaires
- Pour afficher les X et les O, vous pouvez utiliser des ImageView et les éléments Horizontal et Vertical Divider pour dessiner les lignes. Utilisez `android:background="@color/colorPrimaryDark"` pour changer la couleur.
- Vous pouvez également utiliser des ImageButton comme cases pour la grille de jeu.

## Grille de Correction
### Étape 1 (20 points)
- L'interface est adéquate et comprend tous les éléments demandés (10 points)
- Bon choix de layouts et d'éléments dans le XML (5 points)
- Les zones de noms et pointages sont bien visibles et distinctes (3 points)
**Total Étape 1 : /20**

### Étape 2 (15 points)
- Validation des noms avant le début d'une partie (5 points)
- Les pointages des joueurs sont initialisés et affichés au départ (5 points)
- Les noms ne peuvent pas être modifiés ou supprimés durant la partie (5 points)
**Total Étape 2 : /15**

### Étape 3 (15 points)
- Le premier joueur fait apparaître les X et le deuxième les O (8 points)
- Des images ont été utilisées dans les cases de jeu (7 points)
**Total Étape 3 : /15**

### Étape 4 (50 points)
- La logique dans le code a bien été implémentée et utilisation de structures adéquates (10 points)
- Le jeu déclare le gagnant par son nom (5 points)
- Le pointage du gagnant s'incrémente (3 points)
- Toutes les possibilités de victoire sont couvertes par la logique (8 points)
- C'est le gagnant qui débute la prochaine partie (4 points)
- Une nulle peut être déclarée et celui qui débute la prochaine partie est interchangé (5 points)
**Total Étape 4 : /50**

### Pénalités : -
- Des boutons pour débuter le jeu, recommencer la partie et quitter sont présents (2 points)
- Les pointages sont sauvegardés tant que la session est active (5 points)
- Un bouton permet de réinitialiser les cases après chaque tour de jeu pour une autre partie (5 points)
- Un bouton offre la possibilité de quitter le jeu et de changer les joueurs (5 points)

## Limitations et Améliorations Futures
Ce projet a été développé à des fins de pratique dans le cadre d'un cours. Voici quelques limitations et suggestions d'améliorations futures :

- **Exigence d'enregistrement des joueurs** : Dans un contexte réel, il serait absurde de demander aux joueurs de s'enregistrer avant de jouer à un jeu aussi simple que le Tic-Tac-Toe. Cette fonctionnalité a été ajoutée pour illustrer des techniques de validation et de gestion des données utilisateur.

- **Validation des noms** : La validation des noms des joueurs a été limitée à vérifier qu'ils sont différents. Des vérifications supplémentaires pourraient être ajoutées, comme des restrictions sur les caractères autorisés ou la longueur des noms.

- **Messages de tour et de gagnant** : Les messages indiquant à qui c'est le tour et qui est le gagnant s'affichent en même temps. Il serait préférable de modifier ces messages pour qu'ils apparaissent de manière séquentielle afin d'améliorer l'expérience utilisateur.

## Installation
1. Cloner ce dépôt sur votre machine locale.
2. Ouvrir le projet dans Android Studio.
3. Assurez-vous que toutes les dépendances sont correctement configurées.
4. Lancer l'application sur un émulateur ou un appareil Android.

## Utilisation
1. Lancez l'application.
2. Entrez les noms des joueurs et choisissez les images/symboles.
3. Commencez la partie et suivez les instructions pour jouer.
4. À la fin de la partie, choisissez de rejouer ou de retourner à l'écran d'accueil.

