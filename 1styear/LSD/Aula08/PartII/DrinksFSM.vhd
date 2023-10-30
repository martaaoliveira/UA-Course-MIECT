library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity DrinksFSM is	
	port(reset   : in  std_logic;
		  clk     : in  std_logic;
		  coin_20 : in  std_logic;
		  coin_50 : in  std_logic;
		  drinks   : out std_logic);
end DrinksFSM;



architecture Behavioral of DrinksFSM is

type TState is (E0, E1, E2, E3, E4, E5);
signal pState, nState: TState;


begin

sync_proc : process(clk)

begin
if (rising_edge(clk)) then
if (reset = '1') then
pState <= E0;
else
pState <= nState;
end if;
end if;

end process;


comb_proc : process(pState,coin_20,coin_50)

begin



case pState is
-----------------------E0
when E0 =>
drinks <= '0'; 
if (coin_20 = '1') then
nState <= E1;
elsif(coin_50 = '1') then
nState <= E3;
else
nState <= E0;
end if;

----------------------E1
when E1 =>
drinks <= '0'; 

if (coin_20 = '1') then
nState <= E0;

elsif(coin_50='1')then
nState <= E4;

else
nState <= E1;

end if;

------------------------E2
when E2 =>

drinks <= '0'; 

if (coin_20 = '1') then
nState <= E3;
drinks <= '0';

elsif(coin_50='1')then
nState <= E5;

else
nState <= E3;

end if;

-----------------------------E3

when E3 =>

drinks <= '0'; 

if (coin_20 = '1') then
nState <= E5;


elsif(coin_50='1')then
nState <= E5;


else
nState <= E3;

end if;
----------------------------------E4
when E4 =>

drinks <= '0'; 

if (coin_20 = '1') then
nState <= E5;


elsif(coin_50='1')then
nState <= E5;


else
nState <= E4;

end if;
------------------------------------------E5
when E5 =>

drinks <= '1';
nState <= E0;


when others =>  
nState <= E0;

end case;
end process;




end Behavioral;
