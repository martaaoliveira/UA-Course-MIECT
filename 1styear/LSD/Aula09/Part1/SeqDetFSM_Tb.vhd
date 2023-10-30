library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity SeqDetFSM_tb is
end SeqDetFSM_tb;

architecture Stimulus of SeqDetFSM_tb is

	-- Sinais para ligar à UUT
	signal s_clk  : std_logic;
	signal s_xin  : std_logic;
	signal s_yout : std_logic;
	signal s_reset :std_logic;

	
	
begin

	-- Instanciação da(UUT)
	uut : entity work.SeqDetFSM(MealyArch)
	port map(clk    => s_clk,
				xin    => s_xin,
				yout   => s_yout,
				reset => s_reset);
				
				
				
-- Process : clk
clock_proc : process
	begin
		
		s_clk <= '0'; 
		wait for 10 ns;
		
		s_clk <= '1'; 
		wait for 10 ns;
	end process;



	
 -- Process stim
   stim_proc : process
   begin
		s_xin <= '1';
		s_reset <= '0';
      wait for 20 ns;
		
		s_xin <= '0';
      wait for 20 ns;
      
		s_xin <= '1';
      wait for 40 ns;
		
		s_xin <= '0';
      wait for 40 ns;
      
		s_xin <= '1';
      wait for 50 ns;
		
		s_xin <= '0';
      wait for 20 ns;
		
		s_xin <= '1';
      wait for 10 ns;
		
		s_xin <= '0';
      wait for 20 ns;
		
		s_xin <= '1';
      wait for 10 ns;
		
   end process;
end Stimulus;
	
	