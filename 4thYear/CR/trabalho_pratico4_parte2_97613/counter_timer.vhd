----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 14.03.2024 18:20:11
-- Design Name: 
-- Module Name: counter_timer - Behavioral
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

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity counter_timer is
  Port (clk: in std_logic; 
        clk_2hz: in std_logic; --frequencia decrementar
        reset: in std_logic;
        enable: in std_logic; -- campo de segundos ss vai ser decrementado à frequência de 1Hz.
        controler: in std_logic; -- modo pausar ou start
        up: in std_logic; --incrementar
        down: in std_logic; --decrementar
        status: in std_logic_vector(3 downto 0);
        DM : out std_logic_vector(3 downto 0);
        UM : out std_logic_vector(3 downto 0);
        DS : out std_logic_vector(3 downto 0);
        US : out std_logic_vector(3 downto 0);
        led: out std_logic);
end counter_timer;

architecture Behavioral of counter_timer is
signal s_counter_us,s_counter_ds, s_counter_um, s_counter_dm : std_logic_vector(3 downto 0);
signal s_pulseOut_us,s_pulseOut_ds, s_pulseOut_um, s_pulseOut_dm : std_logic;
signal s_enable_ds,s_enable_um, s_enable_dm : std_logic;
signal s_btnU, s_btnD:std_logic;
begin

s_btnU <= up and clk_2Hz and (status(0) or status(1) or status(2) or status(3));
s_btnD <= down and clk_2Hz and (status(0) or status(1) or status(2) or status(3));

--unidade segundo
counter_us: entity work.counter_to9(Behavioral)
    Port Map(clk => clk,
             reset => reset,
             enable => enable,-- frequencia 1hz
             controler => controler,
             up => s_btnU,
             down => s_btnD,
             status => status(0),
             counter => s_counter_us,
             pulseOut => s_pulseOut_us);

s_enable_ds <= (enable and s_pulseOut_us and controler);
  
 -- dezenas segundos  
counter_ds: entity work.counter_to5(Behavioral)
    Port Map(clk => clk,
             reset => reset,
             enable => s_enable_ds,
             controler => controler, 
             up => s_btnU,
             down => s_btnD,
             status => status(1),
             counter => s_counter_ds,
             pulseOut => s_pulseOut_ds);

s_enable_um <= (enable and s_pulseOut_ds and s_pulseOut_us and controler);

--unidade minuto
counter_um: entity work.counter_to9(Behavioral)
    Port Map(clk => clk,
             reset => reset,
             enable => s_enable_um,
             controler => controler, 
             up => s_btnU,
             down => s_btnD,
             status => status(2),
             counter => s_counter_um,
             pulseOut => s_pulseOut_um);

s_enable_dm <= (enable and s_pulseOut_um and s_pulseOut_ds and s_pulseOut_us and controler );

--dezenas minuto  
counter_dm: entity work.counter_to5(Behavioral)
    Port Map(clk => clk,
             reset => reset,
             enable => s_enable_dm,
             controler=>controler,
             up => s_btnU,
             down => s_btnD,
             status => status(3),
             counter => s_counter_dm,
             pulseOut => s_pulseOut_dm);
 

 DM <= s_counter_dm;
 UM <= s_counter_um;
 DS <= s_counter_ds;
 US <=s_counter_us;
 
 
led <= '1' when (s_counter_dm ="0000" and s_counter_ds ="0000" and s_counter_um ="0000" and s_counter_us ="0000") 
           else '0';


end Behavioral;