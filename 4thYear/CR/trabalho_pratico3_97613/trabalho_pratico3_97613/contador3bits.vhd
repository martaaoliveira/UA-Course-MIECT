----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 29.02.2024 17:26:04
-- Design Name: 
-- Module Name: contador3bits - Behavioral
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

entity contador3bits is
  Port (clk : in std_logic;
        enable: in std_logic;
        count: out std_logic_vector(2 downto 0));
end contador3bits;

architecture Behavioral of contador3bits is
    signal s_counter:natural;
begin
    process(clk)
    begin
        if(rising_edge(clk)) then
            if(enable='1') then
                if(s_counter>7)then
                    s_counter <=0;
                 else
                    s_counter <=(s_counter+1);
                end if;
          end if;
          count <= std_logic_vector(to_unsigned(s_counter,count'length));
        end if;
end process;
end Behavioral;
