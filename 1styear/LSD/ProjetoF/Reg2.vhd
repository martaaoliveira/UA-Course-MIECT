library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity Reg2 is
	port(reset  : in  std_logic;
		  clk	   : in  std_logic;
		  enable : in  std_logic_vector(7 downto 0);
		  dIn : in  std_logic_vector(7 downto 0);
		  dOut: out std_logic_vector(7 downto 0));
end Reg2;

architecture Behavioral of Reg2 is
begin
	process(clk)
	begin
		if(rising_edge(clk)) then
			if(reset = '1') then
				dOut <= (others => '0');
				
			elsif(enable = "00000100") then
				dOut <= dIn;
			end if;
		end if;
	end process;
end Behavioral;