--INITIALISATION TABLE PROFILE
insert into profil (`type`) values
('Développeur'),
('Chef de Projet'),
('Administrateur');

--INITIALISATION TABLE DEPARTMENT
insert into service (`nom`) values
('Dev Web'),
('SIGP3'),
('Appli HR'),
('Dev HR');

--INITIALISATION TABLE USER
insert into utilisateur (`email`, `prenom`, `idrh`, `nom`, `mdp`, `telephone`, `fonction`, `id_service`, `id_profil`) values
('alain.zanzibar@laposte.fr', 'Alain', 'paaa111', 'Zanzibar', 'aaa', '0678564321', 'développeur java', '1', '1'),
('beatrice.yemen@laposte.fr', 'Béatrice', 'pbbb222', 'Yemen', 'bbb', '0659527564', 'cheffe de projet Dev Web', '1', '2'),
('claude.xeres@laposte.fr', 'Claude', 'xccc333', 'Xéres', 'ccc', '0765098765', 'développeur php', '2', '1'),
('denise.wapiti@laposte.fr', 'Denise', 'pddd444', 'Wapiti', 'ddd', '0664598750', 'cheffe de service Dev Web', '1', '3'),
('etienne.vanuatu@laposte.fr', 'Etienne', 'xeee555', 'Vanuatu', 'eee', '0743867290', 'développeur java', '1', '1'),
('françois.uruguay@laposte.fr', 'François', 'pfff666', 'Uruguay', 'fff', '0749715064', 'développeur angular', '3', '1'),
('gustave.tuvalu@laposte.fr', 'Gustave', 'pggg777', 'Tuvalu', 'ggg', '0603528677', 'chef de projet SIGP3', '2', '3'),
('helene.suriname@laposte.fr', 'Hélène', 'xhhh999', 'Suriname', 'hhh', '0602352963', 'développeuse php symphony', '1', '1'),
('irene.rwanda@laposte.fr', 'Irène', 'piii010', 'Rwanda', 'iii', '0620954826', 'cheffe de projet Dev Web', '1', '2'),
('julie.tuvalu@laposte.fr', 'Julie', 'xjjj011', 'Tuvalu', 'jjj', '0762327490', 'développeur drupal', '1', '1'),
('françois.quatar@laposte.fr', 'François', 'pkkk012', 'Quatar', 'kkk', '0644623978', 'Chef de service SIGP3', '2', '3');

--TABLE ID_USER_ROLE
--insert into user_role_list (`user_id_utilisateur`, `role_list`) values
--(1, 'ROLE_DEV'),
--(2, 'ROLE_SERVICE'), (2, 'ROLE_DEV'),
--(3, 'ROLE_DEV'),
--(4, 'ROLE_ADMIN'), (4, 'ROLE_SERVICE'), (4, 'ROLE_DEV'),
--(5, 'ROLE_DEV'),
--(6, 'ROLE_DEV'),
--(7, 'ROLE_ADMIN'), (7, 'ROLE_SERVICE'), (7, 'ROLE_DEV'),
--(8, 'ROLE_DEV'),
--(9, 'ROLE_SERVICE'), (2, 'ROLE_DEV'),
--(10, 'ROLE_DEV'),
--(11, 'ROLE_ADMIN'), (11, 'ROLE_SERVICE'), (11, 'ROLE_DEV');

--INITIALISATION TABLE APPLICATION
insert into application (`ccx`, `email_client`, `prenom_client`, `direction_client`, `nom`, `nom_client`, `telephone_client`) values
('LPM', 'zoe.albanie@laposte.fr', 'Zoé', 'DHAG', 'Intranet_HA', 'Albanie', '0166557845'),
('GOB', 'yann.benin@laposte.fr', 'Yann', 'DSI Centrale / CSoRH / GID', 'BAHIA', 'Benin', '0162890464'),
('TE_', 'xavier.congo@laposte.fr', 'Xavier', 'DSI Centrale / CSoRH / IDaRH', 'Elections', 'Congo', '0169464959'),
('LR_', 'william.danemark@laposte.fr', 'William', 'DSI Centrale / CSoRH / GAPF', 'Net-RH', 'Danemark', '0169464762'),
('J5P', 'viviane.equateur@laposte.fr', 'Viviane', 'DSI Centrale / CSoRH / GID', 'site Carrières V2', 'Equateur', '0169464959');

--TABLE DE JOINTURE USER_APPLICATION
insert into utilisateur_application (`id_utilisateur`, `id_application`) values
(1, 3), (1, 5),
(2, 1),
(3, 4), (3, 5),
(4, 2), (4, 3), (4, 4),
(5, 1), (5, 2),
(6, 1), (6, 3), (6, 4), (6, 5),
(7, 4),
(8, 1), (8, 2), (8, 3), (8, 4), (8, 5),
(9, 2), (9, 3), (9, 5);

--INITIALISATION TABLE ENVIRONMENT
insert into environnement (`nom`) values
('Développement'),
('Intégration'),
('Recette'),
('Production');

--INITIALISATION TABLE PLATEFORM
insert into plateforme (`nom`) values
('Amont'),
('Dév/Amont'),
('Int/Amont'),
('Rec/Amont'),
('Production');

--INITIALISATION TABLE ROOT
insert into racine (`nom`) values
('DSI Centrale'),
('CertiNomis'),
('DigiCert'),
('DASU');

--INITIALISATION TABLE SERVER
insert into serveur (`nom`) values
('Apache'),
('Red Hat'),
('Microsoft'),
('iPlanet'),
('javasoft'),
('IBM'),
('Web Sphere'),
('Inet LONDRES'),
('Londres V5.1'),
('Londres V5.2');

--INITIALISATION TABLE CERTIFICATE
insert into certificat (`fin_validite`, `date_emission`, `adresse_principale`, `lien_installation`, `mdp_certificat`, `id_application`, `id_environnement`, `id_plateforme`, `id_racine`, `nom_certificat`) values
('2016-12-17', '2014-12-17', 'www.bahia.rh.intra.laposte.fr', 'telechargements/gob1', 'MDP01', 2, 4, 5, 1, 'GOB-20141217-prod'),
('2021-01-25', '2019-01-25', 'www.bahia-int.rh.intra.laposte.fr', 'telechargements/gob2', 'MDP02', 2, 2, 3, 1, 'GOB-20190125-int'),
('2019-01-31', '2017-01-31', 'www.e-recrutement-rec.rh.intra.laposte.fr', 'telechargements/j5p1', 'MDP03', 5, 3, 4, 4, 'J5P-20170131-rec'),
('2019-08-03', '2017-08-03', 'www.laposterecrute.fr', 'telechargements/j5p2', 'MDP04', 5, 4, 5, 1, 'J5P-20170803-prod'),
('2019-03-18', '2017-03-18', 'www.int.laposterecrute.fr', 'telechargements/j5p3', 'MDP05', 5, 2, 3, 2, 'J5P-20170318-int'),
('2019-03-29', '2017-03-29', 'www.e-recrutement-dev.rh.intra.laposte.fr', 'telechargements/j5p4', 'MDP06', 5, 1, 2, 3, 'J5P-20170329-dev'),
('2020-08-06', '2018-08-06', 'www.mon-espace-ha-int.extra.laposte.fr', 'telechargements/lpm1', 'MDP07', 1, 2, 3, 4, 'LPM-20180806-int'),
('2020-08-06', '2018-08-06', 'www.mon-espace-ha.extra.laposte.fr', 'telechargements/lpm2', 'MDP08', 1, 4, 5, 3, 'LPM-20180806-prod'),
('2020-04-10', '2018-04-10', 'www.intranet-rh-preprod.rh.intra.laposte.fr', 'telechargements/lr_1', 'MDP09', 4, 1, 2, 1, 'LR_-20180410-dev'),
('2020-04-12', '2018-04-12', 'www.netrh.extra.laposte.fr', 'telechargements/lr_2', 'MDP10', 4, 4, 5, 2, 'LR_-20180412-prod'),
('2020-04-12', '2018-04-12', 'www.netrh-int.extra.laposte.fr', 'telechargements/lr_3', 'MDP11', 4, 1, 2, 1, 'LR_-20180412-dev'),
('2019-07-18', '2017-07-18', 'www.elections.rh.intra.laposte.fr', 'telechargements/te_1', 'MDP12', 3, 4, 5, 2, 'TE_-20170718-prod'),
('2019-07-18', '2017-07-18', 'www.elections-int.rh.intra.laposte.fr', 'telechargements/te_2', 'MDP13', 3, 1, 2, 1, 'TE_-20170718-dev');

--TABLE DE JOINTURE CERTIFICATE_SERVER
insert into certificat_serveur (`id_certificat`, `id_serveur`) values
(1, 1), (1, 2),
(2, 8),
(3, 1), (3, 2), (3, 9),
(4, 1), (4, 2), (4, 10),
(5, 1), (5, 2), (5, 10),
(6, 1), (6, 2), (6, 9),
(7, 1), (7, 2), (7, 10),
(8, 1), (8, 2), (8, 10),
(9, 1), (9, 2), (9, 9),
(10, 1), (10, 2), (10, 9),
(11, 1), (11, 2), (11, 9),
(12, 1), (12, 2), (12, 9),
(13, 1), (13, 2), (13, 9);

--INITIALISATION TABLE ADDRESSALTERNATIVE
insert into adressealternative (`id_certificat`, `adresse_alternative`) values
(4 ,'www.e-recrutement.rh.laposte.fr'),
(5 ,'www.e-recrutement-int.rh.laposte.fr'),
(9 ,'www.intranet-rh-preprod-ext.rh.intra.laposte.fr'),
(10 ,'www.intranet-rh.rh.intra.laposte.fr'),
(11 ,'www.intranet-rh.int.rh.intra.laposte.fr'),
(12 ,'www.elections-1.rh.intra.laposte.fr'),
(12 ,'www.elections-2.rh.intra.laposte.fr'),
(12 ,'www.elections-3.rh.intra.laposte.fr'),
(12 ,'www.rh-elections.extra.laposte.fr'),
(13 ,'www.elections-1-int.rh.intra.laposte.fr'),
(13 ,'www.elections-2-int.rh.intra.laposte.fr'),
(13 ,'www.rh-elections-int.extra.laposte.fr');

--INITIALISATION TABLE TYPEDEMAND
insert into typedemande (`type`) values
('Création'),
('Renouvellement'),
('Révocation');

--INITIALISATION TABLE STATUSDEMAND
insert into statutdemande (`statut`) values
('Non'),
('Oui'),
('En cours');

--INITIALISATION TABLE DEMAND
insert into demande (`date_realisation_souhaitee`, `date_demande`, `date_transmission`, `description_contexte`, `email_referent`, `remarque_racine`, `id_application`, `id_certificat`, `id_statut_demande`, `id_type_demande`, `id_utilisateur`) values
('2019-03-15', '2019-01-24', '2019-02-27', 'Renouvellement GOB-prod', null, 'Mail renouvellement envoyé', 2, 1, 3, 2, 8),
('2019-03-02', '2019-01-16', '2019-02-15', 'Révocation J5P-int', null, 'Certificat révoqué', 5, 5, 2, 3, 1),
('2019-02-23', '2019-02-21', null, 'Création J5P-prod', 'john.moldavie@gmail.com', null, 5, 4, 3, 1, 3),
('2019-05-07', '2019-02-28', null, 'Renouvellement J5P-int', 'marie.angola@laposte.net', 'Refus car certificat révoqué, faire une demande de création', 5, 5, 1, 2, 6),
('2018-12-17', '2018-11-04', '2018-12-27', 'Création LPM-dev', null, 'Mail création envoyé', 1, 8, 2, 1, 8);
