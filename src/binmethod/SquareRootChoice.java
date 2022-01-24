/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SquareRootChoice extends BinFormulae {
    
    private int n;//size of data
    public int k;//number of bins
    
    public SquareRootChoice(List _list){
        n=_list.size(); // the lisr of data is passed in to determine the size
    }
    
    @Override
    public int getNumberOfBins(){//get the number of bins calculated by square root choice
        return k;
    }
    
    @Override
    public void calculateNumberOfBins(){ // calculate the number of bins using square root choice
        double K = Math.ceil(Math.sqrt(n));
        k=(int)K;
    }
}
