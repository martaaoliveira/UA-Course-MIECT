library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;


entity ROM_16_8 is
port(address : in std_logic_vector(3 downto 0);
dataOut : out std_logic_vector(7 downto 0));
end ROM_16_8;


architecture Behavioral of ROM_16_8 is

subtype TDataWord is std_logic_vector(7 downto 0);

type TROM is array (0 to 15) of TDataWord;
constant c_memory: TROM := ("11111011", "00010010","10011011", "10010011", "01011011", "00111010","11111011", "00010010", "10100011", "10011010","01111011", "00010010", "10101001", "00110110","11011011", "01010010");

begin
dataOut <= c_memory(to_integer(unsigned(address)));


end Behavioral;