
INSERT INTO INSTALACAO (ID, MAXLOT, NOME, TYPEINS) VALUES (1, 20, "Estudio 1","Estudio")
INSERT INTO INSTALACAO (ID, MAXLOT, NOME, TYPEINS) VALUES (2, 15, "Estudio 2","Estudio")
INSERT INTO INSTALACAO (ID, MAXLOT, NOME, TYPEINS) VALUES (3, 10, "Biclas","Sala de Bicicletas")
INSERT INTO INSTALACAO (ID, MAXLOT, NOME, TYPEINS) VALUES (4, 20, "Piscina 25","Piscina")

INSERT INTO MODALIDADE (ID, CUSTOHORA, DURACAOMIN, NOME) VALUES (1, 7, 50,"Pilates")
INSERT INTO MODALIDADE (ID, CUSTOHORA, DURACAOMIN, NOME) VALUES (2, 10,45 ,"Step")
INSERT INTO MODALIDADE (ID, CUSTOHORA, DURACAOMIN, NOME) VALUES (3, 10,50 ,"GAP")
INSERT INTO MODALIDADE (ID, CUSTOHORA, DURACAOMIN, NOME) VALUES (4, 10,55 ,"Indoor Cycling")
INSERT INTO MODALIDADE (ID, CUSTOHORA, DURACAOMIN, NOME) VALUES (5, 15,45 ,"Hidroginastica")

INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (1,1)
INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (1,2)
INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (1,3)
INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (2,1)
INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (3,4)
INSERT INTO INSTALACAO_MODALIDADE (Instalacao_ID,Modalidades_ID) VALUES (4,5)

INSERT INTO UTENTE (ID, NIF, NOME, NUMEROINSC) VALUES (1,223842389,"Ulisses",1)
INSERT INTO UTENTE (ID, NIF, NOME, NUMEROINSC) VALUES (2,256039682,"David",2)
INSERT INTO UTENTE (ID, NIF, NOME, NUMEROINSC) VALUES (3,269901841,"Teresa",3)
INSERT INTO UTENTE (ID, NIF, NOME, NUMEROINSC) VALUES (4,197672337,"Querubim",4)
INSERT INTO UTENTE (ID, NIF, NOME, NUMEROINSC) VALUES (5,221057552,"Cicero",5)


