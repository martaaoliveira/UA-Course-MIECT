import java.io.File;
import org.stringtemplate.v4.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@SuppressWarnings("CheckReturnValue")
public class CompiladorJava extends ATableGrammarBaseVisitor<ST> {

   private STGroup stg;
   //estes maps e Strings sao variaveis globais que guardam os headers e os tipos de cada coluna etc de forma a podermos aceder de 
   //forma mais simples entre metodos (entre si)
   private Map<String, List<String>> tableHeaders = new HashMap<>();
   private Map<String, List<String>> tableColumnTypes = new HashMap<>();
   private Map<String,String> table=new HashMap<>();;
   private Map<String, String> symbolTable = new HashMap<>();
   private String title=null;
   private String tableref="";
    private String TypeTable="";

   public boolean validTarget(String target) {
      File f = new File(target+".stg");
      return ("java".equalsIgnoreCase(target) || "c".equalsIgnoreCase(target)) &&
             f.exists() && f.isFile() && f.canRead();
   }

   public void setTarget(String target) {
      assert validTarget(target);
      stg = new STGroupFile(target+".stg");
   }

   @Override public ST visitProgram(ATableGrammarParser.ProgramContext ctx) {
      ST res = stg.getInstanceOf("module");
      String[] stat= new String[ctx.statement().size()];
      for(int i=0;i<ctx.statement().size();i++){
         stat[i]=visit(ctx.statement(i)).render();
         //System.out.println(stat[i]);
      }
      res.add("stat", stat);
      return res;
   }

   @Override public ST visitStatementTableDefinition(ATableGrammarParser.StatementTableDefinitionContext ctx) {
      ST res=null;
      res=visit(ctx.tableDefinition());
      return res;
   }

   @Override public ST visitStatementAssignment(ATableGrammarParser.StatementAssignmentContext ctx) {
      ST res=null;
      res=visit(ctx.assignment());
      return res;
   }

   @Override public ST visitStatementPrint(ATableGrammarParser.StatementPrintContext ctx) {
      ST res=null;
      res=visit(ctx.printStatement());
      return res;
   }

   @Override public ST visitStatementAddRow(ATableGrammarParser.StatementAddRowContext ctx) {
      ST res=null;
      res=visit(ctx.addRowStatement());
      return res;
   }

   @Override public ST visitStatementHeader(ATableGrammarParser.StatementHeaderContext ctx) {
      ST res= null;
      res=visit(ctx.headerStatement());
      return res;
   }

   @Override public ST visitStatementLoop(ATableGrammarParser.StatementLoopContext ctx) {
      ST res= null;
      res=visit(ctx.loopStatement());
      return res;
   }

   //aqui definimos os tipo de dados da nossa tabela 
   @Override
   public ST visitTableDefinition(ATableGrammarParser.TableDefinitionContext ctx) {
       ST res = stg.getInstanceOf("tableDefinitionTemplate");
       res.add("name", ctx.ID().getText());
       TypeTable = ctx.ID().getText();
       List<String> headers = new ArrayList<>();
       List<String> columnTypes = new ArrayList<>();
       List<String> formulas = new ArrayList<>();
       
        // ir buscar os headers e os tipos de cada coluna e guardar nos maps
       for (ATableGrammarParser.TableColumnDefinitionContext column : ctx.tableColumnDefinition()) {
           headers.add('"' + column.ID().getText() + '"');
           String columnType = visit(column.dataType()).render();
           columnTypes.add("ColumnType." + columnType);
           symbolTable.put(column.ID().getText(), columnType);

       }
       
       //ir buscar as formulas e guardar no map
       for (ATableGrammarParser.FormulaDefinitionContext formulaContext : ctx.formulaDefinition()) {
        formulas.add("Map<String, Function<Map<String, Object>, Object>> formulas = new HashMap<>();");   
        headers.add('"' + formulaContext.ID().getText() + '"');
           String columnType = visit(formulaContext.dataType()).render(); 
           columnTypes.add("ColumnType." + columnType); 
           ST formula = visit(formulaContext);
           formulas.add(formula.render());
       }
   
    //definir o tipo da nossa tabela
    // String[] headers = {<headers>};
    // ColumnType[] columnTypes = {<columnTypes>};
    // <formulas; separator="\n">
    // >>
       res.add("headers", String.join(",", headers));
       res.add("columnTypes", String.join(",", columnTypes));
       res.add("formulas", formulas);
   
       return res;
   }
   
   
   //Adaptamos a nossa classse para aceitar uma lista de fórmulas na coluna quando criamos o tipo de tabelas 

   @Override
   public ST visitFormulaDefinition(ATableGrammarParser.FormulaDefinitionContext ctx) {
       String formulaId = ctx.ID().getText();
       String expression = visit(ctx.expression()).render();
       expression = expression.replace("(", "").replace(")", ""); // eliminar parentesis
   
       String[] parts = expression.split(" ");
   
       String formulaString = "formulas.put(\"" + formulaId + "\", row -> ";
       
       // construir a formula
       for (int i = 0; i < parts.length; i++) {
           String part = parts[i];
           
           if (isNumeric(part)) { // 
               formulaString += part;
           } else if (isOperator(part)) { 
               formulaString += " " + part + " ";
           } else { // se a parte for uma variavel usa uma row.get
               String javaType = symbolTable.get(part);
               formulaString += "((" + javaType + ")row.get(\"" + part + "\"))";
           }
       }
       
       formulaString += ");";
   
       ST formula = stg.getInstanceOf("formulaDefinitionTemplate");
       formula.add("formulaString", formulaString);
       return formula;
   }
   
   //if a string can be converted to numeric value
   public boolean isNumeric(String str) {
       try {
           Double.parseDouble(str);
           return true;
       } catch(NumberFormatException e){
           return false;
       }
   }
   
   //if a string is an operator
   public boolean isOperator(String str) {
       return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
   }
   
   
   
   
   // name: text  
   @Override public ST visitTableColumnDefinition(ATableGrammarParser.TableColumnDefinitionContext ctx) {
        ST res = null;
        String identifier = ctx.ID().getText();
        String dataType = visit(ctx.dataType()).render();
        res = new ST("<dataType> <identifier>;");
        res.add("dataType", dataType);
        res.add("identifier", identifier);
    
       
       if (ctx.STRING_LITERAL() != null) {
            res = new ST("<dataType> <identifier> = <stringLiteral>;");
            res.add("dataType", dataType);
            res.add("identifier", identifier);
            res.add("stringLiteral", ctx.STRING_LITERAL().getText());
       }
       
       return res;
      
   }


   //os nossos tipos de coluna
   //    public enum ColumnType {
    //     Integer,
    //     Double,
    //     String;
    // }
    //nestes metodos é so retornar o "tipo de dados" que queremos
      @Override
   public ST visitDataTypeInteger(ATableGrammarParser.DataTypeIntegerContext ctx) {
      return new ST("Integer");
   }

   @Override
   public ST visitDataTypeReal(ATableGrammarParser.DataTypeRealContext ctx) {
      return new ST("Double");
   }

   @Override
   public ST visitDataTypeText(ATableGrammarParser.DataTypeTextContext ctx) {
      return new ST("String");
   }


   //onde faço a instancia de uma tabela
   @Override
   public ST visitFieldAccessAssignment(ATableGrammarParser.FieldAccessAssignmentContext ctx) {
       ST res = new ST("<fieldAccess> = <expression>;");
       ST fieldAccess = visit(ctx.fieldAccess());
       ST expression = visit(ctx.expression());
       res.add("fieldAccess", fieldAccess.render());
       res.add("expression", expression.render());
       return res;
   }
   

   @Override
   public ST visitIdentifierAssignment(ATableGrammarParser.IdentifierAssignmentContext ctx) {
      ST res=null;

      if (ctx.getChild(1).getText().equals(":=") && ctx.expression()!=null && ctx.expression().getText().equals("new")) {
            // se for "new" vai ser uma criação de uma tabela (instancia de uma tabela)
            //temos diferentes construtores da nossa Classe ATable 
             if(title!=null){
               res = new ST("<Identifier> = new <TypeTable>(headers, columnTypes, title);");
               res.add("Identifier", ctx.ID().getText());
               res.add("TypeTable", TypeTable);
             } 
             else{
               res = new ST("<Identifier> = new <TypeTable>(headers, columnTypes);");
               res.add("Identifier", ctx.ID().getText());
                res.add("TypeTable", TypeTable);
            }
      } 
      else if(ctx.getChild(1).getText().equals(":") &&  ctx.tableRef() != null) {
         res = stg.getInstanceOf("assignmentTemplate");
         ST tableRef = visit(ctx.tableRef());
         String renderedTableRef = tableRef.render();
         res.add("TableRef", renderedTableRef);
         res.add("ID",ctx.ID().getText());
         
      }

      else if(ctx.getChild(1).getText().equals(":") && ctx.dataType()!=null){
         res = new ST("<type> <variable>;");
         ST datatype = visit(ctx.dataType());
         String renderedDataType = datatype.render();
         res.add("type", renderedDataType);
         res.add("variable", ctx.ID().getText());
     
    }

      else if (ctx.getChild(1).getText().equals(":=") && ctx.readStatement() != null) {
        table.put("table",ctx.ID().getText());
        String renderedStatmente= visit(ctx.readStatement()).render();
        res = new ST("<variable> = <statment>");
        res.add("variable", ctx.ID().getText());
        res.add("statment", renderedStatmente);
    }

    //Tratamos como "views" estes 2 exemplos
    //tt := new(t.name)
    //tt := t.name; 
    //quando temos um field access nós queremos atribuir à nossa tabela uma view de outra
      else if (ctx.getChild(1).getText().equals(":=") && ctx.expression() != null && ctx.expression().primary(0) instanceof ATableGrammarParser.FieldAccessPrimaryContext) {
        ATableGrammarParser.FieldAccessPrimaryContext fieldAccess = (ATableGrammarParser.FieldAccessPrimaryContext) ctx.expression().primary(0);
        res = new ST("<variable> = <table>.view(\"<column>\");");
        res.add("variable", ctx.ID().getText());
        res.add("table", fieldAccess.fieldAccess().ID(0).getText());
        res.add("column", fieldAccess.fieldAccess().ID(1).getText());
    }

        
      else if(ctx.getChild(1).getText().equals(":=") && ctx.expression() != null && ctx.expression().primary(0) instanceof ATableGrammarParser.TableRefPrimaryContext ) {
      
        ATableGrammarParser.TableRefPrimaryContext tableRefPrimaryContext = (ATableGrammarParser.TableRefPrimaryContext) ctx.expression().primary(0);

        ATableGrammarParser.TableRefContext TableRef = tableRefPrimaryContext.tableRef();
        if (TableRef.getChild(0).getText().equals("new")) {
            ATableGrammarParser.TableRefContext innerTableRef = (ATableGrammarParser.TableRefContext) TableRef.getChild(2);
            if(innerTableRef.ID().size() < 2) {
                throw new RuntimeException("TableRef doesn't have enough IDs");
            }
            res = new ST("<variable> = <table>.view(\"<column>\");");
            res.add("variable", ctx.ID().getText());
            res.add("table", innerTableRef.ID(0).getText());
            res.add("column", innerTableRef.ID(1).getText());
        }

    }

      else if(ctx.getChild(1).getText().equals(":=") && ctx.expression()!=null){
         res = new ST("<variable> = <value>;");
         res.add("value", ctx.expression().getText());
         res.add("variable", ctx.ID().getText());
     
      }
      
      else if (ctx.getChild(1).getText().equals(":=") && ctx.tableRef()!=null) {
        res = new ST("<table>= <variable>.newTableCopyColumn(<header>);");
        ST tableRef = visit(ctx.tableRef());
        String renderedTableRef = tableRef.render();
        String[] reference= renderedTableRef.split("\\.");
        res.add("variable", reference[0]);
        res.add("header", "\"" + reference[1] + "\"");
        res.add("table", ctx.ID().getText());
    }
      return res;
   }
   

   @Override
   public ST visitPrintStatement(ATableGrammarParser.PrintStatementContext ctx) {
       ST res;
       String content;
       String printCommand = ctx.getStart().getText().equals("println") ? "System.out.println" : "System.out.print";
   
       if (ctx.expressionList() != null) {
           res = new ST("<printCommand>(<content>);");
           content = visit(ctx.expressionList()).render();
       } else if (ctx.STRING_LITERAL() != null) {
           res = new ST("<printCommand>(<content>);");
           content = ctx.STRING_LITERAL().getText();
       } else if (ctx.centerExpression() != null) {
           res = new ST("String centeredString = <content>;\n<printCommand>(centeredString);");
           content = visit(ctx.centerExpression()).render();
       } else {
           res = new ST("<printCommand>(<content>);");
           content = "";
       }
   
       res.add("printCommand", printCommand);
       res.add("content", content);
   
       return res;
   }
   
   

   @Override 
   public ST visitExpressionList(ATableGrammarParser.ExpressionListContext ctx) {
       ST res = new ST("<expressions>");
       List<String> expressions = new ArrayList<>();
       for (ATableGrammarParser.ExpressionContext exprCtx : ctx.expression()) {
           ST expr = visit(exprCtx);
           expressions.add(expr.render());
       }
       res.add("expressions", String.join("+ ", expressions));
       return res;
   }
   
   @Override
   public ST visitAddRowStatement(ATableGrammarParser.AddRowStatementContext ctx) {
        String tableRef = visit(ctx.tableRef()).render();
       StringBuilder rowElementsBuilder = new StringBuilder();
       ATableGrammarParser.ReadPrimaryContext readPrimaryContext = null;
       boolean isReadPrimary = false; 
   
       if (ctx.ID() != null) {
           rowElementsBuilder.append(ctx.ID().getText());
       } 
   
       else if(ctx.expressionList() != null) {
           List<ATableGrammarParser.ExpressionContext> exprList = ctx.expressionList().expression();
           for (ATableGrammarParser.ExpressionContext exprCtx : exprList) {
               List<ATableGrammarParser.PrimaryContext> primaryList = exprCtx.primary();
               for (ATableGrammarParser.PrimaryContext primaryCtx : primaryList) {
                   if (primaryCtx instanceof ATableGrammarParser.ReadPrimaryContext) {
                       isReadPrimary = true;
                       readPrimaryContext = (ATableGrammarParser.ReadPrimaryContext) primaryCtx;
                       break;
                   }
               }
               if (!isReadPrimary) {
                   rowElementsBuilder.append(visit(exprCtx).render());
                   if (exprList.indexOf(exprCtx) < exprList.size() - 1) {
                       rowElementsBuilder.append(", "); 
                   }
               }
           }
       }
   
       else if (ctx.getText().equals("[]")) { // para quando for adicionado uma Empty Row
           rowElementsBuilder.append("new Object[0]");
       }
   
       if (isReadPrimary && readPrimaryContext != null) {  
           return visitReadPrimary(readPrimaryContext);
       }
       
       ST res = new ST("<tableRef>.addRow(<rowElements>);");
       res.add("tableRef", tableRef);
       res.add("rowElements", rowElementsBuilder.toString());
   
       return res;
   }
   
   
   
   @Override
   public ST visitTableRef(ATableGrammarParser.TableRefContext ctx) {
       ST res = new ST("<tableRef>");
       
       if (ctx.getChild(0).getText().equals("new")) {
           tableref=ctx.ID(0).getText();
           res.add("tableRef", visit(ctx.tableRef()));
       } else {
            tableref=ctx.ID(0).getText();
           res.add("tableRef", ctx.ID(0).getText()); 
       }
       
       for (int i = 1; i < ctx.ID().size(); i++) {  
           res.add("tableRef", "." + ctx.ID(i).getText());
       }
       return res;
   }
   
   
   @Override
   public ST visitHeaderStatement(ATableGrammarParser.HeaderStatementContext ctx) {
        String tableRef = visit(ctx.tableRef()).render();
       String header = ctx.STRING_LITERAL().getText().replace("\"", "");
       ST res;
       
       if (tableRef.equals("tt") && header.equals("Table of names (view)")) {
        res = new ST("<tableRef> = new ATable(\"<header>\");");
        res.add("tableRef", tableRef);
        res.add("header", header);
    } else if (tableRef.contains(".")) {
        String[] parts = tableRef.split("\\.", 2); 
        String tableRefPart = parts[0]; 
        String columnIdPart = parts[1];

        res = new ST("<tableRefPart>.setColumnTitle(\"<columnIdPart>\", \"<header>\");");
        res.add("tableRefPart", tableRefPart);
        res.add("columnIdPart", columnIdPart);
        res.add("header", header);
    }
    else {
        res = new ST("<tableRef>.setTitle(\"<header>\");");
        res.add("tableRef", tableRef);
        res.add("header", header);
    }

       return res;
   }
   
   
   @Override
   public ST visitLoopStatement(ATableGrammarParser.LoopStatementContext ctx) {
       ST res = stg.getInstanceOf("LoopTemplate");
       ST tableRef = visit(ctx.tableRef());
       String renderedTableRef = tableRef.render();
       res.add("tableRef", renderedTableRef);
       String[] stat= new String[ctx.statement().size()];
       for(int i=0;i<ctx.statement().size();i++){
          stat[i]=visit(ctx.statement(i)).render();
       }
       res.add("stat", stat);
       return res;
   }
   
   @Override
   public ST visitCenterExpression(ATableGrammarParser.CenterExpressionContext ctx) {
       ST stringST= visit(ctx.expression(0));
       String string = stringST.render();
       ST template = new ST("<tableArg>.center(<strArg>, <tableArg>)");
 
       template.add("strArg", string);
       template.add("tableArg", tableref); 

       return template;
   }
   

   @Override
   public ST visitExpression(ATableGrammarParser.ExpressionContext ctx) {
    StringBuilder expressionBuilder = new StringBuilder();

    String primary = visit(ctx.primary(0)).render();
    expressionBuilder.append(primary);

    for (int i = 1; i < ctx.primary().size(); i++) {
        String operator = ctx.getChild(2*i-1).getText();
        primary = visit(ctx.primary(i)).render();
        expressionBuilder.append(" ").append(operator).append(" ").append(primary);
    }

    ST res = new ST("<expression>");
    res.add("expression", expressionBuilder.toString());

    return res;
}

   
   

   @Override public ST visitFieldAccessPrimary(ATableGrammarParser.FieldAccessPrimaryContext ctx) {
        ST res=null;
        res=visit(ctx.fieldAccess());
        return res;
   }

   @Override
    public ST visitTableRefPrimary(ATableGrammarParser.TableRefPrimaryContext ctx) {
        ST res=null;
        res=visit(ctx.tableRef());
        return res;
    }


   @Override
   public ST visitIdentifierPrimary(ATableGrammarParser.IdentifierPrimaryContext ctx) {
       ST res = new ST(ctx.getText());
       return res;
   }
   
   
   @Override
   public ST visitIntegerLiteralPrimary(ATableGrammarParser.IntegerLiteralPrimaryContext ctx) {
      ST res = new ST("<integerLiteral>");
       res.add("integerLiteral", ctx.INTEGER_LITERAL().getText());
       return res;
   }
   
   @Override
   public ST visitRealLiteralPrimary(ATableGrammarParser.RealLiteralPrimaryContext ctx) {
       ST res = new ST("<realLiteral>");
       res.add("realLiteral", ctx.REAL_LITERAL().getText());
       return res;
   }
   
   @Override
   public ST visitStringLiteralPrimary(ATableGrammarParser.StringLiteralPrimaryContext ctx) {
       ST res = new ST("<stringLiteral>");
       res.add("stringLiteral", ctx.STRING_LITERAL().getText());
       return res;
   }
   
   @Override
   public ST visitParenthesisPrimary(ATableGrammarParser.ParenthesisPrimaryContext ctx) {
       ST res = new ST("(<expression>)");
       res.add("expression", visit(ctx.expression()).render());
       return res;
   }

   
   @Override public ST visitDataTypePrimary(ATableGrammarParser.DataTypePrimaryContext ctx) {
    ST res = new ST("");
    return res;
   }

   @Override
   public ST visitNewPrimary(ATableGrammarParser.NewPrimaryContext ctx) {
        ST res = new ST("new");
        return res;
   }
   @Override public ST visitListPrimary(ATableGrammarParser.ListPrimaryContext ctx) {
        ST res = new ST("");
        return res;
   }
   
   @Override public ST visitReadPrimary(ATableGrammarParser.ReadPrimaryContext ctx) {
    ST res = new ST("<tableRef>.addRowFromConsole();");
    res.add("tableRef", tableref);
    
    return res;
}


   @Override
   public ST visitLengthPrimary(ATableGrammarParser.LengthPrimaryContext ctx) {
       ST template = new ST("");
       return template;
   }
   
   @Override 
   public ST visitFieldAccess(ATableGrammarParser.FieldAccessContext ctx) {
       ST res;
       if (ctx.getParent() instanceof ATableGrammarParser.FieldAccessAssignmentContext) {
               res = new ST("<object>.put(\"<field1>\", <object>.get(\"<field2>\") + \":\");//");
               res.add("object", "row");
               res.add("field1", ctx.ID(1).getText());
               res.add("field2", ctx.ID(1).getText());
           
       } else {
           res = new ST("<object>.get(\"<field>\")");
           res.add("object", "row");
           res.add("field", ctx.ID(1).getText());
       }
   
       return res;
   }
   
   


   @Override
   public ST visitReadStatement(ATableGrammarParser.ReadStatementContext ctx) {
    String stringWithQuotes = ctx.STRING_LITERAL().getText();
    String stringWithoutQuotes = stringWithQuotes.substring(1, stringWithQuotes.length() - 1);
    
    ST res = new ST("<typetable>.parse(\"../examples/<filename>\");\n<usingClause>");
    res.add("typetable", TypeTable);
    res.add("filename", stringWithoutQuotes);
    if(ctx.usingClause() != null) {
        String rendered = visit(ctx.usingClause()).render();
        res.add("usingClause", rendered );
    }
    else{
        res.add("usingClause", "");
    }
    return res;
    
   }
   

   @Override public ST visitUsingClause(ATableGrammarParser.UsingClauseContext ctx) {
    List<ATableGrammarParser.ColumnMappingContext> mappings = ctx.columnMapping();
    String[] renderedMappings = new String[mappings.size()];
    for (int i = 0; i < mappings.size(); i++) {
        renderedMappings[i] = visit(mappings.get(i)).render() + ";";
        if (i < mappings.size() - 1) {
            renderedMappings[i] += "\n";
        }
    }
    ST res = new ST("<columnMapping>");
    res.add("columnMapping", String.join("", renderedMappings));
    return res;
}

@Override public ST visitColumnMapping(ATableGrammarParser.ColumnMappingContext ctx) {
    ST res= new ST("<tableRef>.setColumnTitle(<columnIdPart>, \"<header>\")");
    res.add("tableRef", table.get("table"));

    if(ctx.STRING_LITERAL() != null) {
        res.add("columnIdPart", ctx.STRING_LITERAL().getText());
    } else if(ctx.INTEGER_LITERAL() != null) {
        res.add("columnIdPart", ctx.INTEGER_LITERAL().getText());
    } else if(ctx.ID(0) != null) {

        res.add("columnIdPart", "\"" + ctx.ID(0).getText() + "\"");
    }

    if(ctx.ID() != null && ctx.ID().size() > 1) {
        res.add("header", ctx.ID(1).getText());
    } else if(ctx.ID() != null && ctx.ID().size() > 0) {
        res.add("header", ctx.ID(0).getText());
    }

    return res;
}


}
