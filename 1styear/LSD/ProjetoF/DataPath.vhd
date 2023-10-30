library ieee;
use ieee.std_logic_1164.ALL;
library work;

entity DataPath is
PORT(
	EnPc : in std_logic;
	RI : in std_logic;
	clk : in  std_logic;
	reset: in  std_logic;
	RegDst : in std_logic;
	RegWr : in std_logic;
	ALUSrc : in std_logic; 
	ALUOp : in std_logic_vector(3 downto 0);
	MemWr : in std_logic; 
	MemtoReg : in std_logic;
	func : out std_logic_vector(3 downto 0);
	opcode: out std_logic_vector(2 downto 0));		 
end DataPath;



architecture Structural of DataPath is
 --Signals between blocks
 
	signal s_instrucao : std_logic_vector(15 downto 0);
	signal s_extendedsignals : std_logic_vector(7 downto 0);
	signal s_res : std_logic_vector(7 downto 0);
	signal s_RD1 : std_logic_vector(7 downto 0);
	signal s_RD2 : std_logic_vector (7 downto 0);
	signal s_WD :std_logic_vector(7 downto 0);
	signal s_RD :std_logic_vector(7 downto 0); --DMemory
	signal s_Cnt : STD_LOGIC_VECTOR(2 DOWNTO 0);
	signal s_shAmount2 : std_logic_vector(3 downto 0);
	signal s_dataln0 : std_logic_vector(7 downto 0);
	signal s_dataln1 : std_logic_vector(7 downto 0);
	signal s_dataln0_1 : std_logic_vector(7 downto 0);
	signal s_dataln1_2 : std_logic_vector(7 downto 0);
	signal s_dataln0_3 : std_logic_vector(2 downto 0);
	signal s_dataln1_4 : std_logic_vector(2 downto 0);
	signal s_y1:std_logic_vector(7 downto 0);
	signal s_y2:std_logic_vector(7 downto 0);
	signal s_y3:std_logic_vector(2 downto 0);
	
	begin
	
Alu : entity work.ALU(Behavioral)
 port map(operation => ALUOp,
				op1 	=> s_RD1,
				op2 	=> s_y1,
				res 	=> s_res,
				shAmount2 =>s_shAmount2);
	
	
imemory : entity work.Instruction_Memory(Behavioral)
		generic map(N  => 3)
		port map(
					EN      => RI,
					RA 	=> s_Cnt,
					RD    => s_instrucao,
					--RD(15 downto 13)=> opcode,
					--RD(3 downto 0) => func,
					clk => clk);
					
registers : entity work.Registers(Behavioral)
		
		port map(clk 			=> clk,
					WE => RegWr,
					Reset 		=> Reset,
					RA1 => s_instrucao(12 downto 10),
					RA2 => s_instrucao(9  downto 7),
					WA => s_y3,
					WD 	=> s_y2,
					RD1 	=> s_RD1,
					RD2 	=> s_RD2);
					
extendedsignals : entity work.SignExtend(Behavioral)
	port map(signals     => s_instrucao(6 downto 0),
				extendedsignals   => s_extendedsignals);
				
programcounter : entity work.pc(Behavioral)
	generic map(N   => 3)
	port map(clk 	 => clk,
				reset  => reset,
				pc_en => EnPC,
				pc_out    => s_Cnt);

dmemory256_8 : entity work.DMemory(Behavioral)
	
	port map(writeClk	=> clk,
				WE	   => MemWr,
				Addr 	=> s_res,
				WD 	=> s_WD,
				RD	=> s_RD);
				
mux8bits_1 : entity work.Mux2_1_1(Behavioral) --multiplexer da alusrc
	
	port map(sel     => ALUSrc,
			   dataln0  => s_RD2,
				dataln1  => s_extendedsignals,
				y  => s_y1);
				
mux8bits_1_2 : entity work.Mux2_1_2(Behavioral) --multiplexer da MemToreg
	port map(sel =>MemtoReg,
				dataln0  => s_dataln0_1,
				dataln1  => s_dataln1_2,
				y  => s_y2);
			
mux8bits_1_3 : entity work.Mux2_1_3(Behavioral) --multiplexer da RegDst
	port map(sel =>RegDst,
				dataln0  => s_dataln0_3 ,
				dataln1  => s_dataln1_4,
				y  => s_y3);
				
func <= s_instrucao(3 downto 0);
opcode <= s_instrucao(15 downto 13);

end Structural;