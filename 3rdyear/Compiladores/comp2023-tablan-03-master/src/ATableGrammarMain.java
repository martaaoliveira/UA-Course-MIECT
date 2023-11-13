import static java.lang.System.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;

public class ATableGrammarMain {
   public static void main(String[] args) {
      try {
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromStream(System.in);
         // create a lexer that feeds off of input CharStream:
         ATableGrammarLexer lexer = new ATableGrammarLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         ATableGrammarParser parser = new ATableGrammarParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            
            checkSemantics visitor1 = new checkSemantics();
            visitor1.visit(tree);
            if (ErrorHandling.error())System.exit(1);
    //        System.out.println("Semantic analysis completed without errors.");
              // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            System.out.println("Compiling to Java...");
            CompiladorJava visitor0 = new CompiladorJava();
            //visitor0.validTarget("java");
            visitor0.setTarget("java");
            ST code = visitor0.visit(tree);
            //visitor0.visit(tree);
            String fileName ="OutputJava";
            code.add("name",fileName);
            PrintWriter pw = new PrintWriter(new File(fileName+".java"));
            pw.print(code.render());
            pw.close();
            }
         }
          
         catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
         catch(RecognitionException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }

   }


