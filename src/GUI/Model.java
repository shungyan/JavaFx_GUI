/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import binmethod.RiceRule;
import binmethod.SquareRootChoice;
import binmethod.SturgesFormula;
import java.util.ArrayList;
import java.util.List;
import mathutils.DataFitting;
import statutils.BasicStats;
import statutils.DataperBin;
import statutils.NormalisedFrequency;

/**
 *
 * @author ADMIN
 */
public class Model {
    public List<Double> Data=new ArrayList<Double>();
    private double width;
    private double[] limit;
    private double[] frequencyperbin;
    private double normalisationfactor;
    private double mean;
    private double sigma;
    private double[] NewFrequency= new double[10000];
    private double[] center=new double[10000];
    
    public Model(List<Double> data){
        Data=data;
    }
    
    public String setMean(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats     
        stats.calculateMean();
        double mean=stats.getMean();
        String MEAN=Double.toString(mean);//convert the mean to string        
        return MEAN;       
    }
    
    public String setVariance(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats  
        stats.calculateMean();
        stats.calculateVariance();
        double variance=stats.getVariance();
        String VARIANCE=Double.toString(variance);//convert the variance to string 
        return VARIANCE;       
    }
    
    public String setStandardDeviation(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats  
        stats.calculateMean();
        stats.calculateVariance();
        stats.calculateStandardDeviation();
        double standard_deviation=stats.getStandardDeviation();//convert standard deviation to string
        String sd=Double.toString(standard_deviation);
        return sd;       
    }
    public String setMax(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats       
        stats.calculateMax();
        double max=stats.getMax();
        String MAX=Double.toString(max);//convert max to string
        return MAX;       
    }
    public String setMin(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats       
        stats.calculateMin();
        double min=stats.getMin();
        String MIN=Double.toString(min);//convert min to string 
        return MIN;       
    }
    public String setMedian(){
        BasicStats stats = new BasicStats(Data);//create a new instance of basic stats      
        stats.calculateMedian(); 
        double median=stats.getMedian();
        String MEDIAN=Double.toString(median);//convert median to string 
        return MEDIAN;       
    }
    
    public int setSturgesFormula(){
        SturgesFormula SturgesInstance = new SturgesFormula(Data);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        int k=SturgesInstance.getNumberOfBins();
        return k;
    }
    
    public int setRiceRuleFormula(){
        RiceRule RiceRuleInstance = new RiceRule(Data);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By Rice Rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());
        int k=RiceRuleInstance.getNumberOfBins();
        return k;
    }
    
    public int setSquareRootChoice(){
        SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(Data);
        SquareRootChoiceInstance.calculateNumberOfBins();
        System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInstance.getNumberOfBins());
        int k=SquareRootChoiceInstance.getNumberOfBins();
        return k;
    }
    
    public void calculateandgetBinInfo(double max,double min,int k){
        DataperBin dataperbin=new DataperBin(Data,max,min,k);//create a new instance of dataperbin 
        dataperbin.calculateWidth();//calculate width
        width=dataperbin.getWidth();
        dataperbin.calculateLimit();//calculate limit
        limit=dataperbin.getLimit();
        dataperbin.calculateCenter();//calculate center
        center=dataperbin.getCenter();
        dataperbin.calculateFrequencyPerBin();//calculate frequency per bin
        frequencyperbin=dataperbin.getFrequencyPerBin();
    }
    
    public double setWidth(){
        
        return width;
    }
    
    public double[] setLimit(){
        
        return limit;
    }
    
    public double[] setFrequencyPerBin(){
        
        return frequencyperbin;
    }
    
    public double[] setNormalisedFrequency(double[] Bin,int k){
        NormalisedFrequency normalisedfrequency=new NormalisedFrequency(width,Bin,k);//create an instance of normalised frequency
        normalisedfrequency.calculateNormalisedFrequency();//calculate normalised frequency
        double[] normalisedFrequency=normalisedfrequency.getNormalisedFrequency();
        return normalisedFrequency;
    }
    
    public String[] setRange(double[] Limit,int k){
        float[] LIMIT= new float[10000];
        String[] limit= new String[10000];
        String[] range= new String[10000];
        
        //convert the double value of limit to string
        for (int i=0;i<k+1;i++){
            LIMIT[i]=(float)Limit[i];
            limit[i]=String.valueOf(LIMIT[i]);
        }
        
        //put the limits into range array
        for (int i=0;i<k;i++){
            range[i]=limit[i]+"-"+limit[i+1];
        }
        
        return range;
    }
       
    
    public void calculateandgetFitData(int k,double[] Limit,double[] normalisedFrequency){
        DataFitting datafitting=new DataFitting(k,Limit,normalisedFrequency);//create an instance of datafitting
        datafitting.calculateCenter();//calculate center
        datafitting.calculateParameters();//calculate parameters
        datafitting.calculateNewFrequency();//calculate new frequency
        normalisationfactor=datafitting.getNormalisationFactor();
        mean=datafitting.getMean();
        sigma=datafitting.getSigma();
        NewFrequency=datafitting.getNewFrequency();
        center=datafitting.getCenter();
    }
    
    public String setNormalisationFactor(){
        String NORMALISATIONFACTOR=Double.toString(normalisationfactor);//convert normalisation factor to string 
        return NORMALISATIONFACTOR;
    }
    
    public String setMean2(){
        String MEAN=Double.toString(mean);//convert mean to string
        return MEAN;
    }
    
    public String setSigma(){
        String SIGMA=Double.toString(sigma);//convert sigma to string 
        return SIGMA;
    }
    
    public double[] setNewFrequency(){
        return NewFrequency;
    }
    
    public String[] setCenter(int k){
        String[] Center=new String[10000];
        
        //Convert the center of range from double to string
        for (int i=0;i<k;i++){
            Center[i]=String.valueOf(center[i]);
        }
        return Center;
    }
    
    public double getMax(){//return max in double 
        BasicStats stats = new BasicStats(Data);     
        stats.calculateMax();
        double max=stats.getMax();
        return max;
    }
    
    public double getMin(){//return min in double
        BasicStats stats = new BasicStats(Data);     
        stats.calculateMin();
        double min=stats.getMin();
        return min;
    }
        
    
}
