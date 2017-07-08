
package classifier4J.vector;

import classifier4J.vector.TermVector;

import java.util.HashMap;
import java.util.Map;


public class HashMapTermVectorStorage implements TermVectorStorage {
    private Map storage = new HashMap();
    
    
    /**
     * @see TermVectorStorage#addTermVector(String, classifier4J.vector.TermVector)
     */
    public void addTermVector(String category, classifier4J.vector.TermVector termVector) {
        storage.put(category, termVector);
    }

    /**
     * @see TermVectorStorage#getTermVector(String)
     */
    public classifier4J.vector.TermVector getTermVector(String category) {
        return (TermVector) storage.get(category);
    }

}
