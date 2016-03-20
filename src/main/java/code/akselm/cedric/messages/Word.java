package code.akselm.cedric.messages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Axel on 3/12/2016.
 */
public class Word {

    private static final Pattern format = Pattern.compile("\\\\Q][(){},.;!?<>%\\\\E]");
    public String word;
    public String unformattedWord;
    public Set<Word> possessions = new HashSet<>(); //what the word is related to
    public int wordIndex;
    public boolean isPlural;
    public String originalSentence;
    public POS partOfSpeech;

    public Word(String word, int index, String originalSentence) {
        this.unformattedWord = word;
        this.wordIndex = index;
        this.originalSentence = originalSentence;
        this.word = format.matcher(word).replaceAll("").toLowerCase();
        this.partOfSpeech = POS.get(this.word);
    }

    public boolean isWord(String word){
        if (isPlural) return this.word.contains(word);
        else return (word.equalsIgnoreCase(this.word));
    }

    /* word before:
if ends in "s" and not "es", check to see if it's a singular noun that can be missing a ', and if it is, add to possessions
if ends in 's, add to possessions
if ends in s', add to possessions

if word after is "of", add the word after "of" to possessions
 */

    public void examine(ArrayList<Word> sentence){ //wordIndex must line up with word's index in sentence
        try{
            Word before = sentence.get(wordIndex - 1);
            if (before != null){
                if (before.word.contains("'") && before.word.endsWith("s") && (before.partOfSpeech == POS.NAMED_NOUN || before.partOfSpeech == POS.PRONOUN)){
                    possessions.add(before);
                }
            }
        }catch(Exception e){
            //
        }

        try{
            Word after = sentence.get(wordIndex + 1);
            if (after != null){
                if (after.isWord("of") && after.partOfSpeech == POS.PREPOSITION){
                    Word theWord = sentence.get()
                }
            }
        }catch(Exception e){
            //
        }


    }
}
