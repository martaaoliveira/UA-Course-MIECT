library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;




entity RAM_1P_Demo is

	port( KEY	: in std_logic_vector(0 downto 0);--writeClk	
			SW			: in std_logic_vector(12 downto 0);--writeEnable + writeData 
			LEDR		: out std_logic_vector(7 downto 0));-- readData
			
end RAM_1P_Demo;


architecture Behavioral of RAM_1P_Demo is
	
	
begin

ram16x8 : entity work.RAM_1P_16_8(Behavioral)
port map( clk =>  KEY(0),
			writeEnable => SW(0),
			writeData => SW(8 downto 1),
			address => SW(12 downto 9),
			readData => LEDR(7 downto 0));
	
	
end Behavioral;