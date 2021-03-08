package phone.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import phone.Main;
import phone.Pizza;
import phone.mysqlconnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PizzaMenuController {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	ResultSet srs=null;
	Statement stmt=null;

	static int cnt=0;

	@FXML  Main main = new Main();

	String p_type,p_size;
	int p1,p2,p3;
	String currentdate;

	@FXML    private ComboBox<String> comboBox, comboBox2;
	@FXML    private CheckBox ch1;
	@FXML    private CheckBox ch2;
	@FXML    private CheckBox ch3;
	@FXML    private Button b1;
	@FXML    private Button b2;
	@FXML    private Button b3;
	@FXML    private Button b4;
	@FXML    private TextArea ta; 
	@FXML    private Label lab;

	@FXML    private Label lbl_name;
	@FXML    private Label lbl_phone;
	@FXML    private Label lbl_address;
	@FXML    private Label lbl_date;

	ObservableList<String> comboBoxList = 
			FXCollections.observableArrayList("����δ� ����: 12,000��","�������� ����: 15,000��","�Ұ�� ����: 18,000��","�޺� ����: 20,000��");
	ObservableList<String> comboBoxList2 = 
			FXCollections.observableArrayList("small size: 0��","medium size: 1,500��","large size: 3,000��");
	ObservableList<Pizza> list;

	@FXML	 private TableView<Pizza> tableContent;
	@FXML    private TableColumn<Pizza, String> col_date;
	@FXML    private TableColumn<Pizza, String> col_pizza;
	@FXML    private TableColumn<Pizza, String> col_size;
	@FXML    private TableColumn<Pizza, Integer> col_1;
	@FXML    private TableColumn<Pizza, Integer> col_2;
	@FXML    private TableColumn<Pizza, Integer> col_3;

	String sel1 = "";
	String sel2 = "";
	String sel3 = "";

	int num1=0;
	int num2=0;
	int num3=0;

	public void initialize() {	
		comboBox.setValue("���� ����");
		comboBox.setItems(comboBoxList);	
		comboBox2.setItems(comboBoxList2);

		conn = mysqlconnect.ConnectDb();

		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		currentdate = format.format(Calendar.getInstance().getTime());
		lbl_date.setText(currentdate);
		list = FXCollections.observableArrayList();
		fetch();
		// ������ id�� ã��, cnt
		try {
			stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			srs = stmt.executeQuery("select * from pizza_history");
			srs.last();
			cnt = srs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fetch() {
		try {
			String sql = "select * from pizza_history where name =? and phone=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, main.m_name);
			pst.setString(2, main.m_phone);

			rs = pst.executeQuery();
			while(rs.next()) {		
				int r1 = rs.getInt("id");
				String r2 = rs.getString("name");
				String r3 = rs.getString("phone");
				String r4 = rs.getString("address");
				String r5 = rs.getString("pizza_type");
				String r6 = rs.getString("pizza_size");
				int r7 = rs.getInt("add_1");
				int r8 = rs.getInt("add_2");
				int r9 = rs.getInt("add_3");
				String r10 = rs.getString("date");

				lbl_name.setText(r2);
				lbl_phone.setText(r3);
				lbl_address.setText(r4);
				lbl_date.setText(currentdate);

				Pizza pizza = new Pizza(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);	

				list.add(pizza);

				col_date.setCellValueFactory(new PropertyValueFactory<Pizza,String>("date"));
				col_pizza.setCellValueFactory(new PropertyValueFactory<Pizza,String>("pizza_type"));
				col_size.setCellValueFactory(new PropertyValueFactory<Pizza,String>("pizza_size"));
				col_1.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_1"));										
				col_2.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_2"));
				col_3.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_3"));

				tableContent.setItems(list);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void onClickbombo(ActionEvent event) {
		String sel = comboBox.getValue() ;
		p_type = sel;
		int a = comboBox.getSelectionModel().getSelectedIndex(); 
		ta.appendText(sel+"�� ���� \n");

		if(a==0)
			num1 += 12000;
		else 	if(a==1)
			num1 += 15000;
		else 	if(a==2)
			num1 += 18000;
		else 	if(a==3)
			num1 += 20000;

		sel1+=sel +"\n";
	}

	@FXML
	void onClickCombo2(ActionEvent event) {
		String sel = comboBox2.getValue() ;
		p_size = sel;
		ta.appendText(sel+"�� ���� \n");

		if(sel.equals("small size: 0��"))
			num3 += 0;
		else if(sel.equals("medium size: 1,500��"))
			num3 += 1500;
		else if(sel.equals("large size: 3,000��"))
			num3 += 3000;

		sel3+=sel +"\n";
	}

	@FXML
	void onClickb1(ActionEvent event) {
		Integer number = num1+num2+num3;
		NumberFormat numberFormatter;
		numberFormatter = NumberFormat.getNumberInstance();
		String quantityOut = numberFormatter.format(number);

		ta.setText("�ֹ� ����");
		ta.appendText("\n\n"+ sel1 + sel3 +sel2+"\n");
		ta.appendText("\n total = "+ quantityOut +"�� �Դϴ�.\n");
		lab.setText(quantityOut);
		p1=0;
		p2=0;
		p3=0;

		if(ch1.isSelected())
			p1=1;
		else 
			p1=0;
		if(ch2.isSelected())
			p2=1;
		else 
			p2=0;
		if(ch3.isSelected())
			p3=1;
		else 
			p3=0;

		String sql = "insert into pizza_history (id,name,phone,address,pizza_type,pizza_size,add_1,add_2,add_3,date)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, ++cnt);
			pst.setString(2, main.m_name);
			pst.setString(3, main.m_phone);
			pst.setString(4, main.m_address);
			pst.setString(5, p_type);
			pst.setString(6, p_size);
			pst.setInt(7, p1);
			pst.setInt(8, p2);
			pst.setInt(9, p3);
			pst.setString(10, currentdate);

			pst.execute();

			JOptionPane.showMessageDialog(null,"Saved");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void onClickb2(ActionEvent event) {

		if(ch1.isSelected())
			ch1.fire();
		if(ch2.isSelected())
			ch2.fire();
		if(ch3.isSelected())
			ch3.fire();

		sel1="";
		sel2="";
		sel3="";

		num1=0;
		num2=0;
		num3=0;

		comboBox2.setValue("���� ũ��");
		comboBox.setValue("���� ����");
		ta.setText(""); 

	}
	@FXML

	void onClickb3(ActionEvent event) {
		main.showMainView();
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../view/MainItem.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickch1(ActionEvent event) {
		if(ch1.isSelected()) {
			ta.appendText(ch1.getText()+" ���� \n");
			num2+=2000;
			sel2+=ch1.getText()+"\n";
		}
		if(ch1.isSelected()== false) {
			ta.appendText(ch1.getText()+" ��� \n");
			num2-=2000;
			sel2+=ch1.getText()+" ���\n";
		}
	}

	@FXML
	void onClickb4(ActionEvent event) throws IOException {
      	main.showMainView();
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../view/PizzaMain.fxml"));
			Main.mainLayout.setCenter(root);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void onClickch2(ActionEvent event) {
		if(ch2.isSelected()) {
			ta.appendText(ch2.getText()+" ���� \n");
			num2+=1000;
			sel2+=ch2.getText()+"\n";
		}
		if(ch2.isSelected()== false) {
			ta.appendText(ch2.getText()+" ��� \n");
			num2-=1000;
			sel2+=ch2.getText()+" ���\n";
		}
	}

	@FXML
	void onClickch3(ActionEvent event) {
		if(ch3.isSelected()) {
			ta.appendText(ch3.getText()+" ���� \n");
			num2+=500;
			sel2+=ch3.getText()+"\n";
		}
		if(ch3.isSelected()== false) {
			ta.appendText(ch3.getText()+" ��� \n");
			num2-=500;
			sel2+=ch3.getText()+" ���\n";
		}

	}

}

