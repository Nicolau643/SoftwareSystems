DELETE FROM Aula_days
DELETE FROM AulaAtiva_Utente
DELETE FROM AulaAtiva_Sessao
DELETE FROM Inscricao_Sessao
DELETE FROM Instalacao_Modalidade
DELETE FROM Inscricao
DELETE FROM Sessao
DELETE FROM Aula
DELETE FROM AulaAtiva
DELETE FROM Instalacao
DELETE FROM Utente
DELETE FROM Modalidade

INSERT INTO Instalacao (id, maxLot, nome, typeIns) VALUES (1, 20, "Estudio 1","Estudio")
INSERT INTO Instalacao (id, maxLot, nome, typeIns) VALUES (2, 15, "Estudio 2","Estudio")
INSERT INTO Instalacao (id, maxLot, nome, typeIns) VALUES (3, 10, "Biclas","SalaDeBicicletas")
INSERT INTO Instalacao (id, maxLot, nome, typeIns) VALUES (4, 20, "Piscina 25","Piscina")

INSERT INTO Modalidade (id, custoHora, duracaoMin, nome) VALUES (1, 7, 50,"Pilates")
INSERT INTO Modalidade (id, custoHora, duracaoMin, nome) VALUES (2, 10,45 ,"Step")
INSERT INTO Modalidade (id, custoHora, duracaoMin, nome) VALUES (3, 10,50 ,"GAP")
INSERT INTO Modalidade (id, custoHora, duracaoMin, nome) VALUES (4, 10,55 ,"Indoor Cycling")
INSERT INTO Modalidade (id, custoHora, duracaoMin, nome) VALUES (5, 15,45 ,"Hidroginastica")

INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (1,1)
INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (1,2)
INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (1,3)
INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (2,1)
INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (3,4)
INSERT INTO Instalacao_Modalidade (Instalacao_id,modalidades_id) VALUES (4,5)

INSERT INTO Utente (id, nif, nome, numeroInsc) VALUES (1,223842389,"Ulisses",1)
INSERT INTO Utente (id, nif, nome, numeroInsc) VALUES (2,256039682,"David",2)
INSERT INTO Utente (id, nif, nome, numeroInsc) VALUES (3,269901841,"Teresa",3)
INSERT INTO Utente (id, nif, nome, numeroInsc) VALUES (4,197672337,"Querubim",4)
INSERT INTO Utente (id, nif, nome, numeroInsc) VALUES (5,221057552,"Cicero",5)