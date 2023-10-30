import java.util.*;
import java.math.BigInteger;

@SuppressWarnings("CheckReturnValue")
public class Execute extends BigIntCalcBaseVisitor<BigInteger> {

   Map<String,BigInteger> map= new HashMap<>();

   @Override public BigInteger visitShow(BigIntCalcParser.ShowContext ctx) {
      BigInteger res = null;
      res=visit(ctx.expr());
      if(res!=null){
         System.out.println(res);
      }
      return res;
   }

   @Override public BigInteger visitAssignment(BigIntCalcParser.AssignmentContext ctx) {
      BigInteger res = null;
      res=visit(ctx.expr());
      String id = ctx.ID().getText();
      if(res!=null && !map.containsKey(id)){
         map.put(id,res);
      }
      return res;
   }

   @Override public BigInteger visitExprInteger(BigIntCalcParser.ExprIntegerContext ctx) {
      BigInteger res = new BigInteger(ctx.INTEGER().getText());
      return res;
   }

   @Override public BigInteger visitExprID(BigIntCalcParser.ExprIDContext ctx) {
      BigInteger res=null;
      String id = ctx.ID().getText();
      if(id!=null) res= map.get(id);
      //System.out.println(map.get(id));
      return res;

   }

      @Override public BigInteger visitExprUnary(BigIntCalcParser.ExprUnaryContext ctx) {
         BigInteger res=visit(ctx.expr());
         if(res!=null){
            if(ctx.op.getText().equals("-")){
               res=res.negate();
            }
         }
         return res;

   }

      @Override public BigInteger visitExprDiv(BigIntCalcParser.ExprDivContext ctx) {
         BigInteger res1=visit(ctx.expr(0));
         BigInteger res2=visit(ctx.expr(1));
         if(res1!=null && res2!=null){
            res1=res1.divide(res2);
            
         }
         return res1;
   }

      @Override public BigInteger visitExprMod(BigIntCalcParser.ExprModContext ctx) {
         BigInteger res1=visit(ctx.expr(0));
         BigInteger res2=visit(ctx.expr(1));
         if(res1!=null && res2!=null){
            res1=res1.mod(res2);
            
         }
         return res1;
   }

         @Override public BigInteger visitExprMul(BigIntCalcParser.ExprMulContext ctx) {
         BigInteger res1=visit(ctx.expr(0));
         BigInteger res2=visit(ctx.expr(1));
         if(res1!=null && res2!=null){
            res1=res1.multiply(res2);
            
         }
         return res1;
   }
   
      @Override public BigInteger visitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx) {
         BigInteger res1=visit(ctx.expr(0));
         BigInteger res2=visit(ctx.expr(1));
      
         if(res1!=null && res2!=null){
            if(ctx.op.getText().equals("+")){
               res1=res1.add(res2);
            }
            else if(ctx.op.getText().equals("-")){
               res1=res1.subtract(res2);
            }
            else{
               System.err.println("Operand not defined");
            }
         }
         return res1;
   }

      @Override public BigInteger visitExprParen(BigIntCalcParser.ExprParenContext ctx) {
         BigInteger res=visit(ctx.expr());
         return res;
   }

}
