library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity Reg0 is
	port(--reset  : in  std_logic;
			clk : in std_logic;
		  dOut: out std_logic_vector(7 downto 0));
end Reg0;

architecture Behavioral of Reg0 is
begin
	process(clk)
	begin
		if(rising_edge(clk)) then
			dOut<= (others =>'0');
		end if;
	end process;
end Behavioral;