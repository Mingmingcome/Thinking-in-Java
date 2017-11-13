// 2017-11-3 11:06:29
import java.util.regex.*;

/*! Here's a block of text to use as input to
    the regular expression matcher. Note that we'll
    first extract the block of text by looking for
    the special delimiters, then process the
    extracted block. !*/

public class TheReplacements {
    public static void main(String[] args) throws Exception {
        String s = TextFile.read("TheReplacements.java");
        // Match the specially commented block of text above:
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL)
            .matcher(s); 
        if(mInput.find()) {
            s = mInput.group(1);
        }
        // Replace two or more spaces with a single space:
        s = s.replaceAll(" {2,}", " ");
        s = s.replaceAll("(?m)^ +", "");
        System.out.println(s);
        s = s.replaceFirst("[aeiou]", "(VOEL1)");
        StringBuffer sbuf = new StringBuffer();
        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(s);
        // Process the find information as you
        // perform th replacements:
        while(m.find())
            m.appendReplacement(sbuf, m.group().toUpperCase());
        // Put in the remainder of the text:
        m.appendTail(sbuf);
        System.out.println(sbuf);
    }
}