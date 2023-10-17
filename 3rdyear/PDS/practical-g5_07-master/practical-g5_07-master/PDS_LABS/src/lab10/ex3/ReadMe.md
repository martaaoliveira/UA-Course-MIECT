Tendo em conta o exercício no guião 8 de PDS, no exercício 1, que é sobre a Gestão de acesso a contas bancárias achámos interessante acrescentar uma funcionalidade onde vários bancos podiam negociar transações financeira entre si. Numa abordagem sem utilizar um mediator, cada banco precisava  manter uma referência  dos outros banco para iniciar a negociação.

Usamos o padrão do Mediator para simplificar esse processo. 
Foi criada uma classe de Mediator, 'CentralBank', que coordena as transações entre os diferentes bancos. Assim, os bancos só precisam de comunicar com o 'CentralBank'.

Utilizamos os slides das aulas e o site https://refactoring.guru/ como fontes externas para o auxílio da resolução desta questão.

