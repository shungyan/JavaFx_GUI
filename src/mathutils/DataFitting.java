/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;

import binmethod.SturgesFormula;
import static java.lang.Math.exp;
import static java.util.Collections.list;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.math3.fitting.*;
import statutils.BasicStats;
import statutils.NormalisedFrequency;


/**
 *
 * @author ADMIN
 */
public class DataFitting {
    
    public static double[] parameters;
    
    public double[] NewFrequency=new double[1000000];
    
    
    public static double[] center=new double[1000000];
    
    private static double[] NormalisedFrequency= new double[10000000];
    
    private static int k;
    
    private double[] limit= new double[10000000];
    
    
    
    public DataFitting(int K,double[] Limit,double[] normalisedfrequency){
        NormalisedFrequency=normalisedfrequency;
        k=K;
        limit=Limit;
    }
    
    public static void calculateParameters(){//calculate parameters normalisation factor, mean and sigma
        WeightedObservedPoints obs = new WeightedObservedPoints();
        
        
        for (int i=0;i<k;i++){
            obs.add(center[i],NormalisedFrequency[i]);//pass in the center and the normalised frequency of bin
        }
        
      
        parameters = GaussianCurveFitter.create().fit(obs.toList());
        
        
        // Print out result on screen 
        System.out.printf("Normalization factor = %f\n",parameters[0]);
        System.out.printf("Mean = %f\n",parameters[1]);
        System.out.printf("Sigma = %f\n",parameters[2]);
    }
    
    public void calculateCenter(){
        for (int i=0;i<k;i++){//loop through the limit
            center[i]=(limit[i]+limit[i+1])/2;//calculate the center between limits
        }
    }
    
    public void calculateNewFrequency(){//calculate new frequency for the fitted line
        double normalisation_factor=parameters[0];
        double mean=parameters[1];
        double sigma=parameters[2];
        
        //calculate the new frequency for each bin
        for(int i=0;i<k;i++){
           double x=(center[i]-mean)/sigma;
           double y=Math.pow(x,2);
           double z=(-0.5)*y;
           NewFrequency[i]=normalisation_factor*Math.pow(2.718,z);
       }
    }
    
    public double[] getNewFrequency(){
        return NewFrequency;
    }
    
    public double[] getCenter(){
        return center;
    }
    
    
    public double getNormalisationFactor(){
        return parameters[0];
    }
    
    public double getMean(){
        return parameters[1];
    }
    
    public double getSigma(){
        return parameters[2];
    }
}
