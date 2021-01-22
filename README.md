# Projet NeoCampus réalisé dans le cadre de l'UE projet de L3S1

-contient une interface, un serveur, une base de données et des fonctions pour intéragir avec celle ci

## Installation

1-installer WampServer et tout les composants nécessaires a phpMyAdmin.
2-Lancer phpMyAdmin et se connecter avec comme nom d'utilisateur "root" et en laissant le mot de passe vide.
3-Créer une nouvelle base de données nommée "projetcapteurs".
4-Importer la base de données depuis le fichier "projetcapteurs.sql" fourni.

## Utilisation

Pour le lancement :
-Sur la fenêtre d'acceuil cliquer sur OK en entrant 8952 comme port pour lancer le serveur et l'interface du projet.
-Le serveur étant lancé vous pouvez maintenant lancer un simulateur de capteurs dont les données pourront donc être interceptées.

Pour l'interface : 
-Sur la droite du panneau vous pouvez créer un nouveau graphe "a posteriori" qui permet de voir les courbes de jusqu'à trois capteurs du meme type sur l'intervalle de jours séléctionné.
-sur la gauche du panneau vous pouvez voir les valeurs en temps réel des différents capteurs connectés actuellement.
-Sur le bas du panneau vous avez accès à l'arbre des capteurs en fonction du bâtiment et de l'étage, en cliquant sur un capteur dans cet arbre ses différentes informations et modifier ses seuils.

## Informations complémentaires

-Le sujet de ce projet étant de stocker et de pouvoir gérer des données nous n'avons pas mit de bouton pour vider la base de donnée sur notre interface bien que nous avions une fonction pour cela. 
  Si tout de meme pour faire des essais vous avez besoin de la vider voici les lignes à entrer dans phpMyAdmin dans la partie SQL entre deux lancement du projet :

delete valeurs from valeurs;
delete capteurs from capteurs;
delete etages from etages;
delete batiments from batiments;

## Credits

-Fait par Clément Régis, Thomas Girerd et Hugo Beaurain
