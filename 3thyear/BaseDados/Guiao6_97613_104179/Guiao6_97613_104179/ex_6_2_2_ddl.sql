USE Modulo_Encomendas;
GO
CREATE SCHEMA Modulo_Encomendas;
GO

CREATE TABLE Modulo_Encomendas.PRODUTO(
	codigo			INT,
	nome			NVARCHAR(50),
	preco			INT,
	taxa_iva		DECIMAL(10,2),
	unid_armazem	INT,

	CONSTRAINT PK_PRODUTO PRIMARY KEY(codigo)
);

CREATE TABLE Modulo_Encomendas.TIPO_FORNECEDOR(
	codigo			INT,
	designacao		NVARCHAR(100),

	CONSTRAINT PK_TIPO_FORNECEDOR PRIMARY KEY(codigo)
);

CREATE TABLE Modulo_Encomendas.FORNECEDOR(
	NIF					INT,
	nome				NVARCHAR(30),
	FAX					INT,
	endereco			NVARCHAR(80),
	cond_pagamento		INT,
	codigo_tipo_forn	INT,

	CONSTRAINT PK_FORNECEDOR PRIMARY KEY(NIF),
	CONSTRAINT FK_FORNECEDOR FOREIGN KEY(codigo_tipo_forn) REFERENCES Modulo_Encomendas.TIPO_FORNECEDOR(codigo)
);

CREATE TABLE Modulo_Encomendas.ENCOMENDA(
	numero				INT,
	data_encomenda		DATE,
	NIF_fornecedor		INT,

	CONSTRAINT PK_ENCOMENDA PRIMARY KEY(numero),
	CONSTRAINT FK_ENCOMENDA FOREIGN KEY(NIF_fornecedor) REFERENCES Modulo_Encomendas.FORNECEDOR(NIF)
);

create table Modulo_Encomendas.Item(
	NumEnc INT NOT NULL CHECK(NumEnc>0),
	CodProd INT NOT NULL CHECK(CodProd>0),
	Unidades INT NOT NULL CHECK(Unidades>0),
	PRIMARY KEY (NumEnc,CodProd),
	FOREIGN KEY (NumEnc) REFERENCES  Modulo_Encomendas.Encomenda(Numero),
	FOREIGN KEY (CodProd) REFERENCES  Modulo_Encomendas.Produto(Codigo)
);
