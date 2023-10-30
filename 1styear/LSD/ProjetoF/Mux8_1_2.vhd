library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;


ENTITY MUX8_1_2 IS --Para os registers

PORT ( SEL: IN STD_LOGIC_VECTOR(2 DOWNTO 0);

 input0,input1,input2,input3,input4,input5,input6,input7 :IN STD_LOGIC_vector(7 downto 0);

muxOut: OUT STD_LOGIC_vector(7 downto 0) );

END MUX8_1_2;

 

ARCHITECTURE BEHAVIORAL OF MUX8_1_2 IS

BEGIN

PROCESS (SEL,input0,input1,input2,input3,input4,input5,input6,input7)

BEGIN

CASE  SEL IS

WHEN "000" => muxOut <= input0;

WHEN "001" => muxOut <= input1;

WHEN "010" => muxOut <= input2;

WHEN "011" => muxOut <= input3;

WHEN "100" => muxOut <= input4;

WHEN "101" => muxOut <= input5;

WHEN "110" => muxOut <= input6;

WHEN "111" => muxOut <= input7;

WHEN OTHERS => NULL;

END CASE;

END PROCESS;

END BEHAVIORAL;