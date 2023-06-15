LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateAnd2 IS
  PORT (x0, x1: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateAnd2;

ARCHITECTURE logicFunction OF gateAnd2 IS
BEGIN
  y <= x0 AND x1;
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateOr2 IS
  PORT (x0, x1: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateOr2;

ARCHITECTURE logicFunction OF gateOr2 IS
BEGIN
  y <= x0 OR x1;
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateXOr2 IS
  PORT (x0, x1: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateXOr2;

ARCHITECTURE logicFunction OF gateXOr2 IS
BEGIN
  y <= x0 XOR x1;
END logicFunction;


LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateNOr2 IS
  PORT (x0, x1: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateNOr2;

ARCHITECTURE logicFunction OF gateNOr2 IS
BEGIN
  y <= NOT(x0 OR x1);
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateNAnd2 IS
  PORT (x0, x1: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateNAnd2;

ARCHITECTURE logicFunction OF gateNAnd2 IS
BEGIN
  y <= NOT(x0 AND x1);
END logicFunction;

LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateNOr3 IS
  PORT (x0, x1, x2: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateNOr3;

ARCHITECTURE logicFunction OF gateNOr3 IS
BEGIN
  y <= NOT(x0 OR x1 OR x2);
END logicFunction;


LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY gateXOr4 IS
  PORT (x0, x1, x2, x3: IN STD_LOGIC;
        y: OUT STD_LOGIC);
END gateXOr4;

ARCHITECTURE logicFunction OF gateXOr4 IS
BEGIN
  y <= (x0 XOR x1 XOR x2 XOR x3);
END logicFunction;

