package classifier4J;

import classifier4J.bayesian.WordsDataSourceException;


public abstract class AbstractCategorizedTrainableClassifier extends AbstractClassifier implements ITrainableClassifier {

    /**
     * @see classifier4J.IClassifier#classify(String)
     */
    public double classify(String input) throws WordsDataSourceException, ClassifierException {
        return classify(ICategorisedClassifier.DEFAULT_CATEGORY, input);
    }

    public void teachMatch(String input) throws WordsDataSourceException, ClassifierException {
        teachMatch(ICategorisedClassifier.DEFAULT_CATEGORY, input);
    }

    public void teachNonMatch(String input) throws WordsDataSourceException, ClassifierException {
        teachNonMatch(ICategorisedClassifier.DEFAULT_CATEGORY, input);
    }

}
