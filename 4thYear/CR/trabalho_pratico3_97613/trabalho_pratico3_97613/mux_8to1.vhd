----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 29.02.2024 18:41:18
-- Design Name: 
-- Module Name: mux_8to1 - Behavioral
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

entity mux_8to1 is
    port (
        sel: in std_logic_vector(2 downto 0);
        D0, D1, D2, D3, D4, D5, D6, D7: in std_logic_vector(3 downto 0);
        mux_out: out std_logic_vector(3 downto 0)
    );
end mux_8to1;

architecture Behavioral of mux_8to1 is
begin
    process(sel, D0, D1, D2, D3, D4, D5, D6, D7)
    begin
        case sel is
            when "000" =>
                mux_out <= D0;
            when "001" =>
                mux_out <= D1;
            when "010" =>
                mux_out <= D2;
            when "011" =>
                mux_out <= D3;
            when "100" =>
                mux_out <= D4;
            when "101" =>
                mux_out <= D5;
            when "110" =>
                mux_out <= D6;
            when "111" =>
                mux_out <= D7;
            when others =>
                mux_out <= (others => '0'); 
        end case;
    end process;
end Behavioral;

