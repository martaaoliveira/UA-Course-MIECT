----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 15.03.2024 21:50:30
-- Design Name: 
-- Module Name: PulseGenerator800hz - Behavioral
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

entity PulseGenerator800hz is
    Port (clk: in std_logic; 
          reset: in std_logic;
          pulse: out std_logic);
end PulseGenerator800hz;

architecture Behavioral of PulseGenerator800hz is
--1.25 ms (1/(800Hz))
constant MAX : natural :=125_000; 
signal s_count : natural range 0 to MAX-1;
begin
    process(clk)
    begin
        if (rising_edge(clk)) then
            pulse<='0'; 
            if(reset = '1')then
                s_count  <=0;
            else
                s_count <= (s_count+1);
                if(s_count=MAX-1) then
                    s_count<=0;
                    pulse<='1'; --ativa o pulso (clk) em cada período de 1.25 ms
                end if;
            end if;
        end if;
    end process;
end Behavioral;