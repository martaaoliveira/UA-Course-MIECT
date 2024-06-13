----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08.03.2024 12:51:06
-- Design Name: 
-- Module Name: PulseGenerator1hz - Behavioral
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


entity PulseGenerator1hz is
    Port (clk: in std_logic; 
          reset: in std_logic;
          pulse: out std_logic);
end PulseGenerator1hz;

architecture Behavioral of PulseGenerator1hz is
--dividir a frequencia 100MHz/1hz
constant MAX : natural :=100_000_000; 
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
                    pulse<='1'; 
                end if;
            end if;
        end if;
    end process;
end Behavioral;
