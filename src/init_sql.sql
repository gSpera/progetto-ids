BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "cliente" (
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"tipo"	INTEGER NOT NULL,
	"indirizzo"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"codice_univoco_fatturazione"	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS "impiegato" (
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"puoRegistrareClienti"	INTEGER,
	"puoRegistrareImpiegati"	INTEGER
);
CREATE TABLE IF NOT EXISTS "fattorino" (
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS "prodotto" (
	"codice"	INTEGER NOT NULL UNIQUE,
	"categoria"	TEXT,
	"descrizione"	TEXT,
	"prezzoAlKg"	REAL,
	"pesoInKg"	INTEGER,
	"venduto"	INTEGER,
	"riportato"	INTEGER,
	"fatturato"	INTEGER,
	PRIMARY KEY("codice" AUTOINCREMENT)
);
INSERT INTO "cliente" VALUES ('speralogistica','passwordsicura',0,'via boh, 123','email@example.it','ABC123');
INSERT INTO "impiegato" VALUES ('giovanni','giovanni',1,0);
CREATE VIEW utenti AS
SELECT username, password, 0 AS tipo FROM cliente
UNION SELECT username, password, 1 AS tipo FROM impiegato
UNION SELECT username, password, 2 AS tipo FROM fattorino;
COMMIT;
