# Rock Paper Scissors (Well) Game

Ce projet vise à trouver quels symboles jouer au jeu du **Pierre Feuille Ciseaux Puits** pour maximiser ses chances de
victoire.

## Description du jeu

Le jeu **Pierre Feuille Ciseaux Puits** est une variante classique du célèbre jeu de société.  
Les règles sont les suivantes :

- **Pierre** est battue par **Feuille** et tombe dans le **Puits**.
- **Feuille** est battue par **Ciseaux** et couvre la **Pierre**.
- **Ciseaux** sont battus par la **Pierre** et coupent la **Feuille**.
- **Puits** engloutit **Pierre** et **Ciseaux**, mais est battu par la **Feuille**.

La **Pierre** et le **Puits** battent tous les deux les **Ciseaux**, mais sont battus par la **Feuille**. 
Cependant le**Puits** bat la **Pierre**.  
Ce qui signifie que le Puits n'est rien d'autre qu'une sorte de super Pierre et que la Pierre perd tout son intérêt.  
La meilleure stratégie serait donc de jouer seulement les symboles **Feuille**, **Ciseaux** ou **Puits**.  
**Voyons si nous arriverons à le prouver programmatiquement...**

## Fonctionnalités

- Simulation d'une partie de **Pierre Feuille Ciseaux Puits** avec une stratégie donnée.
- Simulation d'un grand nombre de parties pour évaluer les probabilités de victoire de chaque stratégie.
- Visualisation des résultats sous forme de fichier texte.
- Comparaison des stratégies pour déterminer qu'elle est la meilleure. *TODO*

## Technologies Utilisées

- **Langage** : Java
- **Tests** : Junit 5, AssertJ

