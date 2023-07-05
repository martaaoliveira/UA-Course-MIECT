import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;
import ATableJava.ATable;
import ATableJava.ATable.ColumnType;
import ATableJava.Table;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Console;
import java.util.function.Function;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("CheckReturnValue")
public class checkSemantics extends ATableGrammarBaseVisitor<Boolean> {

    private SymbolTable symbolTable;

    private boolean tableDefined = false;
    private boolean instanciada = false;

    public checkSemantics() {
        this.symbolTable = new SymbolTable();
    }

    private boolean isValidDataType(String dataType) {
        return dataType.equals("integer") || dataType.equals("real") || dataType.equals("text");
    }

    private ATable.ColumnType getColumnType(String dataType) {
        switch (dataType.toLowerCase()) {
            case "integer":
                return ATable.ColumnType.Integer;
            case "double":
                return ATable.ColumnType.Double;
            case "string":
                return ATable.ColumnType.String;
            default:
                return null;
        }
    }

    @Override
    public Boolean visitProgram(ATableGrammarParser.ProgramContext ctx) {
        Boolean result = visitChildren(ctx);
        return result;
    }

    @Override
    public Boolean visitStatementTableDefinition(ATableGrammarParser.StatementTableDefinitionContext ctx) {

        Boolean result = visitChildren(ctx);
        return result;

    }

    @Override
    public Boolean visitStatementAssignment(ATableGrammarParser.StatementAssignmentContext ctx) {

        Boolean result = visitChildren(ctx);
        return result;

    }

    @Override
    public Boolean visitStatementPrint(ATableGrammarParser.StatementPrintContext ctx) {
        return visit(ctx.printStatement());

    }

    @Override
    public Boolean visitStatementAddRow(ATableGrammarParser.StatementAddRowContext ctx) {
        Boolean result = visitChildren(ctx);
        return result;

    }

    @Override
    public Boolean visitStatementHeader(ATableGrammarParser.StatementHeaderContext ctx) {

        Boolean result = visitChildren(ctx);
        return result;
    }

    @Override
    public Boolean visitStatementLoop(ATableGrammarParser.StatementLoopContext ctx) {
        Boolean result = visitChildren(ctx);
        return result;

    }

    @Override
    public Boolean visitTableDefinition(ATableGrammarParser.TableDefinitionContext ctx) {
        List<ATableGrammarParser.TableColumnDefinitionContext> columnDefs = ctx.tableColumnDefinition();
        tableDefined = true;

        if (!ctx.getChild(2).getText().equals("ATable") && !ctx.getChild(2).getText().equals("Table")) {
            ErrorHandling.printError("Error: Type Table must be ATable or Table");
        }
        if (columnDefs.isEmpty()) {
            ErrorHandling.printError("Error: No column definitions found in table definition");
            
        }

        for (ATableGrammarParser.TableColumnDefinitionContext columnDef : columnDefs) {
            String columnName = columnDef.ID().getText();
            ATableGrammarParser.DataTypeContext dataTypeCtx = columnDef.dataType();

            if (!isValidDataType(dataTypeCtx.getText())) {
                ErrorHandling.printError("Error: Invalid data type in column definition: " + dataTypeCtx.getText());
                //return false;
            }

            // Criar um novo Symbol para a coluna e adicioná-lo à tabela de símbolos
            Symbol columnSymbol = new Symbol(columnName, dataTypeCtx.getText());
            symbolTable.addSymbol(columnSymbol);
            
        }

        Boolean result = visitChildren(ctx);
        return result;
    }

    @Override
    public Boolean visitFormulaDefinition(ATableGrammarParser.FormulaDefinitionContext ctx) {
        String formulaName = ctx.ID().getText();

        // Verificar se a fórmula já foi definida anteriormente
        // if (!symbolTable.isVariableDeclared(formulaName)) {
        //     ErrorHandling.printError("Error: Formula " + formulaName + " is already defined");
        //     //return false;
        // }

        // Verificar se o tipo de dados da fórmula é válido
        ATableGrammarParser.DataTypeContext dataTypeCtx = ctx.dataType();
        if (dataTypeCtx != null && !isValidDataType(dataTypeCtx.getText())) {
            ErrorHandling.printError("Error: Invalid data type in formula definition: " + dataTypeCtx.getText());
            //return false;
        }

        // Criar um novo Symbol para a fórmula e adicioná-lo à tabela de símbolos
        Symbol formulaSymbol = new Symbol(formulaName, dataTypeCtx.getText());
        symbolTable.addSymbol(formulaSymbol);

        return visitChildren(ctx);
    }

    @Override
    public Boolean visitTableColumnDefinition(ATableGrammarParser.TableColumnDefinitionContext ctx) {
        String columnName = ctx.ID().getText();

        // Verificar se a coluna já foi definida anteriormente
        if (!symbolTable.isVariableDeclared(columnName)) {
            ErrorHandling.printError("Error: Column " + columnName + " is already defined");
            //return false;
        }

        // Verificar se o tipo de dados da coluna é válido
        ATableGrammarParser.DataTypeContext dataTypeCtx = ctx.dataType();
        if (!isValidDataType(dataTypeCtx.getText())) {
            ErrorHandling.printError("Error: Invalid data type in column definition: " + dataTypeCtx.getText());
            ////return false;
        }

        // Criar um novo Symbol para a coluna e adicioná-lo à tabela de símbolos
        Symbol columnSymbol = new Symbol(columnName, dataTypeCtx.getText());
        symbolTable.addSymbol(columnSymbol);
        
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitDataTypeInteger(ATableGrammarParser.DataTypeIntegerContext ctx) {
        // Não há verificações semânticas
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitDataTypeReal(ATableGrammarParser.DataTypeRealContext ctx) {
        // Não há verificações semânticas
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitDataTypeText(ATableGrammarParser.DataTypeTextContext ctx) {
        // Não há verificações semânticas
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitFieldAccessAssignment(ATableGrammarParser.FieldAccessAssignmentContext ctx) {
        ATableGrammarParser.FieldAccessContext fieldAccessContext = ctx.fieldAccess();
        String objectName = fieldAccessContext.ID(0).getText(); 
        String fieldName = fieldAccessContext.ID(1).getText();  

        
        // Verify if the object and field are declared in the symbol table
        if (!symbolTable.isVariableDeclared(objectName)) {
            ErrorHandling.printError("Error: Object " + objectName + " not declared in symbol table");
            // return false;
        }
    
        if (!symbolTable.isVariableDeclared(fieldName)) {
            ErrorHandling.printError("Error: Field " + fieldName + " not declared in symbol table");
            // return false;
        }
    
        return visitChildren(ctx);
    }


   
    



    //assignment: 
    // fieldAccess ':=' expression ';' #FieldAccessAssignment
    // | ID (':=' expression | ':=' readStatement | ':' dataType (':=' expression)? | ':' tableRef | ':=' tableRef) ';' #IdentifierAssignment
    // ;


    @Override
    public Boolean visitIdentifierAssignment(ATableGrammarParser.IdentifierAssignmentContext ctx) {
        if (ctx.expression() == null && ctx.readStatement() == null && ctx.dataType() == null
                && ctx.tableRef() == null) {
            ErrorHandling.printError("Error: Expression is null");
            //return false;
        }
        
        //c: integer
        if(ctx.getChild(1).getText().equals(":") && ctx.dataType()!=null){
            String variablename = ctx.getChild(0).getText();
            if(symbolTable.isVariableDeclared(variablename)){
                ErrorHandling.printError("Error: Variable " + variablename + " is already defined");
                //return false;
            }
            else{
                Symbol id = new Symbol(variablename);
                symbolTable.addSymbol(id);
            }
        }


        if(ctx.getChild(1).getText().equals(":") && ctx.tableRef()!=null){
            String variableName = ctx.ID().getText();
            
            if(symbolTable.isVariableDeclared(variableName)){
                ErrorHandling.printError("Error: Variable " + variableName + " already declared");
                //return false;
            }
            else if(!ctx.tableRef().getText().equals("ATable") && !ctx.tableRef().getText().equals("Table")){
                ErrorHandling.printError("Error: Type " + ctx.tableRef().getText() + " doesnt exist");
                //return false;
            }
            else{
                Symbol id = new Symbol(variableName);
                symbolTable.addSymbol(id);
            }
            
        } 


        if(ctx.getChild(1).getText().equals(":=") && ctx.expression() != null ) {
            String variableName = ctx.ID().getText();
            if(!symbolTable.isVariableDeclared(variableName)){
                ErrorHandling.printError("Error: variable " + variableName + " not declared");
                //return false;
            }
        }

        

        if(ctx.getChild(1).getText().equals(":=") && ctx.expression() != null && ctx.expression().primary(0) instanceof ATableGrammarParser.FieldAccessPrimaryContext ) {
            String variableName = ctx.expression().primary(0).getText();
            String[] symbols = variableName.split("\\.");
            if (!symbolTable.isVariableDeclared(symbols[0])){
                ErrorHandling.printError("Error: Table " + symbols[0] + " doesn't exist");
            }
            if (!symbolTable.isVariableDeclared(symbols[1])){
                ErrorHandling.printError("Error: Variable " + symbols[1] + " doesn't exist");
            }
            
        }

        
        


        if(ctx.getChild(1).getText().equals(":=") && ctx.expression() != null && ctx.expression().primary(0) instanceof ATableGrammarParser.TableRefPrimaryContext ) {
            String variableName = ctx.expression().primary(0).getText();
            variableName = variableName.replace("(", "");
            variableName = variableName.replace(")", "");
            variableName = variableName.replace("new", "");
            String[] symbols = variableName.split("\\.");
            if (!symbolTable.isVariableDeclared(symbols[0])){
                ErrorHandling.printError("Error: Table " + symbols[0] + " doesn't exist");
            }
            if (!symbolTable.isVariableDeclared(symbols[1])){
                ErrorHandling.printError("Error: Variable " + symbols[1] + " doesn't exist");
            }
            
        }


        if(ctx.getChild(1).getText().equals(":=") && ctx.readStatement()!=null){
            String variableName = ctx.ID().getText();
            if(!symbolTable.isVariableDeclared(variableName)){
                ErrorHandling.printError("Error: VARIABLE " + variableName + " not declared");
                //return false;
            }
        } 


        if (!tableDefined) {
            ErrorHandling.printError("TableDefinition does not exist");
            //return false;
        }

        String variableName = ctx.ID().getText();
    
        Boolean result = visitChildren(ctx);
        return result;
    }

@Override
public Boolean visitPrintStatement(ATableGrammarParser.PrintStatementContext ctx) {
    if (ctx.expressionList() == null && ctx.STRING_LITERAL() == null && ctx.centerExpression() == null) {
        ErrorHandling.printError("arguments of print Statment not passed");
        //return false;
    }
    if(ctx.expressionList() != null) {
        for (ATableGrammarParser.ExpressionContext exprCtx : ctx.expressionList().expression()) {
            if (exprCtx.primary() != null && exprCtx.primary().size() > 0 && exprCtx.primary().get(0) instanceof ATableGrammarParser.IdentifierPrimaryContext) {
                ATableGrammarParser.IdentifierPrimaryContext idCtx = (ATableGrammarParser.IdentifierPrimaryContext) exprCtx.primary().get(0);
                String variablename = idCtx.ID().getText();
                if(!symbolTable.isVariableDeclared(variablename)){
                    ErrorHandling.printError("Error: Variable " + variablename + " not declared");
                    //return false;
                }
            }
        }
    }
    Boolean result = visitChildren(ctx);
    return result;
}





    @Override
    public Boolean visitExpressionList(ATableGrammarParser.ExpressionListContext ctx) {
        for (ATableGrammarParser.ExpressionContext expressionCtx : ctx.expression()) {
            if (expressionCtx == null) {
                ErrorHandling.printError("Error: Invalid expression in expression list");
                //return false;
            }
        }

        return visitChildren(ctx);

    }

@Override
public Boolean visitAddRowStatement(ATableGrammarParser.AddRowStatementContext ctx) {
    String tableName = ctx.tableRef().getText();
    if(instanciada==false){
        ErrorHandling.printError("Error: Table " + tableName + " not initialized");
    }
    // Check if the table is declared and initialized
    if (!symbolTable.isVariableDeclared(tableName)) {
        ErrorHandling.printError("Error: Table " + tableName + " not declared or not initialized");
        //return false;
    }
    
    else if(ctx.expressionList() != null) {
        List<ATableGrammarParser.ExpressionContext> exprList = ctx.expressionList().expression();
        boolean contains = ctx.expressionList().expression(1).primary(0).getText().contains("readin");
        if (contains == false && !symbolTable.isVariableDeclared(ctx.expressionList().expression(1).primary(0).getText())){
            ErrorHandling.printError("Error: Variable " + ctx.expressionList().expression(1).getText() + " does not exist ");
        }
        for (ATableGrammarParser.ExpressionContext exprCtx : exprList) {
            List<ATableGrammarParser.PrimaryContext> primaryList = exprCtx.primary();
            for (ATableGrammarParser.PrimaryContext primaryCtx : primaryList) {
                // Check if primaryCtx is instance of ReadPrimaryContext
                if (primaryCtx instanceof ATableGrammarParser.ReadPrimaryContext) {
                    // Cast to ReadPrimaryContext
                    ATableGrammarParser.ReadPrimaryContext readPrimaryCtx = (ATableGrammarParser.ReadPrimaryContext) primaryCtx;
                    if (readPrimaryCtx.STRING_LITERAL() != null) {
                        // Extract column name from the user input prompt
                        String columnName = readPrimaryCtx.STRING_LITERAL().getText().replace("\"", "");
                        if (columnName.endsWith(":")) {
                            columnName = columnName.substring(0, columnName.length() - 1);
                        }
                        // Check if the column exists in the symbol table
                        if (!symbolTable.isVariableDeclared(columnName)) {
                            ErrorHandling.printError("Error: Column " + columnName + " does not exist in table " + tableName);
                            //return false;
                        }

                        
                    }
                }
            }
        }
    }
    return visitChildren(ctx);
}




    @Override
    public Boolean visitTableRef(ATableGrammarParser.TableRefContext ctx) {
        Boolean result = visitChildren(ctx);
        return result;
    }

    @Override
    public Boolean visitHeaderStatement(ATableGrammarParser.HeaderStatementContext ctx) {
        // Get the table name from tableRef
    
        String tableName = ctx.tableRef().getText().split("\\.")[0];
        if(instanciada==false){
            ErrorHandling.printError("Error: Table " + tableName + " not initialized");
        }
        // Verify that the table is defined
        if (!symbolTable.isVariableDeclared(tableName)) {
            ErrorHandling.printError("Error: Table " + tableName + " not declared");
        }

        Boolean result = visitChildren(ctx);
        return result;
    }

    @Override
    public Boolean visitLoopStatement(ATableGrammarParser.LoopStatementContext ctx) {
        String variablename = ctx.ID().getText();
        Symbol id = new Symbol(variablename);
        symbolTable.addSymbol(id);
        
        if(!symbolTable.isVariableDeclared(ctx.tableRef().getText())){
            ErrorHandling.printError("Error: Variable " + ctx.tableRef().getText() + " not declared or not initialized");
            //return false;
        }

            
        if (ctx.statement().isEmpty()) {
            ErrorHandling.printError("Error: Invalid statement in loop statement");
            //return false;
        }

        return visitChildren(ctx);
    }

    // Don't know if it's Working
    @Override
    public Boolean visitCenterExpression(ATableGrammarParser.CenterExpressionContext ctx) {
        if (ctx.expression().get(0) == null || ctx.expression().get(1) == null || ctx.expression().size() != 2) {
            ErrorHandling.printError("Error: Invalid expression in center expression");
        //return false;
        }
        return visitChildren(ctx);
    }



    @Override
    public Boolean visitExpression(ATableGrammarParser.ExpressionContext ctx) {
        ATableGrammarParser.PrimaryContext primaryCtx = ctx.primary().get(0);
        if (primaryCtx == null) {
            ErrorHandling.printError("Error: Invalid primary in expression");
            //return false;
        }
        return visitChildren(ctx);
    }





    @Override
    public Boolean visitFieldAccessPrimary(ATableGrammarParser.FieldAccessPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    
    @Override
    public Boolean visitIdentifierPrimary(ATableGrammarParser.IdentifierPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    
    @Override
    public Boolean visitIntegerLiteralPrimary(ATableGrammarParser.IntegerLiteralPrimaryContext ctx) {
        if (ctx.INTEGER_LITERAL() == null) {
            ErrorHandling.printError("Error: Invalid integer literal");
            //return false;
        }
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitRealLiteralPrimary(ATableGrammarParser.RealLiteralPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitStringLiteralPrimary(ATableGrammarParser.StringLiteralPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitParenthesisPrimary(ATableGrammarParser.ParenthesisPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    // nao é preciso
    @Override
    public Boolean visitDataTypePrimary(ATableGrammarParser.DataTypePrimaryContext ctx) {
        return visitChildren(ctx);
    }

   
    @Override
    public Boolean visitNewPrimary(ATableGrammarParser.NewPrimaryContext ctx) {
        instanciada = true;
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitListPrimary(ATableGrammarParser.ListPrimaryContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Boolean visitReadPrimary(ATableGrammarParser.ReadPrimaryContext ctx) {
        ATableGrammarParser.DataTypeContext dataTypeCtx = ctx.dataType();
        if (dataTypeCtx == null || !isValidDataType(dataTypeCtx.getText())) {
            ErrorHandling.printError("Error: Invalid data type in formula definition: " + dataTypeCtx.getText());
            //return false;
        }
        return visitChildren(ctx);

        //se mudar o string literal para um nome que nao existe, devia dar erro e n da
    }

@Override
public Boolean visitLengthPrimary(ATableGrammarParser.LengthPrimaryContext ctx) {
    String tableName = ctx.tableRef().getText(); // get the table reference
    String variableName = ctx.ID().getText(); // get the variable name

    if (!symbolTable.isVariableDeclared(tableName)) {
        ErrorHandling.printError("Error: Table "+ tableName + " doesnt exist");
        return false;
    }
    else if (!symbolTable.isVariableDeclared(variableName)) {
        ErrorHandling.printError("Error: Variable "+ variableName+ " does not exist");
    }

    return visitChildren(ctx);
}


    @Override
    public Boolean visitTableRefPrimary(ATableGrammarParser.TableRefPrimaryContext ctx) {
        if (ctx.tableRef() == null) {
            ErrorHandling.printError("Error: Invalid table reference");
            //return false;
        }
        return visitChildren(ctx);

    }

@Override
public Boolean visitFieldAccess(ATableGrammarParser.FieldAccessContext ctx) {
    
    String objectName = ctx.ID(0).getText();
    String fieldName = ctx.ID(1).getText();


    
    
    if (!symbolTable.isVariableDeclared(objectName)) {
        ErrorHandling.printError("Error: Symbol " + objectName + " not declared in symbol table");
        
    }
    
    if (!symbolTable.isVariableDeclared(fieldName)) {
        ErrorHandling.printError("Error: Field " + fieldName + " not declared in table " + objectName);
        // return false;
    }
    
    return visitChildren(ctx);
}


    //readStatement: 'read' STRING_LITERAL (usingClause)?;
    @Override
    public Boolean visitReadStatement(ATableGrammarParser.ReadStatementContext ctx) {
       
        String stringWithQuotes = ctx.STRING_LITERAL().getText();
        String stringWithoutQuotes = stringWithQuotes.substring(1, stringWithQuotes.length() - 1);

        // Verificar se a string literal está vazia
        if (stringWithoutQuotes.isEmpty()) {
            ErrorHandling.printError("Error: Empty string literal in read statement");
            //return false;
        }


        // Verificar a extensão do arquivo
        if (!stringWithoutQuotes.toLowerCase().endsWith(".csv")) {
            ErrorHandling.printError("Error: Invalid file extension in read statement. Expected .csv");
            //return false;
        }

        return visitChildren(ctx);
    }

    @Override
    public Boolean visitUsingClause(ATableGrammarParser.UsingClauseContext ctx) {
        /*
        if(ctx.columnMapping() == null){
            ErrorHandling.printError("Error: Invalid Using clause");
            //return false;
            
        }
        */
        return visitChildren(ctx);
    }

    //columnMapping: (('column')? (ID | STRING_LITERAL | INTEGER_LITERAL)) 'as' ID;

    @Override
    public Boolean visitColumnMapping(ATableGrammarParser.ColumnMappingContext ctx) {

    if(ctx.INTEGER_LITERAL()!=null) {
        
        int column= (Integer.parseInt(ctx.INTEGER_LITERAL().getText()));
        if(column<=0){
            ErrorHandling.printError("Error: Invalid column number");
            //return false;
        }
    }

    else if(ctx.STRING_LITERAL()!=null) {
        String column= ctx.STRING_LITERAL().getText();
        if(column.isEmpty() || symbolTable.isVariableDeclared(column)){
            ErrorHandling.printError("Error: Invalid column name");
            //return false;
        }
    }
        return visitChildren(ctx);
    }
}
