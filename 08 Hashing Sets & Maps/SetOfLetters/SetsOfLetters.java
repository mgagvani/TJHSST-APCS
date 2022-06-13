// Name: Manav Gagvani
// Date: 3-3-22

import java.util.*;
import java.io.*;

public class SetsOfLetters {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "declarationLast.txt";
        fillTheSets(fileName);
    }

    public static void fillTheSets(String fn) throws FileNotFoundException {
        Scanner infile = new Scanner(new File(fn));
        Set<Set<Character>> lowercase = new HashSet<Set<Character>>();
        Set<Set<Character>> uppercase = new HashSet<Set<Character>>();
        Set<Set<Character>> nonalpha = new HashSet<Set<Character>>();
        /** PART 1 and 2 and 3 */
        while (infile.hasNextLine()) {
            Set<Character> lc = new TreeSet<Character>();
            Set<Character> uc = new TreeSet<Character>();
            Set<Character> na = new TreeSet<Character>();


            String s = infile.nextLine();
            System.out.println(s);

            
            for (Character c : s.toCharArray()) {
                // if (c == ' ')
                //     continue;

                if (Character.isUpperCase(c)) {
                    uc.add(c);
                } else if (Character.isLowerCase(c)) {
                    lc.add(c);
                } else {
                    na.add(c);
                }
            }
            System.out.println("Lower Case: " + lc);
            System.out.println("Upper Case: " + uc);
            System.out.println("Other: " + na);

            lowercase.add(lc);
            uppercase.add(uc);
            nonalpha.add(na);

        }
        Set<Character> cU = new TreeSet<Character>();
        Set<Character> cL = new TreeSet<Character>();
        Set<Character> cNa = new TreeSet<Character>();

        char[] letters = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        Boolean good = true;
        for(char c: letters) {
            for(Set<Character> s: lowercase) {
                if(s.contains(c)) {
                    good = true;
                }
                else {
                    good = false;
                }
            }
            if(good) cL.add(c);
        }
        Boolean god = true;
        char[] upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        for(char c: upperCase) {
            for(Set<Character> s: uppercase) {
                if(s.contains(c)) {
                    god = true;
                }
                else {
                    god = false;
                }
            }
            if(god) cU.add(c);
        }
        Boolean gd = true;
        char[] chars = ",;!@#$%^&*()))_+~".toCharArray();
        for(char c: chars) {
            for(Set<Character> s: nonalpha) {
                if(s.contains(c)) {
                    gd = true;
                }
                else {
                    gd = false;
                }
            }
            if(gd) cNa.add(c);
        }

        System.out.println("Common Lower Case: " + cL);
        System.out.println("Common Upper Case: " + cU);
        System.out.println("Common Other: " + cNa);
    }
}

/***********************************
 * ----jGRASP exec: java SetsOfLetters_teacher
 * 
 * We, therefore, the Representatives of the united States of
 * Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 * Upper Case: [R, S, W]
 * Other: [ , ,]
 * 
 * America, in General Congress, Assembled, appealing to the
 * Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 * Upper Case: [A, C, G]
 * Other: [ , ,]
 * 
 * Supreme Judge of the world for the rectitude of our intentions,
 * Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 * Upper Case: [J, S]
 * Other: [ , ,]
 * 
 * do, in the Name, and by the Authority of the good People of
 * Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 * Upper Case: [A, N, P]
 * Other: [ , ,]
 * 
 * these Colonies, solemnly publish and declare, That these United
 * Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 * Upper Case: [C, T, U]
 * Other: [ , ,]
 * 
 * Colonies are, and of Right ought to be Free and Independent
 * Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 * Upper Case: [C, F, I, R]
 * Other: [ , ,]
 * 
 * States; that they are Absolved from all Allegiance to the
 * Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 * Upper Case: [A, S]
 * Other: [ , ;]
 * 
 * British Crown, and that all political connection between them
 * Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 * Upper Case: [B, C]
 * Other: [ , ,]
 * 
 * and the State of Great Britain, is and ought to be totally
 * Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 * Upper Case: [B, G, S]
 * Other: [ , ,]
 * 
 * dissolved; and that as Free and Independent States, they have
 * Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 * Upper Case: [F, I, S]
 * Other: [ , ,, ;]
 * 
 * full Power to levy War, conclude Peace, contract Alliances,
 * Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 * Upper Case: [A, P, W]
 * Other: [ , ,]
 * 
 * establish Commerce, and to do all other Acts and Things which
 * Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 * Upper Case: [A, C, T]
 * Other: [ , ,]
 * 
 * Independent States may of right do. And for the support of this
 * Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 * Upper Case: [A, I, S]
 * Other: [ , .]
 * 
 * Declaration, with a firm reliance on the protection of divine
 * Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 * Upper Case: [D]
 * Other: [ , ,]
 * 
 * Providence, we mutually pledge to each other our Lives, our
 * Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 * Upper Case: [L, P]
 * Other: [ , ,]
 * 
 * Fortunes and our sacred Honor.
 * Lower Case: [a, c, d, e, n, o, r, s, t, u]
 * Upper Case: [F, H]
 * Other: [ , .]
 * 
 * Common Lower Case: [d, e, n, o, r, t]
 * Common Upper Case: []
 * Common Other: [ ]
 * ----jGRASP: operation complete.
 ************************************/