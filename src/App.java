import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
public class App extends Application{
String mainreq,mainnum,mainid;
int res=0;
int paniPuri=0, masalaPuri=0,bhelPuri =0;
public static void main(String[] args)  throws IOException{ 
launch(args);
}
public void start(Stage primaryStage) {
primaryStage.setTitle("Employee Login Form");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        Label userName = new Label("Enter Username:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        Button btn = new Button("Sign in");
        grid.add(btn, 1, 4);
        Label response = new Label();
        grid.add(response,1,5);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try 
                {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        final String user="root";
                        final String pass="root123";
                        final String db_url="jdbc:mysql://localhost:3306/login";
                        Connection con=DriverManager.getConnection(db_url,user,pass);                        
                        Statement stmt=(Statement) con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from users;");
                        String eD;
                        String dD;
                        String eP,dP;
                        while(rs.next())
                        {
                            eD = userTextField.getText();
                            dD = rs.getString(1);
                            eP = pwBox.getText();
                            dP = rs.getString(2);
                            if(eD.equals(dD) && eP.equals(dP))
                            {
                                res = 1;
                                primaryStage.close();
                                break;
                            }
                            else
                            {
                                res = 0;
                                System.out.println("Wrong username or password");
                                System.exit(0);
                            }
                            con.close();
                        }
                        rs.close();
                 }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
                if(res == 1){
                GridPane ask = new GridPane();
                ask.setHgap(10);
                ask.setVgap(10);
                ask.setAlignment(Pos.CENTER);
                ask.setPadding(new Insets(25, 25, 25, 25));
                Scene askScene = new Scene(ask, 300, 300);
                Stage askWindow = new Stage();
                askWindow.setScene(askScene);
                askWindow.setTitle("Order Button");
                Button newOrder = new Button("New Order");
                ask.add(newOrder,0,1);
                newOrder.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent f){
                        GridPane data = new GridPane();
                        data.setHgap(10);
                        data.setVgap(10);
                        data.setAlignment(Pos.CENTER);
                        data.setPadding(new Insets(25, 25, 25, 25));
                        Scene dataScene = new Scene(data, 400, 300);
                        Stage details = new Stage();
                        details.setScene(dataScene);
                        details.setTitle("Customer Details");
                        Label d = new Label("Enter customer data");
                        Font font = Font.font("Italic", FontWeight.BLACK, 15);
                        d.setFont(font);
                        data.add(d,0,1);
                        Label id = new Label("Customer ID:");
                        data.add(id,0,2);
                        TextField ID = new TextField();
                        data.add(ID,1,2);
                        Label name = new Label("Customer name:");
                        data.add(name,0,3);
                        TextField nameD = new TextField();
                        data.add(nameD,1,3);
                        Button submit = new Button("Submit details");
                        data.add(submit,0,6);
                        submit.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent g){
                                String name;
                                int id_g = Integer.parseInt(ID.getText());                                
                                name = nameD.getText();
                                mainreq = nameD.getText();
                                mainid = ID.getText();
                                details.close();
                                GridPane gridOrder = new GridPane();
                                gridOrder.setHgap(10);
                                gridOrder.setVgap(10);
                                gridOrder.setPadding(new Insets(25, 25, 25, 25));
                                Scene secondScene = new Scene(gridOrder, 700, 400);
                                Stage newWindow = new Stage();
                                newWindow.setTitle("Order Page");
                                newWindow.setScene(secondScene);
                                Label welcome = new Label("Canteen Bill");
                                gridOrder.add(welcome,0,1);
                                Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 20);
                                welcome.setFont(font);
                                //menu
                                Label menu = new Label("Menu");
                                gridOrder.add(menu,0,2);
                                Font menuFont = Font.font("Aerial",FontWeight.BLACK, 15);
                                menu.setFont(menuFont);
                                //Price
                                Label price = new Label("Price");
                                gridOrder.add(price,1,2);
                                price.setFont(menuFont);
                                //Quantity
                                Label quantity = new Label("Quantity");
                                gridOrder.add(quantity,2,2);
                                quantity.setFont(menuFont);
                                Label bill = new Label("Total Bill");
                                gridOrder.add(bill,3,2);
                                bill.setFont(menuFont);
                                //menu items
                                Label pp = new Label("Panipuri");
                                gridOrder.add(pp,0,3);
                                Label mp = new Label("Masala Puri");
                                gridOrder.add(mp,0,4);
                                Label bp = new Label("Bhel Puri");
                                gridOrder.add(bp,0,5);
                                //menu price
                                Label ppPrice = new Label("RS. 25");
                                gridOrder.add(ppPrice,1,3);
                                Label mpPrice = new Label("RS. 30");
                                gridOrder.add(mpPrice,1,4);
                                Label bpPrice = new Label("RS. 30");
                                gridOrder.add(bpPrice,1,5);
                                //Quantity textfield
                                TextField ppE = new TextField();
                                gridOrder.add(ppE,2,3);
                                TextField mpE = new TextField();
                                gridOrder.add(mpE,2,4);
                                TextField bpE = new TextField();
                                gridOrder.add(bpE,2,5);
                                Label panipuri = new Label();
                                gridOrder.add(panipuri,3,3);
                                Label masalapuri = new Label();
                                gridOrder.add(masalapuri,3,4);
                                Label bhelpuri = new Label();
                                gridOrder.add(bhelpuri,3,5);
                                Label t = new Label();
                                gridOrder.add(t,3,8);
                                Button foodprice = new Button("Check Total Price");
                                gridOrder.add(foodprice,3,9);
                               
                                
                                foodprice.setOnAction(new EventHandler<ActionEvent>(){
                                    public void handle(ActionEvent h){
                                        int p=0,m=0,b2=0,total=1;
                                        if (ppE.getText().equals(""))
                                        {
                                            panipuri.setText("Rs."+p);
                                        }
                                        else{
                                            p = Integer.parseInt(ppE.getText());
                                            p = p * 25;
                                            panipuri.setText("Rs."+String.valueOf(p));
                                        }
                                        if (mpE.getText().equals(""))
                                        {
                                            masalapuri.setText("Rs."+String.valueOf(m));
                                        }
                                        else{
                                            m = Integer.parseInt(mpE.getText());
                                            m = m * 30;
                                            masalapuri.setText("Rs."+String.valueOf(m));
                                        }
                                        if (bpE.getText().equals(""))
                                        {
                                            bhelpuri.setText("Rs."+String.valueOf(b2));
                                        }
                                        else{
                                            b2 = Integer.parseInt(bpE.getText());
                                            b2 = b2 * 30;
                                            bhelpuri.setText("Rs."+String.valueOf(b2));
                                        }
                                        total = p + m + b2;
                                        t.setText("Total Price is Rs."+total);
                                    }
                                });
                                Button order = new Button("Place Order");
                                gridOrder.add(order,2,8);
                                order.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent e) {
                                        if(ppE.getText().equals(""))
                                        {
                                            paniPuri = 0;
                                        }
                                        else{
                                            paniPuri = Integer.parseInt(ppE.getText())*25;
                                        }
                                        if(mpE.getText().equals(""))
                                        {
                                            masalaPuri = 0;
                                        }
                                        else{
                                            masalaPuri = Integer.parseInt(mpE.getText())*30;
                                        }
                                        if(bpE.getText().equals(""))
                                        {
                                            bhelPuri = 0;
                                        }
                                        else{
                                            bhelPuri = Integer.parseInt(bpE.getText())*30;
                                        }
                                        try 
                                        {
                                            String pp1,mp1,bp1,id;
                                            pp1=ppE.getText();
                                            mp1=mpE.getText();
                                            bp1=bpE.getText();
                                            id=ID.getText();
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                           final String user="root";
                                           final String pass="root123";
                                           final String db_url="jdbc:mysql://localhost:3306/login";
                                           Connection con=DriverManager.getConnection(db_url,user,pass);
                                           Statement stmt=(Statement) con.createStatement();
                            
                                           String sql = "INSERT INTO user_details("+"name,"+"ppE,"+"mpE,"+"bpE,"+"id_g)"+ "VALUES(?,?,?,?,?)";
                                            PreparedStatement preparedStatement=con.prepareStatement(sql);
                                            preparedStatement.setString(1,name);
                                            preparedStatement.setString(2,pp1);
                                            preparedStatement.setString(3,mp1);
                                            preparedStatement.setString(4,bp1);
                                            preparedStatement.setString(5,id);
                                            preparedStatement.execute();
                                            con.close();
                                        }
                                        catch(Exception e1)
                                        {
                                            e1.printStackTrace();
                                        }
                                        GridPane bill = new GridPane();
                                        bill.setHgap(10);
                                        bill.setVgap(10);
                                        bill.setPadding(new Insets(25, 25, 25, 25));
                                        Scene billScene = new Scene(bill, 300, 300);
                                        Stage billWindow = new Stage();
                                        billWindow.setScene(billScene);
                                        billWindow.setTitle("Final Bill");
                                        Font menuFont = Font.font("Aerial",FontWeight.BLACK, 15);
                                        Label billMenu = new Label("Food Ordered");
                                        bill.add(billMenu,0,1);
                                        billMenu.setFont(menuFont);
                                        Label billPrice = new Label("Price");
                                        bill.add(billPrice,1,1);
                                        billPrice.setFont(menuFont);
                                        int i=2,j=2;
                                        if(paniPuri != 0)
                                        {
                                            Label finalP = new Label("Pani Puri");
                                            bill.add(finalP,0,i);
                                            i++;
                                            Label finalPB = new Label();
                                            bill.add(finalPB,1,j);
                                            j++;
                                            finalPB.setText(Integer.toString(paniPuri));
                                        }
                                        if(masalaPuri != 0)
                                        {
                                            Label finalM = new Label("Masala Puri");
                                            bill.add(finalM,0,i);
                                            i++;
                                            Label finalPM = new Label();
                                            bill.add(finalPM,1,j);
                                            j++;
                                            finalPM.setText(Integer.toString(masalaPuri));
                                        }
                                        if(bhelPuri != 0)
                                        {
                                            Label finalB = new Label("Bhel Puri");
                                            bill.add(finalB,0,i);
                                            i++;
                                            Label finalBB = new Label();
                                            bill.add(finalBB,1,j);
                                            j++;
                                            finalBB.setText(Integer.toString(bhelPuri));
                                        }
                                        Label totalBill = new Label();
                                        bill.add(totalBill,0,i);
                                        int finalBillPrint = paniPuri + masalaPuri + bhelPuri;
                                        if(finalBillPrint > 0)
                                            totalBill.setText("Total Price : Rs."+Integer.toString(finalBillPrint));
                                        billWindow.show();
                                    }
                                });
                                newWindow.show();
                            }
                        });

                        details.show();
                    }
                });
                askWindow.show();
            }}
        });
        primaryStage.show();
    }
}    