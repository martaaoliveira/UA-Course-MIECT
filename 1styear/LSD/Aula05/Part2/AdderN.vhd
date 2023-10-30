library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity AdderN is

generic (K : positive :=  4 );
port(operando0 : in std_logic_vector(K-1 downto 0);
	  operando1 : in std_logic_vector(K-1 downto 0);     result : out std_logic_vector (K-1 downto 0));
	  
end AdderN;

	  
architecture Behavioral of AdderN is

begin 
result <= std_logic_vector(unsigned(operando0)+ unsigned (operando1));


end Behavioral;	  