----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 08.03.2024 11:42:08
-- Design Name: 
-- Module Name: counter_to5 - Behavioral
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



entity counter_to5 is
  Port (clk: in std_logic; 
        reset: in std_logic;
       controler: in std_logic;
        enable: in std_logic;
        status: in std_logic;
        up  : in std_logic;
        down: in std_logic;
        counter : out std_logic_vector(3 downto 0);
        pulseOut: out std_logic); --"enable" contadores cascata 
end counter_to5;

architecture Behavioral of counter_to5 is
      signal s_counter : natural :=5;
begin

    process(clk)
    begin
        if(rising_edge(clk)) then
           if(reset ='1' or s_counter < 0 or s_counter>5) then
                s_counter <= 5;
          --quando controler esta em 0 esta em pause por isso nao decrementa
           elsif(enable ='1'and controler ='1') then
                s_counter <= (s_counter-1);
           
           --para mudar o valor contador dependendo estado btnU e btnD
           elsif (status='1') then
                if(up='1') then 
                    s_counter <= (s_counter+1);
                elsif(down='1') then
                    s_counter <= (s_counter-1);
                end if;
           end if;
           
           if(s_counter=0 )then
                pulseOut<='1';
           else 
                pulseOut<='0';
           end if;
           counter<= std_logic_vector(to_unsigned(s_counter,counter'length));
        
        end if;
    end process;
    
end Behavioral;
