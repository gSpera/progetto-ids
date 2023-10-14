BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "prodotto" (
	"codice"	INTEGER NOT NULL UNIQUE,
	"categoria"	INTEGER,
	"descrizione"	TEXT,
	"stato"	INTEGER,
	"pesoInKg"	INTEGER,
	"pescatore"	TEXT,
	PRIMARY KEY("codice" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "utente" (
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"tipo"	INTEGER NOT NULL,
	"nome"	INTEGER,
	"tipo_cliente"	INTEGER,
	"indirizzo"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	PRIMARY KEY("username")
);
CREATE TABLE IF NOT EXISTS "ordine" (
	"codice"	INTEGER,
	"fattorino"	TEXT,
	FOREIGN KEY("fattorino") REFERENCES "utente"("username"),
	PRIMARY KEY("codice")
);
CREATE TABLE IF NOT EXISTS "ordine_prodotti" (
	"ordine"	INTEGER,
	"prodotto"	INTEGER
);
CREATE TABLE IF NOT EXISTS "categoria_prodotto" (
	"codice"	INTEGER,
	"categoria"	INTEGER,
	"tipo"	INTEGER,
	"descrizione"	INTEGER,
	"prezzo_al_kg"	REAL,
	PRIMARY KEY("codice")
);
CREATE TABLE IF NOT EXISTS "fattura" (
	"codice"	INTEGER,
	"cliente"	TEXT,
	FOREIGN KEY("cliente") REFERENCES "utente"("username"),
	PRIMARY KEY("codice")
);
CREATE TABLE IF NOT EXISTS "fattura_prodotti" (
	"fattura"	INTEGER,
	"prodotto"	INTEGER
);
COMMIT;
