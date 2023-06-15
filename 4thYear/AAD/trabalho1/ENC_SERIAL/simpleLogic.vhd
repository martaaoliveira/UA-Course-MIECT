LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateOr2 IS
  PORT (x1, x2: IN STD_LOGIC;
        y:      OUT STD_LOGIC);
END gateOr2;

ARCHITECTURE logicFunction OF gateOr2 IS
BEGIN
  y <= x1 OR x2;
END logicFunction;


LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateAnd2 IS
  PORT (x1, x2: IN STD_LOGIC;
        y:      OUT STD_LOGIC);
END gateAnd2;

ARCHITECTURE logicFunction OF gateAnd2 IS
BEGIN
  y <= x1 AND x2;
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateNand2 IS
  PORT (x1, x2: IN STD_LOGIC;
        y:      OUT STD_LOGIC);
END gateNand2;

ARCHITECTURE logicFunction OF gateNand2 IS
BEGIN
  y <= NOT (x1 AND x2);
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateNor2 IS
  PORT (x1, x2: IN STD_LOGIC;
        y:      OUT STD_LOGIC);
END gateNor2;

ARCHITECTURE logicFunction OF gateNor2 IS
BEGIN
  y <= NOT (x1 OR x2);
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateXor2 IS
  PORT (x1, x2: IN STD_LOGIC;
        y:      OUT STD_LOGIC);
END gateXor2;

ARCHITECTURE logicFunction OF gateXor2 IS
BEGIN
  y <= x1 XOR x2;
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateXor8 IS
  PORT (x1, x2: IN STD_LOGIC_VECTOR(7 DOWNTO 0);
        y:      OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
END gateXor8;

ARCHITECTURE structure OF gateXor8 IS
COMPONENT gateXor2
    PORT (x1, x2: IN STD_LOGIC;
          y:      OUT STD_LOGIC);
  END COMPONENT;
BEGIN
 y0: gateXor2 PORT MAP (x1(0), x2(0), y(0));
 y1: gateXor2 PORT MAP (x1(1), x2(1), y(1));
 y2: gateXor2 PORT MAP (x1(2), x2(2), y(2));
 y3: gateXor2 PORT MAP (x1(3), x2(3), y(3));
 y4: gateXor2 PORT MAP (x1(4), x2(4), y(4));
 y5: gateXor2 PORT MAP (x1(5), x2(5), y(5));
 y6: gateXor2 PORT MAP (x1(6), x2(6), y(6));
 y7: gateXor2 PORT MAP (x1(7), x2(7), y(7));
END structure;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateAnd8 IS
  PORT (x1, x2: IN STD_LOGIC_VECTOR(7 DOWNTO 0);
        y:      OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
END gateAnd8;

ARCHITECTURE structure OF gateAnd8 IS
COMPONENT gateAnd2
    PORT (x1, x2: IN STD_LOGIC;
          y:      OUT STD_LOGIC);
  END COMPONENT;
BEGIN
 y0: gateAnd2 PORT MAP (x1(0), x2(0), y(0));
 y1: gateAnd2 PORT MAP (x1(1), x2(1), y(1));
 y2: gateAnd2 PORT MAP (x1(2), x2(2), y(2));
 y3: gateAnd2 PORT MAP (x1(3), x2(3), y(3));
 y4: gateAnd2 PORT MAP (x1(4), x2(4), y(4));
 y5: gateAnd2 PORT MAP (x1(5), x2(5), y(5));
 y6: gateAnd2 PORT MAP (x1(6), x2(6), y(6));
 y7: gateAnd2 PORT MAP (x1(7), x2(7), y(7));
END structure;
