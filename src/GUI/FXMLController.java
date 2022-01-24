/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import statutils.DataperBin;
import statutils.NormalisedFrequency;
import binmethod.*;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextArea;
import java.util.Scanner;
import java.util.ArrayList;
import statutils.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import mathutils.DataFitting;


/**
 *
 * @author ADMIN
 */
public class FXMLController implements Initializable {
    
    FileChooser fc=new FileChooser();
    
    protected List<String> data=new ArrayList<String>();
    public List<Double> Data=new ArrayList<Double>();
    public int n;
    public int k;
    public double Limit[];
    public double Bin[];
    public double[] normalisedFrequency;
    public String[] range=new String[1000000];
    public String[] Center=new String[10000];
    public double[] NewFrequency= new double[10000];
    
    
    
    
    //import from scene builder
    @FXML
    public Text Mean;
    public Text Variance;
    public Text SD;
    public Text Max;
    public Text Min;
    public Text Median;
    public ComboBox BinMethod;
    public Button Plot;
    public BarChart Histogram;
    public LineChart<?, ?> lineChart;
    public Button Fit;
    private Button Save;
    public Text Mean2;
    public Text NormalisationFactor;
    public Text Sigma;
    public StackPane Stackpane;
    public BarChart Histogram2;
    public LineChart<?, ?> lineChart2;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    
    
    
    @FXML
    void CHOOSEFILE(MouseEvent event){//button for choosing text file
        
        //clear the data to prevent repetition of previous data
        data.clear();
        Data.clear();

        
        
        File file=fc.showOpenDialog(new Stage());
        
        //scan through all the data and store it in a string array
        try{
        Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                String DATA=scanner.nextLine();
                data.add(DATA);
            }
        }catch(FileNotFoundException a){
            a.printStackTrace();
        }
        
        n=data.size();//find out the size of data
        
        
        //turn the data array from string to double for calculations
        for (String d : data) {
            Data.add(Double.valueOf(d));
        }
        
        
        //Calculate all the basic stats based on the data 
        
        Model model=new Model(Data);//create a new instancce of model
        Mean.setText(model.setMean());//display mean on GUI

        Variance.setText(model.setVariance());//display variance on GUI

        SD.setText(model.setStandardDeviation());//display standard deviation on GUI
        
        Max.setText(model.setMax());//display max on GUI
        
        Min.setText(model.setMin());//display min on GUI

        Median.setText(model.setMedian()); //display median on GUI
    }
    
    @FXML
    void PLOT(MouseEvent event){//button for plotting histogram of range vs normalised frequency
        
       lineChart2.getData().clear();
       Histogram2.getData().clear();
        String selected_text = BinMethod.getValue().toString();//get the value chosen in combobox

        if(selected_text=="Sturges Formula"){
        // use Sturges Formula to calculate the number of bins
        Model model=new Model(Data);
        k=model.setSturgesFormula();
        
        
        //Calculate the max and min
        double max=model.getMax();
        double min=model.getMin();
        
        //Calculate and get the width, frequency per bin and limit
        model.calculateandgetBinInfo(max,min,k);
        double width=model.setWidth();
        Limit=model.setLimit();
        Bin=model.setFrequencyPerBin();
                
        //Calculate and get normalised Frequency 
        normalisedFrequency=model.setNormalisedFrequency(Bin,k);
        
        //Set the range
        range=model.setRange(Limit,k);
        
        //set the center
        Center=model.setCenter(k);

        //plot the histogram
        Histogram.getData().clear(); //The blank data is removed from barchart
        Histogram.setBarGap(0);
        Histogram.setCategoryGap(0);
        
        Histogram2.getData().clear(); //The blank data is removed from barchart
        Histogram2.setBarGap(0);
        Histogram2.setCategoryGap(0);
        
        XYChart.Series series1=new XYChart.Series();
        XYChart.Series series2=new XYChart.Series();
        
        for (int i=0;i<k;i++){
            
            series1.getData().add(new XYChart.Data(range[i],normalisedFrequency[i]));
            series2.getData().add(new XYChart.Data(Center[i],normalisedFrequency[i]));
            
        }

        Histogram.getData().addAll(series1);
        Histogram2.getData().addAll(series2);
        
        }
        
//-----------------------------------------------------------------------------------------------------------------------------------------------        
        else if (selected_text=="Square Method"){
        // use Square Method to calculate the number of bins
         Model model=new Model(Data);
        k=model.setSquareRootChoice();
        
       //Calculate the max and min
        double max=model.getMax();
        double min=model.getMin();
        
        //Calculate and get the width, frequency per bin and limit
        model.calculateandgetBinInfo(max,min,k);
        double width=model.setWidth();
        Limit=model.setLimit();
        Bin=model.setFrequencyPerBin();
                
        //Calculate and get normalised Frequency 
        normalisedFrequency=model.setNormalisedFrequency(Bin,k);
        
        //Set the range
        range=model.setRange(Limit,k);
        
        //set the center
        Center=model.setCenter(k);

        //plot the histogram
        Histogram.getData().clear(); //The blank data is removed from barchart
        Histogram.setBarGap(0);
        Histogram.setCategoryGap(0);
        
        Histogram2.getData().clear(); //The blank data is removed from barchart
        Histogram2.setBarGap(0);
        Histogram2.setCategoryGap(0);
        
        XYChart.Series series1=new XYChart.Series();
        XYChart.Series series2=new XYChart.Series();
        
        for (int i=0;i<k;i++){
            
            series1.getData().add(new XYChart.Data(range[i],normalisedFrequency[i]));
            series2.getData().add(new XYChart.Data(Center[i],normalisedFrequency[i]));
        }

        Histogram.getData().addAll(series1);
        Histogram2.getData().addAll(series2);
        }
        
//-----------------------------------------------------------------------------------------------------------------------------------------------         
        else if(selected_text=="Rice Rule"){
        // use RiceRule class to calculate the number of bins
        Model model=new Model(Data);
        k=model.setRiceRuleFormula();
        
        //Calculate the max and min
        double max=model.getMax();
        double min=model.getMin();
        
        //Calculate and get the width, frequency per bin and limit
        model.calculateandgetBinInfo(max,min,k);
        double width=model.setWidth();
        Limit=model.setLimit();
        Bin=model.setFrequencyPerBin();
                
        //Calculate and get normalised Frequency 
        normalisedFrequency=model.setNormalisedFrequency(Bin,k);
        
        //Set the range
        range=model.setRange(Limit,k);
     
        //set the center
        Center=model.setCenter(k);
        
        //plot the histogram 
        Histogram.getData().clear(); //The blank data is removed from barchart
        Histogram.setBarGap(0);
        Histogram.setCategoryGap(0);
        
        Histogram2.getData().clear(); //The blank data is removed from barchart
        Histogram2.setBarGap(0);
        Histogram2.setCategoryGap(0);
        
        XYChart.Series series1=new XYChart.Series();
        XYChart.Series series2=new XYChart.Series();
        
        
        for (int i=0;i<k;i++){
            
            series1.getData().add(new XYChart.Data(range[i],normalisedFrequency[i]));
            series2.getData().add(new XYChart.Data(Center[i],normalisedFrequency[i]));
        }

        Histogram.getData().addAll(series1);
        Histogram2.getData().addAll(series2);
        
        }
        
       
        
    }
    
    
    @FXML
    void FIT(ActionEvent event) throws IOException{//Fit button to calculate normalisation factor, mean, sigma and plot the fitted pdf
            
        //Calculate and display the normalisation factor, mean and sigma
        Model model=new Model(Data);
        model.calculateandgetFitData(k,Limit,normalisedFrequency);
        
        NormalisationFactor.setText(model.setNormalisationFactor());
        
        Mean2.setText(model.setMean2());
        
        Sigma.setText(model.setSigma());
        
        
        //Calculate the new freqeuncy for fitted PDF
        NewFrequency=model.setNewFrequency();

        //set the center 
        Center=model.setCenter(k);
        
        
        //Clear the line chart to prevent repetition of data
        lineChart.getData().clear();
        lineChart2.getData().clear();
        
        //Plot the linechart
        XYChart.Series series3=new XYChart.Series();
        XYChart.Series series4=new XYChart.Series();
        
        for (int i=0;i<k;i++){  
            
            series3.getData().add(new XYChart.Data(Center[i],NewFrequency[i]));
            series4.getData().add(new XYChart.Data(Center[i],NewFrequency[i]));
        }


        lineChart.getData().addAll(series3);
        lineChart2.getData().addAll(series4);
        
    }
    
    
    
    
    @FXML
    void SAVE(MouseEvent event){//Save button to save the fitted pdf as png
        saveAsPng(Stackpane, "c:\\Temp\\chart.png");//the location of image saved can be changed here
        
    }
    
    public void saveAsPng(StackPane stackpane, String path) {
            WritableImage image = stackpane.snapshot(new SnapshotParameters(), null);
            File file = new File(path);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //To provide 3 methods for the combobox
        BinMethod.getItems().removeAll(BinMethod.getItems());
        BinMethod.getItems().addAll("Rice Rule", "Square Method", "Sturges Formula");
        
        
        //The barchart was plotted with blank data to remove an xlabel bug       
        XYChart.Series set1=new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data("",0));
        
        Histogram.getData().addAll(set1);  
        
        XYChart.Series set3=new XYChart.Series<>();
        
        set3.getData().add(new XYChart.Data("",0));
        
        Histogram2.getData().addAll(set3);  
        
        
        
        //The linechart was plotted with blank data to remove an xlabel bug
        XYChart.Series set2=new XYChart.Series<>();
        
        set2.getData().add(new XYChart.Data("",0));
        
        lineChart.getData().addAll(set2);  
        
        XYChart.Series set4=new XYChart.Series<>();
        
        set4.getData().add(new XYChart.Data("",0));
        
        lineChart2.getData().addAll(set4);
        
    
    }    
    
}