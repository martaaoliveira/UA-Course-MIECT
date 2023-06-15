-- Copyright (C) 2020  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and any partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel FPGA IP License Agreement, or other applicable license
-- agreement, including, without limitation, that your use is for
-- the sole purpose of programming logic devices manufactured by
-- Intel and sold by Intel or its authorized distributors.  Please
-- refer to the applicable agreement for further details, at
-- https://fpgasoftware.intel.com/eula.

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 20.1.1 Build 720 11/11/2020 SJ Lite Edition"

-- DATE "11/10/2022 20:11:51"

-- 
-- Device: Altera 5CGXFC7C7F23C8 Package FBGA484
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA_LNSIM;
LIBRARY CYCLONEV;
LIBRARY IEEE;
USE ALTERA_LNSIM.ALTERA_LNSIM_COMPONENTS.ALL;
USE CYCLONEV.CYCLONEV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	Decoder_Parallel IS
    PORT (
	y : IN std_logic_vector(7 DOWNTO 0);
	m : OUT std_logic_vector(3 DOWNTO 0);
	valid : OUT std_logic;
	c_d : OUT std_logic;
	c_y0 : OUT std_logic;
	f_c : OUT std_logic;
	m3c : OUT std_logic
	);
END Decoder_Parallel;

-- Design Ports Information
-- m[0]	=>  Location: PIN_P18,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- m[1]	=>  Location: PIN_M22,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- m[2]	=>  Location: PIN_N16,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- m[3]	=>  Location: PIN_L19,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- valid	=>  Location: PIN_K22,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- c_d	=>  Location: PIN_L18,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- c_y0	=>  Location: PIN_N21,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- f_c	=>  Location: PIN_M16,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- m3c	=>  Location: PIN_K21,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[0]	=>  Location: PIN_M18,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[1]	=>  Location: PIN_N19,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[2]	=>  Location: PIN_M20,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[3]	=>  Location: PIN_N20,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[4]	=>  Location: PIN_M21,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[5]	=>  Location: PIN_L22,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[6]	=>  Location: PIN_K17,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- y[7]	=>  Location: PIN_L17,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF Decoder_Parallel IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_y : std_logic_vector(7 DOWNTO 0);
SIGNAL ww_m : std_logic_vector(3 DOWNTO 0);
SIGNAL ww_valid : std_logic;
SIGNAL ww_c_d : std_logic;
SIGNAL ww_c_y0 : std_logic;
SIGNAL ww_f_c : std_logic;
SIGNAL ww_m3c : std_logic;
SIGNAL \~QUARTUS_CREATED_GND~I_combout\ : std_logic;
SIGNAL \y[3]~input_o\ : std_logic;
SIGNAL \y[2]~input_o\ : std_logic;
SIGNAL \xorc01|y~combout\ : std_logic;
SIGNAL \y[6]~input_o\ : std_logic;
SIGNAL \y[7]~input_o\ : std_logic;
SIGNAL \xorc03|y~combout\ : std_logic;
SIGNAL \y[1]~input_o\ : std_logic;
SIGNAL \xorc11|y~combout\ : std_logic;
SIGNAL \y[0]~input_o\ : std_logic;
SIGNAL \y[5]~input_o\ : std_logic;
SIGNAL \xorc13|y~combout\ : std_logic;
SIGNAL \y[4]~input_o\ : std_logic;
SIGNAL \xorc12|y~combout\ : std_logic;
SIGNAL \m1isOne|m_Error|y~0_combout\ : std_logic;
SIGNAL \xorc00|y~combout\ : std_logic;
SIGNAL \xorc02|y~combout\ : std_logic;
SIGNAL \xorc23|y~combout\ : std_logic;
SIGNAL \xorc22|y~combout\ : std_logic;
SIGNAL \xorc21|y~combout\ : std_logic;
SIGNAL \xorc20|y~combout\ : std_logic;
SIGNAL \m2isOne|m_Error|y~0_combout\ : std_logic;
SIGNAL \m_0|y~combout\ : std_logic;
SIGNAL \m1isOne|m_onef|y~0_combout\ : std_logic;
SIGNAL \m0isOne|m_Error|y~0_combout\ : std_logic;
SIGNAL \m_1|y~combout\ : std_logic;
SIGNAL \m_2|y~combout\ : std_logic;
SIGNAL \d_0|y~combout\ : std_logic;
SIGNAL \d_1|y~0_combout\ : std_logic;
SIGNAL \checkY0|y~combout\ : std_logic;
SIGNAL \d_2|y~combout\ : std_logic;
SIGNAL \m3~0_combout\ : std_logic;
SIGNAL \Cvalid|y~0_combout\ : std_logic;
SIGNAL \d|y~0_combout\ : std_logic;
SIGNAL \f_check~0_combout\ : std_logic;
SIGNAL \ALT_INV_y[7]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[6]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[5]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[4]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[3]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[2]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[1]~input_o\ : std_logic;
SIGNAL \ALT_INV_y[0]~input_o\ : std_logic;
SIGNAL \d|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \Cvalid|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \d_2|ALT_INV_y~combout\ : std_logic;
SIGNAL \d_0|ALT_INV_y~combout\ : std_logic;
SIGNAL \d_1|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \checkY0|ALT_INV_y~combout\ : std_logic;
SIGNAL \m_1|ALT_INV_y~combout\ : std_logic;
SIGNAL \m0isOne|m_Error|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \m1isOne|m_onef|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \m1isOne|m_Error|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \xorc13|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc12|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc11|ALT_INV_y~combout\ : std_logic;
SIGNAL \m2isOne|m_Error|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \xorc23|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc22|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc21|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc20|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc03|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc02|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc00|ALT_INV_y~combout\ : std_logic;
SIGNAL \xorc01|ALT_INV_y~combout\ : std_logic;

BEGIN

ww_y <= y;
m <= ww_m;
valid <= ww_valid;
c_d <= ww_c_d;
c_y0 <= ww_c_y0;
f_c <= ww_f_c;
m3c <= ww_m3c;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;
\ALT_INV_y[7]~input_o\ <= NOT \y[7]~input_o\;
\ALT_INV_y[6]~input_o\ <= NOT \y[6]~input_o\;
\ALT_INV_y[5]~input_o\ <= NOT \y[5]~input_o\;
\ALT_INV_y[4]~input_o\ <= NOT \y[4]~input_o\;
\ALT_INV_y[3]~input_o\ <= NOT \y[3]~input_o\;
\ALT_INV_y[2]~input_o\ <= NOT \y[2]~input_o\;
\ALT_INV_y[1]~input_o\ <= NOT \y[1]~input_o\;
\ALT_INV_y[0]~input_o\ <= NOT \y[0]~input_o\;
\d|ALT_INV_y~0_combout\ <= NOT \d|y~0_combout\;
\Cvalid|ALT_INV_y~0_combout\ <= NOT \Cvalid|y~0_combout\;
\d_2|ALT_INV_y~combout\ <= NOT \d_2|y~combout\;
\d_0|ALT_INV_y~combout\ <= NOT \d_0|y~combout\;
\d_1|ALT_INV_y~0_combout\ <= NOT \d_1|y~0_combout\;
\checkY0|ALT_INV_y~combout\ <= NOT \checkY0|y~combout\;
\m_1|ALT_INV_y~combout\ <= NOT \m_1|y~combout\;
\m0isOne|m_Error|ALT_INV_y~0_combout\ <= NOT \m0isOne|m_Error|y~0_combout\;
\m1isOne|m_onef|ALT_INV_y~0_combout\ <= NOT \m1isOne|m_onef|y~0_combout\;
\m1isOne|m_Error|ALT_INV_y~0_combout\ <= NOT \m1isOne|m_Error|y~0_combout\;
\xorc13|ALT_INV_y~combout\ <= NOT \xorc13|y~combout\;
\xorc12|ALT_INV_y~combout\ <= NOT \xorc12|y~combout\;
\xorc11|ALT_INV_y~combout\ <= NOT \xorc11|y~combout\;
\m2isOne|m_Error|ALT_INV_y~0_combout\ <= NOT \m2isOne|m_Error|y~0_combout\;
\xorc23|ALT_INV_y~combout\ <= NOT \xorc23|y~combout\;
\xorc22|ALT_INV_y~combout\ <= NOT \xorc22|y~combout\;
\xorc21|ALT_INV_y~combout\ <= NOT \xorc21|y~combout\;
\xorc20|ALT_INV_y~combout\ <= NOT \xorc20|y~combout\;
\xorc03|ALT_INV_y~combout\ <= NOT \xorc03|y~combout\;
\xorc02|ALT_INV_y~combout\ <= NOT \xorc02|y~combout\;
\xorc00|ALT_INV_y~combout\ <= NOT \xorc00|y~combout\;
\xorc01|ALT_INV_y~combout\ <= NOT \xorc01|y~combout\;

-- Location: IOOBUF_X89_Y9_N56
\m[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \m_0|y~combout\,
	devoe => ww_devoe,
	o => ww_m(0));

-- Location: IOOBUF_X89_Y36_N39
\m[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \m_1|y~combout\,
	devoe => ww_devoe,
	o => ww_m(1));

-- Location: IOOBUF_X89_Y35_N45
\m[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \m_2|y~combout\,
	devoe => ww_devoe,
	o => ww_m(2));

-- Location: IOOBUF_X89_Y38_N5
\m[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \m3~0_combout\,
	devoe => ww_devoe,
	o => ww_m(3));

-- Location: IOOBUF_X89_Y38_N56
\valid~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \Cvalid|ALT_INV_y~0_combout\,
	devoe => ww_devoe,
	o => ww_valid);

-- Location: IOOBUF_X89_Y38_N22
\c_d~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \d|ALT_INV_y~0_combout\,
	devoe => ww_devoe,
	o => ww_c_d);

-- Location: IOOBUF_X89_Y35_N96
\c_y0~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \checkY0|y~combout\,
	devoe => ww_devoe,
	o => ww_c_y0);

-- Location: IOOBUF_X89_Y35_N62
\f_c~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \f_check~0_combout\,
	devoe => ww_devoe,
	o => ww_f_c);

-- Location: IOOBUF_X89_Y38_N39
\m3c~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \m3~0_combout\,
	devoe => ww_devoe,
	o => ww_m3c);

-- Location: IOIBUF_X89_Y35_N78
\y[3]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(3),
	o => \y[3]~input_o\);

-- Location: IOIBUF_X89_Y37_N38
\y[2]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(2),
	o => \y[2]~input_o\);

-- Location: LABCELL_X88_Y36_N33
\xorc01|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc01|y~combout\ = ( !\y[3]~input_o\ & ( \y[2]~input_o\ ) ) # ( \y[3]~input_o\ & ( !\y[2]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111111111111111111110000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datae => \ALT_INV_y[3]~input_o\,
	dataf => \ALT_INV_y[2]~input_o\,
	combout => \xorc01|y~combout\);

-- Location: IOIBUF_X89_Y37_N4
\y[6]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(6),
	o => \y[6]~input_o\);

-- Location: IOIBUF_X89_Y37_N21
\y[7]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(7),
	o => \y[7]~input_o\);

-- Location: LABCELL_X88_Y36_N12
\xorc03|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc03|y~combout\ = ( \y[7]~input_o\ & ( !\y[6]~input_o\ ) ) # ( !\y[7]~input_o\ & ( \y[6]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100001111111100001111000000001111000011111111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_y[6]~input_o\,
	datae => \ALT_INV_y[7]~input_o\,
	combout => \xorc03|y~combout\);

-- Location: IOIBUF_X89_Y36_N4
\y[1]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(1),
	o => \y[1]~input_o\);

-- Location: MLABCELL_X87_Y36_N48
\xorc11|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc11|y~combout\ = ( \y[1]~input_o\ & ( !\y[3]~input_o\ ) ) # ( !\y[1]~input_o\ & ( \y[3]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100001111000011110000111111110000111100001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_y[3]~input_o\,
	dataf => \ALT_INV_y[1]~input_o\,
	combout => \xorc11|y~combout\);

-- Location: IOIBUF_X89_Y36_N21
\y[0]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(0),
	o => \y[0]~input_o\);

-- Location: IOIBUF_X89_Y36_N55
\y[5]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(5),
	o => \y[5]~input_o\);

-- Location: LABCELL_X88_Y36_N6
\xorc13|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc13|y~combout\ = !\y[5]~input_o\ $ (!\y[7]~input_o\)

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101101001011010010110100101101001011010010110100101101001011010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[5]~input_o\,
	datac => \ALT_INV_y[7]~input_o\,
	combout => \xorc13|y~combout\);

-- Location: IOIBUF_X89_Y37_N55
\y[4]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_y(4),
	o => \y[4]~input_o\);

-- Location: LABCELL_X88_Y36_N3
\xorc12|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc12|y~combout\ = ( \y[4]~input_o\ & ( !\y[6]~input_o\ ) ) # ( !\y[4]~input_o\ & ( \y[6]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010110101010101010101010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[6]~input_o\,
	dataf => \ALT_INV_y[4]~input_o\,
	combout => \xorc12|y~combout\);

-- Location: MLABCELL_X87_Y36_N33
\m1isOne|m_Error|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \m1isOne|m_Error|y~0_combout\ = ( \y[2]~input_o\ & ( (!\xorc11|y~combout\ & ((!\y[0]~input_o\ & (!\xorc13|y~combout\ $ (!\xorc12|y~combout\))) # (\y[0]~input_o\ & (\xorc13|y~combout\ & \xorc12|y~combout\)))) # (\xorc11|y~combout\ & ((!\y[0]~input_o\ & 
-- (!\xorc13|y~combout\ & !\xorc12|y~combout\)) # (\y[0]~input_o\ & (!\xorc13|y~combout\ $ (!\xorc12|y~combout\))))) ) ) # ( !\y[2]~input_o\ & ( (!\xorc11|y~combout\ & ((!\y[0]~input_o\ & (\xorc13|y~combout\ & \xorc12|y~combout\)) # (\y[0]~input_o\ & 
-- (!\xorc13|y~combout\ $ (!\xorc12|y~combout\))))) # (\xorc11|y~combout\ & ((!\y[0]~input_o\ & (!\xorc13|y~combout\ $ (!\xorc12|y~combout\))) # (\y[0]~input_o\ & (!\xorc13|y~combout\ & !\xorc12|y~combout\)))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0001011001101000000101100110100001001001100100100100100110010010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc11|ALT_INV_y~combout\,
	datab => \ALT_INV_y[0]~input_o\,
	datac => \xorc13|ALT_INV_y~combout\,
	datad => \xorc12|ALT_INV_y~combout\,
	dataf => \ALT_INV_y[2]~input_o\,
	combout => \m1isOne|m_Error|y~0_combout\);

-- Location: MLABCELL_X87_Y36_N30
\xorc00|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc00|y~combout\ = !\y[0]~input_o\ $ (!\y[1]~input_o\)

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011110000111100001111000011110000111100001111000011110000111100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ALT_INV_y[0]~input_o\,
	datac => \ALT_INV_y[1]~input_o\,
	combout => \xorc00|y~combout\);

-- Location: LABCELL_X88_Y36_N9
\xorc02|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc02|y~combout\ = ( \y[4]~input_o\ & ( !\y[5]~input_o\ ) ) # ( !\y[4]~input_o\ & ( \y[5]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010110101010101010101010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[5]~input_o\,
	dataf => \ALT_INV_y[4]~input_o\,
	combout => \xorc02|y~combout\);

-- Location: LABCELL_X88_Y36_N0
\xorc23|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc23|y~combout\ = !\y[3]~input_o\ $ (!\y[7]~input_o\)

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111111110000000011111111000000001111111100000000111111110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_y[3]~input_o\,
	datad => \ALT_INV_y[7]~input_o\,
	combout => \xorc23|y~combout\);

-- Location: LABCELL_X88_Y36_N57
\xorc22|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc22|y~combout\ = ( \y[2]~input_o\ & ( !\y[6]~input_o\ ) ) # ( !\y[2]~input_o\ & ( \y[6]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010110101010101010101010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[6]~input_o\,
	dataf => \ALT_INV_y[2]~input_o\,
	combout => \xorc22|y~combout\);

-- Location: MLABCELL_X87_Y36_N36
\xorc21|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc21|y~combout\ = ( \y[5]~input_o\ & ( !\y[1]~input_o\ ) ) # ( !\y[5]~input_o\ & ( \y[1]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100001111000011110000111111110000111100001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_y[1]~input_o\,
	dataf => \ALT_INV_y[5]~input_o\,
	combout => \xorc21|y~combout\);

-- Location: LABCELL_X88_Y36_N18
\xorc20|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xorc20|y~combout\ = ( \y[4]~input_o\ & ( !\y[0]~input_o\ ) ) # ( !\y[4]~input_o\ & ( \y[0]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100001111000011110000111111110000111100001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_y[0]~input_o\,
	dataf => \ALT_INV_y[4]~input_o\,
	combout => \xorc20|y~combout\);

-- Location: MLABCELL_X87_Y36_N15
\m2isOne|m_Error|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \m2isOne|m_Error|y~0_combout\ = ( \xorc20|y~combout\ & ( (!\xorc23|y~combout\ & (!\xorc22|y~combout\ $ (!\xorc21|y~combout\))) # (\xorc23|y~combout\ & (!\xorc22|y~combout\ & !\xorc21|y~combout\)) ) ) # ( !\xorc20|y~combout\ & ( (!\xorc23|y~combout\ & 
-- (\xorc22|y~combout\ & \xorc21|y~combout\)) # (\xorc23|y~combout\ & (!\xorc22|y~combout\ $ (!\xorc21|y~combout\))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000010101011010000001010101101001011010101000000101101010100000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc23|ALT_INV_y~combout\,
	datac => \xorc22|ALT_INV_y~combout\,
	datad => \xorc21|ALT_INV_y~combout\,
	dataf => \xorc20|ALT_INV_y~combout\,
	combout => \m2isOne|m_Error|y~0_combout\);

-- Location: MLABCELL_X87_Y36_N24
\m_0|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \m_0|y~combout\ = ( \xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( (!\m1isOne|m_Error|y~0_combout\ & ((!\xorc01|y~combout\ & (\xorc03|y~combout\ & \xorc00|y~combout\)) # (\xorc01|y~combout\ & ((\xorc00|y~combout\) # (\xorc03|y~combout\))))) ) ) 
-- ) # ( !\xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( (\xorc01|y~combout\ & (\xorc03|y~combout\ & (!\m1isOne|m_Error|y~0_combout\ & \xorc00|y~combout\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000010000000100000111000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc01|ALT_INV_y~combout\,
	datab => \xorc03|ALT_INV_y~combout\,
	datac => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	datad => \xorc00|ALT_INV_y~combout\,
	datae => \xorc02|ALT_INV_y~combout\,
	dataf => \m2isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \m_0|y~combout\);

-- Location: MLABCELL_X87_Y36_N0
\m1isOne|m_onef|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \m1isOne|m_onef|y~0_combout\ = ( \xorc13|y~combout\ & ( \y[2]~input_o\ & ( (!\xorc12|y~combout\ & (!\y[0]~input_o\ & \xorc11|y~combout\)) # (\xorc12|y~combout\ & ((!\y[0]~input_o\) # (\xorc11|y~combout\))) ) ) ) # ( !\xorc13|y~combout\ & ( \y[2]~input_o\ 
-- & ( (\xorc12|y~combout\ & (!\y[0]~input_o\ & \xorc11|y~combout\)) ) ) ) # ( \xorc13|y~combout\ & ( !\y[2]~input_o\ & ( (!\xorc12|y~combout\ & (\y[0]~input_o\ & \xorc11|y~combout\)) # (\xorc12|y~combout\ & ((\xorc11|y~combout\) # (\y[0]~input_o\))) ) ) ) # 
-- ( !\xorc13|y~combout\ & ( !\y[2]~input_o\ & ( (\xorc12|y~combout\ & (\y[0]~input_o\ & \xorc11|y~combout\)) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000100000001000101110001011100000100000001000100110101001101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc12|ALT_INV_y~combout\,
	datab => \ALT_INV_y[0]~input_o\,
	datac => \xorc11|ALT_INV_y~combout\,
	datae => \xorc13|ALT_INV_y~combout\,
	dataf => \ALT_INV_y[2]~input_o\,
	combout => \m1isOne|m_onef|y~0_combout\);

-- Location: MLABCELL_X87_Y36_N51
\m0isOne|m_Error|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \m0isOne|m_Error|y~0_combout\ = (!\xorc01|y~combout\ & ((!\xorc03|y~combout\ & (\xorc00|y~combout\ & \xorc02|y~combout\)) # (\xorc03|y~combout\ & (!\xorc00|y~combout\ $ (!\xorc02|y~combout\))))) # (\xorc01|y~combout\ & ((!\xorc03|y~combout\ & 
-- (!\xorc00|y~combout\ $ (!\xorc02|y~combout\))) # (\xorc03|y~combout\ & (!\xorc00|y~combout\ & !\xorc02|y~combout\))))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0001011001101000000101100110100000010110011010000001011001101000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc01|ALT_INV_y~combout\,
	datab => \xorc03|ALT_INV_y~combout\,
	datac => \xorc00|ALT_INV_y~combout\,
	datad => \xorc02|ALT_INV_y~combout\,
	combout => \m0isOne|m_Error|y~0_combout\);

-- Location: MLABCELL_X87_Y36_N12
\m_1|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \m_1|y~combout\ = ( !\m1isOne|m_Error|y~0_combout\ & ( (!\m2isOne|m_Error|y~0_combout\ & (\m1isOne|m_onef|y~0_combout\ & !\m0isOne|m_Error|y~0_combout\)) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000110000000000000011000000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \m2isOne|m_Error|ALT_INV_y~0_combout\,
	datac => \m1isOne|m_onef|ALT_INV_y~0_combout\,
	datad => \m0isOne|m_Error|ALT_INV_y~0_combout\,
	dataf => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \m_1|y~combout\);

-- Location: MLABCELL_X87_Y36_N6
\m_2|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \m_2|y~combout\ = ( \xorc21|y~combout\ & ( !\m0isOne|m_Error|y~0_combout\ & ( (!\m1isOne|m_Error|y~0_combout\ & ((!\xorc22|y~combout\ & (\xorc23|y~combout\ & \xorc20|y~combout\)) # (\xorc22|y~combout\ & ((\xorc20|y~combout\) # (\xorc23|y~combout\))))) ) ) 
-- ) # ( !\xorc21|y~combout\ & ( !\m0isOne|m_Error|y~0_combout\ & ( (!\m1isOne|m_Error|y~0_combout\ & (\xorc22|y~combout\ & (\xorc23|y~combout\ & \xorc20|y~combout\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000010000000100010101000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	datab => \xorc22|ALT_INV_y~combout\,
	datac => \xorc23|ALT_INV_y~combout\,
	datad => \xorc20|ALT_INV_y~combout\,
	datae => \xorc21|ALT_INV_y~combout\,
	dataf => \m0isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \m_2|y~combout\);

-- Location: MLABCELL_X87_Y36_N18
\d_0|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \d_0|y~combout\ = ( \xorc02|y~combout\ & ( \m2isOne|m_Error|y~0_combout\ & ( !\xorc01|y~combout\ $ (!\xorc03|y~combout\) ) ) ) # ( !\xorc02|y~combout\ & ( \m2isOne|m_Error|y~0_combout\ & ( !\xorc01|y~combout\ $ (\xorc03|y~combout\) ) ) ) # ( 
-- \xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( (!\xorc01|y~combout\ & (\xorc03|y~combout\ & ((!\xorc00|y~combout\) # (\m1isOne|m_Error|y~0_combout\)))) # (\xorc01|y~combout\ & ((!\xorc03|y~combout\ & ((!\xorc00|y~combout\) # 
-- (\m1isOne|m_Error|y~0_combout\))) # (\xorc03|y~combout\ & (!\m1isOne|m_Error|y~0_combout\)))) ) ) ) # ( !\xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( (!\xorc01|y~combout\ & (!\xorc03|y~combout\)) # (\xorc01|y~combout\ & (\xorc03|y~combout\ & 
-- ((!\xorc00|y~combout\) # (\m1isOne|m_Error|y~0_combout\)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1001100110001001011101100001011010011001100110010110011001100110",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc01|ALT_INV_y~combout\,
	datab => \xorc03|ALT_INV_y~combout\,
	datac => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	datad => \xorc00|ALT_INV_y~combout\,
	datae => \xorc02|ALT_INV_y~combout\,
	dataf => \m2isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \d_0|y~combout\);

-- Location: LABCELL_X88_Y36_N36
\d_1|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \d_1|y~0_combout\ = ( \y[7]~input_o\ & ( \y[4]~input_o\ & ( !\y[5]~input_o\ $ (!\y[3]~input_o\ $ (!\y[6]~input_o\ $ (!\y[1]~input_o\))) ) ) ) # ( !\y[7]~input_o\ & ( \y[4]~input_o\ & ( !\y[5]~input_o\ $ (!\y[3]~input_o\ $ (!\y[6]~input_o\ $ 
-- (\y[1]~input_o\))) ) ) ) # ( \y[7]~input_o\ & ( !\y[4]~input_o\ & ( !\y[5]~input_o\ $ (!\y[3]~input_o\ $ (!\y[6]~input_o\ $ (\y[1]~input_o\))) ) ) ) # ( !\y[7]~input_o\ & ( !\y[4]~input_o\ & ( !\y[5]~input_o\ $ (!\y[3]~input_o\ $ (!\y[6]~input_o\ $ 
-- (!\y[1]~input_o\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0110100110010110100101100110100110010110011010010110100110010110",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[5]~input_o\,
	datab => \ALT_INV_y[3]~input_o\,
	datac => \ALT_INV_y[6]~input_o\,
	datad => \ALT_INV_y[1]~input_o\,
	datae => \ALT_INV_y[7]~input_o\,
	dataf => \ALT_INV_y[4]~input_o\,
	combout => \d_1|y~0_combout\);

-- Location: MLABCELL_X87_Y36_N42
\checkY0|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \checkY0|y~combout\ = ( \xorc02|y~combout\ & ( \m2isOne|m_Error|y~0_combout\ & ( \xorc00|y~combout\ ) ) ) # ( !\xorc02|y~combout\ & ( \m2isOne|m_Error|y~0_combout\ & ( \xorc00|y~combout\ ) ) ) # ( \xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( 
-- (!\m1isOne|m_Error|y~0_combout\ & ((!\xorc01|y~combout\ & (!\xorc03|y~combout\ & \xorc00|y~combout\)) # (\xorc01|y~combout\ & (\xorc03|y~combout\ & !\xorc00|y~combout\)))) # (\m1isOne|m_Error|y~0_combout\ & (((\xorc00|y~combout\)))) ) ) ) # ( 
-- !\xorc02|y~combout\ & ( !\m2isOne|m_Error|y~0_combout\ & ( (\xorc00|y~combout\ & ((!\xorc01|y~combout\) # ((!\xorc03|y~combout\) # (\m1isOne|m_Error|y~0_combout\)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011101111000100001000111100000000111111110000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc01|ALT_INV_y~combout\,
	datab => \xorc03|ALT_INV_y~combout\,
	datac => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	datad => \xorc00|ALT_INV_y~combout\,
	datae => \xorc02|ALT_INV_y~combout\,
	dataf => \m2isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \checkY0|y~combout\);

-- Location: LABCELL_X88_Y36_N42
\d_2|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \d_2|y~combout\ = ( \xorc23|y~combout\ & ( \xorc20|y~combout\ & ( (!\xorc21|y~combout\ & (\xorc22|y~combout\ & ((\m0isOne|m_Error|y~0_combout\) # (\m1isOne|m_Error|y~0_combout\)))) # (\xorc21|y~combout\ & (!\xorc22|y~combout\ $ 
-- (((!\m1isOne|m_Error|y~0_combout\ & !\m0isOne|m_Error|y~0_combout\))))) ) ) ) # ( !\xorc23|y~combout\ & ( \xorc20|y~combout\ & ( (!\xorc21|y~combout\ & (((!\xorc22|y~combout\)))) # (\xorc21|y~combout\ & (\xorc22|y~combout\ & 
-- ((\m0isOne|m_Error|y~0_combout\) # (\m1isOne|m_Error|y~0_combout\)))) ) ) ) # ( \xorc23|y~combout\ & ( !\xorc20|y~combout\ & ( (!\xorc21|y~combout\ & (((\xorc22|y~combout\)))) # (\xorc21|y~combout\ & ((!\xorc22|y~combout\) # 
-- ((!\m1isOne|m_Error|y~0_combout\ & !\m0isOne|m_Error|y~0_combout\)))) ) ) ) # ( !\xorc23|y~combout\ & ( !\xorc20|y~combout\ & ( !\xorc21|y~combout\ $ (\xorc22|y~combout\) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1010101001010101010101011110101010101010000101010001010101101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \xorc21|ALT_INV_y~combout\,
	datab => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	datac => \m0isOne|m_Error|ALT_INV_y~0_combout\,
	datad => \xorc22|ALT_INV_y~combout\,
	datae => \xorc23|ALT_INV_y~combout\,
	dataf => \xorc20|ALT_INV_y~combout\,
	combout => \d_2|y~combout\);

-- Location: LABCELL_X88_Y36_N48
\m3~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \m3~0_combout\ = ( \m_1|y~combout\ & ( \d_2|y~combout\ & ( !\y[0]~input_o\ $ (((!\d_0|y~combout\) # ((!\d_1|y~0_combout\) # (!\checkY0|y~combout\)))) ) ) ) # ( !\m_1|y~combout\ & ( \d_2|y~combout\ & ( !\y[0]~input_o\ $ (((!\d_0|y~combout\) # 
-- ((!\checkY0|y~combout\) # (\d_1|y~0_combout\)))) ) ) ) # ( \m_1|y~combout\ & ( !\d_2|y~combout\ & ( \y[0]~input_o\ ) ) ) # ( !\m_1|y~combout\ & ( !\d_2|y~combout\ & ( \y[0]~input_o\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010101010101011001010101010101010110",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_y[0]~input_o\,
	datab => \d_0|ALT_INV_y~combout\,
	datac => \d_1|ALT_INV_y~0_combout\,
	datad => \checkY0|ALT_INV_y~combout\,
	datae => \m_1|ALT_INV_y~combout\,
	dataf => \d_2|ALT_INV_y~combout\,
	combout => \m3~0_combout\);

-- Location: MLABCELL_X87_Y36_N54
\Cvalid|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \Cvalid|y~0_combout\ = ( \m1isOne|m_Error|y~0_combout\ ) # ( !\m1isOne|m_Error|y~0_combout\ & ( (\m0isOne|m_Error|y~0_combout\) # (\m2isOne|m_Error|y~0_combout\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011111100111111001111110011111111111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \m2isOne|m_Error|ALT_INV_y~0_combout\,
	datac => \m0isOne|m_Error|ALT_INV_y~0_combout\,
	dataf => \m1isOne|m_Error|ALT_INV_y~0_combout\,
	combout => \Cvalid|y~0_combout\);

-- Location: LABCELL_X88_Y36_N27
\d|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \d|y~0_combout\ = ( \d_2|y~combout\ & ( (!\d_0|y~combout\) # (!\d_1|y~0_combout\ $ (!\m_1|y~combout\)) ) ) # ( !\d_2|y~combout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111111001111111111001100111111111100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \d_0|ALT_INV_y~combout\,
	datac => \d_1|ALT_INV_y~0_combout\,
	datad => \m_1|ALT_INV_y~combout\,
	dataf => \d_2|ALT_INV_y~combout\,
	combout => \d|y~0_combout\);

-- Location: LABCELL_X88_Y36_N24
\f_check~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \f_check~0_combout\ = ( \d_2|y~combout\ & ( (\d_0|y~combout\ & (\checkY0|y~combout\ & (!\m_1|y~combout\ $ (\d_1|y~0_combout\)))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000001000010000000000100001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \m_1|ALT_INV_y~combout\,
	datab => \d_0|ALT_INV_y~combout\,
	datac => \d_1|ALT_INV_y~0_combout\,
	datad => \checkY0|ALT_INV_y~combout\,
	dataf => \d_2|ALT_INV_y~combout\,
	combout => \f_check~0_combout\);

-- Location: LABCELL_X66_Y30_N0
\~QUARTUS_CREATED_GND~I\ : cyclonev_lcell_comb
-- Equation(s):

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
;
END structure;


