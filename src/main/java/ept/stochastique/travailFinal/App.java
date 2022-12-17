package ept.stochastique.travailFinal;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String[] inputFiles = {
    			"/Users/user/Desktop/ProjetsClasse/Cours-DIC2-GIT/modelisation-stochastique-2/data with VANAD/calls-2014/calls-2014-12.csv",
    			/*"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-02.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-03.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-04.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-05.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-06.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-07.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-08.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-09.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-10.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-11.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/calls-2014-12.csv",*/
    	};
    	String[] outputFiles = {
    			"/Users/user/Desktop/ProjetsClasse/Cours-DIC2-GIT/modelisation-stochastique-2/data with VANAD/calls-2014/calls-2014-12-output.csv",
    			/*"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-02.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-03.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-04.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-05.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-06.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-07.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-08.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-09.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-10.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-11.csv",
    			"/home/quantumamar/Desktop/stochastique/TP/data/output/calls-2014-12.csv",	*/
    	};
    	/*for(int i = 0; i<inputFiles.length; i++) {
        	String inputFile = inputFiles[i];
        	TravailFinal tf = new TravailFinal();
    		tf.simulateOneDay(inputFile);
    		String outputFile = outputFiles[i]; 
    		tf.outputFile = outputFile;
    		tf.writeDataInFile("final2.csv",tf.custServed);
    	}*/
    }
}
