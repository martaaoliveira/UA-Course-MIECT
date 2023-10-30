library IEEE;
use IEEE.STD_LOGIC_1164.all;


entity DMemory_Tb is
end DMemory_Tb;


architecture Stimulus of Dmemory_Tb is
	signal s_writeClk, s_WE	: std_logic;
	signal s_WD, s_Addr : std_logic_vector(7 downto 0);
	signal s_RD : std_logic_vector(7 downto 0);
	
	
begin


ram_ut : entity work.DMemory(Behavioral)
		port map(writeClk	=> s_writeClk,
					WE => s_WE,
					WD	=> s_wD,
					Addr	=> s_Addr,
					RD	=> s_RD);
					

writeClk_process : process
		begin
			s_writeClk <= '0'; 
			wait for 10 ns;
			
			s_writeClk <= '1';
			wait for 10 ns;
		end process;
		
		
stim_process : process
		begin
			wait for 5 ns;
			s_WE <= '1';
			s_Addr <= "00000001";
			wait for 20 ns;
			
			s_Addr <= "00000010";
			wait for 20 ns;

			s_WE <= '0';
			wait for 25 ns;
			
			s_WD <= X"35";
			s_Addr 	<= "00000011";
			wait for 20 ns;
			
		end process;
end Stimulus;