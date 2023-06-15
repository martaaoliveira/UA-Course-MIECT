LIBRARY ieee;
USE ieee.std_logic_1164.all;

LIBRARY control;
USE control.all;

LIBRARY storeDev;
USE storeDev.all;

LIBRARY simpleLogic;
USE simpleLogic.all;

ENTITY Encoder_BitSerial IS
  PORT (nGRst: IN STD_LOGIC;
        clk:   IN STD_LOGIC;
        mIn:   IN STD_LOGIC;
        x:   	OUT STD_LOGIC_VECTOR (7 DOWNTO 0);
		  valid:	OUT STD_LOGIC);
END Encoder_BitSerial;

ARCHITECTURE structure OF Encoder_BitSerial IS
  SIGNAL iNSet, iNRst, m, clk0, valid_stat: STD_LOGIC;
  SIGNAL m_8bit, and_out, xor_in, xor_out: STD_LOGIC_VECTOR (7 DOWNTO 0);
  SIGNAL s_k, s_x: STD_LOGIC_VECTOR (7 DOWNTO 0);
  SIGNAL stat:  STD_LOGIC_VECTOR (2 DOWNTO 0);
  COMPONENT gateAnd2
    PORT (x1, x2:  IN STD_LOGIC;
          y:  OUT STD_LOGIC);
  END COMPONENT;
  COMPONENT gateOr2
    PORT (x1, x2:  IN STD_LOGIC;
          y:  OUT STD_LOGIC);
  END COMPONENT;
  COMPONENT gateXor8
    PORT (x1, x2:  IN STD_LOGIC_VECTOR (7 DOWNTO 0);
          y:  OUT STD_LOGIC_VECTOR (7 DOWNTO 0));
  END COMPONENT;
  COMPONENT gateAnd8
    PORT (x1, x2:  IN STD_LOGIC_VECTOR (7 DOWNTO 0);
          y:  OUT STD_LOGIC_VECTOR (7 DOWNTO 0));
  END COMPONENT;
  COMPONENT flipFlopDPET
    PORT (clk, D: IN STD_LOGIC;
          nSet, nRst: IN STD_LOGIC;
          Q, nQ: OUT STD_LOGIC);
  END COMPONENT;
  COMPONENT parReg_8bit
    PORT (nSet: IN STD_LOGIC;
			 nRst: IN STD_LOGIC;
          clk: IN STD_LOGIC;
          D: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
          Q: OUT STD_LOGIC_VECTOR (7 DOWNTO 0));
  END COMPONENT;
  COMPONENT binCounter_3bit
    PORT (nRst: IN STD_LOGIC;
          clk:  IN STD_LOGIC;
          c:    OUT STD_LOGIC_VECTOR (2 DOWNTO 0));
  END COMPONENT;
  COMPONENT control
    PORT (nGRst: IN STD_LOGIC;
          clk:   IN STD_LOGIC;
          add:   IN STD_LOGIC_VECTOR (2 DOWNTO 0);
          nRst:  OUT STD_LOGIC;
          nSetO: OUT STD_LOGIC;
          clkO:  OUT STD_LOGIC;
			 k:	  OUT STD_LOGIC_VECTOR (7 DOWNTO 0));
  END COMPONENT;
BEGIN
  ff:  	flipFlopDPET PORT MAP (clk, mIn, '1', iNRst, m);
  m_8bit <= (OTHERS=>m);
  and8: 	gateAnd8 PORT MAP (m_8bit, s_k, and_out);
  xor8:	gateXor8 PORT MAP (and_out, xor_in, xor_out);
  pr8: 	parReg_8bit PORT MAP ('1', iNRst, clk, xor_out, xor_in);
  bc:  	binCounter_3bit PORT MAP (iNRst, clk, stat);
  con: 	control  PORT MAP (nGRst, clk, stat, iNRst, iNSet, clk0, s_k);
  pr8out: parReg_8bit PORT MAP (iNSet, iNRst, clk0, xor_out, s_x);
  
  validStat: gateAnd2 PORT MAP (stat(2), stat(0), valid_stat);
  validOut:  gateOr2 PORT MAP (clk0, valid_stat, valid);
  x <= s_x;
 END structure;