/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public final class BasicStats {//calculate all the basic stats including mean, variance, standard deviation, max, min and median
    
    private double mean;
    private double variance;
    private double standard_deviation;
    private double max;
    private double min;
    private double median;
    private int n;
    private double[] data=new double[10000000];
    
    public BasicStats(List<Double> list){
        n=list.size(); //find out the size of the list 
        data= list.stream().mapToDouble(Double::doubleValue).toArray();//xonvert the data from list to array
    }
    
    public void calculateMean(){//calculate the mean
        double sum=0;
        for (int i=0;i<n;i++){
            sum+=data[i];//sum up all of the data
         }
        mean=sum/n;//divide the sum by the size of data
    }
    
    public void calculateVariance(){//calculate the variance
        double sum2=0;
        
        for (int i=0;i<n;i++){//loop every data in the array
            double x=data[i]-mean;//subtract the data with mean
            sum2=sum2+Math.pow(x,2);//sum up the data power 2
         }
        
        
        variance=sum2/(n-1);//divide the sum by n-1
    }
    
    public void calculateStandardDeviation(){//calculate the standard deviation
        standard_deviation=Math.sqrt(variance);//square root variance
    }
    
    public void calculateMax(){
        Arrays.sort(data);//sort the array from small to big
        max=data[n-1];//get the last array value
    }
    
    public void calculateMin(){
        Arrays.sort(data);//sort the array from small to big
        min=data[0];//get the first array value
    }
    
    public void calculateMedian(){
        // check if total number of scores is even
        if (n % 2 == 0) {
           double sumOfMiddleElements = data[n / 2] +data[n / 2 - 1];
           // calculate average of middle elements
           median =  sumOfMiddleElements / 2;
         } 
        else {
           // get the middle element
           median = data[n / 2];
         }  
    }
    

    
    public double getMean(){
        return mean;
    }
    
    public double getVariance(){
        return variance;
    }
    
    public double getStandardDeviation(){
        return standard_deviation;
    }
    
    public double getMax(){
        return max;
    }
    
    public double getMin(){
        return min;
    }
    
    public double getMedian(){
        return median;
    }
}