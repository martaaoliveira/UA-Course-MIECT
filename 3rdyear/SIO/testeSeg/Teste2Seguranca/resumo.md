## Segurança - Teste 2 

### Protocolos de autenticação

Definição de Autenticação: **Prova de que uma entidade possui uma identidade.**

**Tipos de prova:**

- Alguma coisa que sabemos (um segredo memorizado)
- Algo que temos 
- Características pessoais  (Biometria)

**Autenticação multi fator**: Usar simultaneamente múltiplos tipos de prova.

**Autorização -> Autenticação**

### Requisitos da Autenticação

- (Trustworthiness) Se é fidedigno, quão bom é a provar a identidade de uma entidade.
- Segredo
- Robustez 
- Simplicidade
- Lidar com vulnerabilidades introduzidas pelas pessoas.

**Entidades** - Pessoas, Hosts, Redes, Serviços/ Servidores

**Deployment model**

- Ao longo do tempo 
- Direcionado (Unidirecional, Bidirecional)

### Interações da autenticação

**Abordagem direta**

- fornece credenciais
- espera pelo veredito

Abordagem de **Desafio-Resposta**

- obtem o desafio
- fornece a resposta computed from challenge and the credentials
- espera pelo veredito

### Autenticação de pessoas

**- Abordagem direta com password conhecido **

(...)

PPP (Point-to-Point Protocol) - used for dial-up connections

- Autenticação unidirecional (Autenticador não é autenticado)

MS-CHAP

- Mutual authentication
- Passwords can be updatete

Authentication metaprotocols

- Generic authentication protocols that encapsulate other specific authentication protocols (EAP, ISAKMP) 

Serviços de autenticação

- Trusted third parties (TTP) used for authentication.
- AAA services (Authentication, Authorization and Accouting)

## PAM 