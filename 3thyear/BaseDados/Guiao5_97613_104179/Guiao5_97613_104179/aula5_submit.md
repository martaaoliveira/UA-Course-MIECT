# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
employee_list= project ⨝ Dno=Dnum employee
πPname,Ssn,Fname,Lname (employee_list)

```


### *b)* 

```
Carlos_ssn = π Mgr_ssn←Ssn (σ Fname='Carlos' ∧ Minit='D' ∧ Lname='Gomes' (employee))
employee_list=employee ⨝ Super_ssn=Mgr_ssn Carlos_ssn
π Fname,Lname (employee_list)

```


### *c)* 

```
Hour_per_project= γ Pno;sum(Hours) -> total_hours works_on

Table=project ⨝Pnumber=Pno Hour_per_project

πPname,total_hours (Table)

```


### *d)* 

```
π Fname, Lname, Pname, Dno, Hours
(σ works_on.Hours > 20
(employee ⨝ Ssn = works_on.Essn
((σ Dnum = 3 σ Pname = 'Aveiro Digital' project ) ⨝ Pnumber = Pno works_on)))

```


### *e)* 

```
π Fname, Minit, Lname (σ Pno=null (employee ⟕ Ssn=Essn works_on))

```


### *f)* 

```
Department= (department⨝Dnumber=Dno σSex='F' employee)

γ Dname;avg(Salary) ->Avg_salary Department

```


### *g)* 

```
Two_orMore = σ count>1 (γ Essn; count(Essn) -> count dependent)

π Fname,Lname,Minit (Two_orMore⨝Essn=Ssn employee)

```


### *h)* 

```
No_dependent = σEssn= null (dependent ⟖Essn=Ssn employee)
π Fname,Minit,Lname (department ⨝Mgr_ssn=Ssn No_dependent)

```


### *i)* 

```

Project_location = σDlocation!='Aveiro'∧Plocation='Aveiro' (project ⨝Dnum=Dnumber dept_location)
π Fname,Minit,Lname,Address (employee ⨝Dno=Dnum Project_location)

```


## ​Problema 5.2

### *a)*

```
π nome (σ fornecedor=null (encomenda ⟗ fornecedor=nif fornecedor))

```

### *b)* 

```
γ produto.nome; avg(item.unidades) -> unidades π item.numEnc, produto.nome, produto.codigo, item.unidades (produto ⨝ codProd = codigo item)

```


### *c)* 

```
γ;avg(num_prod)->media_numero_produtos (γ numEnc;count(codProd)-> num_prod (item))

```


### *d)* 

```
tabelaTMP = γ codProd,fornecedor;sum(unidades)->qnt_total (item ⨝numEnc=numero encomenda)
π fornecedor.nome,produto.nome,qnt_total (tabelaTMP ⨝fornecedor=nif fornecedor ⨝codProd=codigo produto)

```


## ​Problema 5.3

### *a)*

```
πnome,paciente.numUtente (σ numPresc=null (prescricao⨝prescricao.numUtente = paciente.numUtente paciente))
```

### *b)* 

```
γespecialidade;count(numPresc)->total_presc (prescricao ⨝numMedico=numSNS medico)

```


### *c)* 

```
γnome;count(numPresc)->nrPres (prescricao⨝farmacia=nome farmacia)

```


### *d)* 

```
Farmaco=πfarmaco.nome (σ numReg=906 (farmaco ⨝numRegFarm=numReg farmaceutica))
Presc= πnomeFarmaco (σ numRegFarm=906 presc_farmaco)
Farmaco-Presc

```

### *e)* 

```
Farmacia= σfarmacia!=null (presc_farmaco ⨝presc_farmaco.numPresc=prescricao.numPresc prescricao)

Quantidade_farmacos= γ farmacia, numRegFarm; count(numRegFarm)->qtd_farmacos Farmacia

πfarmacia,nome,qtd_farmacos (Quantidade_farmacos ⨝numRegFarm=numReg farmaceutica)

```

### *f)* 

```

Prescricoes=(π numUtente,numMedico prescricao)

Pacientes=σmedicos_difer>1 (γ numUtente; count(numMedico)->medicos_difer Prescricoes)

πnome (paciente ⨝paciente.numUtente=prescricao.numUtente Pacientes )

```
