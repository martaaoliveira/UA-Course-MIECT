----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 16.03.2024 01:42:12
-- Design Name: 
-- Module Name: control_path - Behavioral
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

entity control_path is
  Port (clk: in std_logic;
        reset : in std_logic; 
        btnR: in std_logic;
        start_pause: in std_logic; --btnc
        finished: in std_logic;
        status: out std_logic_vector(3 downto 0);
        controler: out std_logic); -- 1:decrementar  0: pausar
end control_path;

architecture Behavioral of control_path is
    type TState is (START, STOPPED, us, ds, um, dm);
    signal pState, nState: TState := STOPPED; -- present State (estado inicial 59:59)
    
begin

sync_process : process(clk)
    begin
        if (rising_edge (clk)) then
            if (reset = '1') then
                pState <= STOPPED;        
            else
                pState <= nState;
            end if;
         end if;
     end process; 

comb_process : process(clk, pState, reset, start_pause, finished, btnR)
    begin
        case pState is
        
      when STOPPED =>
            status<="0000";
            if (start_pause = '1' ) then
                nState <= START;
            elsif (btnR = '1') then
                nState <= dm;
            else
                nState <= STOPPED;
            end if;              
            controler <= '0';
                
        
         when START =>
                status<="0000";
                controler <= '0';
                if (start_pause = '1') then 
                    nState <= STOPPED;
                elsif (btnR = '1') then
                -- "assim que for ativado o modo de ajuste, o contador para e o dígito das dezenas de minutos começa a piscar"
                    nState <= dm;
                    controler <= '0'; --em modo "pausa"
                else 
                    nState <= START;
                    controler <= '1'; --decrementa normalmente               
                end if; 

            
            when us =>
                status<="0001";
                controler <= '0';
                if(btnR='1') then
                    nState <= STOPPED;
                else
                    nState <=us;
                end if;
             
            
            when ds =>
                status<="0010";
                controler <= '0';
                if(btnR='1') then
                    nState <= us; --passa para o proximo digito
                else
                    nState <=ds; --continua no mesmo 
                end if;
             
             -- ativar o um
            when um =>
                status<="0100";
                controler <= '0';
                if(btnR='1') then
                    nState <= ds;
                else
                    nState <=um;
                end if;
             
             -- ativar o dm
            when dm =>
                status<= "1000";
                controler <= '0';
                if(btnR='1') then
                    nState <= um;
                else
                    nState <=dm;
                end if;  
                    
            when others =>
                status<="0000";
                controler <= '0';
                nState <= STOPPED;
            end case;
        end process;
        
end Behavioral;