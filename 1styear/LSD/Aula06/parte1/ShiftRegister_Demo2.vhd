library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;


entity ShiftRegister_Demo is

port( clock_50 : in std_logic;
		SW : in std_logic;
		LEDR : out std_logic_vector(7 downto 0));
		
		
end ShiftRegister_Demo;



architecture Structural of ShiftRegister_Demo is

begin
ShiftRegister: entity work.ShiftRegisterN(Behavioral)
generic map ( N => 8 )

port map 

end Structural;