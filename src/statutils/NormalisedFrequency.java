/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

/**
 *
 * @author ADMIN
 */
public class NormalisedFrequency {
    
    public double total;
    public double[] NormalisedFrequency=new double[1000000];
    private double width;
    private double[] Bin;
    private int k;
    
    
    public NormalisedFrequency(double Width,double[] bin,int K){
        width=Width;
        Bin=bin;
        k=K;
    }
    
    public void calculateNormalisedFrequency(){//calculate the normalised frequency
        
        //add up the area of the plot 
        for(int i=0;i<k;i++){
            total=total+(Bin[i]*width);
        }
        
        //calculate the normalised frequency
        for(int i=0;i<k;i++){
            NormalisedFrequency[i]=Bin[i]/total;
        }
               
    }
    
    public double[] getNormalisedFrequency(){
        return NormalisedFrequency;
    }
}
