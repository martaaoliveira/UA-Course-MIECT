library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity SeqDetFSM is
	port(reset : in std_logic;
		  clk  : in std_logic;
		  Xin  : in  std_logic;
		  Yout : out std_logic);
end SeqDetFSM;


architecture MealyArch of SeqDetFSM is
type state is (A, B, C, D);
signal PS, NS : state;
begin
sync_proc: process(clk)
begin
if (rising_edge(clk)) then
if (reset = '1') then
PS <= A;
else
PS <= NS;
end if;
end if;
end process;


comb_proc : process(PS, xIn)
begin
Yout <= '0'; -- Frequent output value, could appear
-- in all “when” statements, but would require more code

case PS is

when A =>
if (xin = '1') then NS <= B;
else NS <= A;
end if;
----------------------
when B =>
if (xin = '0') then NS <= C;
else NS <= B;
end if;
------------------------------
when C =>
if (xin = '0') then NS <= D;
else NS <= B;
end if;
----------------------------
when D =>
if (xin = '0') then
NS <= A;
else NS <= B;
yout <= '1'; -- Mealy output
end if;


when others => -- Catch all condition
NS <= A;
end case;
end process;
end MealyArch;

