library IEEE;
library work;
use IEEE.STD_LOGIC_1164.all;

entity Registers_Tb is
end Registers_Tb;

architecture Stimulus of  Registers_Tb is
		signal s_clk :  std_logic;
		signal s_reset : std_logic;
		signal s_RA1 :  std_logic_vector(2 downto 0); -- ler adress
		signal s_RA2 : std_logic_vector(2 downto 0); -- ler adress
		signal s_WA  :  std_logic_vector(2 downto 0); -- escrever adress
		signal s_WD : std_logic_vector (7 downto 0); --escrever data
		signal s_WE :  std_logic; -- escrever enable
		signal s_RD1 :  std_logic_vector (7 downto 0); -- ler data
		signal s_RD2 :  std_logic_vector (7 downto 0); -- ler data

	
begin
	uut: entity work.Registers(Behavioral)
		port map(reset  	=> s_reset,
					clk	 	=> s_clk,
					RA1	 	=> s_RA1,
					RA2	 	=> s_RA2,
					WA	 	=> s_WA,
					WD	 	=> s_WD,
					WE	 	=> s_WE,
					RD1	 	=> s_RD1,
					RD2	 	=> s_RD2);
					
					
	clk_proc: process
		begin
		s_clk <= '0';
		wait for 10 ns;
		s_clk <= '1';
		wait for 10 ns;
	end process;
	
	stim_proc: process
	
		begin
		s_reset <= '1';
		wait for 25 ns;
		
		s_reset <= '0';
		s_RA1 <= "001";
		wait for 30 ns;
		
		s_RA1 <= "010";
		wait for 30 ns;
		
		s_WE <= '1';
		s_WA <= "001";
		s_WD   <= "00001111";
		wait for 30 ns;
		
		
		
		s_RA1 <= "011";
		s_RA2 <= "001";
		wait for 30 ns;
--------------------------------------------		
		s_reset <= '1';
		wait for 35 ns;
		
		s_reset <= '0';
		s_RA1 <= "010";
		s_RA2 <="001";
		wait for 30 ns;
		
		
		
		s_WE <= '1';
		s_WA <= "010";
		s_WD   <= "00000001";
		wait for 30 ns;
		
		
		
		s_RA1 <= "010";
		s_RA2 <= "001";
		wait for 30 ns;
		
		

		
		end process;
end Stimulus;