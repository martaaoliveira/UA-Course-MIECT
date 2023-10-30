library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
library work;

entity Registers is
port( clk : in std_logic;
		reset : in std_logic;
		RA1 : in std_logic_vector(2 downto 0); -- ler adress
		RA2 : in std_logic_vector(2 downto 0); -- ler adress
		WA  : in std_logic_vector(2 downto 0); -- escrever adress
		WD : in std_logic_vector (7 downto 0); --escrever data
		WE : in std_logic; -- escrever enable
		RD1 : out std_logic_vector (7 downto 0); -- ler data
		RD2 : out std_logic_vector (7 downto 0)); -- ler data


end entity;

--
architecture Behavioral of Registers is


signal s_regDataOut0 : std_logic_vector(7 downto 0);
signal s_regDataOut1 : std_logic_vector(7 downto 0);
signal s_regDataOut2 : std_logic_vector(7 downto 0);
signal s_regDataOut3 : std_logic_vector(7 downto 0);
signal s_regDataOut4 : std_logic_vector(7 downto 0);
signal s_regDataOut5 : std_logic_vector(7 downto 0);
signal s_regDataOut6 : std_logic_vector(7 downto 0);
signal s_regDataOut7 : std_logic_vector(7 downto 0);
signal s_decode : std_logic_vector(7 downto 0);
begin
Mux1: entity work.Mux8_1(Behavioral)
		port map(sel => RA1,
					input0 => s_regDataOut0,
					input1 => s_regDataOut1,
					input2 => s_regDataOut2,
					input3 => s_regDataOut3,
					input4 => s_regDataOut4,
					input5 => s_regDataOut5,
					input6 => s_regDataOut6,
					input7 => s_regDataOut7,
					muxOut => RD1);

Mux2:entity work.Mux8_1_2(Behavioral)
		port map(sel => RA2,
					input0 => s_regDataOut0,
					input1 => s_regDataOut1,
					input2 => s_regDataOut2,
					input3 => s_regDataOut3,
					input4 => s_regDataOut4,
					input5 => s_regDataOut5,
					input6 => s_regDataOut6,
					input7 => s_regDataOut7,
					muxOut => RD2);
					
			
---- gerar writeEnables para os 8 blocos 					
Decoder: entity work.decoder3_8(Behavioral)
			port map (y=>s_decode,address=>WA,enable=>WE);

	
reg0:entity work.Reg0(Behavioral)
port map(clk => clk, dOut=>s_regDataOut0);

reg1: entity work.Reg1(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut1);

reg2: entity work.Reg2(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut2);
		
reg3: entity work.Reg3(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut3);
		
reg4: entity work.Reg4(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut4);
		
reg5: entity work.Reg5(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut5);

reg6: entity work.Reg6(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut6);
		
reg7: entity work.Reg7(Behavioral)
		port map(enable=>s_decode,clk => clk, reset=> reset,dIn=>WD,dOut =>s_regDataOut7);
		


end Behavioral;
