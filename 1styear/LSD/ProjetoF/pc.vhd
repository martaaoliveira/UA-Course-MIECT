LIBRARY IEEE;
USE IEEE.std_logic_1164.ALL;
USE IEEE.numeric_std.ALL;

ENTITY pc IS
generic(N : positive := 3);
PORT 
(clk : IN STD_LOGIC;
reset : IN STD_LOGIC;
--pc_in : IN STD_LOGIC_VECTOR(N-1 DOWNTO 0);
pc_en : IN STD_LOGIC;
pc_out : OUT STD_LOGIC_VECTOR(N-1 DOWNTO 0) );
END pc; 




ARCHITECTURE Behavioral OF pc IS
signal s_cntValue : unsigned(N-1 downto 0);
BEGIN

PROCESS(clk, reset)


BEGIN
IF reset = '1' THEN
s_cntValue <= (others => '0');   

elsif RISING_EDGE(clk) then

if pc_en = '1' then
s_cntValue <= s_cntValue + 1; 
end if;
end if;

end process;
pc_out <= std_logic_vector(s_cntValue);

END Behavioral; 