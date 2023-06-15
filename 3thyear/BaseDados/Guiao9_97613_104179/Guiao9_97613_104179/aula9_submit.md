# BD: Guião 9

## ​9.1

### _a)_

```
GO
CREATE PROCEDURE remove_employee
	@ssn as INT
AS
BEGIN
	DELETE FROM dependents WHERE essn = @ssn;
	DELETE FROM works_on WHERE essn = @ssn;
	UPDATE department set Mgr_ssn = NULL where Mgr_ssn = @ssn;
	UPDATE employee set Super_ssn = NULL where Super_ssn = @ssn;
	DELETE FROM employee WHERE ssn = @ssn;
END
GO

```

### _b)_

```
GO
CREATE PROCEDURE dbo.p_DepartmentManagers
    @OldestManagerSSN INT OUTPUT,
    @YearsAsManager INT OUTPUT
AS
BEGIN
    SELECT *
    FROM dbo.DEPARTMENT AS Dep
    JOIN dbo.EMPLOYEE AS Emp
    ON Dep.Mgr_ssn = Emp.Ssn;

    SELECT TOP 1
        @OldestManagerSSN = Emp.Ssn,
        @YearsAsManager = DATEDIFF(YEAR, Dep.Mgr_start_date, GETDATE())
    FROM dbo.DEPARTMENT AS Dep
    JOIN dbo.EMPLOYEE AS Emp
    ON Dep.Mgr_ssn = Emp.Ssn
    ORDER BY Dep.Mgr_start_date ASC;
END;
GO

```

### _c)_

```
GO
CREATE TRIGGER dpt_manager_ ON department
INSTEAD OF INSERT, UPDATE
AS
BEGIN

	DECLARE @Mgr_ssn AS INT;
	SELECT @Mgr_ssn = Mgr_ssn FROM inserted;

	DECLARE @Count INT;
	SELECT @Count = COUNT(*) FROM department WHERE Mgr_ssn = @Mgr_ssn;

	IF (@Count >= 1)
	BEGIN
		RAISERROR('Employee already manages one department', 16, 1);
		ROLLBACK TRANSACTION;
	END
	ELSE
	BEGIN
		INSERT INTO department SELECT * FROM inserted;
		PRINT 'Inserted with success';
	END
END
GO
```

### _d)_

```
CREATE TRIGGER salary
ON dbo.EMPLOYEE
AFTER INSERT, UPDATE
AS 
BEGIN
	DECLARE @ManagerSalary		AS INT;

	SELECT @ManagerSalary = e.Salary
	FROM inserted i 
	JOIN DEPARTMENT d ON i.Dno = d.Dnumber
	JOIN EMPLOYEE e ON d.Mgr_ssn = e.Ssn;

	UPDATE EMPLOYEE
	SET Salary = @ManagerSalary
	WHERE Salary > @ManagerSalary AND Ssn IN (SELECT Ssn FROM inserted);
END
GO

```

### _e)_

```
GO
CREATE FUNCTION get_locations_name (@ssn CHAR(9)) 
RETURNS TABLE
AS
RETURN 
(
	SELECT p.Pname, p.Plocation
	FROM works_on w
	INNER JOIN project p ON w.Pno = p.Pnumber
	WHERE w.Essn = @ssn
);
GO

```

### _f)_

```
GO

CREATE FUNCTION dbo.AboveDepartmentSalary (@DNO int) RETURNS @TABELA TABLE (SSN int)
AS
    BEGIN
        INSERT @TABELA
            SELECT EMPLOYEE.SSN
            FROM EMPLOYEE
            JOIN (SELECT DNO, AVG(SALARY) 'DEPARTMENTSALARY' FROM EMPLOYEE GROUP BY DNO)
            AS AVGDEPSALARY ON AVGDEPSALARY.DNO=EMPLOYEE.Dno
            WHERE EMPLOYEE.SALARY > AVGDEPSALARY.DEPARTMENTSALARY
        RETURN;
    END;
```

### _g)_

```
GO
CREATE FUNCTION recordSet(@departId  INT)
RETURNS @tabela TABLE (
    pname  VARCHAR(15),
    pnumber INT,
    plocation VARCHAR(15),
    dnum INT,
    budget FLOAT,
    totalbudget FLOAT
)
AS
BEGIN
    DECLARE C CURSOR
        FOR
            SELECT Pname, Pnumber, Plocation, Dnum, SUM((Salary*1.0*Hours)/40) AS Budget FROM project JOIN works_on ON Pnumber=Pno
            JOIN employee ON Essn=Ssn WHERE Dnum=@departId
            GROUP BY Pnumber, Pname, Plocation, Dnum;

    DECLARE @pname AS VARCHAR(15), @pnumber AS INT, @plocation as VARCHAR(15), @dnum AS INT, @budget AS FLOAT, @totalbudget AS FLOAT;
    SET @totalbudget = 0;
    OPEN C;
    FETCH C INTO @pname, @pnumber, @plocation, @dnum, @budget;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @totalbudget += @budget;
        INSERT INTO @tabela VALUES (@pname, @pnumber, @plocation, @dnum, @budget, @totalbudget);
        FETCH C INTO @pname, @pnumber, @plocation, @dnum, @budget;
    END
    CLOSE C;
    DEALLOCATE C;
    RETURN;
END
GO
```

### _h)_

```
GO
CREATE TRIGGER triggerRemDepAfter ON dbo.DEPARTMENT AFTER DELETE
AS
BEGIN
    DECLARE @Dname            AS VARCHAR(30);
    DECLARE @Dnumber        AS INT;
    DECLARE @Mgr_ssn        AS INT;
    DECLARE @Mgr_start_date AS DATE;

    SELECT @Dname=DELETED.Dname, @Dnumber=DELETED.Dnumber, @Mgr_ssn=DELETED.Mgr_ssn, @Mgr_start_date=DELETED.Mgr_start_date FROM DELETED;

    IF NOT (EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'DEPARTMENT_DELETED'))
        CREATE TABLE DEPARTMENT_DELETED (
        Dname                VARCHAR(30) NOT NULL,
        Dnumber                INT,
        Mgr_ssn                INT,
        Mgr_start_date        DATE,
        CONSTRAINT DEPARTMENT_DELETED_PK PRIMARY KEY (Dnumber)
    );
    INSERT INTO dbo.DEPARTMENT_DELETED SELECT * FROM DELETED;
END

--------------------------------------------- Instead of ------------------------------
GO
CREATE TRIGGER triggerRemDepInsteadOf ON dbo.DEPARTMENT INSTEAD OF DELETE
AS
BEGIN
    DECLARE @Dname            AS VARCHAR(30);
    DECLARE @Dnumber        AS INT;
    DECLARE @Mgr_ssn        AS INT;
    DECLARE @Mgr_start_date AS DATE;

    SELECT @Dname=DELETED.Dname, @Dnumber=DELETED.Dnumber, @Mgr_ssn=DELETED.Mgr_ssn, @Mgr_start_date=DELETED.Mgr_start_date FROM DELETED;
    IF NOT (EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'DEPARTMENT_DELETED'))
        CREATE TABLE DEPARTMENT_DELETED (
        Dname                VARCHAR(30) NOT NULL,
        Dnumber                INT,
        Mgr_ssn                INT,
        Mgr_start_date        DATE,
        CONSTRAINT DEPARTMENT_DELETED_PK PRIMARY KEY (Dnumber)
    );
    INSERT INTO dbo.DEPARTMENT_DELETED SELECT * FROM DELETED;
    DELETE FROM dbo.DEPARTMENT WHERE Dnumber=@Dnumber;
END

Um Trigger é uma ação que é executada em resposta a um evento que ocorre  numa tabela de uma base de dados. Os dois tipos principais de triggers em bancos de dados relacionais são os After Triggers e os Instead of Triggers.

Os after Triggers são executados após a conclusão da operação que disparou o trigger. São usados para executar ações adicionais após a conclusão da operação principal e podem ser usados para garantir que as alterações numa tabela sejam atualizadas noutras tabelas relacionais.

Algumas vantagens dos after Triggers são a facilidade de implementação e uso, não interferem na operação principal e permitem que as ações sejam executadas após a conclusão da operação principal.

Algumas desvantagens dos after Triggers são:
Não podem ser usados para substituir ou alterar a operação principal e podem aumentar a sobrecarga do banco de dados, em particular, se forem usados para atualizar informações de várias tabelas.

Os Instead of Triggers são executados em vez da operação principal, são usados para alterar a operação principal ou para exercutar outras ações em resposta ao evento e podem ser usados para validar e corrigir dados antes que a operação principal seja terminada.

Algumas vantagens dos Instead of Triggers são: Permitir que a operação principal seja alterada ou corrigida antes que seja terminada e podem ser usados para validar dados antes de serem inseridos ou atualizados na tabela.

Algumas desvantagens dos Instead of Triggers são: A possível  dificuldade acrescida de implementar e usar corretamente juntamente com o potencial de interferir na operação principal e afetar o desempenho.
```

### _i)_

```
As Stored Procedures e as UDFs são ferramentas importantes para a melhoria de desempenho e eficiência de consultas e operações num banco de dados.

As Stored Procedures são procedimentos armazenados pré-compilados e armazenados no banco de dados que podem ser chamados por outras aplicações ou consultas SQL de forma a executar uma série de operações, podem ter parâmetros de entrada e de saída e são ideais para operações mais complexas que envolvam vários procedimentos ou etapas, ou para consultas que precisam de ser executadas com mais frequência.

Um exemplo de uso de Stored Procedures é uma loja online, de forma a calcular o total de vendas de cada produto num intervalo de tempo.

As UDFs são funções definidas pelo usuário que podem ser usadas em consultas SQL, podem ter um ou mais parâmetros de entrada e um valor de retorno, podem ser usadas para operações mais simples ou para operações mais complexas onde exigem uma lógica mais personalizada. São indicadas para consultas que precisam de ser executadas repetidamente ou para simplificar código SQL.

Um bom exemplo de uso é uma empresa que usa uma UDF para calcular a distância entre dois pontos geográficos com base nas suas coordenadas de latitude e longitude.

Para concluir, as principais diferenças são que os Stores Procedures permitem retornar vários valores de output, em contraste com as UDFs que só permitem retornar um.
Stored Procedures permitem chamar outras Stored Procedures, enquanto as UDFs não.
UDFs podem ser usados como fontes de dados nas claúsulas SELECT/ WHERE/ HAVING e as UDFs para os mesmos parâmetros de entrada produzem o mesmo valor de retorno, logo são determinísticas.
```
