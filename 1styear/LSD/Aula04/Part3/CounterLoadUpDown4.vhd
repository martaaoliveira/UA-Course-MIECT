library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.NUMERIC_STD.all;


entity CounterLoadUpDown4 is
 port(clk : in std_logic;
		reset : in std_logic;
		updown : in std_logic;
		load : in std_logic;
		enable : in std_logic;
		DataIn : in std_logic_vector(3 downto 0);
		count : out std_logic_vector(3 downto 0));
end CounterLoadUpDown4;



architecture Behavioral of CounterLoadUpDown4 is
 signal s_count : unsigned(3 downto 0);
 
begin
 process(clk,enable)
 begin
 if (enable='1') then
 s_count <= (others =>'0');
 
elsif(rising_edge(clk))then

if(reset='1')then
s_count <= (others =>'0');

elsif(load ='1')then
s_count <= unsigned(DataIn);

elsif (updown = '1')then
s_count <= s_count +1;

else
s_count <= s_count -1;
 

 end if;
 end if;
 
 end process;
 
 count <= std_logic_vector(s_count);
end Behavioral;