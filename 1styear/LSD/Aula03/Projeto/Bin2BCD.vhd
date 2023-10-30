library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.numeric_std.all;

entity hexa2dec is
   port( binIN   : in  std_logic_vector(3 downto 0);
	      bcdOut0 : out std_logic_vector(3 downto 0);
			bcdOut1 : out std_logic_vector(3 downto 0));
end hexa2dec;

architecture Behav of hexa2dec is
begin
  process(binIN)
   begin
	 case binIN is
	   when "0000" => 
		     bcdOut0 <= "0000";
			  bcdOut1 <= "0000";
		when "0001" => 
		     bcdOut0 <= "0001";
			  bcdOut1 <= "0000";
		when "0010" => 
		     bcdOut0 <= "0010";
			  bcdOut1 <= "0000";
	   when "0011" => 
		     bcdOut0 <= "0011";
			  bcdOut1 <= "0000";
	   when "0100" => 
		     bcdOut0 <= "0100";
			  bcdOut1 <= "0000"; 
		when "0101" => 
		     bcdOut0 <= "0101";
			  bcdOut1 <= "0000";
		when "0110" => 
		     bcdOut0 <= "0110";
			  bcdOut1 <= "0000";
		when "0111" => 
		     bcdOut0 <= "0111";
			  bcdOut1 <= "0000";
		when "1000" => 
		     bcdOut0 <= "1000";
			  bcdOut1 <= "0000";
		when "1001" => 
		     bcdOut0 <= "1001";
			  bcdOut1 <= "0000";
		when "1010" => 
		     bcdOut0 <= "0000";
			  bcdOut1 <= "0001";
		when "1011" => 
		     bcdOut0 <= "0001";
			  bcdOut1 <= "0001";
		when "1100" => 
		     bcdOut0 <= "0010";
			  bcdOut1 <= "0001";
		when "1101" => 
		     bcdOut0 <= "0011";
			  bcdOut1 <= "0001";
		when "1110" => 
		     bcdOut0 <= "0100";
			  bcdOut1 <= "0001";
		when "1111" => 
		     bcdOut0 <= "0101";
			  bcdOut1 <= "0001";	  
	 end case;
   end process;
end Behav;