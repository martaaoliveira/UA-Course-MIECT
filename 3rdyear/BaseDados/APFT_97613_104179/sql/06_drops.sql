use p2g6
go


drop procedure if exists ONLINEGAMES_PLATFORM.sp_SearchJogo;
drop procedure if exists  ONLINEGAMES_PLATFORM.GetJogosOrdenadosPorTopSells;
drop PROCEDURE if exists  ONLINEGAMES_PLATFORM.GetAvaliacoesVendedor;
drop PROCEDURE if exists  ONLINEGAMES_PLATFORM.GetJogosPorPlataforma
drop procedure if exists ONLINEGAMES_PLATFORM.sp_SearchJogo;
drop procedure if exists ONLINEGAMES_PLATFORM.sp_GetFavoriteGames;
drop procedure if exists ONLINEGAMES_PLATFORM.sp_AddToFavorites;
drop procedure if exists ONLINEGAMES_PLATFORM.GetJogosOrdenadosPorVendas;
drop procedure if exists ONLINEGAMES_PLATFORM.GetJogos;
drop procedure if exists ONLINEGAMES_PLATFORM.UpdateTotalVendas;
drop procedure if exists ONLINEGAMES_PLATFORM.GetJogosOrdenadosPorVendedor
drop  PROCEDURE if exists ONLINEGAMES_PLATFORM.GetJogosGeneroFavorito;
drop  PROCEDURE if exists ONLINEGAMES_PLATFORM.EliminarJogo;
drop Procedure if exists ONLINEGAMES_PLATFORM.eliminarutilizador;
drop procedure if exists ONLINEGAMES_PLATFORM.InserirAvaliacao;
drop procedure if exists ONLINEGAMES_PLATFORM.ComprarJogo
drop procedure if exists  ONLINEGAMES_PLATFORM.GetJogosInfo
drop procedure if exists ONLINEGAMES_PLATFORM.GetJogosComprados;

DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.UTILIZADOR
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.Vendedor
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.Comprador
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.GENERO
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.GENERO_PREFERIDO
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.PLATAFORMA
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.ANUNCIOS
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.JOGO
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.AVALIACAO_JOGO
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.AVALIACAO_VENDEDOR
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.FAVORITOS
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.COMPRA
DROP TABLE IF EXISTS  ONLINEGAMES_PLATFORM.TEM
GO
DROP SCHEMA IF EXISTS  ONLINEGAMES_PLATFORM
GO 