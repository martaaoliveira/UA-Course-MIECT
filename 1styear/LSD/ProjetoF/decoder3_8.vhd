library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity decoder3_8 is
Port ( 
enable: in std_logic;
address: in STD_LOGIC_VECTOR (2 downto 0);
y : out STD_LOGIC_VECTOR (7 downto 0));
end decoder3_8;

architecture Behavioral of decoder3_8 is
begin
process(enable, address)
begin

if (enable = '0') then
y <= "00000000";
else

case address is

when "000" =>
y <= "00000001";

when "001" =>
y <= "00000010";

when "010" =>
y <= "00000100";

when "011" =>
y <= "00001000";

when "100" =>
y <= "00010000";


when "101" =>
y <= "00100000";

when "110" =>
y <= "01000000";

when others =>
y <= "10000000";
end case;

end if;



end process;
end Behavioral;
