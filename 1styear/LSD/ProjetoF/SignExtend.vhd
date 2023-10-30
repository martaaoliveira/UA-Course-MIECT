library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;



entity SignExtend  is

port	 (signals 			: in std_logic_vector(6 downto 0);
		  extendedsignals : out std_logic_vector(7 downto 0));



end SignExtend;


architecture Behavioral of SignExtend is

begin	
	
	extendedsignals <= signals(6) & signals(6 downto 0);

end Behavioral;