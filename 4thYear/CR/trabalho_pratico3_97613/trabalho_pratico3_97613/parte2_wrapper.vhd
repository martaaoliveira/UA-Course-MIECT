----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01.03.2024 01:13:36
-- Design Name: 
-- Module Name: parte2_wrapper - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity parte2_wrapper is
    Port ( clk  : in STD_LOGIC;
           an   : out std_logic_vector (7 downto 0);-- ativar "um dos displays"
           seg  : out std_logic_vector(6 downto 0); -- "only illuminate the segments"
           dp   : out STD_LOGIC); --digital point
end parte2_wrapper;

architecture Behavioral of parte2_wrapper is
signal s_en : std_logic;
begin

PulseGenerator : entity work.PulseGenerator(Behavioral)
    port map(   reset   => '0',
                clk   => clk,
                pulse => s_en);
                
Nexys4DispDriver : entity work.Nexys4DispDriver(Behavioral)
    port map(   
        clk         => clk,        -- Clock input
        enable_clk  => s_en,       -- Clock enable signal
        en_digit    => "11111111", -- Todos os dígitos ativados
        en_dot      => "01010000", -- Ativa pontos decimais -> "29.02.2024"
        -- expor nos dipslays 29.02.2024 (data aula prática)
        d0          => "0100",     --4  2024
        d1          => "0010",     -- 2  2024
        d2          => "0000",     -- 0  2024
        d3          => "0010",     -- 2  2024
        d4          => "0010",     --2 de 02 --mês
        d5          => "0000",     --0 de 02 --mês
        d6          => "1001",     --9 de 29 -- dia
        d7          => "0010",     --2 de 29 --dia
        an_L        => an,         -- Saídas para ativar cada digito dos displays
        cat_L       => seg,        -- Saídas para ativar os segmentos dos displays
        dp_L        => dp          -- Saída para o ponto decimal
    );

                
end Behavioral;
