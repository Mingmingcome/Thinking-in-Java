//2017-10-31 20:17:39
import java.util.regex.*;

public class Groups {
	static public final String POEM = 
	  "Twas brillig, and the slithy toves\n" + 
	  "Did gyre and gimble in the wabe.\n" +
	  "All mimsy were the borogoves,\n" + 
	  "And the mome raths outgrabe.\n\n" +
	  "Beware the Jabberwock, my son,\n" +
	  "The jaws that bite, the claws that catch.\n" +
	  "Beware the Jubjub bird, and shun\n" +
    "The frumious Bandersnatch.";

    public static void main(String[] args) {
    	Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
    	  .matcher(POEM);
    	while(m.find()) {
    		for(int i = 0; i < m.groupCount(); i++) {
    			System.out.print("[" + m.group(i) + "]" + " ");
    		}
    		System.out.println();
    	}
    }
}
