library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity IPEuclideanDistance_v1_0_S00_AXIS is
	generic (
		-- Users to add parameters here

		-- User parameters ends
		-- Do not modify the parameters beyond this line

		-- AXI4Stream sink: Data Width
		C_S_AXIS_TDATA_WIDTH	: integer	:= 32
	);
	port (
		-- Users to add ports here
        dataValid    : out std_logic;
        euclideanData : out std_logic_vector(C_S_AXIS_TDATA_WIDTH-1 downto 0);
        readEnabled  : in  std_logic; --dados a ser lidos pelo microblaze
		-- User ports ends
		-- Do not modify the ports beyond this line

		-- AXI4Stream sink: Clock
		S_AXIS_ACLK	: in std_logic;
		-- AXI4Stream sink: Reset
		S_AXIS_ARESETN	: in std_logic;
		-- Ready to accept data in
		S_AXIS_TREADY	: out std_logic;
		-- Data in
		S_AXIS_TDATA	: in std_logic_vector(C_S_AXIS_TDATA_WIDTH-1 downto 0);
		-- Byte qualifier
		S_AXIS_TSTRB	: in std_logic_vector((C_S_AXIS_TDATA_WIDTH/8)-1 downto 0);
		-- Indicates boundary of last packet
		S_AXIS_TLAST	: in std_logic;
		-- Data is in valid
		S_AXIS_TVALID	: in std_logic
	);
end IPEuclideanDistance_v1_0_S00_AXIS;

architecture arch_imp of IPEuclideanDistance_v1_0_S00_AXIS is
    signal s_ready    : std_logic; --so esta pronto se nao houver validos validos(foram consumidos) ou tem dados validos mas ja estao a ser lidos
    signal s_validOut : std_logic;
    signal s_dataOut  : std_logic_vector(C_S_AXIS_TDATA_WIDTH-1 downto 0);
begin
    s_ready <= (not s_validOut) or readEnabled;
    
    process(S_AXIS_ACLK)
	begin
        if (rising_edge (S_AXIS_ACLK)) then
	        if (S_AXIS_ARESETN = '0') then
	           s_validOut <= '0';
	           s_dataOut  <= (others => '0');
       
            elsif (S_AXIS_TVALID = '1') then
	           if (s_ready = '1') then --prontos a consumir
                    --ordenar logo os bytes(resultado processamento)
					--x1 = S_AXIS_TDATA(7 downto 0)
					--x2 = S_AXIS_TDATA(23 downto 16)
					--y2 = S_AXIS_TDATA(31 downto 24)
					--y1 = S_AXIS_TDATA(15 downto 8)
					s_dataOut <= "00000000000000" &
								 std_logic_vector(
										signed((S_AXIS_TDATA(23) & S_AXIS_TDATA(23 downto 16))) - signed((S_AXIS_TDATA(7) & S_AXIS_TDATA(7 downto 0)))
									  * signed((S_AXIS_TDATA(23) & S_AXIS_TDATA(23 downto 16))) - signed((S_AXIS_TDATA(7) & S_AXIS_TDATA(7 downto 0)))
								  +
										signed((S_AXIS_TDATA(31) & S_AXIS_TDATA(31 downto 24))) - signed((S_AXIS_TDATA(15) & S_AXIS_TDATA(15 downto 8)))
									  * signed((S_AXIS_TDATA(31) & S_AXIS_TDATA(31 downto 24))) - signed((S_AXIS_TDATA(15) & S_AXIS_TDATA(15 downto 8)))
                                 );
                    s_validOut <= '1';
	           end if;
	      
	        elsif (readEnabled = '1') then --ja nao ha mais dados validos
	           s_validOut <= '0';               
            end if;
        end if;
    end process;

	dataValid     <= s_validOut;
	euclideanData  <= s_dataOut; 
	S_AXIS_TREADY <= s_ready;	-- vai diretamente para a interface respetiva que o slave controla 

end arch_imp;