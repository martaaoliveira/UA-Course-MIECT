library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Mux2_1 is
port(sel : in std_logic;
dataln0 : in std_logic;
dataln1 : in std_logic;
dataOut : out std_logic);
end Mux2_1;
architecture Behavioral of Mux2_1 is
begin
process(sel, dataln0, dataln1)
begin
if (sel = '0') then
dataOut <= dataln0;
else
dataOut <= dataln1;
end if;
end process;
end Behavioral;