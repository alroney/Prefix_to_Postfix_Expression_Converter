package conversionPack;

/**
 * Author: Andrew Roney
 * Project Name: Prefix_to_Postfix_Expression_Converter. (Project 1)
 * Date: 8/17/2022
 * 
 * ExpressionEvaluatorGUI class creates the interface and runs the conversion process.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExpressionEvaluatorGUI extends JFrame{
	private Label lblUserExpression;
	private Label lblResult;
	private Label lblBlank;
	
	private TextField txtExpression;
	private TextField txtResult;
	
	private Button btnPostToPre;
	private Button btnPreToPost;
	
	
	//Finalize setup of GUI and implement the click event method
	ExpressionEvaluatorGUI() {
		super("Expression Conversion");//Super is JFrame class
		setupGUI();//Get the buttons, text and layout for GUI
		
		setTitle("Expression Evaluator");//Sets the title of the frame located at the top.
		setSize(500, 200);//Size of frame
		setLocationRelativeTo(null);//Positions the frame to the center of the screen.
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Hitting the 'x' on the top right corner of the window.
		setVisible(true);//Display the frame.
		
		setClickEventListeners();//Implement the button functionality.
	}
	
//Method: Setup the GUI with its Buttons, Text and Layout.
	private void setupGUI() {
		setLayout(new FlowLayout());//Set low layout.
		lblUserExpression = new Label("Enter Postfix or Prefix Expression");//Create the label.
			add(lblUserExpression);//Add label to the frame.
		
		txtExpression = new TextField("", 40);//Create text box with 40 character limit.
			txtExpression.setEditable(true);
			add(txtExpression);
		
		JPanel nestedBtn = new JPanel();
			btnPreToPost = new Button("Prefix to Postfix");//Create the button for Prefix conversion.
				nestedBtn.add(btnPreToPost);
			
			btnPostToPre = new Button("Postfix to Prefix");//Create the button for Postfix conversion.
				nestedBtn.add(btnPostToPre);
				
			add(nestedBtn);
		
		lblBlank = new Label("");
			add(lblBlank);
			
		lblResult = new Label("Result");
			add(lblResult);
		
		txtResult = new TextField("", 50);//Create text box with 50 character limit.
			txtResult.setEditable(false);
		
		JPanel nested = new JPanel();//Create a nested panel
			nested.add(lblResult);
			nested.add(txtResult);
			add(nested, BorderLayout.NORTH);
	}
	
	
	
/*Action Events*/
	private void setClickEventListeners() {
		//Prefix to Postfix button.
		btnPreToPost.addActionListener(e -> {
			
			String postfix = null;
		
			try {
				postfix = EvaluateExpression.preToPost(txtExpression.getText());
			}
			
			catch(SyntaxError se) {//Display user defined SyntaxError.
				JOptionPane.showMessageDialog(ExpressionEvaluatorGUI.this, se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
			txtResult.setText("Postfix Expression: "+ postfix);
		});
		
		//Postfix to Prefix button.
		btnPostToPre.addActionListener(e -> {
			
			String prefix = null;
			
			try {
				prefix = EvaluateExpression.postToPre(txtExpression.getText());
			}
			catch(SyntaxError se) {//Display user defined SyntaxError.
				JOptionPane.showMessageDialog(ExpressionEvaluatorGUI.this, se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			txtResult.setText("Prefix Expression: "+ prefix);
		});
	}
	
	
/*MAIN*/
	public static void main(String[] args) {
		new ExpressionEvaluatorGUI();//RUN the GUI
	}
	
}
