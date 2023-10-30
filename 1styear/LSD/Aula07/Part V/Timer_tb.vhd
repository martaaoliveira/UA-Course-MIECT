library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity Timer_tb is
end Timer_Tb;

architecture Stimulus of Timer_tb is

----- Sinais para ligar à UUT
	signal s_clk      : std_logic;
	signal s_enable   : std_logic;
	signal s_reset    : std_logic;
	signal s_start    : std_logic;
	signal s_timerOut : std_logic;
	
	begin
   
   uut: entity work.Timer(Behavioral)
		  generic map(N => 10)
        port map(clk      => s_clk,
					  start    => s_start,
                 reset    => s_reset,
					  enable   => s_enable,
                 timerOut => s_timerOut);
					  
--------------------------------------------------------------------------------