
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class Linear_Regression {
	
    public double b = 0 ;  //b
    public double k = 0 ;  //k
    public double alpha = 0.1 ;  

    public int max_itea = 10 ; 
    
    public static ArrayList<String> readCsv(String filepath) {
        File csv = new File(filepath); 
        csv.setReadable(true);
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String line = "";
        String everyLine = "";
        
        ArrayList<String> allString = new ArrayList<String>(); 
        //ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>(); 
        
        try {
            while ((line = br.readLine()) != null) 
            {
            	
                everyLine = line;
                
                everyLine=everyLine.replace(",", " ");
                //System.out.println(everyLine);
    			allString.add(everyLine);
            }
          
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
        
    }


    
    
    ArrayList<Double> space=new ArrayList<Double>();   	
	ArrayList<Double> price=new ArrayList<Double>();
	
    public void gradientDescient(){
        double sum0 =0.0 ;
        double sum1 =0.0 ;
        for (int i=0;i<space.size();i++) {
        	sum1 += (space.get(i)*k+b-price.get(i))*space.get(i);
            sum0 += space.get(i)*k+b-price.get(i);
            
            //System.out.println((space.get(i)*k+b-price.get(i))*space.get(i));
        }
        /*
        for (int i=0;i<space.size();i++) {
        	sum1 += (space.get(i)*k+b-price.get(i))*space.get(i);
            sum0 += space.get(i)*k+b-price.get(i);
            
            //System.out.println((space.get(i)*k+b-price.get(i))*space.get(i));
        }*/
        //System.out.println("0 "+alpha*sum0);
        //System.out.println("1 "+alpha*sum1);
        //System.out.println();
        k = k - alpha*sum1/space.size() ; 
        b = b - alpha*sum0/space.size() ; 
    	
        
    }

    public void lineGre() {
        int itea = 0 ;
        while( itea< max_itea){
            //System.out.println(error_rate);
            //System.out.println("The current step is :"+itea);
            //System.out.println("b "+b);
            //System.out.println("k "+k);
            //System.out.println();
            gradientDescient();
            itea ++ ;
        }
    } ;

    public static void main(String[] args) throws IOException {
    	ArrayList<String> result = readCsv("C:\\Users\\Qifei Chen\\eclipse-workspace\\Project_2\\src\\kc_house_data.csv");
    	System.out.println(result.get(0));
    	String [] header = result.get(0).split("\\s+");
    	int priceTag=0,spaceTag=0;
    	
    	for(int i=0;i<header.length;i++) {
    		if(header[i].equals("price")) {
    			priceTag=i;
    		}
    		if(header[i].equals("sqft_living")) {
    			spaceTag=i;
    		}
    	}
    	
    	Linear_Regression linearRegression = new Linear_Regression() ;
    	for(int i=1;i<result.size();i++) {
    		String [] temp = result.get(i).split("\\s+");
    		linearRegression.space.add(Double.valueOf(temp[spaceTag]));
    		linearRegression.price.add(Double.valueOf(temp[priceTag]));
    		
    	}
        /*for(int i=1;i<25;i++) {
        	linearRegression.space.add(i);
    		linearRegression.price.add(3*i);
        }
    	/*double spaceMax=Collections.max(linearRegression.space);
    	double spaceMin=Collections.min(linearRegression.space);
    	double priceMax=Collections.max(linearRegression.price);
    	double priceMin=Collections.min(linearRegression.price);
    	
    	/*for(int i=0;i<linearRegression.space.size();i++) {
    		linearRegression.space.set(i, (linearRegression.space.get(i)-spaceMin)/spaceMax-spaceMin);
    		linearRegression.price.set(i, (linearRegression.price.get(i)-priceMin)/priceMax-priceMin);
    	}*/
    	
        linearRegression.lineGre();

        
        

        

    }

}

