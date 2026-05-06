Application Web de Gestion et de Réservation de Biens Immobiliers

Mini-projet – Développement d’applications Web avec Jakarta EE
Classe : MPSSI1 – Année universitaire : 2025-2026

1. Description générale

Cette application web permet de gérer des biens immobiliers, leurs réservations et paiements au sein d’une plateforme dédiée.
Trois types d’utilisateurs sont disponibles :

Administrateur :

Gestion des utilisateurs (CRUD)

Gestion des biens (CRUD)

Validation, refus, annulation des réservations

Consultation des statistiques

Visualisation d’un calendrier par bien

Modération des avis laissés par les utilisateurs

Client (Utilisateur simple):

Consultation de la liste des biens

Création de réservations (bien + date/heure début/fin)

Consultation de ses propres réservations

Paiement en ligne de ses réservations

Annulation des réservations futures

Vue calendrier des disponibilités

Possibilité de laisser une note et un avis textuel sur un bien

Agent :

Tableau de bord dédié

Gestion déléguée d'une partie des biens et réservations

Règles métier importantes

Un bien ne peut pas être réservé deux fois sur un même créneau.

Le paiement finalise et valide la réservation.

Les réservations passées ne sont plus modifiables.

L’administrateur peut annuler n’importe quelle réservation.

Workflow de validation :

EN_ATTENTE → VALIDEE (après paiement) / REFUSEE / ANNULEE.

2. Technologies utilisées
Back-end

Java

Jakarta Servlet & JSP (Tomcat 11)

JSTL + Expression Language (EL)

Hibernate ORM (MySQL)

Front-end

JSP + JSTL

Bootstrap 5 (CDN)

CSS personnalisé (assets/css/style.css)

Base de données

MySQL 8

Serveur

Apache Tomcat 11

IDE

Eclipse IDE for Enterprise Java and Web Developers

3. Fonctionnalités principales
3.1. Gestion des utilisateurs (Admin)

Liste, ajout, modification, suppression

Rôle (ADMIN / CLIENT / AGENT)

Activation / désactivation de compte

3.2. Gestion des biens (Admin)

CRUD complet

Informations : nom, description, catégorie, prix, etc.

3.3. Réservations
Côté client

Création d’une réservation

Règles :

Date future obligatoire

Fin > Début

Aucun chevauchement avec :

les réservations validées du même bien

Paiement en ligne sécurisé

Liste de mes réservations

Annulation d’une réservation future

Côté administrateur

Liste complète des réservations

Actions :

Valider

Refuser

Annuler (toute réservation, tout utilisateur)

Les réservations passées sont verrouillées

3.4. Vues calendrier
Utilisateur : /user/calendar

Vue personnalisée des disponibilités

Tableau (jours × heures)

Créneaux colorés avec bien + statut

Administrateur : /admin/calendar?bienId=...

Sélection d’un bien

Vue hebdomadaire/mensuelle des réservations de ce bien

3.5. Statistiques (Admin)

Nombre total de réservations et paiements

Répartition par statut

Top 5 des biens les plus demandés

3.6. Avis / commentaires
Client :

Note (1 à 5)

Commentaire

Modification / suppression

Calcul automatique de la note moyenne

Administrateur :

Liste de tous les avis

Suppression (modération)

4. Architecture du projet
4.1. Pattern MVC

Modèle : JavaBeans + DAO (Hibernate)

Vue : JSP + JSTL + EL

Contrôleur : Servlets regroupées par rôle (admin / user / agent)

4.2. Organisation des packages
src/main/java
  config
    HibernateUtil.java

  model
    Utilisateur.java
    Admin.java
    Client.java
    Agent.java
    Bien.java
    CategorieBien.java
    Reservation.java
    Paiement.java
    AvisBien.java

  dao
    ...

  service
    ...

  controller.admin
    AdminDashboardServlet.java
    AdminUserServlet.java
    AdminBienServlet.java
    AdminReservationServlet.java
    AdminStatsServlet.java
    AdminCalendarServlet.java
    AdminAvisServlet.java

  controller.user
    UserHomeServlet.java
    UserReservationServlet.java
    UserCalenderServlet.java
    UserBienDetailsServlet.java
    UserPaiementServlet.java

  controller.auth
    LoginServlet.java
    RegisterServlet.java
    LogoutServlet.java

  controller.agent
    AgentDashboardServlet.java

  filter
    ...

4.3. Arborescence des vues (JSP)
src/main/webapp/
  assets/
    css/
      ...

  WEB-INF/
    web.xml

    views/
      includes/
        header.jsp
        footer.jsp

      auth/
        login.jsp
        register.jsp

      admin/
        dashboard.jsp
        ...
      user/
        home.jsp
        ...
      agent/
        dashboard.jsp

5. Base de données
5.1. ORM Hibernate

La base de données PlateformeLocation est gérée par Hibernate.

Création automatique :

La propriété hibernate.hbm2ddl.auto = update génère les tables automatiquement.

Tables générées :

utilisateur

bien

categorie_bien

reservation

paiement

avis_bien

Contraintes :

PK, FK gérées par les annotations (@Id, @ManyToOne, etc.)

5.2. Configuration Hibernate
Fichier `src/main/resources/hibernate.cfg.xml` :
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/PlateformeLocation</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">pass</property>

Adapter selon votre configuration.

6. Installation et lancement
6.1. Prérequis

JDK 8+

MySQL

Tomcat 11

Eclipse 

6.2. Étapes

Importer le projet PlateformeLocation dans Eclipse

Créer la base de données vide PlateformeLocation via MySQL

Configurer HibernateUtil.java ou hibernate.cfg.xml si nécessaire

Vérifier les drivers (MySQL, Hibernate) dans WEB-INF/lib

Configurer le serveur Tomcat 11

Lancer l’application :

http://localhost:8080/PlateformeLocation/login

6.3. Comptes de test

Admin :

login : admin

mot de passe : admin

7. Points techniques importants

Filtres de sécurité :

Filtres pour protéger /user/*, /admin/* et /agent/*

Validation des réservations :

Vérification des chevauchements via les services et DAO

Gestion stricte des statuts et intégration du paiement

DAO / ORM :

Toutes les requêtes SQL sont remplacées par des appels Hibernate (HQL/Criteria)

MVC :

Les Servlets contrôlent, les JSP affichent

Sécurité :

Hachage des mots de passe avec jBCrypt

8. Améliorations possibles

Intégration d'une vraie API de paiement (ex: Stripe)

Recherche avancée de biens avec filtres complexes

Internationalisation (FR/EN)

9. Auteurs

aya.benjeddou2026@gmail.com

Projet réalisé dans le cadre du module : Développement d’applications Web avec Jakarta EE
Encadré par : Sofiane Hachicha et Nesrine Akrout
