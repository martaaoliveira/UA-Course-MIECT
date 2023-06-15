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

-- DATE "11/13/2022 01:40:14"

-- 
-- Device: Altera 5CGXFC7C7F23C8 Package FBGA484
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA;
LIBRARY ALTERA_LNSIM;
LIBRARY CYCLONEV;
LIBRARY IEEE;
USE ALTERA.ALTERA_PRIMITIVES_COMPONENTS.ALL;
USE ALTERA_LNSIM.ALTERA_LNSIM_COMPONENTS.ALL;
USE CYCLONEV.CYCLONEV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	Encoder_BitSerial IS
    PORT (
	nGRst : IN std_logic;
	clk : IN std_logic;
	mIn : IN std_logic;
	x : OUT std_logic_vector(7 DOWNTO 0);
	valid : OUT std_logic
	);
END Encoder_BitSerial;

-- Design Ports Information
-- x[0]	=>  Location: PIN_N19,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[1]	=>  Location: PIN_N20,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[2]	=>  Location: PIN_K17,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[3]	=>  Location: PIN_N16,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[4]	=>  Location: PIN_M21,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[5]	=>  Location: PIN_N21,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[6]	=>  Location: PIN_M20,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- x[7]	=>  Location: PIN_M18,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- valid	=>  Location: PIN_L17,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- clk	=>  Location: PIN_M16,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- nGRst	=>  Location: PIN_L22,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- mIn	=>  Location: PIN_M22,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF Encoder_BitSerial IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_nGRst : std_logic;
SIGNAL ww_clk : std_logic;
SIGNAL ww_mIn : std_logic;
SIGNAL ww_x : std_logic_vector(7 DOWNTO 0);
SIGNAL ww_valid : std_logic;
SIGNAL \~QUARTUS_CREATED_GND~I_combout\ : std_logic;
SIGNAL \clk~input_o\ : std_logic;
SIGNAL \clk~inputCLKENA0_outclk\ : std_logic;
SIGNAL \bc|ff0|Q~0_combout\ : std_logic;
SIGNAL \nGRst~input_o\ : std_logic;
SIGNAL \bc|ff2|Q~0_combout\ : std_logic;
SIGNAL \bc|ff2|Q~q\ : std_logic;
SIGNAL \con|nad2|y~0_combout\ : std_logic;
SIGNAL \bc|ff0|Q~q\ : std_logic;
SIGNAL \con|cMem|prog~1_combout\ : std_logic;
SIGNAL \bc|ff1|Q~q\ : std_logic;
SIGNAL \con|nord|y~0_combout\ : std_logic;
SIGNAL \pr8|ff0|Q~q\ : std_logic;
SIGNAL \mIn~input_o\ : std_logic;
SIGNAL \ff|Q~q\ : std_logic;
SIGNAL \xor8|y0|y~combout\ : std_logic;
SIGNAL \pr8out|ff0|Q~q\ : std_logic;
SIGNAL \pr8|ff1|Q~feeder_combout\ : std_logic;
SIGNAL \pr8|ff1|Q~q\ : std_logic;
SIGNAL \xor8|y1|y~combout\ : std_logic;
SIGNAL \pr8out|ff1|Q~q\ : std_logic;
SIGNAL \pr8|ff2|Q~q\ : std_logic;
SIGNAL \xor8|y2|y~combout\ : std_logic;
SIGNAL \pr8out|ff2|Q~q\ : std_logic;
SIGNAL \pr8|ff3|Q~q\ : std_logic;
SIGNAL \xor8|y3|y~combout\ : std_logic;
SIGNAL \pr8out|ff3|Q~q\ : std_logic;
SIGNAL \pr8|ff4|Q~q\ : std_logic;
SIGNAL \xor8|y4|y~combout\ : std_logic;
SIGNAL \pr8out|ff4|Q~q\ : std_logic;
SIGNAL \pr8|ff5|Q~q\ : std_logic;
SIGNAL \xor8|y5|y~combout\ : std_logic;
SIGNAL \pr8out|ff5|Q~q\ : std_logic;
SIGNAL \pr8|ff6|Q~q\ : std_logic;
SIGNAL \xor8|y6|y~combout\ : std_logic;
SIGNAL \pr8out|ff6|Q~q\ : std_logic;
SIGNAL \pr8|ff7|Q~q\ : std_logic;
SIGNAL \xor8|y7|y~combout\ : std_logic;
SIGNAL \pr8out|ff7|Q~q\ : std_logic;
SIGNAL \validOut|y~combout\ : std_logic;
SIGNAL \ALT_INV_nGRst~input_o\ : std_logic;
SIGNAL \ALT_INV_clk~input_o\ : std_logic;
SIGNAL \con|nord|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \pr8|ff7|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff6|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff5|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff4|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff3|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff2|ALT_INV_Q~q\ : std_logic;
SIGNAL \xor8|y1|ALT_INV_y~combout\ : std_logic;
SIGNAL \pr8|ff1|ALT_INV_Q~q\ : std_logic;
SIGNAL \con|nad2|ALT_INV_y~0_combout\ : std_logic;
SIGNAL \ff|ALT_INV_Q~q\ : std_logic;
SIGNAL \pr8|ff0|ALT_INV_Q~q\ : std_logic;
SIGNAL \bc|ff1|ALT_INV_Q~q\ : std_logic;
SIGNAL \bc|ff2|ALT_INV_Q~q\ : std_logic;
SIGNAL \bc|ff0|ALT_INV_Q~q\ : std_logic;

BEGIN

ww_nGRst <= nGRst;
ww_clk <= clk;
ww_mIn <= mIn;
x <= ww_x;
valid <= ww_valid;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;
\ALT_INV_nGRst~input_o\ <= NOT \nGRst~input_o\;
\ALT_INV_clk~input_o\ <= NOT \clk~input_o\;
\con|nord|ALT_INV_y~0_combout\ <= NOT \con|nord|y~0_combout\;
\pr8|ff7|ALT_INV_Q~q\ <= NOT \pr8|ff7|Q~q\;
\pr8|ff6|ALT_INV_Q~q\ <= NOT \pr8|ff6|Q~q\;
\pr8|ff5|ALT_INV_Q~q\ <= NOT \pr8|ff5|Q~q\;
\pr8|ff4|ALT_INV_Q~q\ <= NOT \pr8|ff4|Q~q\;
\pr8|ff3|ALT_INV_Q~q\ <= NOT \pr8|ff3|Q~q\;
\pr8|ff2|ALT_INV_Q~q\ <= NOT \pr8|ff2|Q~q\;
\xor8|y1|ALT_INV_y~combout\ <= NOT \xor8|y1|y~combout\;
\pr8|ff1|ALT_INV_Q~q\ <= NOT \pr8|ff1|Q~q\;
\con|nad2|ALT_INV_y~0_combout\ <= NOT \con|nad2|y~0_combout\;
\ff|ALT_INV_Q~q\ <= NOT \ff|Q~q\;
\pr8|ff0|ALT_INV_Q~q\ <= NOT \pr8|ff0|Q~q\;
\bc|ff1|ALT_INV_Q~q\ <= NOT \bc|ff1|Q~q\;
\bc|ff2|ALT_INV_Q~q\ <= NOT \bc|ff2|Q~q\;
\bc|ff0|ALT_INV_Q~q\ <= NOT \bc|ff0|Q~q\;

-- Location: IOOBUF_X89_Y36_N5
\x[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff0|Q~q\,
	devoe => ww_devoe,
	o => ww_x(0));

-- Location: IOOBUF_X89_Y35_N79
\x[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff1|Q~q\,
	devoe => ww_devoe,
	o => ww_x(1));

-- Location: IOOBUF_X89_Y37_N5
\x[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff2|Q~q\,
	devoe => ww_devoe,
	o => ww_x(2));

-- Location: IOOBUF_X89_Y35_N45
\x[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff3|Q~q\,
	devoe => ww_devoe,
	o => ww_x(3));

-- Location: IOOBUF_X89_Y37_N56
\x[4]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff4|Q~q\,
	devoe => ww_devoe,
	o => ww_x(4));

-- Location: IOOBUF_X89_Y35_N96
\x[5]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff5|Q~q\,
	devoe => ww_devoe,
	o => ww_x(5));

-- Location: IOOBUF_X89_Y37_N39
\x[6]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff6|Q~q\,
	devoe => ww_devoe,
	o => ww_x(6));

-- Location: IOOBUF_X89_Y36_N22
\x[7]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \pr8out|ff7|Q~q\,
	devoe => ww_devoe,
	o => ww_x(7));

-- Location: IOOBUF_X89_Y37_N22
\valid~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \validOut|y~combout\,
	devoe => ww_devoe,
	o => ww_valid);

-- Location: IOIBUF_X89_Y35_N61
\clk~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_clk,
	o => \clk~input_o\);

-- Location: CLKCTRL_G10
\clk~inputCLKENA0\ : cyclonev_clkena
-- pragma translate_off
GENERIC MAP (
	clock_type => "global clock",
	disable_mode => "low",
	ena_register_mode => "always enabled",
	ena_register_power_up => "high",
	test_syn => "high")
-- pragma translate_on
PORT MAP (
	inclk => \clk~input_o\,
	outclk => \clk~inputCLKENA0_outclk\);

-- Location: LABCELL_X88_Y35_N18
\bc|ff0|Q~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \bc|ff0|Q~0_combout\ = !\bc|ff0|Q~q\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1100110011001100110011001100110011001100110011001100110011001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff0|ALT_INV_Q~q\,
	combout => \bc|ff0|Q~0_combout\);

-- Location: IOIBUF_X89_Y36_N55
\nGRst~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_nGRst,
	o => \nGRst~input_o\);

-- Location: LABCELL_X88_Y35_N42
\bc|ff2|Q~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \bc|ff2|Q~0_combout\ = ( \bc|ff0|Q~q\ & ( !\bc|ff1|Q~q\ $ (!\bc|ff2|Q~q\) ) ) # ( !\bc|ff0|Q~q\ & ( \bc|ff2|Q~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011111111000000001111111100110011110011000011001111001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff1|ALT_INV_Q~q\,
	datad => \bc|ff2|ALT_INV_Q~q\,
	dataf => \bc|ff0|ALT_INV_Q~q\,
	combout => \bc|ff2|Q~0_combout\);

-- Location: FF_X88_Y35_N44
\bc|ff2|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \bc|ff2|Q~0_combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \bc|ff2|Q~q\);

-- Location: LABCELL_X88_Y35_N9
\con|nad2|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \con|nad2|y~0_combout\ = ( \bc|ff2|Q~q\ & ( (\clk~input_o\ & ((!\nGRst~input_o\) # ((\bc|ff1|Q~q\ & !\bc|ff0|Q~q\)))) ) ) # ( !\bc|ff2|Q~q\ & ( (!\nGRst~input_o\ & \clk~input_o\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0010001000100010001000100010001000100011001000100010001100100010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_nGRst~input_o\,
	datab => \ALT_INV_clk~input_o\,
	datac => \bc|ff1|ALT_INV_Q~q\,
	datad => \bc|ff0|ALT_INV_Q~q\,
	dataf => \bc|ff2|ALT_INV_Q~q\,
	combout => \con|nad2|y~0_combout\);

-- Location: FF_X88_Y35_N47
\bc|ff0|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \bc|ff0|Q~0_combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \bc|ff0|Q~q\);

-- Location: LABCELL_X88_Y35_N6
\con|cMem|prog~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \con|cMem|prog~1_combout\ = ( \bc|ff0|Q~q\ & ( !\bc|ff1|Q~q\ ) ) # ( !\bc|ff0|Q~q\ & ( \bc|ff1|Q~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011111111000000001111111111111111000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \bc|ff1|ALT_INV_Q~q\,
	dataf => \bc|ff0|ALT_INV_Q~q\,
	combout => \con|cMem|prog~1_combout\);

-- Location: FF_X88_Y35_N8
\bc|ff1|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \con|cMem|prog~1_combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \bc|ff1|Q~q\);

-- Location: LABCELL_X88_Y35_N45
\con|nord|y~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \con|nord|y~0_combout\ = LCELL(( \bc|ff2|Q~q\ & ( (!\bc|ff1|Q~q\ & !\clk~input_o\) ) ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000011000000110000001100000011000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff1|ALT_INV_Q~q\,
	datac => \ALT_INV_clk~input_o\,
	dataf => \bc|ff2|ALT_INV_Q~q\,
	combout => \con|nord|y~0_combout\);

-- Location: FF_X88_Y35_N56
\pr8|ff0|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y0|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff0|Q~q\);

-- Location: IOIBUF_X89_Y36_N38
\mIn~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_mIn,
	o => \mIn~input_o\);

-- Location: FF_X88_Y35_N41
\ff|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \mIn~input_o\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \ff|Q~q\);

-- Location: LABCELL_X88_Y35_N48
\xor8|y0|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y0|y~combout\ = ( \bc|ff0|Q~q\ & ( \pr8|ff0|Q~q\ ) ) # ( !\bc|ff0|Q~q\ & ( !\pr8|ff0|Q~q\ $ (((!\bc|ff2|Q~q\) # ((!\ff|Q~q\) # (\bc|ff1|Q~q\)))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101100101010101010110010101010101010101010101010101010101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \pr8|ff0|ALT_INV_Q~q\,
	datab => \bc|ff2|ALT_INV_Q~q\,
	datac => \bc|ff1|ALT_INV_Q~q\,
	datad => \ff|ALT_INV_Q~q\,
	dataf => \bc|ff0|ALT_INV_Q~q\,
	combout => \xor8|y0|y~combout\);

-- Location: FF_X88_Y35_N32
\pr8out|ff0|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y0|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff0|Q~q\);

-- Location: MLABCELL_X87_Y35_N27
\pr8|ff1|Q~feeder\ : cyclonev_lcell_comb
-- Equation(s):
-- \pr8|ff1|Q~feeder_combout\ = ( \xor8|y1|y~combout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \xor8|y1|ALT_INV_y~combout\,
	combout => \pr8|ff1|Q~feeder_combout\);

-- Location: FF_X87_Y35_N28
\pr8|ff1|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \pr8|ff1|Q~feeder_combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff1|Q~q\);

-- Location: LABCELL_X88_Y35_N15
\xor8|y1|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y1|y~combout\ = ( \bc|ff0|Q~q\ & ( \bc|ff2|Q~q\ & ( \pr8|ff1|Q~q\ ) ) ) # ( !\bc|ff0|Q~q\ & ( \bc|ff2|Q~q\ & ( !\pr8|ff1|Q~q\ $ (((!\ff|Q~q\) # (\bc|ff1|Q~q\))) ) ) ) # ( \bc|ff0|Q~q\ & ( !\bc|ff2|Q~q\ & ( !\pr8|ff1|Q~q\ $ (((!\ff|Q~q\) # 
-- (\bc|ff1|Q~q\))) ) ) ) # ( !\bc|ff0|Q~q\ & ( !\bc|ff2|Q~q\ & ( \pr8|ff1|Q~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011111111000011001111001100001100111100110000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff1|ALT_INV_Q~q\,
	datac => \ff|ALT_INV_Q~q\,
	datad => \pr8|ff1|ALT_INV_Q~q\,
	datae => \bc|ff0|ALT_INV_Q~q\,
	dataf => \bc|ff2|ALT_INV_Q~q\,
	combout => \xor8|y1|y~combout\);

-- Location: FF_X88_Y35_N26
\pr8out|ff1|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y1|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff1|Q~q\);

-- Location: FF_X87_Y35_N7
\pr8|ff2|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y2|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff2|Q~q\);

-- Location: LABCELL_X88_Y35_N36
\xor8|y2|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y2|y~combout\ = ( \bc|ff1|Q~q\ & ( \bc|ff0|Q~q\ & ( \pr8|ff2|Q~q\ ) ) ) # ( !\bc|ff1|Q~q\ & ( \bc|ff0|Q~q\ & ( \pr8|ff2|Q~q\ ) ) ) # ( \bc|ff1|Q~q\ & ( !\bc|ff0|Q~q\ & ( !\pr8|ff2|Q~q\ $ (((!\ff|Q~q\) # (\bc|ff2|Q~q\))) ) ) ) # ( !\bc|ff1|Q~q\ & ( 
-- !\bc|ff0|Q~q\ & ( !\pr8|ff2|Q~q\ $ (((!\ff|Q~q\) # (!\bc|ff2|Q~q\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100111100001111000000111100001111000011110000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ff|ALT_INV_Q~q\,
	datac => \pr8|ff2|ALT_INV_Q~q\,
	datad => \bc|ff2|ALT_INV_Q~q\,
	datae => \bc|ff1|ALT_INV_Q~q\,
	dataf => \bc|ff0|ALT_INV_Q~q\,
	combout => \xor8|y2|y~combout\);

-- Location: FF_X88_Y35_N13
\pr8out|ff2|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y2|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff2|Q~q\);

-- Location: FF_X88_Y35_N20
\pr8|ff3|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y3|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff3|Q~q\);

-- Location: LABCELL_X88_Y35_N33
\xor8|y3|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y3|y~combout\ = ( \bc|ff0|Q~q\ & ( \ff|Q~q\ & ( !\pr8|ff3|Q~q\ $ (((\bc|ff1|Q~q\) # (\bc|ff2|Q~q\))) ) ) ) # ( !\bc|ff0|Q~q\ & ( \ff|Q~q\ & ( !\bc|ff2|Q~q\ $ (!\bc|ff1|Q~q\ $ (\pr8|ff3|Q~q\)) ) ) ) # ( \bc|ff0|Q~q\ & ( !\ff|Q~q\ & ( \pr8|ff3|Q~q\ ) 
-- ) ) # ( !\bc|ff0|Q~q\ & ( !\ff|Q~q\ & ( \pr8|ff3|Q~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011111111000000001111111100111100110000111100000000111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff2|ALT_INV_Q~q\,
	datac => \bc|ff1|ALT_INV_Q~q\,
	datad => \pr8|ff3|ALT_INV_Q~q\,
	datae => \bc|ff0|ALT_INV_Q~q\,
	dataf => \ff|ALT_INV_Q~q\,
	combout => \xor8|y3|y~combout\);

-- Location: FF_X88_Y35_N52
\pr8out|ff3|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y3|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff3|Q~q\);

-- Location: FF_X88_Y35_N2
\pr8|ff4|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y4|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff4|Q~q\);

-- Location: LABCELL_X88_Y35_N21
\xor8|y4|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y4|y~combout\ = ( \bc|ff1|Q~q\ & ( !\pr8|ff4|Q~q\ $ (((!\bc|ff0|Q~q\) # ((!\ff|Q~q\) # (\bc|ff2|Q~q\)))) ) ) # ( !\bc|ff1|Q~q\ & ( !\pr8|ff4|Q~q\ $ ((((!\bc|ff2|Q~q\) # (!\ff|Q~q\)) # (\bc|ff0|Q~q\))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101011001010101010101100101010101011001010101010101100101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \pr8|ff4|ALT_INV_Q~q\,
	datab => \bc|ff0|ALT_INV_Q~q\,
	datac => \bc|ff2|ALT_INV_Q~q\,
	datad => \ff|ALT_INV_Q~q\,
	dataf => \bc|ff1|ALT_INV_Q~q\,
	combout => \xor8|y4|y~combout\);

-- Location: FF_X88_Y35_N35
\pr8out|ff4|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y4|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff4|Q~q\);

-- Location: FF_X88_Y35_N58
\pr8|ff5|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y5|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff5|Q~q\);

-- Location: LABCELL_X88_Y35_N3
\xor8|y5|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y5|y~combout\ = ( \bc|ff1|Q~q\ & ( \bc|ff2|Q~q\ & ( \pr8|ff5|Q~q\ ) ) ) # ( !\bc|ff1|Q~q\ & ( \bc|ff2|Q~q\ & ( !\pr8|ff5|Q~q\ $ (((!\ff|Q~q\) # (\bc|ff0|Q~q\))) ) ) ) # ( \bc|ff1|Q~q\ & ( !\bc|ff2|Q~q\ & ( !\pr8|ff5|Q~q\ $ (((!\bc|ff0|Q~q\) # 
-- (!\ff|Q~q\))) ) ) ) # ( !\bc|ff1|Q~q\ & ( !\bc|ff2|Q~q\ & ( !\pr8|ff5|Q~q\ $ (((!\bc|ff0|Q~q\) # (!\ff|Q~q\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000001111111100000000111111110000001100111100110000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \bc|ff0|ALT_INV_Q~q\,
	datac => \ff|ALT_INV_Q~q\,
	datad => \pr8|ff5|ALT_INV_Q~q\,
	datae => \bc|ff1|ALT_INV_Q~q\,
	dataf => \bc|ff2|ALT_INV_Q~q\,
	combout => \xor8|y5|y~combout\);

-- Location: FF_X88_Y35_N28
\pr8out|ff5|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y5|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff5|Q~q\);

-- Location: FF_X88_Y35_N5
\pr8|ff6|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y6|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff6|Q~q\);

-- Location: LABCELL_X88_Y35_N24
\xor8|y6|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y6|y~combout\ = ( \bc|ff0|Q~q\ & ( \bc|ff2|Q~q\ & ( \pr8|ff6|Q~q\ ) ) ) # ( !\bc|ff0|Q~q\ & ( \bc|ff2|Q~q\ & ( !\pr8|ff6|Q~q\ $ (((!\ff|Q~q\) # (\bc|ff1|Q~q\))) ) ) ) # ( \bc|ff0|Q~q\ & ( !\bc|ff2|Q~q\ & ( !\pr8|ff6|Q~q\ $ (((!\ff|Q~q\) # 
-- (!\bc|ff1|Q~q\))) ) ) ) # ( !\bc|ff0|Q~q\ & ( !\bc|ff2|Q~q\ & ( !\pr8|ff6|Q~q\ $ (((!\ff|Q~q\) # (!\bc|ff1|Q~q\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100111100000011110011110000111100000011110000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ff|ALT_INV_Q~q\,
	datac => \pr8|ff6|ALT_INV_Q~q\,
	datad => \bc|ff1|ALT_INV_Q~q\,
	datae => \bc|ff0|ALT_INV_Q~q\,
	dataf => \bc|ff2|ALT_INV_Q~q\,
	combout => \xor8|y6|y~combout\);

-- Location: FF_X88_Y35_N17
\pr8out|ff6|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y6|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff6|Q~q\);

-- Location: FF_X88_Y35_N23
\pr8|ff7|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \xor8|y7|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8|ff7|Q~q\);

-- Location: LABCELL_X88_Y35_N54
\xor8|y7|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \xor8|y7|y~combout\ = ( \ff|Q~q\ & ( !\bc|ff2|Q~q\ $ (!\pr8|ff7|Q~q\ $ (((\bc|ff1|Q~q\) # (\bc|ff0|Q~q\)))) ) ) # ( !\ff|Q~q\ & ( \pr8|ff7|Q~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000011111111000000001111111101101100100100110110110010010011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \bc|ff0|ALT_INV_Q~q\,
	datab => \bc|ff2|ALT_INV_Q~q\,
	datac => \bc|ff1|ALT_INV_Q~q\,
	datad => \pr8|ff7|ALT_INV_Q~q\,
	dataf => \ff|ALT_INV_Q~q\,
	combout => \xor8|y7|y~combout\);

-- Location: FF_X88_Y35_N50
\pr8out|ff7|Q\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \con|nord|y~0_combout\,
	asdata => \xor8|y7|y~combout\,
	clrn => \con|nad2|ALT_INV_y~0_combout\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \pr8out|ff7|Q~q\);

-- Location: LABCELL_X88_Y35_N57
\validOut|y\ : cyclonev_lcell_comb
-- Equation(s):
-- \validOut|y~combout\ = ((\bc|ff0|Q~q\ & \bc|ff2|Q~q\)) # (\con|nord|y~0_combout\)

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0001111100011111000111110001111100011111000111110001111100011111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \bc|ff0|ALT_INV_Q~q\,
	datab => \bc|ff2|ALT_INV_Q~q\,
	datac => \con|nord|ALT_INV_y~0_combout\,
	combout => \validOut|y~combout\);

-- Location: MLABCELL_X87_Y61_N0
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


