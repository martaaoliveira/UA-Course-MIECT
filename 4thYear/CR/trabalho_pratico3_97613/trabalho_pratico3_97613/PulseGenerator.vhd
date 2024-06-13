----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 01.03.2024 01:15:25
-- Design Name: 
-- Module Name: pulse_gen - Behavioral
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
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PulseGenerator is
    Port (clk: in std_logic; 
          reset: in std_logic;
          pulse: out std_logic);
end PulseGenerator;

architecture Behavioral of PulseGenerator is
--1.25 ms (1/(800Hz))
constant MAX : natural :=125_000; 
signal s_count : natural range 0 to MAX-1;
begin
    process(clk)
    begin
        if (rising_edge(clk)) then
            pulse<='0'; --inicializar a 0
            if(reset = '1')then
                s_count  <=0;
            else
                s_count <= (s_count+1);
                if(s_count=MAX-1) then
                    s_count<=0;
                    pulse<='1'; --ativa o pulso (clk) ativo 10 ns em cada período de 1.25 ms
                end if;
            end if;
        end if;
    end process;
end Behavioral;
