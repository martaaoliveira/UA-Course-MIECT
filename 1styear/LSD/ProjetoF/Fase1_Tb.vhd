library IEEE;
use IEEE.STD_LOGIC_1164.all; 
library work;

entity Fase1_Tb is 
end Fase1_Tb;

architecture Stimulus of Fase1_Tb is 
	signal s_clk      : std_logic;
   signal s_reset    : std_logic;
	signal s_MemWr    : std_logic;
   signal s_RI       : std_logic;
	signal s_EnPC     : std_logic;
	signal s_MemToReg : std_logic;
	signal s_RegDst   : std_logic;
   signal s_RegWr    : std_logic;
   signal s_ALUSrc   : std_logic;
	signal s_ALUOp    :  std_logic_vector(3 downto 0);
	
	
begin
	uut: entity work.DataPath(Structural)
		
		port map(CLK 	   => s_clk,
					Reset    => s_reset,
					MemWr    => s_MemWr,
					RI       => s_RI,
					EnPC     => s_EnPC,
					MemToReg => s_MemToReg,
					RegDst   => s_RegDst,
					RegWr    => s_RegWr,
					ALUSrc   => s_ALUSrc,
					ALUOp    => s_ALUOp);
					
clk_proc: process
	begin
		s_clk <= '0';
		wait for  10 ns;
		s_clk <= '1';
		wait for  10 ns;
	end process;
	
	stim_proc: process
	begin
		wait for 80 ns;
		s_reset 		<= '0';
		s_EnPC 		<= '1';
		s_RI 			<= '1';
		s_RegWr 		<= '0';
		s_RegDst 	<= '0';
		s_ALUSrc 	<= '0';
		s_MemWr 		<= '0';
		s_MemToReg  <= '0';
		s_ALUOp 		<= "0000";
	end process;
end Stimulus;