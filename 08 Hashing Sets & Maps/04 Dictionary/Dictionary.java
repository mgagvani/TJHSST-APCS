// Name: Manav Gagvani
// Date: 3/8/22

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {	
		Map<String, Set<String>> dictionary = new TreeMap<String, Set<String>>();
		while(infile.hasNextLine()) {
			String key = infile.nextLine();
			String value = infile.nextLine();
			add(dictionary, key, value);
		}
		return dictionary;
   }
   
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
		if(dictionary.containsKey(word)) {
			dictionary.get(word).add(translation);
		}
		else {
			dictionary.put(word, new TreeSet<String>());
			dictionary.get(word).add(translation);
		}
   }
   
   
   public static void display(Map<String, Set<String>> m) {
	   for(String s: m.keySet()) {
		   System.out.println(s + " " + m.get(s).toString());
	   }
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {	
	   Map<String, Set<String>> map = new TreeMap<String, Set<String>>();

	   /** OLD APPROACH
	   for(Set<String> value: dictionary.values()) {
			for(String s: value) {
				// find corresponding key
				String trans = "__TEMP__";
				for(String key: dictionary.keySet()) {
					if(dictionary.get(key).contains(s)) trans = key;
				}
				if(trans.equals("__TEMP__")) trans = s;
				add(newDict, s, trans);
			}
	   }
	   */
	   for(String key: dictionary.keySet()) {
		   for(String value: dictionary.get(key)) {
			   if(!map.containsKey(value)) map.put(value, new TreeSet<String>());
			   map.get(value).add(key);
		   }
	   }


	   return map;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/