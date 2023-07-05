import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class PasseioBicicleta extends Atividade {
    private String[] locais;

    public PasseioBicicleta(int identificador, String nome, String[] locais) {
        super(identificador, nome);
        this.locais = locais;
        Arrays.sort(locais);
    }

    public PasseioBicicleta(int identificador, String nome) {
        super(identificador, nome);
        this.locais = new String[0];
    }

    @Override
    public Collection<String> locais() {
        Collection<String> c = new ArrayList<>();
        for (String r : locais){
            c.add(r);
        }
        return c;
    }

    public void addLocal(String local) {
        Set<String> locaisSet = new TreeSet<>();
        for (int i = 0; i < locais.length; i++) {
            locaisSet.add(locais[i]);
        }
        locaisSet.add(local);
        this.locais = new String[locais.length + 1];
        locaisSet.toArray(locais);
        Arrays.sort(locais);
    }

    public String[] getLocais() {
        return this.locais;
    }

    public void setLocais(String[] locais) {
        this.locais = locais;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((locais == null) ? 0 : locais.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PasseioBicicleta other = (PasseioBicicleta) obj;
        if (locais == null) {
            if (other.locais != null)
                return false;
        } else if (!locais.equals(other.locais))
            return false;
        return true;
    }

}
