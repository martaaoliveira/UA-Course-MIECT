USE p2g6
GO

DROP TRIGGER IF EXISTS ONLINEGAMES_PLATFORM.Trigger_CheckStock;
GO

CREATE TRIGGER ONLINEGAMES_PLATFORM.Trigger_CheckStock
ON ONLINEGAMES_PLATFORM.COMPRA
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @stock INT;
    DECLARE @NOME_JOGO VARCHAR(100);
    DECLARE @ID_ANUNCIO INT;
    DECLARE @NIF_COMPRADOR VARCHAR(30);
    DECLARE @NR_ARTIGOS INT;
    
    SELECT @NOME_JOGO = NOME_JOGO, @ID_ANUNCIO = ID_ANUNCIO, @NIF_COMPRADOR = NIF_COMPRADOR, @NR_ARTIGOS = 1
    FROM INSERTED;

    SELECT @stock = STOCK 
    FROM ONLINEGAMES_PLATFORM.JOGO
    WHERE NOME = @NOME_JOGO AND ANUNCIO_ID = @ID_ANUNCIO;

    IF @stock >= @NR_ARTIGOS
    BEGIN
        -- Atualizar o estoque do jogo na tabela JOGO
        UPDATE ONLINEGAMES_PLATFORM.JOGO 
        SET STOCK = STOCK - 1
        WHERE NOME = @NOME_JOGO AND ANUNCIO_ID = @ID_ANUNCIO;

        -- Inserir os registros na tabela COMPRA
        INSERT INTO ONLINEGAMES_PLATFORM.COMPRA (NIF_COMPRADOR, NOME_JOGO, ID_ANUNCIO, NR_ARTIGOS)
        SELECT NIF_COMPRADOR, NOME_JOGO, ID_ANUNCIO, NR_ARTIGOS FROM INSERTED;
    END
    ELSE
    BEGIN
        RAISERROR ('There is no stock for that game!', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END
END;

go

drop trigger if exists ONLINEGAMES_PLATFORM.trg_check_duplicado_genero_pref ;
go

CREATE TRIGGER ONLINEGAMES_PLATFORM.trg_check_duplicado_genero_pref 
ON ONLINEGAMES_PLATFORM.GENERO_PREFERIDO
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Se o gênero que está inserido (já existe como um gênero favorito do mesmo NIF_Comprador) não efetuar nenhuma alteração
    -- Caso contrário, inserção.
    INSERT INTO ONLINEGAMES_PLATFORM.GENERO_PREFERIDO (NIF_Comprador, Nome_Genero)
    SELECT i.NIF_Comprador, i.Nome_Genero
    FROM inserted i
    WHERE NOT EXISTS (
        SELECT 1
        FROM ONLINEGAMES_PLATFORM.GENERO_PREFERIDO gp
        WHERE gp.NIF_Comprador = i.NIF_Comprador AND gp.Nome_Genero = i.Nome_Genero
    );
END;
GO










