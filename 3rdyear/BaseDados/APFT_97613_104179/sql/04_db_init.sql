use p2g6
go



-- tabela de utilizadores

--(ID, Email, Palavra-Passe, NIF do utilizador)
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('raquelseica@ua.pt','livroearte','000000000'); --Vendedor
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('marta.alex@ua.pt','1','111111111'); --Vendedor
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('eduardoalves@ua.pt','1234','222222222'); --Comprador
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('filipaoliveira@ua.pt','melita','333333333'); --Comprador
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('andre.tomas21@ua.pt','vinte21','444444444'); --Vendedor
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('marianabarbara@ua.pt','sofista','555555555'); --Vendedor
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('kikonovo@ua.pt','olhosazuis','666666666'); --Comprador
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('tiaguito@ua.pt','vivaaosporting','777777777'); --Comprador
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('brunosilva10@ua.pt','jajao1977','888888888'); --Comprador
INSERT INTO ONLINEGAMES_PLATFORM.UTILIZADOR values ('rodrigorock94@ua.pt','bowlingod37','999999999'); --Vendedor




--tabela de compradores
--(Morada do Comprador, NIF do Comprador)
INSERT INTO ONLINEGAMES_PLATFORM.Comprador values ('Figueira','222222222'); --Eduardo
INSERT INTO ONLINEGAMES_PLATFORM.Comprador values ('Oliveira do Bairro','333333333'); --Filipa
INSERT INTO ONLINEGAMES_PLATFORM.Comprador values ('Aveiro','666666666'); --Francisco
INSERT INTO ONLINEGAMES_PLATFORM.Comprador values ('Cantanhede','777777777'); --Tiago
INSERT INTO ONLINEGAMES_PLATFORM.Comprador values ('Viseu','888888888'); --Bruno




--tabela de vendedores
--(NR de artigos vendidos, IBAN do vendedor , NIF do vendedor)
INSERT INTO ONLINEGAMES_PLATFORM.Vendedor values (0,'PT500000','000000000'); --Raquel
INSERT INTO ONLINEGAMES_PLATFORM.Vendedor values (0,'PT501111','111111111'); --Marta
INSERT INTO ONLINEGAMES_PLATFORM.Vendedor values (0,'PT504444','444444444'); --Andr�
INSERT INTO ONLINEGAMES_PLATFORM.Vendedor values(0,'PT505555','555555555');  --Mariana
INSERT INTO ONLINEGAMES_PLATFORM.Vendedor values(0,'PT509999','999999999');  --Rodrigo





-- Tabela de generos
--(Nome do G�nero)
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Terror'); --Outlast, Alien Isolation, Ghost Watchers, Dead By Daylight
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Sports'); --FIFA 20.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Romance'); --Doki Doki Literature Club!
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Action'); -- GTA 5; God of War, Hades, The Last of Us.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Anime'); -- DragonBall Z: Budokai 2
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Fantasy'); -- Legend of Zelda, Pokemon Scarlet, Final Fantasy X.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('RPG');  --Persona 3 Portable, Elden Ring; Fire Emblem:Three Houses.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Strategy') -- Age of Empires.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('FPS') -- Counter-Strike.
INSERT INTO ONLINEGAMES_PLATFORM.GENERO values ('Rhythm'); --Just Dance e Guitar Hero



--Tabela de generos preferidos dos compradores
--(NIF do comprador, Nome do G�nero Favorito do Comprador )
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('222222222','Anime'); --Eduardo
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('222222222','Fantasy'); --Eduardo
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('333333333','RPG'); --Filipa
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('333333333','Romance'); --Filipa
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('666666666','Terror'); --Francisco
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('666666666','Rhythm'); --Francisco
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('777777777','Sports'); --Tiago
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('777777777','Strategy'); --Tiago
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('888888888','Action'); --Bruno
INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO values ('888888888','FPS'); --Bruno





--tabela de anuncios
-- (ID do jogo, Titulo, Descrição do Anúncio, NIF do Vendedor)

--Vendedores: 000000000 ->Raquel, 111111111 -> Marta, 444444444 -> André, 555555555 -> Mariana, 999999999 -> Rodrigo.
--              Raquel vende 4       Marta vende 5        André vende 3       Mariana vende 3       Rodrigo vende 6. 

INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (1,'The Last of Us - PS4','Sealed and New; Price: 10 euros','111111111'); --Marta vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (2,'Just Dance - Wii','Semi-New; Price: 5 euros','111111111'); --Marta vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (3,'Outlast - PC','Sealed and New; Price: 20 euros','111111111'); --Marta vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (13,'Alien Isolation - PS4','Semi-New; Price: 20 euros','111111111'); --Marta vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (14,'Ghost Watchers - PC','Sealed and New; Price: 12 euros','111111111'); --Marta vende 
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (4,'Guitar Hero - PS3','Semi-New; Price: 10 euros','555555555'); -- Mariana vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (9,'Elden Ring - PC','Semi-New; Price: 45 euros','555555555'); --Mariana vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (17,'GTA 5 - PS3','Sealed and New; Price: 25 euros','555555555'); --Mariana vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (5,'Persona 3 Portable - PSP','Sealed and New; Price: 60 euros','999999999'); --Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (7,'Counter-Strike - PC','Semi-New; Price: 6 euros','999999999'); --Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (21,'Age of Empires - PC','Semi-New; Price: 4 euros','999999999'); --Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (19,'DragonBall Z: Budokai 2 - PS2','Sealed and New; Price: 10 euros','999999999'); --Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (18,'Hades - PC','Semi-New; Price: 25 euros','999999999'); --Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (8,'Fire Emblem:Three Houses - Switch','Sealed and New; Price: 50 euros','999999999');--Rodrigo vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (6,'Legend of Zelda - Switch','Sealed and New; Price: 60 euros','444444444'); --André vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (10,'FIFA 20 - PS4','Semi-New; Price: 5 euros','444444444'); --André vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (11,'God of War - PS2','Semi-New; Price: 10 euros','444444444'); --André vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (12,'Pokemon Scarlet - Switch','Sealed and New; Price: 40 euros','000000000'); -- Raquel vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (15,'Dead By Daylight - PC','Semi-New; Price: 10 euros','000000000'); --Raquel vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (16,'Doki Doki Literature Club! - PC','Semi-New; Price: 2 euros','000000000'); --Raquel vende
INSERT INTO ONLINEGAMES_PLATFORM.ANUNCIOS values (20,'Final Fantasy X - PS2','Sealed and New; Price: 10 euros','000000000'); -- Raquel vende



--plataformas dos jogos
--(Nome da Plataforma, Descrição da Plataforma)
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('Ps4','Playstation 4');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('Nintendo Wii','');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('pc','Computer');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('ps3','Playstation 3');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('ps2','Playstation 2');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('Switch','Nintendo Switch');
INSERT INTO ONLINEGAMES_PLATFORM.PLATAFORMA values('PSP','Playstation Portable');

--Jogo
-- (Número de jogos disponiveis em stock, Nome , Preço , Descrição do jogo, ID do Anúncio, Nome da Plataforma)
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (4,'The Last of Us - PS4',10,'The Last of Us is a critically acclaimed action-adventure game set in a post-apocalyptic world devastated by a fungal infection. Players follow the journey of Joel, a hardened survivor, and Ellie, a young girl with a mysterious immunity to the infection, as they traverse dangerous landscapes and confront hostile human and infected enemies.',1,'Ps4'); 
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Just Dance - Wii',5,'Just Dance is a popular rhythm-based dancing game that invites players to get up and move to the beat of various catchy songs. Using motion-tracking technology, players follow on-screen dance moves and attempt to mimic them as accurately as possible.',2,'Nintendo Wii');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (3,'Outlast - PC',20,'Outlast is a critically acclaimed first-person survival horror game that puts players in the shoes of investigative journalist Miles Upshur. Armed only with a camcorder, players must explore the eerie and foreboding Mount Massive Asylum, which is home to unimaginable horrors and a dark secret.',3,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (2,'Guitar Hero - PS3',10,'Guitar Hero is a popular rhythm-based music game that allows players to simulate playing a guitar by using a specialized controller shaped like a guitar.',4,'ps3');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Persona 3 Portable - PSP',60,'Persona 3 Portable is a role-playing video game that combines elements of social simulation and dungeon exploration. Players assume the role of a high school student who discovers the ability to summon powerful Personas, manifestations of their inner psyche.',5,'PSP');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (3,'Alien Isolation - PS4',20,'Alien: Isolation is a critically acclaimed survival horror game that plunges players into a terrifying and suspenseful experience set in the iconic Alien universe.',6,'Ps4');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (2,'Ghost Watchers - PC',12,'Ghost Watchers is a co-op online horror game. You will explore and hunt ghosts in abandoned houses.',7,'Ps4');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Elden Ring - PC',45,'Elden Ring promises a dark and immersive fantasy experience. Players will embark on an epic journey, exploring diverse landscapes, engaging in intense combat, and unraveling a deep and mysterious storyline.',8,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (3,'GTA 5 - PS3',25,'GTA 5 is an open-world action-adventure game that immerses players in a sprawling fictional city known as Los Santos. Set in a satirical rendition of modern-day Southern California, players navigate a diverse cast of characters and engage in a variety of thrilling missions and activities.',9,'Ps3');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (7,'Counter-Strike - PC',6,'Counter-Strike is an iconic first-person shooter game that has become a cornerstone of the competitive gaming scene. Players are divided into two teams, terrorists and counter-terrorists, and engage in intense, fast-paced battles across various maps.',10,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (2,'Age of Empires - PC',4,'Age of Empires is a renowned real-time strategy game franchise that takes players on a journey through various historical periods. From ancient civilizations to medieval kingdoms and beyond, players can build and manage their own empire, gather resources, train armies, and engage in epic battles.',11,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'DragonBall Z: Budokai 2 - PS2',10,'DragonBall Z: Budokai 2 is a fighting video game that brings the epic battles and iconic characters of the popular DragonBall Z anime series to life.',12,'ps2');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (2,'Hades - PC',25,'Hades is an action-packed rogue-like game that takes players on a thrilling journey through the realm of Greek mythology. As Zagreus, the son of Hades, players must battle their way through hordes of mythical creatures to escape the Underworld and reach the surface.',13,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Fire Emblem:Three Houses - Switch',50,'Fire Emblem: Three Houses" is a tactical role-playing game set in a world of political intrigue and warfare. Players take on the role of a professor at the prestigious Officers Academy, where they must choose one of three noble houses and guide a group of students in their education and battles.',14,'Switch');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (5,'Legend of Zelda - Switch',60,'The Legend of Zelda is a beloved video game franchise known for its captivating blend of action, adventure, and puzzle-solving. Players assume the role of a courageous hero named Link, who embarks on epic quests to rescue Princess Zelda and defeat the malevolent Ganon.',15,'Switch');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (8,'FIFA 20 - PS4',5,'FIFA 20 is a popular sports video game that simulates the exhilarating world of professional soccer. Offering a realistic and immersive experience, players can take control of their favorite teams and compete in various modes, including career mode, Ultimate Team, and online multiplayer matches.',16,'Ps4');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (2,'God of War - PS2',10,'God of War is an action-adventure video game franchise that follows the journey of Kratos, a Spartan warrior turned god, as he battles through mythological realms, confronts powerful deities, and seeks redemption while navigating a deeply immersive and visually stunning world inspired by various mythologies.',17,'ps2');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (5,'Pokemon Scarlet - Switch',40,'Capture, battle, and train Pokemon in the region of Paldea, a vast area filled with lakes, towering peaks, deserts, villages, and expanding cities.',18,'Switch');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (5,'Dead By Daylight - PC',10,'Dead by Daylight is a thrilling asymmetrical multiplayer horror game where players can take on the role of either a Survivor or a ruthless Killer. The Survivors must work together to repair generators and escape the nightmarish realm, while the Killer hunts them down using unique abilities and strategies.',19,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Doki Doki Literature Club! - PC',2,'Doki Doki.',20,'pc');
INSERT INTO ONLINEGAMES_PLATFORM.JOGO values (1,'Final Fantasy X - PS2',10,'Final Fantasy X is a captivating role-playing game set in the fantasy world of Spira. Players embark on a grand adventure alongside the main protagonist, Tidus, as he seeks to save the world from the destructive entity known as Sin.',21,'ps2');


--Avaliação jogo
--(NIF do comprador, Nome do jogo, Avaliação, ID do anúncio)
--222222222 -> Eduardo; 333333333->Filipa; 666666666 -> Francisco; 777777777 -> Tiago; 888888888->Bruno.

-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','The Last of Us - PS4',4,1); --Eduardo avalia jogo vendido por Marta(111111111).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('333333333','Just Dancze - Wii',3,2); --Filipa avalia jogo vendido por Marta(111111111).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','Outlast - PC',3,3); --Eduardo avalia jogo vendido por Marta(111111111).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('666666666','Guitar Hero - PS3',4,4); --Francisco avalia jogo vendido por Mariana(555555555).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','Persona 3 Portable - PSP',5,5); --Eduardo avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('333333333','Alien Isolation - PS4',4,6); --Filipa avalia jogo vendido por Marta(111111111).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('777777777','Ghost Watchers - PC',3,7); --Tiago avalia jogo vendido por Marta(111111111).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('333333333','Elden Ring - PC',5,8); --Filipa avalia jogo vendido por Mariana(555555555).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('666666666','GTA 5 - PS3',3,9); --Francisco avalia jogo vendido por Mariana(555555555).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('888888888','Counter-Strike - PC',5,10);--Bruno avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('666666666','Age of Empires - PC',2,11);--Francisco avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('777777777','DragonBall Z: Budokai 2 - PS2',3,12); --Tiago avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('888888888','Hades - PC',4,13); --Bruno avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('333333333','Fire Emblem:Three Houses - Switch',5,14); --Filipa avalia jogo vendido por Rodrigo(999999999).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','Legend of Zelda - Switch',4,15); --Eduardo avalia jogo vendido por Andr�(444444444).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('888888888','FIFA 20 - PS4',2,16); --Bruno avalia jogo vendido por Andr�(444444444).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('888888888','God of War - PS2',5,17); --Bruno avalia jogo vendido por Andr�(444444444).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('666666666','Pokemon Scarlet - Switch',3,18); --Francisco avalia jogo vendido por Raquel(000000000).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','Dead By Daylight - PC',3,19); --Eduardo avalia jogo vendido por Raquel(000000000).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('777777777','Doki Doki Literature Club!',1,20); --Tiago avalia jogo vendido por Raquel(000000000).
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_JOGO values ('222222222','Final Fantasy X - PS2',5,21); --Eduardo avalia jogo vendido por Raquel(000000000).



--Avaliação vendedor
--(NIF Comprador, NIF Vendedor, Avaliação Vendedor, Data de Avaliação)
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_VENDEDOR values('222222222','111111111',4,'2023-04-25'); -- 
-- INSERT INTO ONLINEGAMES_PLATFORM.AVALIACAO_VENDEDOR values ('222222222','111111111',2,'2023-04-22');



--Compra
--(NIF do Comprador, Nome do Jogo, ID do Anúncio, NR de Artigos)
-- INSERT INTO ONLINEGAMES_PLATFORM.COMPRA values('222222222','The Last of Us - PS4',1,1);
-- INSERT INTO ONLINEGAMES_PLATFORM.COMPRA values ('222222222','Outlast - PC',3,1);


--Tem
--(Nome do Género, Nome do Jogo, ID do Anúncio)
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Action','The Last of Us - PS4',1);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Rhythm','Just Dance - Wii',2);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Terror','Outlast - PC',3);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Rhythm','Guitar Hero - PS3',4);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('RPG','Persona 3 Portable - PSP',5);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Terror','Alien Isolation - PS4',6);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Terror','Ghost Watchers - PC',7);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('RPG','Elden Ring - PC',8);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Action','GTA 5 - PS3',9);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('FPS','Counter-Strike - PC',10);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Strategy','Age of Empires - PC',11);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Anime','DragonBall Z: Budokai 2 - PS2',12);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Action','Hades - PC',13);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('RPG','Fire Emblem:Three Houses - Switch',14);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Fantasy','Legend of Zelda - Switch',15);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Sports','FIFA 20 - PS4',16);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Action','God of War - PS2',17);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Fantasy','Pokemon Scarlet - Switch',18);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Terror','Dead By Daylight - PC',19);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Romance','Doki Doki Literature Club! - PC',20);
INSERT INTO ONLINEGAMES_PLATFORM.TEM values ('Fantasy','Final Fantasy X - PS2',21);


