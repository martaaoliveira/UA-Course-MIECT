
import java.util.HashMap;
import java.util.Map;



public class SymbolTable {
    private Map<String, Symbol> symbols;

    public SymbolTable() {
        this.symbols = new HashMap<>();
    }

    public void addSymbol(Symbol symbol) {
        this.symbols.put(symbol.getName(), symbol);
    }

    public Symbol getSymbol(String name) {
        return this.symbols.get(name);
    }


    public boolean isVariableDeclared(String variableName) {
        return symbols.containsKey(variableName);
    }

   
    
    public boolean isFieldAccessValid(String fieldName) {
        return symbols.containsKey(fieldName);
    }
    
    public boolean isColumnDefined(String columnName) {
        return symbols.containsKey(columnName);
    }
    
    public boolean isFormulaDefined(String formula) {
        return symbols.containsKey(formula);
    }

    
    
     
    
}
