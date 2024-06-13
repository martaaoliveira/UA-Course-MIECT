----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 14.03.2024 18:21:17
-- Design Name: 
-- Module Name: parte2_wrapper - Behavioral
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

entity parte2_wrapper is
  Port (clk: in std_logic;
        btnR : in std_logic;
        btnC: in std_logic; --start/pause
        btnU : in std_logic; -- incrementar 
        btnD : in std_logic;-- decrementar 
        an: out std_logic_vector(7 downto 0);
        seg: out std_logic_vector(6 downto 0);
        dp: out std_logic;
        led: out std_logic_vector(0 downto 0));
end parte2_wrapper;

architecture Behavioral of parte2_wrapper is
    signal s_led : std_logic;
    signal s_dp: std_logic;
    signal s_dp_enable : std_logic_vector(7 downto 0);
    signal s_enable_digit, s_an: std_logic_vector(7 downto 0);
    signal s_btnC, s_btnR, s_btnU, s_btnD: std_logic; 
    signal  s_controler: std_logic;
    signal s_status: std_logic_vector(3 downto 0);
    signal s_en800hz,s_en1hz,s_en2hz  : std_logic;
    signal s_dm, s_um, s_us, s_ds : std_logic_vector(3 downto 0);
    begin
    

DebounceUnit_R: entity work.DebounceUnit(Behavioral)
    generic map(kHzClkFreq => 100_000,
                mSecMinInWidth=>100,
                inPolarity=>'1',
                outPolarity=>'1')
                
    port map(refclk=>clk,
             dirtyIn=> btnR,
             pulsedOut=>s_btnR);
        
DebounceUnit_C: entity work.DebounceUnit(Behavioral)
generic map(kHzClkFreq => 100_000,
            mSecMinInWidth=>100,
            inPolarity=>'1',
            outPolarity=>'1')
            
port map(refclk=>clk,
         dirtyIn=> btnC,
         pulsedOut=>s_btnC);


flipflop_U: entity work.flip_flop(Behavioral)
port map (clk => clk,
         dataIn => btnU,
         dataOut => s_btnU);

flipflop_D: entity work.flip_flop(Behavioral)
port map (clk => clk,
         dataIn => btnD,
         dataOut => s_btnD);
         
                
PulseGenerator800hz : entity work.PulseGenerator800hz(Behavioral)
    port map(   reset   => '0',
                clk   => clk,
                pulse => s_en800hz);
                
PulseGenerator1hz : entity work.PulseGenerator1hz(Behavioral)
    port map(   reset   => '0',
                clk   => clk,
                pulse => s_en1hz);

PulseGenerator2hz : entity work.PulseGenerator2hz(Behavioral)
    port map(   reset   => '0',
                clk   => clk,
                pulse => s_en2hz);
                            


control_unit: entity work.control_path(Behavioral)
     port map (clk => clk,
              reset => '0',
              btnR => s_btnR,--modo ajuste
              start_pause => s_btnC, -- controlo contador horario
              finished => s_led, -- contador chegou ao fim
              status => s_status,
              controler => s_controler);
                   
                   
                   
countTimer: entity work.counter_timer(Behavioral)
    port map(clk => clk,
             clk_2hz =>s_en2hz, -- para incrementar ou decrementar a esta frequencia
             reset => '0',
             enable => s_en1hz, --campo segundos a ser decrementado a frequencia 1hz 
             controler => s_controler,
             up=> s_btnU,
             down=> s_btnD,
             status => s_status,
             DM => s_dm,
             UM => s_um,
             DS => s_ds,
             US => s_us,
             led =>s_led);
 
blinkPoint: process(s_en1hz)
    begin
        if(rising_edge(clk) and s_en1hz='1' and s_controler = '1') then
            s_dp <= not s_dp;
        end if;
    end process;

s_dp_enable(2) <= s_dp;

blinkDigit: process(clk)
begin
    if rising_edge(s_en2hz) then --frequencia 2hz a piscar digitos
            s_enable_digit <= "00001111"; 

            -- Verificar qual dígito deve piscar
            case s_status is
                when "1000" => -- Dezenas de minutos
                    s_enable_digit(3) <= not s_enable_digit(3); 
                when "0100" => -- Unidades de minutos
                    s_enable_digit(2) <= not s_enable_digit(2); 
                when "0010" => -- Dezenas de segundos
                    s_enable_digit(1) <= not s_enable_digit(1); 
                when "0001" => -- Unidades de segundos
                    s_enable_digit(0) <= not s_enable_digit(0); 
                when others => 
                    s_enable_digit <= "00001111"; -- Todos os dígitos ativos
            end case;
        end if;
end process;

                                        
Nexys4DispDriver : entity work.Nexys4DispDriver(Behavioral)
    port map(   
        clk         => clk,
        enable_clk  => s_en800hz,
        en_digit    => s_enable_digit,
        en_dot      =>s_dp_enable, 
        d0          => s_us,    
        d1          => s_ds,     
        d2          => s_um,    
        d3          => s_dm,     
        d4          => (others => '0'),     
        d5          => (others => '0'),     
        d6          => (others => '0'),    
        d7          => (others => '0'),     
        an_L        => an,         
        cat_L       => seg,        
        dp_L        => dp          
);



led(0) <= s_led;

end Behavioral;
