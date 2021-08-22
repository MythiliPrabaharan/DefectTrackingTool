
import java.lang.*;
import java.util.StringTokenizer;
import java.io.*;
class Stemmer
{  
  public static String[] stopWrds=
  {
    "a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards",
    "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also",
    "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", 
    "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", 
    "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", 
    "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand",
    "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
    "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain",
    "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", 
    "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", 
    "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", 
    "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else",
    "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone",
    "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", 
    "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further",
    "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", 
    "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes",
    "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", 
    "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id",
    "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", 
    "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", 
    "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", 
    "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", 
    "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", 
    "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", 
    "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless",
    "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", 
    "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", 
    "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", 
    "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", 
    "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", 
    "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", 
    "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", 
    "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several",
    "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", 
    "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", 
    "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", 
    "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then",
    "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these",
    "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those",
    "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", 
    "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", 
    "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", 
    "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", 
    "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", 
    "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", 
    "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", 
    "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", 
    "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"
  };
  private char[] b;
  private int i;  /* offset into b */
  private int i_end;  /* offset to end of stemmed word */
  private int j, k;
  private static final int INC = 50;  /* unit of size whereby b is increased */
  public Stemmer()
  {
    b = new char[INC];
    i = 0;
    i_end = 0;
  }
  public void add(char ch)
  {  
    if (i == b.length)
    {
      char[] new_b = new char[i+INC];
      for (int c = 0; c < i; c++) new_b[c] = b[c];
      b = new_b;
    }
    b[i++] = ch;
  }
  public void add(char[] w, int wLen)
  {
    if (i+wLen >= b.length)
    {  
      char[] new_b = new char[i+wLen+INC];
      for (int c = 0; c < i; c++) new_b[c] = b[c];
      b = new_b;
    }
    for (int c = 0; c < wLen; c++) b[i++] = w[c];
  }
  public String toString()
  {
    return new String(b,0,i_end);
  }
  public int getResultLength()
  { 
    return i_end;
  }
  public char[] getResultBuffer()
  {
    return b;
  }
  private final boolean cons(int i)
  {
    switch (b[i])
    { 
      case 'a': case 'e': case 'i': case 'o': case 'u': 
        return false;
      case 'y': return (i==0) ? true : !cons(i-1);
      default: return true;
    }
  }
  private final int m()
  {
    int n = 0;
    int i = 0;
    while(true)
    { 
      if (i > j) 
        return n;
      if (! cons(i))
        break; 
      i++;
    }
    i++;
    while(true)
    {  
      while(true)
        { 
          if (i > j) 
            return n;
          if (cons(i)) 
            break;
          i++;
        }
      i++;
      n++;
      while(true)
      {
        if (i > j)
          return n;
        if (! cons(i))
          break;
        i++;
      }
      i++;
    }
  }
  private final boolean vowelinstem()
  {
    int i;
    for (i = 0; i <= j; i++)
      if (! cons(i))
        return true;
    return false;
  }
  private final boolean doublec(int j)
  {
    if (j < 1) 
      return false;
    if (b[j] != b[j-1]) 
      return false;
    return cons(j);
  }
  private final boolean cvc(int i)
  {
    if (i < 2 || !cons(i) || cons(i-1) || !cons(i-2))
      return false;
    int ch = b[i];
    if (ch == 'w' || ch == 'x' || ch == 'y')
      return false;
    return true;
  }
  private final boolean ends(String s)
  { 
    int l = s.length();
    int o = k-l+1;
    if (o < 0)
      return false;
    for (int i = 0; i < l; i++)
      if (b[o+i] != s.charAt(i))
        return false;
    j = k-l;
    return true;
  }
  private final void setto(String s)
  {
    int l = s.length();
    int o = j+1;
    for (int i = 0; i < l; i++)
      b[o+i] = s.charAt(i);
    k = j+l;
  }
  private final void r(String s)
  {
    if (m() > 0)
      setto(s);
  }
  private final void step1()
  {
    if (b[k] == 's')
    {
      if (ends("sses"))
        k -= 2; 
      else if (ends("ies"))
        setto("i");
      else if (b[k-1] != 's')
        k--;
    }
    if (ends("eed"))
    {
      if (m() > 0)
        k--;
    }
    else if ((ends("ed") || ends("ing")) && vowelinstem())
    {
      k = j;
      if (ends("at")) 
        setto("ate");
      else if (ends("bl"))
       setto("ble"); 
      else if (ends("iz"))
        setto("ize");
      else if (doublec(k))
      {
        k--;
        int ch = b[k];
        if (ch == 'l' || ch == 's' || ch == 'z') 
          k++;
      }
      else if (m() == 1 && cvc(k)) setto("e");
    }
  }
  private final void step2()
  {
    if (ends("y") && vowelinstem())
      b[k] = 'i';
  }
  private final void step3() { if (k == 0) return; /* For Bug 1 */ switch (b[k-1])
  {
    case 'a':
      if (ends("ational"))
      {
        r("ate");
        break;
      }
      if (ends("tional"))
      {
        r("tion");
        break;
      }
    break;
    case 'c': 
      if (ends("enci"))
      {
        r("ence");
        break;
      }
      if (ends("anci"))
      {
        r("ance");
        break;
      }
    break;
    case 'e':
      if (ends("izer"))
      {
        r("ize");
        break;
      }
    break;
    case 'l':
      if (ends("bli"))
      {
        r("ble");
        break;
      }
      if (ends("alli"))
      {
        r("al");
        break;
      }
      if (ends("entli"))
      {
        r("ent");
        break;
      }
      if (ends("eli"))
      {
        r("e");
        break;
      }
      if (ends("ousli"))
      {
        r("ous");
        break;
      }
    break;
    case 'o': 
      if (ends("ization"))
      {
        r("ize");
        break;
      }
      if (ends("ation"))
      {
        r("ate");
        break;
      }
      if (ends("ator"))
      {
        r("ate");
        break;
      }
    break;
    case 's': 
      if (ends("alism"))
      {
        r("al");
        break;
      }
      if (ends("iveness"))
      {
        r("ive");
        break;
      }
      if (ends("fulness"))
      {
        r("ful");
        break;
      }
      if (ends("ousness"))
      {
        r("ous");
        break;
      }
    break;
    case 't': 
      if (ends("aliti"))
      {
        r("al");
        break;
      }
      if (ends("iviti"))
      {
        r("ive");
        break;
      }
      if (ends("biliti"))
      {
        r("ble");
        break; 
      }
    break;
    case 'g':
      if (ends("logi"))
      {
        r("log");
        break;
      }
    }
  }
  private final void step4()
  {
    switch (b[k])
    {
      case 'e':
        if (ends("icate"))
        {
          r("ic");
          break;
        }
        if (ends("ative"))
        {
          r("");
          break; 
        }
        if (ends("alize"))
        {
          r("al");
          break;
        }
      break;
      case 'i':
        if (ends("iciti"))
        {
          r("ic");
          break;
        }
      break;
      case 'l':
        if (ends("ical"))
        {
          r("ic");
          break;
        }
        if (ends("ful"))
        {
          r("");
          break;
        }
      break;
      case 's':
        if (ends("ness"))
        {
          r("");
          break;
        }
      break;
    }
  }
  private final void step5()
  {
    if (k == 0)
      return;
    /* for Bug 1 */ 
    switch (b[k-1])
    {
      case 'a':
        if (ends("al"))
          break;
      return;
      case 'c':
        if (ends("ance"))
          break;
        if (ends("ence"))
          break;
      return;
      case 'e':
        if (ends("er"))
          break;
      return;
      case 'i':
        if (ends("ic"))
          break;
      return;
      case 'l':
        if (ends("able")) 
          break;
        if (ends("ible"))
          break; 
      return;
      case 'n':
        if (ends("ant"))
          break;
        if (ends("ement")) 
          break;
        if (ends("ment")) 
          break;
        /* element etc. not stripped before the m */
        if (ends("ent"))
          break;
      return;
        case 'o':
        if (ends("ion") && j >= 0 && (b[j] == 's' || b[j] == 't'))
          break;
        /* j >= 0 fixes Bug 2 */
        if (ends("ou"))
          break;
      return;
      /* takes care of -ous */
      case 's':
        if (ends("ism"))
          break; 
      return;
      case 't':
        if (ends("ate"))
          break;
        if (ends("iti"))
          break;
      return;
      case 'u':
        if (ends("ous")) 
          break; 
      return;
      case 'v': 
        if (ends("ive")) 
          break; 
      return;
      case 'z': 
        if (ends("ize")) 
          break; 
      return;
      default: return;
    }
    if (m() > 1) 
      k = j;
  }
  private final void step6()
  { 
    j = k;
    if (b[k] == 'e')
    {
      int a = m();
      if (a > 1 || a == 1 && !cvc(k-1))
        k--;
    }
    if (b[k] == 'l' && doublec(k) && m() > 1)
      k--;
  }
  public void stem()
  { 
    k = i - 1;
    if (k > 1)
    {
      step1();
      step2();
      step3();
      step4();
      step5();
      step6(); 
    }
    i_end = k+1;
    i = 0;
  }
  public static void main(String args[]) 
  {
    char[] w = new char[501];
    Stemmer s = new Stemmer();
    for (int i = 0; i < args.length; i++) //Should Change here
    try
    {
      FileInputStream in = new FileInputStream(args[i]);  //Should Change here
      try
      {
        while(true)
        { 
          int ch = in.read();
          //System.out.println((char)ch + "~~~~~~~~");
          if (Character.isLetter((char) ch)) // Returns True or False
          {
            int j = 0;
            while(true)
            {
              ch = Character.toLowerCase((char) ch);
              w[j] = (char) ch;
              if (j < 500)
                j++;
              ch = in.read();
              //System.out.println((char)ch + "++++++++++++++");
              if (!Character.isLetter((char) ch))
              {
                for (int c = 0; c < j; c++) 
                  s.add(w[c]);
                s.stem();
                String u;
                u = s.toString();
                String msg =  u;//"This program gives sample code for String Tokenizer";
                StringTokenizer st = new StringTokenizer(msg," ");
                while(st.hasMoreTokens())
                {
                  //System.out.println(st.nextToken());
                  int flag=1;
                  String s1=st.nextToken();
                  s1=s1.toLowerCase();
                  for(int ii=0;ii<stopWrds.length;ii++)
                  {
                    if(s1.equals(stopWrds[ii]))
                    {
                      flag=0;
                    }
                  }
                  if(flag!=0)
                  {
                    System.out.print(s1);
                    System.out.print("\n");
                  }
                }
                break;
              }
            }
          }
          if (ch < 0)
            break;
          System.out.print((char)ch);
        }
      }
      catch (IOException e)
      {
        System.out.println("error reading " + e);
        break;
      }
    }
    catch (Exception e)
    {   
      System.out.println("Error Found" + e);
      break;
    }
  }
}