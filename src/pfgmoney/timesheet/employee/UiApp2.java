package pfgmoney.timesheet.employee;

import java.awt.EventQueue;









import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JComboBox;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Panel;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class UiApp2 {

	private JFrame frame;
	public int flag=0;
	public int flag2=0;
	public int flag3=0;
	private JTextField StHr;
	private JTextField StMin;
	private JTextField EndHr;
	private JTextField EndMin;
	private JTextField tasks;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiApp2 window = new UiApp2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiApp2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(53, 75, 73, 16);
		frame.getContentPane().add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(53, 104, 73, 16);
		frame.getContentPane().add(lblEndTime);
		
		JLabel lblSelectLetterType = new JLabel("Tasks completed");
		lblSelectLetterType.setBounds(12, 147, 114, 16);
		frame.getContentPane().add(lblSelectLetterType);
		
		JButton btnNewButton = new JButton("Submit Timesheet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//UPDATING TULLAMARINE Letter REGISTER
				try {
					LogOn2 log = new LogOn2();
					System.out.println(log.forUserCompare);
					FileInputStream file = new FileInputStream(new File("C:\\Users\\Azima\\Desktop\\thomas.xlsx"));
					 // Create Workbook instance holding reference to .xlsx file 
		            XSSFWorkbook workbook = new XSSFWorkbook(file); 
		  
		            // Get first/desired sheet from the workbook 
		            int totalSheets=workbook.getNumberOfSheets();
		            XSSFSheet sheet = workbook.getSheetAt(1); 
		            System.out.println(sheet.getSheetName());
		            // Iterate through each rows one by one 
		            Iterator<Row> rowIterator = sheet.iterator(); 
		           int rowCount =0;
		           
		            while (rowIterator.hasNext()) { 
		            	rowCount++;
		                Row row = rowIterator.next(); 
		                }
				                // this Writes the workbook  
				            	Row row = sheet.createRow(rowCount++); 
				            	//getting local date
				            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				            	Calendar cal = Calendar.getInstance();
				            	System.out.println(dateFormat.format(cal.getTime()));
				            	String[] contents={"asd","ssss"};
				               	 int cellnum = 0;
				            	 for (String obj : contents) { 
//					              this line creates a cell in the next column of that row 
					                   Cell cell = row.createCell(cellnum++); 
					                   cell.setCellValue((String)obj); 
					                } 
				                FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Azima\\Desktop\\thomas.xlsx")); 
				                workbook.write(out); 
				                out.close(); 
				                System.out.println("we.xlsx written successfully on disk."); 
				            
				            
		                System.out.println(""); 
		            
		            file.close(); 
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton.setBounds(47, 234, 162, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(233, 234, 97, 25);
		frame.getContentPane().add(btnExit);
		
		StHr = new JTextField();
		StHr.setBounds(135, 72, 32, 22);
		frame.getContentPane().add(StHr);
		StHr.setColumns(10);
		
		StMin = new JTextField();
		StMin.setBounds(179, 72, 30, 22);
		frame.getContentPane().add(StMin);
		StMin.setColumns(10);
		
		EndHr = new JTextField();
		EndHr.setBounds(135, 101, 32, 22);
		frame.getContentPane().add(EndHr);
		EndHr.setColumns(10);
		
		EndMin = new JTextField();
		EndMin.setBounds(179, 101, 32, 22);
		frame.getContentPane().add(EndMin);
		EndMin.setColumns(10);
		
		tasks = new JTextField();
		tasks.setBounds(138, 136, 192, 85);
		frame.getContentPane().add(tasks);
		tasks.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(":");
		lblNewLabel.setBounds(171, 75, 19, 16);
		frame.getContentPane().add(lblNewLabel);
		
		label = new JLabel(":");
		label.setBounds(171, 104, 5, 16);
		frame.getContentPane().add(label);
		
		
		
		
		
		
		
	}
}
