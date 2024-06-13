----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 15.03.2024 22:02:18
-- Design Name: 
-- Module Name: flip_flop - Behavioral
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

--para armazenar o valor dos digitos
entity flip_flop is
    Port (clk: in std_logic;
          DataIn: in std_logic;
          DataOut: out std_logic );
end flip_flop;

architecture Behavioral of flip_flop is

begin
    process(clk)
    begin
        if(rising_edge(clk))then
            DataOut<=DataIn;
        end if;
    end process;

end Behavioral;
