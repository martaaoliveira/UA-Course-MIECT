# BD: Guião 7


## ​7.2 
 
### *a)*

```
1Fn pois a 1FN não permite nested relations (relações dentro de relações)
```

### *b)* 

```
Para a 2FN, existe dependências entre autor e livro. Então separa-se numa nova tabela os atributos (Nome_autor, Afiliação_Autor).

Para colocar na 3FN é preciso eliminar as dependências parciais. Cria se 2 novas tabelas:
(Editor, Endereço_Editor) e (Tipo_Livro, NoPaginas, Preço).

Portanto:

1FN -> Livro (Titulo_Livro, Nome_Autor, Afiliacao_Autor, Tipo_Livro, Preço, NoPaginas, Editor, Endereco_Editor, Ano_Publicacao)

2FN ->  Livro (Titulo_Livro, Nome_Autor, Tipo_Livro, Preco, NoPaginas, Editor, Endereco_Editor, Ano_Publicacao)
	  Autor (Nome_Autor, Afiliacao_Autor)

3FN -> Livro (Titulo_Livro, Nome_Autor, Tipo_Livro, NoPaginas, Editor, Ano_Publicacao)
	 Autor (Nome_Autor, Afiliacao_Autor)
	 Preco_Livro (Tipo_Livro, NoPaginas, Preço)
	 Editor (Editor, Endereco_Editor)

```




## ​7.3
 
### *a)*

```
{A, B}
```


### *b)* 

```
R1={A,B,C}
R2={A,D,E,I,J}
R3={B,F,G,H}
```


### *c)* 

```
R1={A,B,C}
R2={A,D,E}
R3={B,F}
R4={F,G,H}
R5={D,I,J}
```


## ​7.4
 
### *a)*

```
{A,B}
```


### *b)* 

```
R1(A,B,C,D)
R2(D,E)
```


### *c)* 

```
R1(A,B,C,D)
R2(D,E)
R3(A,C)
```



## ​7.5
 
### *a)*

```
{A,B}
```

### *b)* 

```
R1(A,B,C,D,E)
R2(A,C,D)
```


### *c)* 

```
R1(A,B,D,E)
R2(A,C)
R3(C,D)
```

### *d)* 

```
R1(A,B,D,E)
R2(A,C)
R3(C,D)
```
