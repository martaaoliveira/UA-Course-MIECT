----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 29.02.2024 17:11:32
-- Design Name: 
-- Module Name: display_7_seg - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity display_7_seg is
  Port (counter: in std_logic_vector(3 downto 0);
        seg_L: out std_logic_vector(6 downto 0));
end display_7_seg;

architecture Behavioral of display_7_seg is
begin
--ative low
seg_L <=    "1000000" when (counter = "0000") else --0
            "1111001" when (counter = "0001") else --1
            "0100100" when (counter = "0010") else --2
            "0110000" when (counter = "0011") else --3
            "0011001" when (counter = "0100") else --4
            "0010010" when (counter = "0101") else --5
            "0000010" when (counter = "0110") else --6
            "0111000" when (counter = "0111") else --7
            "0000000" when (counter = "1000") else --8
            "0011000" when (counter = "1001") else --9
            "0001000" when (counter = "1010") else --A
            "0000011" when (counter = "1011") else --B
            "1000110" when (counter = "1100") else --C
            "0100001" when (counter = "1101") else --D
            "0000110" when (counter = "1110") else --E
            "0001110" when (counter = "1111") else --F
            "1111111";  
end Behavioral; 