
package classifier4J.vector;


import classifier4J.vector.TermVector;

public interface TermVectorStorage {
    public void addTermVector(String category, classifier4J.vector.TermVector termVector);
    public TermVector getTermVector(String category);
}
