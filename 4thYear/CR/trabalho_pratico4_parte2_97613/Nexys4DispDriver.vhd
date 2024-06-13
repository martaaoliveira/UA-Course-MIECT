----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 29.02.2024 17:13:14
-- Design Name: 
-- Module Name: Nexys4DispDriver - Behavioral
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

use IEEE.NUMERIC_STD.ALL;

entity Nexys4DispDriver is
  Port (clk: in std_logic;
        enable_clk: in std_logic;
        EN_digit : in std_logic_vector (7 downto 0); --Vetor de 8 bits para ativação (1) ou desativação (0) de cada dígito.
        EN_dot: in std_logic_vector(7 downto 0); --vetor de 8 bits para ativação (1) ou desativação (0) de cada ponto decimal.
        --D0 a D7 representam os valores hexadecimais a serem exibidos em cada dígito dos displays de 7 segmentos.
        D0: in std_logic_vector(3 downto 0);
        D1: in std_logic_vector(3 downto 0);
        D2: in std_logic_vector(3 downto 0);
        D3: in std_logic_vector(3 downto 0);
        D4: in std_logic_vector(3 downto 0);
        D5: in std_logic_vector(3 downto 0);
        D6: in std_logic_vector(3 downto 0);
        D7: in std_logic_vector(3 downto 0);
        --an_L controla a ativação de cada dígito nos displays (active low).
        an_L: out std_logic_vector(7 downto 0);
        --cat_L Um vetor de 7 bits que controla quais segmentos estão ativos num determinado dígito do display (active low)
        cat_L: out std_logic_vector(6 downto 0);
        --Um sinal que controla o ponto decimal do dígito atualmente selecionado (active low).
        dp_L: out std_logic);
end Nexys4DispDriver;

architecture Behavioral of Nexys4DispDriver is
signal s_count : std_logic_vector(2 downto 0);
signal s_digit : std_logic_vector(3 downto 0); --sinal para o digito

begin

contador3bits : entity work.contador3bits(Behavioral)
    port map(   clk   => clk,
                enable    => enable_clk,
                count => s_count);
                
                
mux_8to1: entity work.mux_8to1(Behavioral)
    port map(   SEL => s_count,
                D0   => d0,
                D1   => d1,
                D2   => d2,
                D3   => d3,
                D4   => d4,                  
                D5   => d5,        
                D6   => d6,
                D7   => d7,
                mux_out => s_digit);
                
display_7_seg: entity work.display_7_seg(Behavioral)
    port map(   counter   => s_digit, --recebe como entrada o valor da saida MUX_OUT (guardada em s_d)
                seg_L   => cat_L);--ativa segmentos
                
               
process(s_count)
begin
    --desativar todos os dígitos do display (active low)
    an_L <= (others => '1');               
    --Se o enable_digit estiver ativo
    --então o dígito específico é ativado(low) (an_L(TO_INTEGER(unsigned(s_count))) <= '0')
    if EN_digit(TO_INTEGER(unsigned(s_count))) = '1' then
       an_L(TO_INTEGER(unsigned(s_count))) <= '0';
       -- dp_l é active low então é definido para o inverso do valor correspondente em EN_dot
       dp_L <= not(EN_dot(TO_INTEGER(unsigned(s_count))));
    end if;
end process;

end Behavioral;
