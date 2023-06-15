use Company
GO

CREATE SCHEMA COMPANY;
GO

CREATE TABLE COMPANY.Employee (
	Fname		varchar(20)		not null,
	Minit		char(1)					,
	Lname		varchar(20)		not null,
	Ssn			int				not null,
	Bdate		date,
	[Address]	varchar(50),
	Sex			char(1)			not null,
	Salary		decimal(6,2)	not null,
	Super_ssn	int,
	Dno			int				not null,
	
	CONSTRAINT PK_Employee PRIMARY KEY(Ssn),
	CONSTRAINT FK_Employee FOREIGN KEY(Super_ssn) REFERENCES COMPANY.Employee(Ssn) 
);

CREATE TABLE COMPANY.Department(
	Dname			varchar(20)		not null,
	Dnumber			int				not null check (Dnumber>0),
	Mgr_Ssn			int,
	Mgr_start_date	date,

	CONSTRAINT PK_Department PRIMARY KEY (Dnumber)
);

CREATE TABLE COMPANY.Dept_locations(
	Dnumber		int			not null check (Dnumber>0),
	Dlocation	varchar(20)	not null,

	CONSTRAINT PK_Dept_Locat PRIMARY KEY (Dnumber,Dlocation),
	CONSTRAINT FK_Dept_Locat FOREIGN KEY(Dnumber) REFERENCES COMPANY.Department(Dnumber)
);

CREATE TABLE COMPANY.Project(
	Pname		varchar(30)		not null,
	Pnumber		int				not null,
	Plocation	varchar(15)		not null,
	Dnum		int				not null,

	CONSTRAINT PK_Project PRIMARY KEY (Pnumber),
	CONSTRAINT FK_Project FOREIGN KEY(Dnum) REFERENCES COMPANY.Department(Dnumber)
);

CREATE TABLE COMPANY.Works_on(
	Essn	int				not null,
	Pno		int				not null,
	[Hours]	decimal(3,1)	not null,

	CONSTRAINT PK_Works_On PRIMARY KEY (Essn,Pno),
	CONSTRAINT FK_Works_On_Essn FOREIGN KEY(Essn) REFERENCES COMPANY.Employee(Ssn),
	CONSTRAINT FK_Works_On_Pno FOREIGN KEY(Pno) REFERENCES COMPANY.Project(Pnumber)
);

CREATE TABLE [COMPANY.Dependent](
	Essn			int		not null,
	Dependent_name	varchar(30)	not null,
	Sex				char(1)		not null,
	Bdate			date				,
	Relationship	varchar(20)	not null

	CONSTRAINT PK_Dependent PRIMARY KEY (Essn,Dependent_name),
	CONSTRAINT FK_Dependent FOREIGN KEY(Essn) REFERENCES COMPANY.Employee(Ssn)
);