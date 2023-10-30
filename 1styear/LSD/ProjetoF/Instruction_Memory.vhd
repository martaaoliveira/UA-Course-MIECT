library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;



entity Instruction_Memory is
generic (N : positive := 3); --8

port (
 EN: in std_logic;
 clk : in std_logic;
 RA : in std_logic_vector(N-1 downto 0);
 RD: out  std_logic_vector(15 downto 0));
end Instruction_Memory;



architecture Behavioral of Instruction_Memory is


constant NUM_WORDS : integer := 8;
subtype TDataWord is std_logic_vector(15 downto 0);

type ROM_type is array (0 to (NUM_WORDS - 1) ) of TDataWord;

constant c_memory: Rom_type := ("1110000010000000", "1110000100000001", 
"1110000110000010", "0010010101000100", "1001001010000001","0011010110010000",
"1100000010000100","0000000000000000");


--LW $0, $1, 0
--LW $0, $2, 1 
--LW $0, $3, 2 
--XOR $1, $2, $4 
--ADDI $4, $5, 1 
--ADD $5, $3, $1 
--SW $0, $1,4

begin
process(clk)
begin
if(rising_edge(clk))then
if(EN='1') then
RD <= c_memory(to_integer(unsigned(RA)));
end if;
end if;
end process;
end Behavioral;
