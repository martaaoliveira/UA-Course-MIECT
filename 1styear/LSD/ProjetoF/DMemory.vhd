library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

ENTITY DMemory IS --256*8
	port ( writeClk : in std_logic;
			 WE     : in  std_logic;
			 WD : in  std_logic_vector(7 downto 0); 
			 Addr : in  std_logic_vector(7 downto 0);
			 RD : out std_logic_vector(7 downto 0));                     
END DMemory;




architecture Behavioral of DMemory is

constant NUM_WORDS : integer := 256;
subtype TDataWord is std_logic_vector(7 downto 0);
type TMemory is array (0 to NUM_WORDS-1) of TDataWord;
signal s_memory: TMemory := (X"FF", X"0A",X"05", others => X"00");



begin
process(writeClk)
begin
if (rising_edge(writeClk)) then
if (WE = '1') then
s_memory(to_integer(unsigned(Addr))) <= WD;
end if;
end if;
end process;

RD <= s_memory(to_integer(unsigned(Addr)));

end Behavioral;


