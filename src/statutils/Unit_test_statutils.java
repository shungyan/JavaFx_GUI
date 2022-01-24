/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

import binmethod.SturgesFormula;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Unit_test_statutils {
    public static void main(String[] args) {
    // Create array of data
    List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10.,11.);
        // calculate the basic stats
       BasicStats stats = new BasicStats(exampleData);
       stats.calculateMean();
       System.out.printf("Mean: %.7f \n", stats.getMean());
       stats.calculateVariance();
       System.out.printf("Variance: %.7f \n", stats.getVariance());
       stats.calculateStandardDeviation();
       System.out.printf("Standard Deviation: %.7f \n", stats.getStandardDeviation());
       stats.calculateMax();
       System.out.printf("Max: %.7f \n", stats.getMax());
       stats.calculateMin();
       System.out.printf("Min: %.7f \n", stats.getMin());
       stats.calculateMedian();
       System.out.printf("Median: %.7f \n", stats.getMedian());
       

       
       //test for sturges formula 
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        int k=SturgesInstance.getNumberOfBins();
       

        int size=exampleData.size();
        double max=stats.getMax();
        double min=stats.getMin();
        
        
        DataperBin dataperbin=new DataperBin(exampleData,max,min,k);
         //calculate width
        dataperbin.calculateWidth();
        System.out.printf("Width: %.7f \n", dataperbin.getWidth()); 
        
        
        //calculate limit
        dataperbin.calculateLimit();
        double[] Limit=dataperbin.getLimit();
        for (int j=0;j<k+1;j++){
             System.out.printf("Limit %d:%,.7f\n",j+1,Limit[j]);
        } 
        
        //calculate frequency per bin
        dataperbin.calculateFrequencyPerBin();
        double[] Bin=dataperbin.getFrequencyPerBin();
        for (int j=0;j<k;j++){
             System.out.printf("Bin %d:%,.7f\n",j+1,Bin[j]);
        } 
        
     
        //calculate normalised frequency
        Double width=dataperbin.getWidth();
        NormalisedFrequency normalisedfrequency=new NormalisedFrequency(width,Bin,k);
        normalisedfrequency.calculateNormalisedFrequency();
        double[] Normalised_Frequency=normalisedfrequency.getNormalisedFrequency();
        for(int i=0;i<k;i++){
            System.out.printf("Normalised Frequency %d:%,.7f\n",i+1,Normalised_Frequency[i]);
        }
    }
}
