# ndongTourisme
***
*Site qui propose diférents type de Services. Des hotels, des Restaurants et des activités. Les clients pourront voir les détails sur les prestataires et voir les prix abonnés qu'ils peuvent avoir grâce au CNAMTOURISME.
*_Une petite modification a été faite par rapport à l'énoncé qui parlait de marge prise par le site mais j'ai préféré utiliser ces marges comme des remises faites au client._
*Les clients pourront rechercher des Services selon différents critères.
*Les clients pourront aussi laisser des commentaires.
***

## Informations generales

## Installation

1. Créez une nouvelle base de données dans mysql en important le fichier ndong.sql 
2. Dans le fichier webapp/WEB-INF/classes/hibernate.cfg.xml  , modifier les propriétés hibernate.connection.url , hibernate.connection.username et hibernate.connection.password avec les informations correspondantes à votre base de données fraichement crée.
3. Télecharger le fichier war du projet et importez le dans votre IDE. (Initialement il a été crée avec Eclipse)
4. Démarrer le projet dans votre IDE avec votre serveur. (Initialement ce projet tournait avec Tomcat8)
**Toutes les bibliothèques et ressources nécessaire sont jointes avec le projet (driver jdbc , jstl , hibernate , jquery )**
