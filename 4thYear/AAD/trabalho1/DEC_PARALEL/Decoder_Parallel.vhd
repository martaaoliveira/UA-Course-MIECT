LIBRARY ieee;
USE ieee.std_logic_1164.all;

LIBRARY simpleLogic;
USE simpleLogic.all;

ENTITY mC_decoder IS
	PORT (c: 		IN STD_LOGIC_VECTOR (3 DOWNTO 0);
         mIsOne: OUT STD_LOGIC;
			mError: OUT STD_LOGIC);
END mC_decoder;

ARCHITECTURE structure OF mC_decoder IS
  SIGNAL and01, and23, or01, or23, nor01, nor23, nand01, nand23: STD_LOGIC;
  SIGNAL mOne1, mOne2, mZero1, mZero2: STD_LOGIC;
  SIGNAL m_one, m_zero: STD_LOGIC;
  
  COMPONENT gateNOr2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateNOr3
    PORT (x0, x1, x2: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateNAnd2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateAnd2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateOr2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
BEGIN
  -- calculation of mIsOne
  and_01: 	gateAnd2 PORT MAP (c(0), c(1), and01);
  and_23: 	gateAnd2 PORT MAP (c(2), c(3), and23);
  or_01:		gateOr2	PORT MAP (c(0), c(1), or01);
  or_23: 	gateOr2 	PORT MAP (c(2), c(3), or23);
  m_one1:	gateAnd2	PORT MAP (and23, or01, mOne1);
  m_one2:	gateAnd2	PORT MAP (and01, or23, mOne2);
  m_onef:	gateOr2	PORT MAP (mOne1, mOne2, m_one);
  -- calculation of mIsZero
  nand_01: 	gateNAnd2 PORT MAP (c(0), c(1), nand01);
  nand_23: 	gateNAnd2 PORT MAP (c(2), c(3), nand23);
  nor_01:	gateNOr2  PORT MAP (c(0), c(1), nor01);
  nor_23:  	gateNOr2  PORT MAP (c(2), c(3), nor23);
  m_zero1: 	gateAnd2  PORT MAP (nand23, nor01, mZero1);
  m_zero2: 	gateAnd2  PORT MAP (nand01, nor23, mZero2);
  m_zerof: 	gateOr2	  PORT MAP (mZero1, mZero2, m_zero);
  -- calculation of mError
  m_Error: gateNOr2 PORT MAP (m_zero, m_one, mError);
  -- output mIsOne
  mIsOne <= m_one;
END structure;



-- Decoder Complete

LIBRARY ieee;
USE ieee.std_logic_1164.all;

LIBRARY simpleLogic;
USE simpleLogic.all;

ENTITY Decoder_Parallel IS
	PORT (y: 		IN STD_LOGIC_VECTOR (7 DOWNTO 0);
         m: 		OUT STD_LOGIC_VECTOR (3 DOWNTO 0);
			valid:	OUT STD_LOGIC);
END Decoder_Parallel;

ARCHITECTURE structure OF Decoder_Parallel IS
  SIGNAL c0, c1, c2: STD_LOGIC_VECTOR (3 DOWNTO 0);
  SIGNAL m0_One, m1_One, m2_One: STD_LOGIC;
  SIGNAL m0_Error, m1_Error, m2_Error: STD_LOGIC;
  SIGNAL c_valid:		STD_LOGIC;
  SIGNAL m0, m1, m2, m3: STD_LOGIC;
  SIGNAL d0, d1, d2: STD_LOGIC;
  SIGNAL check_y0, check_D, f_check: STD_LOGIC;
  
  COMPONENT gateOr2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateXOr2
    PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateAnd2
	PORT (x0, x1: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateNOr3
    PORT (x0, x1, x2: IN STD_LOGIC;
          y: OUT STD_LOGIC);
  END COMPONENT;
  
  COMPONENT gateXOr4 
	PORT (x0, x1, x2, x3: IN STD_LOGIC;
         y: OUT STD_LOGIC);
	END COMPONENT;
  
  COMPONENT mC_decoder
    PORT (c: 		IN STD_LOGIC_VECTOR (3 DOWNTO 0);
          mIsOne: OUT STD_LOGIC;
			 mError: OUT STD_LOGIC);
  END COMPONENT;  
BEGIN
  -- c0# calculation
  xorc00:	gateXOr2 PORT MAP (y(0), y(1), c0(0));	-- c00 = y0 xor y1
  xorc01:	gateXOr2 PORT MAP (y(2), y(3), c0(1));	-- c01 = y2 xor y3
  xorc02:	gateXOr2 PORT MAP (y(4), y(5), c0(2));	-- c02 = y4 xor y5
  xorc03:	gateXOr2 PORT MAP (y(6), y(7), c0(3));	-- c03 = y6 xor y7
  -- c1# calculation
  xorc10:	gateXOr2 PORT MAP (y(0), y(2), c1(0));	-- c10 = y0 xor y2
  xorc11:	gateXOr2 PORT MAP (y(1), y(3), c1(1));	-- c11 = y1 xor y3
  xorc12:	gateXOr2 PORT MAP (y(4), y(6), c1(2));	-- c12 = y4 xor y6
  xorc13:	gateXOr2 PORT MAP (y(5), y(7), c1(3));	-- c13 = y5 xor y7
  -- c2# calculation
  xorc20:	gateXOr2 PORT MAP (y(0), y(4), c2(0));	-- c20 = y0 xor y4
  xorc21:	gateXOr2 PORT MAP (y(1), y(5), c2(1));	-- c21 = y1 xor y5
  xorc22:	gateXOr2 PORT MAP (y(2), y(6), c2(2));	-- c22 = y2 xor y6
  xorc23:	gateXOr2 PORT MAP (y(3), y(7), c2(3));	-- c23 = y3 xor y7
  
  -- obtain the values of m#_One and m#_Error
  m0isOne: mC_decoder PORT MAP (c0, m0_One, m0_Error);
  m1isOne: mC_decoder PORT MAP (c1, m1_One, m1_Error);
  m2isOne: mC_decoder PORT MAP (c2, m2_One, m2_Error);
  
  Cvalid: gateNOr3 PORT MAP (m0_Error, m1_Error, m2_Error, c_valid);
  
  -- obtain the values of m'0, m'1, m'2
  m_0: gateAnd2 PORT MAP (m0_One, c_valid, m0);
  m_1: gateAnd2 PORT MAP (m1_One, c_valid, m1);
  m_2: gateAnd2 PORT MAP (m2_One, c_valid, m2); 
  
  -- obtain the values of d0, d1, d2
  d_0: gateXOr4 PORT MAP (c0(1), c0(2), c0(3), m0, d0); -- d0 = c01 xor c02 xor c03 xor m'0
  d_1: gateXOr4 PORT MAP (c1(1), c1(2), c1(3), m1, d1); -- d1 = c11 xor c12 xor c13 xor m'1
  d_2: gateXOr4 PORT MAP (c2(1), c2(2), c2(3), m2, d2); -- d2 = c21 xor c22 xor c23 xor m'2
  
  -- check if theres's any error, and if so where it is (y0 or the remaining bits)
  d: 			gateNOr3 PORT MAP (d0, d1, d2, check_D); -- if d = 1, there's no error, or there's an error on y0
  checkY0: gateXOr2	PORT MAP (c0(0), m0, check_y0); -- if d = 1 and check_y0 = 1, there's an error on y0
  
  -- obtain the value of m'3  --fcheck:	gateAnd2 PORT MAP (check_D, check_y0, f_check);
  --m_3: gateXOr2	PORT MAP (y(0), f_check, m(3));
  f_check <= check_D and check_y0;
  m3 <= f_check xor y(0);

  -- outputs
  valid <= c_valid;
  m(0)  <= m0;
  m(1)  <= m1;
  m(2)  <= m2;
  m(3)  <= m3;
END structure;