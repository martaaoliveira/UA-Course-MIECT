package lab09.ex1;

import java.util.*;

public class VectorGeneric<T> {
    private T[] vec;		
	private int nElem;	      
	private final static int ALLOC = 50;   
	private int dimVec = ALLOC;     

	@SuppressWarnings("unchecked")
	public VectorGeneric() {
		vec = (T[]) new Object[dimVec];
		nElem = 0;
	}
	
	public boolean addElem(T elem) {
		if (elem == null)
			return false;
		ensureSpace();
		vec[nElem++] = elem;
		return true;
	}

	private void ensureSpace() {
		if (nElem>=dimVec) {
			dimVec += ALLOC;
			@SuppressWarnings("unchecked")
			T[] newArray = (T[]) new Object[dimVec];
			System.arraycopy(vec, 0, newArray, 0, nElem );
			vec = newArray;
		}
	}

	public boolean removeElem(T elem) {
		for (int i = 0; i < nElem; i++) {
			if (vec[i].equals(elem)) {
				if (nElem-i-1 > 0) // not last element
					System.arraycopy(vec, i+1, vec, i, nElem-i-1 );
				vec[--nElem] = null; // libertar último objecto para o GC
				return true;
			}
		}
		return false;
	}

	public int totalElem() {
		return nElem;
	}
	
	public T getElem(int i) {
		return (T) vec[i];
	}

    public Iterator<T> iterator() {
        return new VectorIterator<T>();
    }

	@SuppressWarnings("unchecked")
    private class VectorIterator<K> implements Iterator<K> {
        private int indice;

        VectorIterator() {
            this.indice = 0;
        }

        public boolean hasNext() {
            return (indice < nElem);
        }

        public K next() {
            if (hasNext())
                return (K) VectorGeneric.this.vec[indice++];
            throw new NoSuchElementException("only " + nElem + " elements");
        }

    }

    public ListIterator<T> listIterator() {
        return new VectorListIterator<T>(0);
    }

    public ListIterator<T> listIterator(int indice) {
        return new VectorListIterator<T>(indice);
    }

	@SuppressWarnings("unchecked")
    private class VectorListIterator<K> implements ListIterator<K> {
        private int indice;

        VectorListIterator(int indice) {
            this.indice = indice;
        }

        public void add(K elem) {
            throw new UnsupportedOperationException("Operacao nao suportada!");
        }

        public boolean hasNext() {
            return (indice < nElem);
        }

        public boolean hasPrevious() {
            return (indice > 0);
        }

        public K next() {
            if (hasNext())
                return (K) VectorGeneric.this.vec[indice++];
            throw new NoSuchElementException("only " + nElem + " elements");
        }

        public int nextIndex() {
            int localIndex = indice;            
            return localIndex++;
        }

        public K previous() {
            if (hasPrevious()) {
                indice--;
                return (K) VectorGeneric.this.vec[indice];
            }
            throw new NoSuchElementException("only " + nElem + " elements");
        }

        public int previousIndex() {
            int localIndex = indice-1;            
            return localIndex;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operacao nao suportada!");  
        }

        public void set(K elem) {
            throw new UnsupportedOperationException("Operacao nao suportada!");  
        }
    }
}