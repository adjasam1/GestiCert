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
(9, 2), (9, 3), (9, 5),
(10, 2), (10, 3), (10, 4),
(11, 1), (11, 2), (11, 3), (11, 4), (11, 5);

--INITIALISATION TABLE CERTIFICATE
insert into certificat (`fin_validite`, `date_emission`, `adresse_principale`, `lien_installation`, `mdp_certificat`, `id_application`, `id_environnement`, `id_plateforme`, `id_racine`, `nom_certificat`) values
('2016-12-17', '2014-12-17', 'www.bahia.rh.intra.laposte.fr', 'telechargements/gob1', 'MTcxMjIwMTQtR09CL3Byb2Q', 2, 4, 5, 1, 'GOB-20141217-prod'),
('2021-01-25', '2019-01-25', 'www.bahia-int.rh.intra.laposte.fr', 'telechargements/gob2', 'MjUwMTIwMTktR09CL2ludA', 2, 2, 3, 1, 'GOB-20190125-int'),
('2019-01-31', '2017-01-31', 'www.e-recrutement-rec.rh.intra.laposte.fr', 'telechargements/j5p1', 'MzEwMTIwMTctSjVQL3JlYw', 5, 3, 4, 4, 'J5P-20170131-rec'),
('2019-08-03', '2017-08-03', 'www.laposterecrute.fr', 'telechargements/j5p2', 'MDMwODIwMTctSjVQL3Byb2Q', 5, 4, 5, 1, 'J5P-20170803-prod'),
('2019-03-18', '2017-03-18', 'www.int.laposterecrute.fr', 'telechargements/j5p3', 'MTgwMzIwMTctSjVQL2ludA', 5, 2, 3, 2, 'J5P-20170318-int'),
('2019-03-29', '2017-03-29', 'www.e-recrutement-dev.rh.intra.laposte.fr', 'telechargements/j5p4', 'MjkwMzIwMTctSjVQL2Rldg', 5, 1, 2, 3, 'J5P-20170329-dev'),
('2020-08-06', '2018-08-06', 'www.mon-espace-ha-int.extra.laposte.fr', 'telechargements/lpm1', 'MDYwODIwMTgtTFBNL2ludA', 1, 2, 3, 4, 'LPM-20180806-int'),
('2020-08-06', '2018-08-06', 'www.mon-espace-ha.extra.laposte.fr', 'telechargements/lpm2', 'MDYwODIwMTgtTFBNL3Byb2Q', 1, 4, 5, 3, 'LPM-20180806-prod'),
('2020-04-10', '2018-04-10', 'www.intranet-rh-preprod.rh.intra.laposte.fr', 'telechargements/lr_1', 'MTAwNDIwMTgtTFJfL2Rldg', 4, 1, 2, 1, 'LR_-20180410-dev'),
('2020-04-12', '2018-04-12', 'www.netrh.extra.laposte.fr', 'telechargements/lr_2', 'MTIwNDIwMTgtTFJfL3Byb2Q', 4, 4, 5, 2, 'LR_-20180412-prod'),
('2020-04-12', '2018-04-12', 'www.netrh-int.extra.laposte.fr', 'telechargements/lr_3', 'MTIwNDIwMTgtTFJfL2Rldg', 4, 1, 2, 1, 'LR_-20180412-dev'),
('2019-07-18', '2017-07-18', 'www.elections.rh.intra.laposte.fr', 'telechargements/te_1', 'MTgwNzIwMTctVEVfL3Byb2Q', 3, 4, 5, 2, 'TE_-20170718-prod'),
('2019-07-18', '2017-07-18', 'www.elections-int.rh.intra.laposte.fr', 'telechargements/te_2', 'MTgwNzIwMTctVEVfL2Rldg', 3, 1, 2, 1, 'TE_-20170718-dev');

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