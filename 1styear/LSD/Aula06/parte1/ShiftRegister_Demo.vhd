
library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;

entity ShiftRegister_Demo is
port (CLOCK_50: in std_logic;
      SW      : in std_logic_vector (0 downto 0);
		LEDR    : out std_logic_vector(7 downto 0);
		LEDG    : out std_logic_vector(0 downto 0));
end ShiftRegister_Demo;

architecture Shell of ShiftRegister_Demo is
signal s_clockmod: std_logic;
begin
modificador: work.ClkDividerN(Behavioral)
					generic map(divFactor => 50000000)
					port map(clkIn => CLOCK_50,
								clkOut => s_clockmod);
shift: work.ShiftRegisterN(Behavioral)
					generic map(N => 8)
					port map(clk => s_clockmod,
								sin => SW(0),
								dataOut => LEDR(7 downto 0));
LEDG(0) <= s_clockmod;
end Shell;