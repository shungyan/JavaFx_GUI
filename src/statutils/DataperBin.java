/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DataperBin {
    
    public double width;
    public double[] Limit = new double[10000000];
    public double[] Bin = new double[100000];
    public double[] Data=new double[10000000];
    private double max;
    private double min;
    private int k;
    private int size;
    private double[] center=new double[10000000];
    
    public DataperBin(List<Double> data,double Max,double Min,int K){
        
        Data= data.stream().mapToDouble(Double::doubleValue).toArray();//convert the data fromo list to array
        size=data.size();//find the size of data
        max=Max;
        min=Min;
        k=K;
    }
    
    public void calculateWidth(){
        //calculate width
        width=(max-min)/k;
    }
    
    public void calculateLimit(){
        //calculate limit
        Limit[0]=min;//assign the first limit as minimum
        for (int i=1;i<k+1;i++){//loop the limit 
            Limit[i]=Limit[i-1]+width+0.000000000001;//add the previous limit by width
        }  
    }
    
    public void calculateCenter(){
        for (int i=0;i<k;i++){//loop through the limit
            center[i]=(Limit[i]+Limit[i+1])/2;//calculate the center between limits
        }
    }
    
    public void calculateFrequencyPerBin(){
        //calculate the frequency per bin
        for (int i=0;i<size;i++){//loop through the data            
            for (int j=0;j<k;j++){//loop through the bin
                if(Data[i]>=Limit[j]&&Data[i]<Limit[j+1]){//check whether the array of data is within the range of this bin
                    Bin[j]+=1; // if the value of the data is within the range, frequency of bin is increased by 1
                }
            }         
        }
    }
    
    
    
    public double[] getFrequencyPerBin(){
        return Bin;
    }
    
    public double[] getLimit(){
        return Limit;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double[] getCenter(){
        return center;
    }
    
}
