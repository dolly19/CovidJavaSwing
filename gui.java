/* Assumption
1.  input dates must be according to constraint
2.  date formate should be dd/mm/yyyy  */
import javax.swing.*;
import java.text.ParseException;
import java.time.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// main class
public class gui {
    public static void main (String[] args){

        GUI ob = new GUI();
    }
}
// patient class containing info of patients
class patient {
    String A[][] = {{"FLORA", "6", "A", "01/04/2020"}, {"CAERY", "72", "A", "01/06/2020"}, {"SMITH", "89", "A", "07/08/2020"}, {"BOB", "74", "A", "04/07/2020"}, {"ROBERTZ", "50", "A", "09/08/2020"}};
    String B[][] = {{"DENYS", "24", "B", "01/04/2020"}, {"JULIE", "86", "B", "02/05/2020"}, {"PEARSON", "47", "B", "04/06/2020"}, {"DAVID", "7", "B", "14/06/2020"}, {"ANDERSON", "62", "B", "27/07/2020"}};
    String C[][] ={{"JIM", "42", "C", "18/05/2020"}, {"THOMAS", "21", "C", "11/06/2020"}, {"RACHEL", "48", "C", "24/07/2020"}};
    String D[][] = {{"JOHN", "95", "D", "01/06/2020"}, {"KEVIM", "37", "D", "05/06/2020"},{"EDITH", "42", "D", "07/06/2020"} ,{"TOM", "67", "D", "20/06/2020"}, {"MARY", "17", "D", "21/06/2020"}, {"HAZEL", "87", "D", "26/06/2020"}, {"JOHNSON", "10", "D", "01/08/2020"}};
    ArrayList<ArrayList<String> > data = new ArrayList<ArrayList<String> >();
    //act method calculate the number of active cases on a particular date in each tower
    public int act(String[][] arr, String we) {
        int act =0;
        int ye = Integer.parseInt(we.substring(6));
        int de = Integer.parseInt(we.substring(0, 2));
        int me = Integer.parseInt(we.substring(3, 5));
        for(int i=0; i<arr.length; i++){
            SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
            String f = arr[i][3];
            int yf = Integer.parseInt(f.substring(6));
            int df = Integer.parseInt(f.substring(0, 2));
            int mf = Integer.parseInt(f.substring(3, 5));
            String end = we;
            if(me>mf){
                try {
                    Date dateBefore = myFormat.parse(f);
                    Date dateAfter = myFormat.parse(we);
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    float diff = (difference / (1000 * 60 * 60 * 24));
                    if (diff < 22)
                        act = act + 1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(me == mf){
                if(de>df ){
                    try{
                        Date dateBefore = myFormat.parse(f);
                        Date dateAfter = myFormat.parse(we);
                        long difference = dateAfter.getTime() - dateBefore.getTime();
                        float diff = (difference / (1000 * 60 * 60 * 24));
                        if (diff < 22)
                            act = act + 1;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(de == df){
                    act = act+1;
                }
            }

        }

        return act;
    }

    //rec method calculate the number of recovered cases on a particular date in each tower
    public int rec(String[][] arr, String we) {
        int recov =0;
        int ye = Integer.parseInt(we.substring(6));
        int de = Integer.parseInt(we.substring(0, 2));
        int me = Integer.parseInt(we.substring(3, 5));
        for(int i=0; i<arr.length; i++){
            SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
            String f = arr[i][3];
            int yf = Integer.parseInt(f.substring(6));
            int df = Integer.parseInt(f.substring(0, 2));
            int mf = Integer.parseInt(f.substring(3, 5));
            String end = we;
            if(me>mf){
                try {
                    Date dateBefore = myFormat.parse(f);
                    Date dateAfter = myFormat.parse(we);
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    float diff = (difference / (1000 * 60 * 60 * 24));
                    if (diff >= 22)
                        recov = recov + 1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(me == mf){
                if(de>df ){
                    try{
                        Date dateBefore = myFormat.parse(f);
                        Date dateAfter = myFormat.parse(we);
                        long difference = dateAfter.getTime() - dateBefore.getTime();
                        float diff = (difference / (1000 * 60 * 60 * 24));
                        if (diff >= 22)
                            recov = recov + 1;


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return recov;
    }
    // the box method return a arraylist which stores the required patient detail with their recovery dates
    public ArrayList<ArrayList<String>> box(String[][] arr,String we , String tower) {
        int ye = Integer.parseInt(we.substring(6));
        int de = Integer.parseInt(we.substring(0, 2));
        int me = Integer.parseInt(we.substring(3, 5));
        int p =0;
        String [][] x =  new String[arr.length][4];
        for (int i = 0; i < arr.length; i++) {


            String f = arr[i][3];
            int yf = Integer.parseInt(f.substring(6));
            int df = Integer.parseInt(f.substring(0, 2));
            int mf = Integer.parseInt(f.substring(3, 5));
            SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
            if(me>mf){
                try {

                    x[p][0]= arr[i][0];
                    x[p][1]=tower;
                    Date dateBefore = myFormat.parse(f);
                    Date dateAfter = myFormat.parse(we);
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    float diff = (difference / (1000 * 60 * 60 * 24));
                    if(diff >= 22)
                        x[p][2] = "Recovered";

                    else
                        x[p][2] = "Active";

                    LocalDate start1 = LocalDate.of(yf, mf, df).plusDays(22);
                    x[p][3] = start1.toString();
                

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ++p;
    


            }
            else if (me == mf){
                if(de>df ){
                    try{
                        x[p][0]= arr[i][0];
                        x[p][1]=tower;
                        Date dateBefore = myFormat.parse(f);
                        Date dateAfter = myFormat.parse(we);
                        long difference = dateAfter.getTime() - dateBefore.getTime();
                        float diff = (difference / (1000 * 60 * 60 * 24));
                        if(diff >= 22)
                            x[p][2] = "Recovered";
                        else
                            x[p][2] = "Active";
                        LocalDate start1 = LocalDate.of(yf, mf, df).plusDays(22);
                        x[p][3] = start1.toString();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ++p;
        


                }
                if(de == df){
                    x[p][0]= arr[i][0];
                    x[p][1]=tower;
                    x[p][2] = "Active";
                    LocalDate start1 = LocalDate.of(yf, mf, df).plusDays(22);
                    x[i][3] = start1.toString();
                
                    ++p;


                }
            }



        }
        for(int j=0; j<x.length; j++){
            if(x[j][0] == null) {
                break;
            }
            data.add(new ArrayList<String>(Arrays.asList(x[j])));

        }

        return data;
    }



}
//this class inheriting JFrame class to create JFrame window 
class GUI extends JFrame {

    GUI() {
        setVisible(true);
        setSize(300, 300);
        JPanel panel = new JPanel();
        JPanel panel1= new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("COVID-19 INFO");
        JLabel tower = new JLabel("Tower");
        JCheckBox a = new JCheckBox("A");
        JCheckBox b = new JCheckBox("B");
        JCheckBox c = new JCheckBox("C");
        JCheckBox d = new JCheckBox("D");
        JButton but = new JButton("Search");
        JLabel reporting = new JLabel("      Reporting Date");
        JTextField t = new JTextField(10);
        panel.add(tower);
        panel.add(a);
        panel.add(b);
        panel.add(c);
        panel.add(d);
        panel1.add(reporting);
        panel1.add(t);
        add(but);
        getContentPane().add(BorderLayout.NORTH, panel);
        getContentPane().add(BorderLayout.CENTER, panel1);
        getContentPane().add(BorderLayout.SOUTH, but);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                patient ob = new patient();
                JFrame window = new JFrame("DETAILS");
                JLabel acti = new JLabel("Active Cases");
                JLabel actl = new JLabel("                    ");
                JLabel recl = new JLabel("                    ");
                JLabel reci = new JLabel("Recovered Cases");
                window.setVisible(true);
                window.setSize(500, 500);
                window.setLayout(new FlowLayout());

                window.add(acti);
                window.add(actl);
                window.add(reci);
                window.add(recl);
                String e = t.getText();
                int active = 0;
                boolean  D = false;
                boolean  C = false;
                boolean  B = false;
                boolean  A = false;

                int recovered = 0;
                if (a.isSelected()) {
                    active = active + ob.act(ob.A, e);
                    recovered = recovered + ob.rec(ob.A, e);
                    A= true;

                }
                if (b.isSelected()) {
                    active = active + ob.act(ob.B, e);
                    recovered = recovered + ob.rec(ob.B, e);
                    B=true;
                }
                if (c.isSelected()) {
                    active = active + ob.act(ob.C, e);
                    recovered = recovered + ob.rec(ob.C, e);
                    C=true;
                }
                if (d.isSelected()) {
                    active = active + ob.act(ob.D, e);
                    recovered = recovered + ob.rec(ob.D, e);
                    D=true;
                }
                actl.setText(active + "          ");
                recl.setText(recovered + "          ");
                if(A){
                    ob.box(ob.A , e ,"A");
                }
                if(B){
                    ob.box(ob.B , e ,"B");
                }
                if(C){
                    ob.box(ob.C , e ,"C");
                }
                if(D){
                    ob.box(ob.D , e ,"D");
                }





                String[] columns = {"NAME" , "TOWER" ,"STATUS" , "RECOVERY DATE"};


                Object [][] array = new Object[ob.data.size()][4];
                for(int i=0; i<ob.data.size(); i++){
                    array[i] = ob.data.get(i).toArray();
                }
                JTable j = new JTable(array, columns);
                j.setBounds(30, 40, 200, 300);
                JScrollPane sp = new JScrollPane(j);
                window.add(sp);







            }
        });

    }
}





