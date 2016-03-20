package code.akselm.cedric.nets;

import code.akselm.cedric.Cedric;
import code.akselm.cedric.data.SavableNet;
import code.akselm.cedric.nets.tools.FileLabelAwareIterator;
import code.akselm.cedric.nets.tools.LabelSeeker;
import code.akselm.cedric.nets.tools.MeansBuilder;
import org.canova.api.util.ClassPathResource;
import org.deeplearning4j.berkeley.Pair;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.text.documentiterator.LabelAwareIterator;
import org.deeplearning4j.text.documentiterator.LabelledDocument;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Axel on 3/20/2016.
 */
public class QuestionClassificationModel extends SavableNet{
    private static final Logger log = LoggerFactory.getLogger(QuestionClassificationModel.class);
    ParagraphVectors paragraphVectors;

    public QuestionClassificationModel(){
        if (Cedric.getDataManager().exists("nets", "question_classification")){
            load(Cedric.getDataManager().get("nets", "question_classification"));
        }
        else {
            try {
                ClassPathResource resource = new ClassPathResource("training");


                // build a iterator for our dataset
                LabelAwareIterator iterator = new FileLabelAwareIterator.Builder()
                        .addSourceFolder(resource.getFile())
                        .build();

                TokenizerFactory t = new DefaultTokenizerFactory();
                t.setTokenPreProcessor(new CommonPreprocessor());


                // ParagraphVectors training configuration
                paragraphVectors = new ParagraphVectors.Builder()
                        .learningRate(0.025)
                        .minLearningRate(0.001)
                        .batchSize(1000)
                        .epochs(20)
                        .iterate(iterator)
                        .trainWordVectors(true)
                        .tokenizerFactory(t)
                        .build();

                // Start model training
                paragraphVectors.fit();

        /*
         At this point we assume that we have model built and we can check, which categories our unlabeled document falls into
         So we'll start loading our unlabeled documents and checking them
        */

         /*       ClassPathResource unlabeledResource = new ClassPathResource("paravec/unlabeled");

                FileLabelAwareIterator unlabeledIterator = new FileLabelAwareIterator.Builder()
                        .addSourceFolder(unlabeledResource.getFile())
                        .build();


     Now we'll iterate over unlabeled data, and check which label it could be assigned to

     Please note: for many domains it's normal to have 1 document fall into few labels at once, with different "weight" for each.

                MeansBuilder meansBuilder = new MeansBuilder((InMemoryLookupTable<VocabWord>) paragraphVectors.getLookupTable(), t);
                LabelSeeker seeker = new LabelSeeker(iterator.getLabelsSource().getLabels(), (InMemoryLookupTable<VocabWord>) paragraphVectors.getLookupTable());

                while (unlabeledIterator.hasNextDocument()) {
                    LabelledDocument document = unlabeledIterator.nextDocument();

                    INDArray documentAsCentroid = meansBuilder.documentAsVector(document);
                    List<Pair<String, Double>> scores = seeker.getScores(documentAsCentroid);


             please note, document.getLabel() is used just to show which document we're looking at now, as a substitute for printing out the whole document itself.
             So, labels on these two documents are used like titles, just to visualize our classification done properly

                    log.info("Document '" + document.getLabel() + "' falls into the following categories: ");
                    for (Pair<String, Double> score : scores) {
                        log.info("        " + score.getFirst() + ": " + score.getSecond());
                    }


                }
*/
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void load(HashMap<String,Serializable> map){
        try{
            byte[] bytes = (byte[])map.get("net");
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(input);
            paragraphVectors = (ParagraphVectors)is.readObject();
            is.close();
            input.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getBytes() {
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(paragraphVectors);
            oo.close();
            return out.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }

        return new byte[0];
    }
}


