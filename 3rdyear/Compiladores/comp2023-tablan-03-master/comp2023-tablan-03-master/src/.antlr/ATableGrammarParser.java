// Generated from /home/joao/Desktop/ECI-Ano-3/C/Projeto/comp2023-tablan-03/src/ATableGrammar.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ATableGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, ID=37, INTEGER_LITERAL=38, 
		REAL_LITERAL=39, STRING_LITERAL=40, WHITESPACE=41, COMMENT=42;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_tableDefinition = 2, RULE_formulaDefinition = 3, 
		RULE_tableColumnDefinition = 4, RULE_dataType = 5, RULE_assignment = 6, 
		RULE_printStatement = 7, RULE_expressionList = 8, RULE_addRowStatement = 9, 
		RULE_tableRef = 10, RULE_headerStatement = 11, RULE_loopStatement = 12, 
		RULE_centerExpression = 13, RULE_expression = 14, RULE_primary = 15, RULE_fieldAccess = 16, 
		RULE_readStatement = 17, RULE_usingClause = 18, RULE_columnMapping = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "tableDefinition", "formulaDefinition", "tableColumnDefinition", 
			"dataType", "assignment", "printStatement", "expressionList", "addRowStatement", 
			"tableRef", "headerStatement", "loopStatement", "centerExpression", "expression", 
			"primary", "fieldAccess", "readStatement", "usingClause", "columnMapping"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'type'", "'table'", "'->'", "'{'", "'}'", "';'", "':'", "':='", 
			"'integer'", "'real'", "'text'", "'println'", "'print'", "','", "'['", 
			"']'", "'[]'", "'>>'", "'new'", "'('", "')'", "'.'", "'for'", "'in'", 
			"'do'", "'end;'", "'center'", "'*'", "'/'", "'+'", "'-'", "'read'", "'length'", 
			"'using'", "'column'", "'as'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "ID", "INTEGER_LITERAL", "REAL_LITERAL", "STRING_LITERAL", "WHITESPACE", 
			"COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ATableGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ATableGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ATableGrammarParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__11) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << T__18) | (1L << T__22) | (1L << ID))) != 0)) {
				{
				{
				setState(40);
				statement();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StatementTableDefinitionContext extends StatementContext {
		public TableDefinitionContext tableDefinition() {
			return getRuleContext(TableDefinitionContext.class,0);
		}
		public StatementTableDefinitionContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class StatementHeaderContext extends StatementContext {
		public HeaderStatementContext headerStatement() {
			return getRuleContext(HeaderStatementContext.class,0);
		}
		public StatementHeaderContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class StatementPrintContext extends StatementContext {
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public StatementPrintContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class StatementLoopContext extends StatementContext {
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public StatementLoopContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class StatementAddRowContext extends StatementContext {
		public AddRowStatementContext addRowStatement() {
			return getRuleContext(AddRowStatementContext.class,0);
		}
		public StatementAddRowContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class StatementAssignmentContext extends StatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public StatementAssignmentContext(StatementContext ctx) { copyFrom(ctx); }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new StatementTableDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				tableDefinition();
				}
				break;
			case 2:
				_localctx = new StatementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				assignment();
				}
				break;
			case 3:
				_localctx = new StatementPrintContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				printStatement();
				}
				break;
			case 4:
				_localctx = new StatementAddRowContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				addRowStatement();
				}
				break;
			case 5:
				_localctx = new StatementHeaderContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				headerStatement();
				}
				break;
			case 6:
				_localctx = new StatementLoopContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(53);
				loopStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableDefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public List<TableColumnDefinitionContext> tableColumnDefinition() {
			return getRuleContexts(TableColumnDefinitionContext.class);
		}
		public TableColumnDefinitionContext tableColumnDefinition(int i) {
			return getRuleContext(TableColumnDefinitionContext.class,i);
		}
		public List<FormulaDefinitionContext> formulaDefinition() {
			return getRuleContexts(FormulaDefinitionContext.class);
		}
		public FormulaDefinitionContext formulaDefinition(int i) {
			return getRuleContext(FormulaDefinitionContext.class,i);
		}
		public TableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableDefinition; }
	}

	public final TableDefinitionContext tableDefinition() throws RecognitionException {
		TableDefinitionContext _localctx = new TableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tableDefinition);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__0);
			setState(57);
			match(T__1);
			setState(58);
			match(ID);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(59);
				match(T__2);
				setState(60);
				match(STRING_LITERAL);
				}
			}

			setState(63);
			match(T__3);
			setState(65); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(64);
					tableColumnDefinition();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(67); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(69);
					formulaDefinition();
					}
					}
					setState(72); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
			}

			setState(76);
			match(T__4);
			setState(77);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaDefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FormulaDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulaDefinition; }
	}

	public final FormulaDefinitionContext formulaDefinition() throws RecognitionException {
		FormulaDefinitionContext _localctx = new FormulaDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formulaDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(ID);
			setState(80);
			match(T__6);
			setState(81);
			dataType();
			setState(82);
			match(T__7);
			setState(83);
			expression();
			setState(84);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableColumnDefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public TableColumnDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableColumnDefinition; }
	}

	public final TableColumnDefinitionContext tableColumnDefinition() throws RecognitionException {
		TableColumnDefinitionContext _localctx = new TableColumnDefinitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tableColumnDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ID);
			setState(87);
			match(T__6);
			setState(88);
			dataType();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(89);
				match(T__2);
				setState(90);
				match(STRING_LITERAL);
				}
			}

			setState(93);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DataTypeRealContext extends DataTypeContext {
		public DataTypeRealContext(DataTypeContext ctx) { copyFrom(ctx); }
	}
	public static class DataTypeTextContext extends DataTypeContext {
		public DataTypeTextContext(DataTypeContext ctx) { copyFrom(ctx); }
	}
	public static class DataTypeIntegerContext extends DataTypeContext {
		public DataTypeIntegerContext(DataTypeContext ctx) { copyFrom(ctx); }
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dataType);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				_localctx = new DataTypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(T__8);
				}
				break;
			case T__9:
				_localctx = new DataTypeRealContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(T__9);
				}
				break;
			case T__10:
				_localctx = new DataTypeTextContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	 
		public AssignmentContext() { }
		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FieldAccessAssignmentContext extends AssignmentContext {
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FieldAccessAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
	}
	public static class IdentifierAssignmentContext extends AssignmentContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public IdentifierAssignmentContext(AssignmentContext ctx) { copyFrom(ctx); }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignment);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new FieldAccessAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				fieldAccess();
				setState(101);
				match(T__7);
				setState(102);
				expression();
				setState(103);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new IdentifierAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(ID);
				setState(120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(106);
					match(T__7);
					setState(107);
					expression();
					}
					break;
				case 2:
					{
					setState(108);
					match(T__7);
					setState(109);
					readStatement();
					}
					break;
				case 3:
					{
					setState(110);
					match(T__6);
					setState(111);
					dataType();
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__7) {
						{
						setState(112);
						match(T__7);
						setState(113);
						expression();
						}
					}

					}
					break;
				case 4:
					{
					setState(116);
					match(T__6);
					setState(117);
					tableRef();
					}
					break;
				case 5:
					{
					setState(118);
					match(T__7);
					setState(119);
					tableRef();
					}
					break;
				}
				setState(122);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintStatementContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public CenterExpressionContext centerExpression() {
			return getRuleContext(CenterExpressionContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_la = _input.LA(1);
			if ( !(_la==T__11 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(127);
				expressionList();
				}
				break;
			case 2:
				{
				setState(128);
				match(STRING_LITERAL);
				}
				break;
			case 3:
				{
				setState(129);
				centerExpression();
				}
				break;
			}
			setState(132);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			expression();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(135);
				match(T__13);
				setState(136);
				expression();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddRowStatementContext extends ParserRuleContext {
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public AddRowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addRowStatement; }
	}

	public final AddRowStatementContext addRowStatement() throws RecognitionException {
		AddRowStatementContext _localctx = new AddRowStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_addRowStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(142);
				match(ID);
				}
				break;
			case T__14:
				{
				setState(143);
				match(T__14);
				setState(144);
				expressionList();
				setState(145);
				match(T__15);
				}
				break;
			case T__16:
				{
				setState(147);
				match(T__16);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(150);
			match(T__17);
			setState(151);
			tableRef();
			setState(152);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableRefContext extends ParserRuleContext {
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(ATableGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ATableGrammarParser.ID, i);
		}
		public TableRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableRef; }
	}

	public final TableRefContext tableRef() throws RecognitionException {
		TableRefContext _localctx = new TableRefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tableRef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(154);
				match(T__18);
				setState(155);
				match(T__19);
				setState(156);
				tableRef();
				setState(157);
				match(T__20);
				}
				break;
			case ID:
				{
				setState(159);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(162);
					match(T__21);
					setState(163);
					match(ID);
					}
					} 
				}
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderStatementContext extends ParserRuleContext {
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public HeaderStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerStatement; }
	}

	public final HeaderStatementContext headerStatement() throws RecognitionException {
		HeaderStatementContext _localctx = new HeaderStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_headerStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			tableRef();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(170);
				match(T__21);
				setState(171);
				match(ID);
				}
			}

			setState(174);
			match(T__2);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING_LITERAL) {
				{
				setState(175);
				match(STRING_LITERAL);
				}
			}

			setState(178);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_loopStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__22);
			setState(181);
			match(ID);
			setState(182);
			match(T__23);
			setState(183);
			tableRef();
			setState(184);
			match(T__24);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__11) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << T__18) | (1L << T__22) | (1L << ID))) != 0)) {
				{
				{
				setState(185);
				statement();
				}
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CenterExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CenterExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_centerExpression; }
	}

	public final CenterExpressionContext centerExpression() throws RecognitionException {
		CenterExpressionContext _localctx = new CenterExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_centerExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			expression();
			setState(194);
			match(T__26);
			setState(195);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public List<PrimaryContext> primary() {
			return getRuleContexts(PrimaryContext.class);
		}
		public PrimaryContext primary(int i) {
			return getRuleContext(PrimaryContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			primary();
			{
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(198);
					((ExpressionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) ) {
						((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(199);
					primary();
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DataTypePrimaryContext extends PrimaryContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public DataTypePrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class NewPrimaryContext extends PrimaryContext {
		public NewPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class LengthPrimaryContext extends PrimaryContext {
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LengthPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class ListPrimaryContext extends PrimaryContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ListPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class IdentifierPrimaryContext extends PrimaryContext {
		public TerminalNode ID() { return getToken(ATableGrammarParser.ID, 0); }
		public IdentifierPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class ReadPrimaryContext extends PrimaryContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public ReadPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class ParenthesisPrimaryContext extends PrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class IntegerLiteralPrimaryContext extends PrimaryContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(ATableGrammarParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class RealLiteralPrimaryContext extends PrimaryContext {
		public TerminalNode REAL_LITERAL() { return getToken(ATableGrammarParser.REAL_LITERAL, 0); }
		public RealLiteralPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class FieldAccessPrimaryContext extends PrimaryContext {
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public FieldAccessPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class TableRefPrimaryContext extends PrimaryContext {
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public TableRefPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	public static class StringLiteralPrimaryContext extends PrimaryContext {
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public StringLiteralPrimaryContext(PrimaryContext ctx) { copyFrom(ctx); }
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_primary);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new NewPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(T__18);
				}
				break;
			case 2:
				_localctx = new FieldAccessPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				fieldAccess();
				}
				break;
			case 3:
				_localctx = new IdentifierPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(207);
				match(ID);
				}
				break;
			case 4:
				_localctx = new IntegerLiteralPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(208);
				match(INTEGER_LITERAL);
				}
				break;
			case 5:
				_localctx = new RealLiteralPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(209);
				match(REAL_LITERAL);
				}
				break;
			case 6:
				_localctx = new StringLiteralPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(210);
				match(STRING_LITERAL);
				}
				break;
			case 7:
				_localctx = new ParenthesisPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(211);
				match(T__19);
				setState(212);
				expression();
				setState(213);
				match(T__20);
				}
				break;
			case 8:
				_localctx = new DataTypePrimaryContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(215);
				dataType();
				setState(216);
				match(T__19);
				setState(217);
				expression();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(218);
					match(T__13);
					setState(219);
					match(STRING_LITERAL);
					}
				}

				setState(222);
				match(T__20);
				}
				break;
			case 9:
				_localctx = new ListPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(224);
				match(T__14);
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__18) | (1L << T__19) | (1L << T__32) | (1L << ID) | (1L << INTEGER_LITERAL) | (1L << REAL_LITERAL) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(225);
					expressionList();
					}
				}

				setState(228);
				match(T__15);
				}
				break;
			case 10:
				_localctx = new ReadPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(229);
				dataType();
				setState(230);
				match(T__19);
				{
				setState(231);
				match(T__31);
				setState(232);
				match(T__23);
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING_LITERAL) {
					{
					setState(233);
					match(STRING_LITERAL);
					}
				}

				}
				setState(236);
				match(T__20);
				}
				break;
			case 11:
				_localctx = new LengthPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(238);
				match(T__32);
				setState(244);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(239);
					tableRef();
					setState(240);
					match(T__21);
					setState(241);
					match(ID);
					}
					break;
				case 2:
					{
					setState(243);
					expression();
					}
					break;
				}
				}
				break;
			case 12:
				_localctx = new TableRefPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(246);
				tableRef();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldAccessContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ATableGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ATableGrammarParser.ID, i);
		}
		public FieldAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccess; }
	}

	public final FieldAccessContext fieldAccess() throws RecognitionException {
		FieldAccessContext _localctx = new FieldAccessContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fieldAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(ID);
			setState(250);
			match(T__21);
			setState(251);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadStatementContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public UsingClauseContext usingClause() {
			return getRuleContext(UsingClauseContext.class,0);
		}
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_readStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(T__31);
			setState(254);
			match(STRING_LITERAL);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(255);
				usingClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UsingClauseContext extends ParserRuleContext {
		public List<ColumnMappingContext> columnMapping() {
			return getRuleContexts(ColumnMappingContext.class);
		}
		public ColumnMappingContext columnMapping(int i) {
			return getRuleContext(ColumnMappingContext.class,i);
		}
		public UsingClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_usingClause; }
	}

	public final UsingClauseContext usingClause() throws RecognitionException {
		UsingClauseContext _localctx = new UsingClauseContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_usingClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(T__33);
			setState(259);
			columnMapping();
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(260);
				match(T__13);
				setState(261);
				columnMapping();
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnMappingContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ATableGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ATableGrammarParser.ID, i);
		}
		public TerminalNode STRING_LITERAL() { return getToken(ATableGrammarParser.STRING_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(ATableGrammarParser.INTEGER_LITERAL, 0); }
		public ColumnMappingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnMapping; }
	}

	public final ColumnMappingContext columnMapping() throws RecognitionException {
		ColumnMappingContext _localctx = new ColumnMappingContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_columnMapping);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(267);
				match(T__34);
				}
			}

			setState(270);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INTEGER_LITERAL) | (1L << STRING_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			setState(272);
			match(T__35);
			setState(273);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0116\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\7\2,\n\2\f\2\16\2/\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\4\3\4\6\4"+
		"D\n\4\r\4\16\4E\3\4\6\4I\n\4\r\4\16\4J\5\4M\n\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6^\n\6\3\6\3\6\3\7\3\7\3\7\5"+
		"\7e\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bu\n"+
		"\b\3\b\3\b\3\b\3\b\5\b{\n\b\3\b\3\b\5\b\177\n\b\3\t\3\t\3\t\3\t\5\t\u0085"+
		"\n\t\3\t\3\t\3\n\3\n\3\n\7\n\u008c\n\n\f\n\16\n\u008f\13\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u0097\n\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\5\f\u00a3\n\f\3\f\3\f\7\f\u00a7\n\f\f\f\16\f\u00aa\13\f\3\r\3"+
		"\r\3\r\5\r\u00af\n\r\3\r\3\r\5\r\u00b3\n\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u00bd\n\16\f\16\16\16\u00c0\13\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\7\20\u00cb\n\20\f\20\16\20\u00ce\13\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u00df\n\21\3\21\3\21\3\21\3\21\5\21\u00e5\n\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\5\21\u00ed\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u00f7\n\21\3\21\5\21\u00fa\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\5\23\u0103\n\23\3\24\3\24\3\24\3\24\7\24\u0109\n\24\f\24\16\24\u010c"+
		"\13\24\3\25\5\25\u010f\n\25\3\25\3\25\3\25\3\25\3\25\3\25\2\2\26\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\5\3\2\16\17\3\2\36!\4\2\'(*"+
		"*\2\u0131\2-\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\bQ\3\2\2\2\nX\3\2\2\2\fd\3"+
		"\2\2\2\16~\3\2\2\2\20\u0080\3\2\2\2\22\u0088\3\2\2\2\24\u0096\3\2\2\2"+
		"\26\u00a2\3\2\2\2\30\u00ab\3\2\2\2\32\u00b6\3\2\2\2\34\u00c3\3\2\2\2\36"+
		"\u00c7\3\2\2\2 \u00f9\3\2\2\2\"\u00fb\3\2\2\2$\u00ff\3\2\2\2&\u0104\3"+
		"\2\2\2(\u010e\3\2\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2"+
		"\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\2\2\3\61\3\3\2\2\2\629\5\6\4\2\639\5"+
		"\16\b\2\649\5\20\t\2\659\5\24\13\2\669\5\30\r\2\679\5\32\16\28\62\3\2"+
		"\2\28\63\3\2\2\28\64\3\2\2\28\65\3\2\2\28\66\3\2\2\28\67\3\2\2\29\5\3"+
		"\2\2\2:;\7\3\2\2;<\7\4\2\2<?\7\'\2\2=>\7\5\2\2>@\7*\2\2?=\3\2\2\2?@\3"+
		"\2\2\2@A\3\2\2\2AC\7\6\2\2BD\5\n\6\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3"+
		"\2\2\2FL\3\2\2\2GI\5\b\5\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3"+
		"\2\2\2LH\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\7\7\2\2OP\7\b\2\2P\7\3\2\2\2QR"+
		"\7\'\2\2RS\7\t\2\2ST\5\f\7\2TU\7\n\2\2UV\5\36\20\2VW\7\b\2\2W\t\3\2\2"+
		"\2XY\7\'\2\2YZ\7\t\2\2Z]\5\f\7\2[\\\7\5\2\2\\^\7*\2\2][\3\2\2\2]^\3\2"+
		"\2\2^_\3\2\2\2_`\7\b\2\2`\13\3\2\2\2ae\7\13\2\2be\7\f\2\2ce\7\r\2\2da"+
		"\3\2\2\2db\3\2\2\2dc\3\2\2\2e\r\3\2\2\2fg\5\"\22\2gh\7\n\2\2hi\5\36\20"+
		"\2ij\7\b\2\2j\177\3\2\2\2kz\7\'\2\2lm\7\n\2\2m{\5\36\20\2no\7\n\2\2o{"+
		"\5$\23\2pq\7\t\2\2qt\5\f\7\2rs\7\n\2\2su\5\36\20\2tr\3\2\2\2tu\3\2\2\2"+
		"u{\3\2\2\2vw\7\t\2\2w{\5\26\f\2xy\7\n\2\2y{\5\26\f\2zl\3\2\2\2zn\3\2\2"+
		"\2zp\3\2\2\2zv\3\2\2\2zx\3\2\2\2{|\3\2\2\2|}\7\b\2\2}\177\3\2\2\2~f\3"+
		"\2\2\2~k\3\2\2\2\177\17\3\2\2\2\u0080\u0084\t\2\2\2\u0081\u0085\5\22\n"+
		"\2\u0082\u0085\7*\2\2\u0083\u0085\5\34\17\2\u0084\u0081\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\b"+
		"\2\2\u0087\21\3\2\2\2\u0088\u008d\5\36\20\2\u0089\u008a\7\20\2\2\u008a"+
		"\u008c\5\36\20\2\u008b\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3"+
		"\2\2\2\u008d\u008e\3\2\2\2\u008e\23\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u0097\7\'\2\2\u0091\u0092\7\21\2\2\u0092\u0093\5\22\n\2\u0093\u0094\7"+
		"\22\2\2\u0094\u0097\3\2\2\2\u0095\u0097\7\23\2\2\u0096\u0090\3\2\2\2\u0096"+
		"\u0091\3\2\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\7\24"+
		"\2\2\u0099\u009a\5\26\f\2\u009a\u009b\7\b\2\2\u009b\25\3\2\2\2\u009c\u009d"+
		"\7\25\2\2\u009d\u009e\7\26\2\2\u009e\u009f\5\26\f\2\u009f\u00a0\7\27\2"+
		"\2\u00a0\u00a3\3\2\2\2\u00a1\u00a3\7\'\2\2\u00a2\u009c\3\2\2\2\u00a2\u00a1"+
		"\3\2\2\2\u00a3\u00a8\3\2\2\2\u00a4\u00a5\7\30\2\2\u00a5\u00a7\7\'\2\2"+
		"\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\27\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae\5\26\f\2\u00ac"+
		"\u00ad\7\30\2\2\u00ad\u00af\7\'\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3"+
		"\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\7\5\2\2\u00b1\u00b3\7*\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\7\b"+
		"\2\2\u00b5\31\3\2\2\2\u00b6\u00b7\7\31\2\2\u00b7\u00b8\7\'\2\2\u00b8\u00b9"+
		"\7\32\2\2\u00b9\u00ba\5\26\f\2\u00ba\u00be\7\33\2\2\u00bb\u00bd\5\4\3"+
		"\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\34\2\2"+
		"\u00c2\33\3\2\2\2\u00c3\u00c4\5\36\20\2\u00c4\u00c5\7\35\2\2\u00c5\u00c6"+
		"\5\36\20\2\u00c6\35\3\2\2\2\u00c7\u00cc\5 \21\2\u00c8\u00c9\t\3\2\2\u00c9"+
		"\u00cb\5 \21\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2"+
		"\2\2\u00cc\u00cd\3\2\2\2\u00cd\37\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00fa"+
		"\7\25\2\2\u00d0\u00fa\5\"\22\2\u00d1\u00fa\7\'\2\2\u00d2\u00fa\7(\2\2"+
		"\u00d3\u00fa\7)\2\2\u00d4\u00fa\7*\2\2\u00d5\u00d6\7\26\2\2\u00d6\u00d7"+
		"\5\36\20\2\u00d7\u00d8\7\27\2\2\u00d8\u00fa\3\2\2\2\u00d9\u00da\5\f\7"+
		"\2\u00da\u00db\7\26\2\2\u00db\u00de\5\36\20\2\u00dc\u00dd\7\20\2\2\u00dd"+
		"\u00df\7*\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\u00e1\7\27\2\2\u00e1\u00fa\3\2\2\2\u00e2\u00e4\7\21\2\2\u00e3"+
		"\u00e5\5\22\n\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3"+
		"\2\2\2\u00e6\u00fa\7\22\2\2\u00e7\u00e8\5\f\7\2\u00e8\u00e9\7\26\2\2\u00e9"+
		"\u00ea\7\"\2\2\u00ea\u00ec\7\32\2\2\u00eb\u00ed\7*\2\2\u00ec\u00eb\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7\27\2\2\u00ef"+
		"\u00fa\3\2\2\2\u00f0\u00f6\7#\2\2\u00f1\u00f2\5\26\f\2\u00f2\u00f3\7\30"+
		"\2\2\u00f3\u00f4\7\'\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f7\5\36\20\2\u00f6"+
		"\u00f1\3\2\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00fa\5\26"+
		"\f\2\u00f9\u00cf\3\2\2\2\u00f9\u00d0\3\2\2\2\u00f9\u00d1\3\2\2\2\u00f9"+
		"\u00d2\3\2\2\2\u00f9\u00d3\3\2\2\2\u00f9\u00d4\3\2\2\2\u00f9\u00d5\3\2"+
		"\2\2\u00f9\u00d9\3\2\2\2\u00f9\u00e2\3\2\2\2\u00f9\u00e7\3\2\2\2\u00f9"+
		"\u00f0\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa!\3\2\2\2\u00fb\u00fc\7\'\2\2"+
		"\u00fc\u00fd\7\30\2\2\u00fd\u00fe\7\'\2\2\u00fe#\3\2\2\2\u00ff\u0100\7"+
		"\"\2\2\u0100\u0102\7*\2\2\u0101\u0103\5&\24\2\u0102\u0101\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103%\3\2\2\2\u0104\u0105\7$\2\2\u0105\u010a\5(\25\2\u0106"+
		"\u0107\7\20\2\2\u0107\u0109\5(\25\2\u0108\u0106\3\2\2\2\u0109\u010c\3"+
		"\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\'\3\2\2\2\u010c\u010a"+
		"\3\2\2\2\u010d\u010f\7%\2\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0111\t\4\2\2\u0111\u0112\3\2\2\2\u0112\u0113\7&"+
		"\2\2\u0113\u0114\7\'\2\2\u0114)\3\2\2\2\36-8?EJL]dtz~\u0084\u008d\u0096"+
		"\u00a2\u00a8\u00ae\u00b2\u00be\u00cc\u00de\u00e4\u00ec\u00f6\u00f9\u0102"+
		"\u010a\u010e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}