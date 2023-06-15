# BD: Guião 6

## Problema 6.1

### *a)* Todos os tuplos da tabela autores (authors);

```
SELECT * FROM authors;
```

### *b)* O primeiro nome, o último nome e o telefone dos autores;

```
SELECT au_lname,au_fname,phone from authors
```

### *c)* Consulta definida em b) mas ordenada pelo primeiro nome (ascendente) e depois o último nome (ascendente); 

```
SELECT au_fname, au_lname, phone FROM authors
ORDER BY au_fname, au_lname;
```

### *d)* Consulta definida em c) mas renomeando os atributos para (first_name, last_name, telephone); 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone FROM authors
ORDER BY au_fname, au_lname;
```

### *e)* Consulta definida em d) mas só os autores da Califórnia (CA) cujo último nome é diferente de ‘Ringer’; 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone FROM authors
WHERE au_lname!='Ringer' AND state='CA'
ORDER BY au_fname, au_lname;
```

### *f)* Todas as editoras (publishers) que tenham ‘Bo’ em qualquer parte do nome; 

```
SELECT * FROM publishers
WHERE pub_name LIKE '%Bo%';
```

### *g)* Nome das editoras que têm pelo menos uma publicação do tipo ‘Business’; 

```
SELECT pub_name  FROM publishers JOIN titles ON (publishers.pub_id=titles.pub_id) 
WHERE type='Business';
```

### *h)* Número total de vendas de cada editora; 

```
SELECT pub_name, SUM(ytd_sales) AS total_sales 
FROM ((sales JOIN titles ON sales.title_id=titles.title_id) JOIN publishers ON publishers.pub_id=titles.pub_id)
GROUP BY pub_name;
```

### *i)* Número total de vendas de cada editora agrupado por título; 

```
SELECT pub_name,title,ytd_sales
FROM (publishers JOIN titles ON publishers.pub_id=titles.pub_id);
```

### *j)* Nome dos títulos vendidos pela loja ‘Bookbeat’; 

```
SELECT title
FROM ((titles FULL JOIN sales ON titles.title_id=sales.title_id) FULL JOIN stores ON sales.stor_id=stores.stor_id)
WHERE stor_name LIKE 'Bookbeat'
```

### *k)* Nome de autores que tenham publicações de tipos diferentes; 

```
SELECT au_fname, au_lname, COUNT (DISTINCT type) AS type_books
FROM((authors JOIN  titleauthor ON authors.au_id=titleauthor.au_id  ) 
JOIN titles ON titles.title_id=titleauthor.title_id)
GROUP BY au_fname, au_lname
HAVING COUNT(DISTINCT type) > 1;
```

### *l)* Para os títulos, obter o preço médio e o número total de vendas agrupado por tipo (type) e editora (pub_id);

```
SELECT titles.pub_id, type, COUNT(ytd_sales) AS sales_amount, AVG(price) AS average_price
FROM publishers JOIN titles ON titles.pub_id=publishers.pub_id
GROUP BY titles.pub_id, type
```

### *m)* Obter o(s) tipo(s) de título(s) para o(s) qual(is) o máximo de dinheiro “à cabeça” (advance) é uma vez e meia superior à média do grupo (tipo);

```
SELECT type, avg(advance) as avg_advance
FROM titles
GROUP BY TYPE 
HAVING avg(advance)*1.5<MAX(advance)
```

### *n)* Obter, para cada título, nome dos autores e valor arrecadado por estes com a sua venda;

```
SELECT title, au_fname, au_lname, ytd_sales*royalty*price/100 as value
FROM authors JOIN titleauthor ON authors.au_id = titleauthor.au_id JOIN titles ON titles.title_id = titleauthor.title_id JOIN sales ON titles.title_id = sales.title_id
GROUP BY title, au_fname, au_lname, ytd_sales, royalty, price
ORDER BY title, au_fname, au_lname;

```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
SELECT	distinct titles.title,
		titles.ytd_sales, 
		titles.ytd_sales * titles.price AS faturacao,
		titles.price * titles.royalty / 100 * titles.ytd_sales  AS auths_revenue,
		titles.price * (100-titles.royalty) / 100 * titles.ytd_sales AS publisher_revenue
FROM	titles
WHERE	titles.ytd_sales IS NOT NULL
		AND titles.price IS NOT NULL
		AND titles.royalty IS NOT NULL
ORDER BY	titles.title
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
SELECT   titles.title,
		CONCAT(au_fname,' ', au_lname),
		titles.ytd_sales, 
		titles.price * titles.royalty / 100 * titles.ytd_sales  AS auths_revenue,
		titles.price * (100-titles.royalty) / 100 * titles.ytd_sales AS publisher_revenue
FROM	titles
JOIN titleauthor ON titleauthor.title_id=titles.title_id
JOIN authors ON authors.au_id=titleauthor.au_id
WHERE	titles.ytd_sales IS NOT NULL
		AND titles.price IS NOT NULL
		AND titles.royalty IS NOT NULL
ORDER BY	titles.title,price, au_fname, au_lname, ytd_sales, royalty
```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
SELECT stor_name, COUNT(DISTINCT title) as different_exemplar
FROM stores
JOIN sales ON stores.stor_id = sales.stor_id JOIN titles ON sales.title_id = titles.title_id
GROUP BY stores.stor_name
HAVING COUNT(title)=(SELECT COUNT(title_id) FROM titles);
```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
SELECT stor_name
FROM stores JOIN sales 
ON sales.stor_id=stores.stor_id
GROUP by stor_name 
HAVING sum(qty) >( SELECT SUM(sales.qty)/COUNT(stor_id) FROM sales)
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
SELECT title from titles 
EXCEPT
SELECT DISTINCT title FROM titles JOIN sales on titles.title_id=sales.title_id
JOIN stores on sales.title_id=stores.stor_id
WHERE stores.stor_name='Bookbeat' 

```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
(SELECT pub_name, stor_name FROM stores, publishers)
EXCEPT
(SELECT pub_name, stor_name FROM publishers 
JOIN (SELECT pub_id AS ppid, sales.stor_id, stor_name FROM titles 
JOIN sales ON titles.title_id=sales.title_id
JOIN stores ON sales.stor_id=stores.stor_id) AS T ON pub_id=ppid);
```

## Problema 6.2

### ​5.1

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_1_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_1_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT Pname, Ssn, Fname, Lname
FROM Company.Project
JOIN Company.Works_on ON Pno=Pnumber
JOIN Company.Employee ON Essn=Ssn;
```

##### *b)* 

```
SELECT Fname,Lname FROM Company.Employee
WHERE Super_ssn=(SELECT Ssn FROM Company.Employee 
WHERE  Fname='Carlos' AND Minit='D' AND Lname='Gomes')

```

##### *c)* 

```
SELECT Pname, sum([Hours]) AS Total_Hours
FROM( COMPANY.Project join Company.Works_on on Pnumber=Pno)
GROUP BY Pname;
```

##### *d)* 

```
SELECT DISTINCT Fname, Lname From Company.Employee
JOIN Company.Department ON Dno=Dnumber 
JOIN Company.Project ON Dnum=Dnumber
JOIN Company.Works_on ON Pno=Pnumber
WHERE [Hours]>20 and Dno=3 and Pname='Aveiro Digital'

```

##### *e)* 

```
SELECT Fname, Minit, Lname FROM Company.Employee
LEFT JOIN Company.Works_on ON Essn=Ssn
WHERE Pno IS NULL
```

##### *f)* 

```
SELECT Dname, AVG(Salary) AS average_salary From Company.Department
JOIN COMPANY.Employee ON Dno=Dnumber
WHERE Sex='F'
GROUP BY Dname;
```

##### *g)* 

```
Select Fname, Minit, Lname, Count(Dependent_name) As NumbersDependents
From Company.Employee 
JOIN [Company.Dependent] ON Ssn=Essn
Group By Fname, Minit,Lname
HAVING COUNT(Dependent_name) > 2;
```

##### *h)* 

```
SELECT Fname,Minit,Lname
FROM COMPANY.Employee 
JOIN COMPANY.Department ON Ssn=Mgr_ssn
Full Join [COMPANY.Dependent] ON Ssn=Essn
WHERE Essn IS NULL

```

##### *i)* 

```
SELECT DISTINCT Fname, Minit, Lname, [Address]
FROM((( COMPANY.Works_on JOIN COMPANY.Employee ON Ssn=Essn) JOIN COMPANY.Project ON Pno=Pnumber) JOIN 
COMPANY.Dept_locations ON Dno=Dnumber)
WHERE Plocation='Aveiro' AND Dlocation!='Aveiro'
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT NIF, nome, FAX, endereco, cond_pagamento, codigo_tipo_forn
FROM( Modulo_Encomendas.FORNECEDOR FULL JOIN Modulo_Encomendas.ENCOMENDA ON NIF=NIF_fornecedor)
WHERE numero IS null
```

##### *b)* 

```
SELECT CodProd, avg(unidades) AS avg_unidades
FROM Modulo_Encomendas.ITEM
GROUP BY CodProd;
```


##### *c)* 

```
SELECT CodProd, avg(Unidades) AS NumUnidades
FROM Modulo_Encomendas.ITEM
GROUP BY CodProd

```


##### *d)* 

```
SELECT Encomenda.NIF_fornecedor, Item.CodProd AS Codigo_Produto, Item.Unidades
FROM Modulo_Encomendas.ENCOMENDA AS Encomenda
JOIN Modulo_Encomendas.ITEM AS Item ON Encomenda.numero = Item.NumEnc
GROUP BY Encomenda.NIF_fornecedor, Item.CodProd, Item.Unidades
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT NOME, PACIENTE.NUMERO_UTENTE FROM PRESCRICAO_MEDICAMENTOS.PRESCRICAO 
FULL OUTER JOIN PRESCRICAO_MEDICAMENTOS.PACIENTE ON PRESCRICAO.NUM_UTENTE_PACIENTE=PACIENTE.NUMERO_UTENTE
WHERE NUMERO IS NULL
```

##### *b)* 

```
SELECT ESPECIALIDADE, COUNT(NUMERO) AS NUM_PRESC_TOTAL FROM PRESCRICAO_MEDICAMENTOS.PRESCRICAO
INNER JOIN PRESCRICAO_MEDICAMENTOS.MEDICO ON ID_MEDICO=NUMERO_IDENTIFICACAO
GROUP BY ESPECIALIDADE
```


##### *c)* 

```
SELECT NOME_FARMACIA, COUNT(NUMERO) AS NUMERO_PRESCRICAO_TOTAL FROM PRESCRICAO_MEDICAMENTOS.PRESCRICAO
INNER JOIN PRESCRICAO_MEDICAMENTOS.FARMACIA ON NOME_FARMACIA=NOME
GROUP BY NOME_FARMACIA
```


##### *d)* 

```
SELECT FARMACO.NOME_COMERCIAL FROM PRESCRICAO_MEDICAMENTOS.FARMACO
INNER JOIN PRESCRICAO_MEDICAMENTOS.FARMACEUTICA ON NUMERO_REGISTO_NACIONAL_FARMACEUTICA=NUMERO_REGISTO_NACIONAL
WHERE NUMERO_REGISTO_NACIONAL_FARMACEUTICA=906
EXCEPT(SELECT NOME_FARMACO FROM PRESCRICAO_MEDICAMENTOS.PRESC_FARMACO WHERE NUM_REGISTO_FARM=906)

```

##### *e)* 

```
SELECT NOME_FARMACIA, NOME, QUANTIDADE_FARMACOS FROM PRESCRICAO_MEDICAMENTOS.FARMACEUTICA
INNER JOIN  (SELECT NOME_FARMACIA, NUM_REGISTO_FARM, COUNT(NUM_REGISTO_FARM) AS QUANTIDADE_FARMACOS FROM
			(SELECT NOME_FARMACIA, NUM_REGISTO_FARM FROM PRESCRICAO_MEDICAMENTOS.PRESC_FARMACO
				INNER JOIN PRESCRICAO_MEDICAMENTOS.PRESCRICAO ON PRESC_FARMACO.NUM_PRESCRICAO=PRESCRICAO.NUMERO
				WHERE NOME_FARMACIA IS NOT NULL) AS FARMACO_VENDIDO
			GROUP BY NOME_FARMACIA, NUM_REGISTO_FARM
			)AS TMP_TABELA 
ON NUM_REGISTO_FARM=NUMERO_REGISTO_NACIONAL
```

##### *f)* 

```
SELECT NOME FROM PRESCRICAO_MEDICAMENTOS.PACIENTE
INNER JOIN	(SELECT NUM_UTENTE_PACIENTE, COUNT(ID_MEDICO) AS MEDICOS_DIFERENTES FROM 
			(SELECT NUM_UTENTE_PACIENTE, ID_MEDICO FROM PRESCRICAO_MEDICAMENTOS.PRESCRICAO) AS TMP
				GROUP BY NUM_UTENTE_PACIENTE
			HAVING COUNT(ID_MEDICO)>1
			)AS VARIOS_MEDICOS
ON PACIENTE.NUMERO_UTENTE = VARIOS_MEDICOS.NUM_UTENTE_PACIENTE
```
