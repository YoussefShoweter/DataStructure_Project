package sample;
import com.sun.javafx.geom.AreaOp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import java.io.PrintWriter;
import java.util.*;

import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;



public class Controller {
     int step;
    public  TableView<Table> TableView;
    public TableColumn<Table, Integer> Unsorted;
    public TableColumn<Table, Integer> Sorted;
    public TableColumn<Table, Integer> Index;


    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private TextField arrsize;
    @FXML
    TextField maxValue;
    @FXML
    private TextField stepSize;
    @FXML
    private CheckBox insertionCheck;
    @FXML
    private CheckBox MergeCheck;
    @FXML
    private CheckBox RadixCheck;
    @FXML
    private CheckBox QuickCheck;
    @FXML
    private CheckBox SelectionCheck;
    @FXML
    private CheckBox CountingCheck;
    @FXML
    private CheckBox HeapCheck;
    @FXML
    private CheckBox BubbleCheck;


    //Insert file from users Pc
    public void Insert(ActionEvent a){
        table.clear();
        lineChart.getData().clear();

boolean k=true;
boolean p=true;
//try and catch for no input of step size
try{
   if(stepSize.getText()==""){
       throw new Exception();
   }
}
catch ( Exception jdek){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("No step size inputed");
    alert.setContentText("Please Input the step size ");
    alert.show();
    p=false;
}
//try and catch for no sort chosen
        try{
        if(!CountingCheck.isSelected()&&!insertionCheck.isSelected() && !SelectionCheck.isSelected()&&!BubbleCheck.isSelected()&&!MergeCheck.isSelected() && !QuickCheck.isSelected() && !HeapCheck.isSelected()&&!RadixCheck.isSelected()){
            throw new Exception();
        }

        }
        catch (Exception klk){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No sort Chosen");
            alert.setContentText("Please select the wanted Sort ");
            alert.show();
k=false;

        }
        // condition to continue rest of code
if (k&&p) {
    //insertion of a file from users Pc
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (.txt)", "*.txt"));
    File f = fc.showOpenDialog(null);
    ArrayList<Integer> rand = new ArrayList<>();
    ArrayList<Integer> sorted = new ArrayList<>();
    //getting data from the file
    if (f != null) {
        try {
            Scanner FileReader = new Scanner(f);
            while (FileReader.hasNextInt()) {
                //rand.add(Integer.parseInt(FileReader.nextLine()));
                rand.add(FileReader.nextInt());
            }
            FileReader.close();


        } catch (Exception e) {
            System.out.println("Error occured");
        }
    }
    //set the values in the text boxes to the values of the file
    arrsize.setText(String.valueOf(rand.size()));
    maxValue.setText(String.valueOf(Collections.max(rand)));
    System.out.println(getMax(rand,15));


    for (int i = 0; i < Integer.parseInt(arrsize.getText()); i++) {
        sorted.add(i, rand.get(i));
        selectionSort(sorted);


    }
    for (int i = 0; i < Integer.parseInt(arrsize.getText()); i++) {
        table.add(new Table(sorted.get(i), rand.get(i), i));


    }
    //Writing the input file after sorting into the New file called Sorted
    try {

        File Rekt = new File("sorted.txt");
        PrintWriter PrintCode = new PrintWriter(Rekt);

        for (int i = 0; i < Integer.parseInt(arrsize.getText()); i++) {
            PrintCode.println(sorted.get(i));

        }
        PrintCode.close();
    }
    catch (IOException c) {
        System.out.println("An error occurred.");
        c.printStackTrace();
    }

    try {
        if (stepSize.getText().equals("")) {
            throw new RuntimeException();

        }
        if (Integer.parseInt(stepSize.getText()) < 0) {
            throw new ArithmeticException();
        }
        if (Integer.parseInt(stepSize.getText()) > rand.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }
    catch (ArithmeticException al) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Negative input error");
        alert.setContentText("Please dont enter a negative value in the step SIze field box");
        alert.show();


    }
    catch (ArrayIndexOutOfBoundsException al) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Step Size Error");
        alert.setContentText("Step Size bigger than Array Size");
        alert.show();
    }
    catch (RuntimeException al) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("No input error");
        alert.setContentText("Please enter a value in the Step size field box");
        alert.show();
    }

    if (insertionCheck.isSelected()) {

        try {
            File Insertion = new File("Insertionsteps.txt");
            //FileWriter WriteCode = new FileWriter(Youssef,true);
            PrintWriter PrintCode = new PrintWriter(Insertion);


            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Insertion Sort");

            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                Insertionsort(StepHelper(rand, i));
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                PrintCode.println(i + "," + step);
            }
            //in case of step size not divisible by array size
            step = 0;
            Insertionsort(StepHelper(rand,rand.size()));
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            PrintCode.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            PrintCode.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }


    }
    if (MergeCheck.isSelected()) {
        try {
            File Merge = new File("MergeSteps.txt");
            //FileWriter WriteCode = new FileWriter(Youssef,true);
            PrintWriter PrintCode = new PrintWriter(Merge);

            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Merge Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                sort(StepHelper(rand, i), 0, i - 1);
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                PrintCode.println(i + "," + step);

            }
            //in case of step size not divisible by array size
            step = 0;
            sort(StepHelper(rand,rand.size()), 0, rand.size() - 1);
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            PrintCode.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            PrintCode.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }
    }
    if (SelectionCheck.isSelected()) {
        try {

            File selection = new File("Selection.txt");
            PrintWriter Selection = new PrintWriter(selection);
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Selection Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                selectionSort(StepHelper(rand, i));
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                Selection.println(i + "," + step);

            }
            //in case of step size not divisible by array size
            step = 0;
            selectionSort(StepHelper(rand,rand.size()));
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            Selection.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            Selection.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }

    }
    if (RadixCheck.isSelected()) {
        try {
            File Radixfile = new File("RadixStep.txt");
            PrintWriter Radixf = new PrintWriter(Radixfile);
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Radix Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                radixsort(StepHelper(rand, i), i);
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                Radixf.println(i + "," + step);
            }
            //in case of step size not divisible by array size
            step = 0;
            radixsort(rand, rand.size());
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            Radixf.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            Radixf.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }

    }
    if (CountingCheck.isSelected()) {
        try {
            File Countingfile = new File("CountingStep.txt");
            PrintWriter Countingf = new PrintWriter(Countingfile);

            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Counting Check");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                countSort(StepHelper(rand, i), i);
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                Countingf.println(i + "," + step);

            }
            //in case of step size not divisible by array size
            step = 0;
            countSort(rand, rand.size());
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            Countingf.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            Countingf.close();

        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }
    }
    if (QuickCheck.isSelected()) {
        try {
            File Quickfile = new File("QuickStep.txt");
            PrintWriter quickf = new PrintWriter(Quickfile);

            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Quick Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                quickSort(StepHelper(rand, i), 0, i - 1);
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                quickf.println(i + "," + step);
            }
            //in case of step size not divisible by array size
            step = 0;
            quickSort(StepHelper(rand,rand.size()), 0, rand.size() - 1);
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            quickf.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            quickf.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }
    }
    if (HeapCheck.isSelected()) {
        try {

            File HeapFile = new File("Heap.txt");
            PrintWriter Hfile = new PrintWriter(HeapFile);

            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("HeapSort Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                hsort(StepHelper(rand, i));
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                Hfile.println(i + "," + step);
            }
            //in case of step size not divisible by array size
            step = 0;
            hsort(StepHelper(rand,rand.size()));
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            Hfile.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            Hfile.close();
        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }

    }
    if (BubbleCheck.isSelected()) {
        try {
            File Bubblefile = new File("BubbleStep");
            PrintWriter bubbly = new PrintWriter(Bubblefile);

            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Bubble Sort");
            for (int i = Integer.parseInt(stepSize.getText()); i <= Integer.parseInt(arrsize.getText()); i = i + Integer.parseInt(stepSize.getText())) {
                step = 0;
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                bubbleSort(StepHelper(rand, i));
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                bubbly.println(i + "," + step);

            }
            //in case of step size not divisible by array size
            step = 0;
            bubbleSort(StepHelper(rand,rand.size()));
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
            bubbly.println(rand.size() + "," + step);
            lineChart.getData().add(Series);
            bubbly.close();

        } catch (IOException c) {
            System.out.println("An error occurred.");
            c.printStackTrace();
        }


    }


    ObservableList<Table> observableList = FXCollections.observableArrayList(table);
    Unsorted.setCellValueFactory(new PropertyValueFactory<>("Unsorted"));
    Sorted.setCellValueFactory(new PropertyValueFactory<>("Sorted"));
    Index.setCellValueFactory(new PropertyValueFactory<>("Index"));
    TableView.setItems(observableList);
}
}


    //clear Buttons
    public void clear(ActionEvent a) {

        stepSize.setText("");
        maxValue.setText("");
        arrsize.setText("");
    }
    public void clearGraph(ActionEvent a) {
        lineChart.getData().clear();

    }


    // Creating Array List Of tables for the Table in GUI
    public ArrayList<Table> table = new ArrayList<Table>();


    //Generate Button ------> Main Button
    public void generate(ActionEvent e) {
        //Clearing the chart and table Before generating not to Draw the same line again
        lineChart.getData().clear();
        table.clear();
        boolean k2 = true;
        try {
            if (!CountingCheck.isSelected() && !insertionCheck.isSelected() && !SelectionCheck.isSelected() && !BubbleCheck.isSelected() && !MergeCheck.isSelected() && !QuickCheck.isSelected() && !HeapCheck.isSelected() && !RadixCheck.isSelected()) {
                throw new Exception();
            }

        } catch (Exception ll) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No sort Chosen");
            alert.setContentText("Please select the wanted Sort ");
            alert.show();
            k2 = false;

        }
        if (k2) {


// Creating the Dynamic Array and instance of Random Class
            ArrayList<Integer> rand = new ArrayList<>();

        ArrayList<Integer> sorted = new ArrayList<>();
        Random rekt = new Random();


//try and catch for Input Error
        boolean must = true;
        try {
            if (arrsize.getText().equals("") || maxValue.getText().equals("") || stepSize.getText().equals("")) {
                throw new RuntimeException();

            }
            if (Integer.parseInt(arrsize.getText()) < 0 || Integer.parseInt(maxValue.getText()) < 0 || Integer.parseInt(stepSize.getText()) < 0) {
                throw new ArithmeticException();
            }
            if (Integer.parseInt(stepSize.getText()) > Integer.parseInt(arrsize.getText())) {
                throw new ArrayIndexOutOfBoundsException();
            }

        }
        catch (ArithmeticException a) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Negative input error");
            alert.setContentText("Please dont enter a negative value in the field Boxes");
            alert.show();


        }
        catch (ArrayIndexOutOfBoundsException a) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Step Size Error");
            alert.setContentText("Step Size bigger than Array Size");
            System.out.println("working");
            alert.show();
            must = false;
        }
        catch (RuntimeException a) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No input error");
            alert.setContentText("Please enter a value in the field Boxes");
            alert.show();

        }
        if (must) {
            //Create File for Unsorted Array
            try {
                File Youssef = new File("Unsorted.txt");
                //FileWriter WriteCode = new FileWriter(Youssef,true);
                PrintWriter PrintCode = new PrintWriter(Youssef);

                for (int i = 0; i < Integer.parseInt(arrsize.getText()); i++) {
                    rand.add((rekt.nextInt(Integer.parseInt(maxValue.getText()))));
                    sorted.add(i, rand.get(i));
                    PrintCode.println(rand.get(i));

                }
                PrintCode.close();

            } catch (IOException c) {
                System.out.println("An error occurred.");
                c.printStackTrace();
            }


//Putting the Sorted elemnts into a new Array
            try {

                File Rekt = new File("sorted.txt");
                PrintWriter PrintCode = new PrintWriter(Rekt);

                Insertionsort(sorted);
                for (int i = 0; i < Integer.parseInt(arrsize.getText()); i++) {
                    table.add(new Table(sorted.get(i), rand.get(i), i));
                    PrintCode.println(sorted.get(i));

                }
                PrintCode.close();
            } catch (IOException c) {
                System.out.println("An error occurred.");
                c.printStackTrace();
            }


            //Sorts User generated Graphs
            if (insertionCheck.isSelected()) {

                try {
                    File Insertion = new File("Insertionsteps.txt");
                    //FileWriter WriteCode = new FileWriter(Youssef,true);
                    PrintWriter PrintCode = new PrintWriter(Insertion);


                    XYChart.Series<String, Number> InsertionSeries = new XYChart.Series<String, Number>();
                    InsertionSeries .setName("Insertion Sort");

                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        InsertionSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        Insertionsort(StepHelper(rand, i));
                        InsertionSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        PrintCode.println(i + "," + step);
                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    Insertionsort(StepHelper(rand,rand.size()));
                    InsertionSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    PrintCode.println(rand.size() + "," + step);
                    lineChart.getData().add(InsertionSeries);
                    PrintCode.close();

                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }


            }
            if (MergeCheck.isSelected()) {
                try {
                    File Merge = new File("MergeSteps.txt");
                    //FileWriter WriteCode = new FileWriter(Youssef,true);
                    PrintWriter PrintCode = new PrintWriter(Merge);

                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("Merge Sort");
                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        sort(StepHelper(rand, i), 0, i - 1);
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        PrintCode.println(i + "," + step);

                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    sort(rand, 0, rand.size() - 1);
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    PrintCode.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    PrintCode.close();
                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }
            }
            if (SelectionCheck.isSelected()) {
                try {

                    File selection = new File("Selection.txt");
                    PrintWriter Selection = new PrintWriter(selection);
                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("Selection Sort");
                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        selectionSort(StepHelper(rand, i));
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        Selection.println(i + "," + step);

                    }
                    //in case of step size not divisible by array size
                   step = 0;
                    selectionSort(StepHelper(rand,rand.size()));
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    Selection.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    Selection.close();
                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }

            }
            if (RadixCheck.isSelected()) {
                try {
                    File Radixfile = new File("RadixStep.txt");
                    PrintWriter Radixf = new PrintWriter(Radixfile);
                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("Radix Sort");
                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        radixsort(StepHelper(rand, i), i);
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        Radixf.println(i + "," + step);
                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    radixsort(StepHelper(rand,rand.size()), rand.size());
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    Radixf.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    Radixf.close();
                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }

            }
            if (CountingCheck.isSelected()) {
                try {
                    File Countingfile = new File("CountingStep.txt");
                    PrintWriter Countingf = new PrintWriter(Countingfile);

                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("Counting Check");
                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        countSort(StepHelper(rand, i), i);
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        Countingf.println(i + "," + step);

                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    countSort(StepHelper(rand,rand.size()), rand.size());
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    Countingf.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    Countingf.close();

                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }
            }
            if (QuickCheck.isSelected()) {
                try {
                    File Quickfile = new File("QuickStep.txt");
                    PrintWriter quickf = new PrintWriter(Quickfile);

                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("Quick Sort");
                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                        quickSort(StepHelper(rand, i), 0, i - 1);
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        quickf.println(i + "," + step);
                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    quickSort(StepHelper(rand,rand.size()), 0, rand.size() - 1);
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    quickf.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    quickf.close();
                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }
            }
            if (HeapCheck.isSelected()) {
                try {

                    File HeapFile = new File("Heap.txt");
                    PrintWriter Hfile = new PrintWriter(HeapFile);

                    XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
                    Series.setName("HeapSort Sort");
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));

                    for (int i = Integer.parseInt(stepSize.getText()); i <= rand.size(); i += (Integer.parseInt(stepSize.getText()))) {
                        step = 0;

                        hsort(StepHelper(rand, i));
                        Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        Hfile.println(i + "," + step);
                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    hsort(StepHelper(rand,rand.size()));
                    Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    Hfile.println(rand.size() + "," + step);
                    lineChart.getData().add(Series);
                    Hfile.close();
                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }

            }
            if (BubbleCheck.isSelected()) {
                try {
                    File Bubblefile = new File("BubbleStep");
                    PrintWriter bubbly = new PrintWriter(Bubblefile);

                    XYChart.Series<String, Number> BubbleSeries = new XYChart.Series<String, Number>();
                    BubbleSeries .setName("Bubble Sort");
                    BubbleSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
                    for (int i = Integer.parseInt(stepSize.getText()); i <= Integer.parseInt(arrsize.getText()); i = i + Integer.parseInt(stepSize.getText())) {
                        step = 0;

                        bubbleSort(StepHelper(rand, i));
                        BubbleSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(i), step));
                        bubbly.println(i + "," + step);

                    }
                    //in case of step size not divisible by array size
                    step = 0;
                    bubbleSort(StepHelper(rand,rand.size()));
                    BubbleSeries .getData().add(new XYChart.Data<String, Number>(String.valueOf(rand.size()), step));
                    bubbly.println(rand.size() + "," + step);
                    lineChart.getData().add(BubbleSeries );
                    bubbly.close();

                } catch (IOException c) {
                    System.out.println("An error occurred.");
                    c.printStackTrace();
                }


            }


            ObservableList<Table> observableList = FXCollections.observableArrayList(table);
            Unsorted.setCellValueFactory(new PropertyValueFactory<>("Unsorted"));
            Sorted.setCellValueFactory(new PropertyValueFactory<>("Sorted"));
            Index.setCellValueFactory(new PropertyValueFactory<>("Index"));
            TableView.setItems(observableList);
        }
        }
    }


   //ASypmtotic Graphs
   public void ASymptotic(ActionEvent a) {
        if (insertionCheck.isSelected() || SelectionCheck.isSelected()||BubbleCheck.isSelected()) {
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("N^2");
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
            for (int i = Integer.parseInt(stepSize.getText()); i < Integer.parseInt(arrsize.getText()); i += (Integer.parseInt(stepSize.getText()))) {
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), (i * i)));

            }
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(Integer.parseInt(arrsize.getText())), (Integer.parseInt(arrsize.getText()) * Integer.parseInt(arrsize.getText()))));
            lineChart.getData().add(Series);

        }
        if (MergeCheck.isSelected() || QuickCheck.isSelected() || HeapCheck.isSelected()) {
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("Nlog(N)");
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
            for (int i = Integer.parseInt(stepSize.getText()); i < Integer.parseInt(arrsize.getText()); i += (Integer.parseInt(stepSize.getText()))) {
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), (i * Math.log((int) i))));

            }
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(Integer.parseInt(arrsize.getText())), (Integer.parseInt(arrsize.getText()) * Math.log((int)Integer.parseInt(arrsize.getText())))));
            lineChart.getData().add(Series);

        }
        if (RadixCheck.isSelected()) {
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("D(N+K)");
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
            for (int i = Integer.parseInt(stepSize.getText()); i < Integer.parseInt(arrsize.getText()); i += (Integer.parseInt(stepSize.getText()))) {
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), (maxValue.getLength())*(i + Integer.parseInt(maxValue.getText()))));

            }
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(Integer.parseInt(arrsize.getText())), (maxValue.getLength())*(Integer.parseInt(arrsize.getText()) + Integer.parseInt(maxValue.getText()))));
            lineChart.getData().add(Series);

        }
        if (CountingCheck.isSelected()) {
            XYChart.Series<String, Number> Series = new XYChart.Series<String, Number>();
            Series.setName("N+K");
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(0), 0));
            for (int i = Integer.parseInt(stepSize.getText()); i < Integer.parseInt(arrsize.getText()); i += (Integer.parseInt(stepSize.getText()))) {
                Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), (i + Integer.parseInt(maxValue.getText()))));

            }
            Series.getData().add(new XYChart.Data<String, Number>(String.valueOf(Integer.parseInt(arrsize.getText())), (Integer.parseInt(arrsize.getText()) + maxValue.getLength())));
            lineChart.getData().add(Series);

        }


    }

    //Here are The Sorts Code

        void Insertionsort(ArrayList<Integer> arr) {


        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            int key = (int) arr.get(i);
            int j = i - 1;
            step += 3;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
                step++;
                step++;
            }
            arr.set(j + 1, key);

        }
    }

        void selectionSort(ArrayList<Integer> arr) {

        for (int i = 0; i < arr.size() - 1; i++) {
            int index = i;
            step++;
            for (int j = i + 1; j < arr.size(); j++) {
                step++;
                if (arr.get(j) < arr.get(index)) {
                    index = j;
                    step++;
                }
            }
            int smallerNumber = arr.get(index);
            arr.set(index, arr.get(i));
            arr.set(i, smallerNumber);
            step += 3;

        }
    }

        int getMax(ArrayList<Integer> arr, int n) {

        int max = 0;
        step += 2;
        arr.set(max, arr.get(0));
        for (int i = 1; i < n; i++) {


            if (arr.get(i) > max) {
                step++;
                arr.set(max, arr.get(i));
            }

        }

        return max;

    } //Helper function

        void hsort(ArrayList<Integer> arr) {//step in heapsort

        int n = arr.size();
        step++;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            step++;
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
            step += 4;
        }
    }

        void heapify(ArrayList<Integer> arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        step += 3;

        if (l < n && arr.get(l) > arr.get(largest)) {
            ;
            largest = l;
            step++;
        }

        if (r < n && arr.get(r) > arr.get(largest)) {
            largest = r;
            step++;
        }
        // If largest is not root
        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            heapify(arr, n, largest);
            step += 4;
        }
    }

        void RadixcountingSort(ArrayList<Integer> arr, int n, int place) { // Radixfunction to implement counting


        int[] output = new int[n + 1];
        int[] count = new int[10];
        step += 2;

        // Calculate count of elements
        for (int i = 0; i < n; i++) {
            count[(arr.get(i) / place) % 10]++;
            step++;
        }

        // Calculate cumulative frequency
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            step++;
        }

        // Place the elements in sorted order
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr.get(i) / place) % 10] - 1] = arr.get(i);
            count[(arr.get(i) / place) % 10]--;
            step++;
        }

        for (int i = 0; i < n; i++) {
            arr.set(i, output[i]);
            step++;
        }
    }

        void radixsort(ArrayList<Integer> arr, int n) {

        // get maximum element from array
        int max = Collections.max(arr);
        step += 3;

        // Apply counting sort to sort elements based on place value
        for (int place = 1; max / place > 0; place *= 10)
            RadixcountingSort(arr, n, place);
    }

        void swap(ArrayList<Integer> arr, int i, int j)//Quicksort func
        {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(i, temp);
        step += 3;
    }

        int partition(ArrayList<Integer> arr, int low, int high)//Quicksort func
        {

        // pivot
        int pivot = arr.get(high);
        int i = (low - 1);


        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr.get(j) < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
                step += 2;
            }
        }
        swap(arr, i + 1, high);
        step += 3;
        return (i + 1);

    }

        void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            step += 3;
        }
    }

        public ArrayList<Integer> StepHelper(ArrayList<Integer> a, int CurrentStep) {
        ArrayList<Integer> x = new ArrayList<>();

        for (int i = 0; i < CurrentStep; i++) {
            x.add(i, a.get(i));
        }

        return x;

    }

        void countSort(ArrayList<Integer> array, int size) {
        int[] output = new int[size + 1];
        int max = array.get(0);
        for (int i = 1; i < size; i++) {
            if (array.get(i) > max)
                max = array.get(i);
            step++;
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
            step++;
        }

        // Store the count of each element
        for (int i = 0; i < size; i++) {
            count[array.get(i)]++;
            step++;
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
            step++;
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
            output[count[array.get(i)] - 1] = array.get(i);
            count[array.get(i)]--;
            step++;
        }

        // Copy the sorted elements into original array
            for (int i = 0; i < size; i++) {
                array.set(i, output[i]);
                step++;
            }
        step += 3;
    }

        void mergenew(ArrayList<Integer> arr, int l, int m, int r) {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int[n1];
            int R[] = new int[n2];

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i){
                 arr.get(l+i);step++;}
            for (int j = 0; j < n2; ++j){
                R[j] = arr.get(m+1+j);step++;}


            int i = 0, j = 0;
            int k = l;
            step+=6;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr.set(l,L[i]);
                    i++;
                    step+=2;
                } else {
                     arr.set(k,L[i]);

                    j++;
                    step+=2;
                }
                k++;
                step++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                 arr.set(k,L[i]);

                i++;
                k++;
                step+=3;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                arr.set(k,R[j]);

                j++;
                k++;
                step+=3;
            }
        }

        void sort(ArrayList<Integer> arr, int l, int r) {
            if (l < r) {
                // Find the middle point
                int m = l + (r - l) / 2;

                // Sort first and second halves
                sort(arr, l, m);
                sort(arr, m + 1, r);

                // Merge the sorted halves
                mergenew(arr, l, m, r);
                step+=4;
            }
        }


        void bubbleSort(ArrayList<Integer> arr)
                                              {
        int n = arr.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr.get(j) > arr.get(j+1))
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,temp);
                    step+=3;
                }
    }





}

