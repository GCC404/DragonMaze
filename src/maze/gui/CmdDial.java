package maze.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class CmdDial extends JDialog {
	
	private char []commands={'W','S','A','D','V'};

	private final JPanel contentPanel = new JPanel();
	private JTextField txtW;
	private JTextField txtS;
	private JTextField txtA;
	private JTextField txtD;
	private JTextField txtV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CmdDial dialog = new CmdDial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CmdDial() {
		setBounds(100, 100, 179, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblUp = new JLabel("Up:");
			contentPanel.add(lblUp);
		}
		{
			txtW = new JTextField();
			txtW.setText("W");
			contentPanel.add(txtW);
			txtW.setColumns(10);
		}
		{
			JLabel lblDown = new JLabel("Down");
			contentPanel.add(lblDown);
		}
		{
			txtS = new JTextField();
			txtS.setText("S");
			contentPanel.add(txtS);
			txtS.setColumns(10);
		}
		{
			JLabel lblLeft = new JLabel("Left");
			contentPanel.add(lblLeft);
		}
		{
			txtA = new JTextField();
			txtA.setText("A");
			contentPanel.add(txtA);
			txtA.setColumns(10);
		}
		{
			JLabel lblRight = new JLabel("Right");
			contentPanel.add(lblRight);
		}
		{
			txtD = new JTextField();
			txtD.setText("D");
			contentPanel.add(txtD);
			txtD.setColumns(10);
		}
		{
			JLabel lblEagle = new JLabel("Eagle");
			contentPanel.add(lblEagle);
		}
		{
			txtV = new JTextField();
			txtV.setText("V");
			contentPanel.add(txtV);
			txtV.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String[] text=new String[4];
						text[0]=txtW.getText();
						text[1]=txtS.getText();
						text[2]=txtA.getText();
						text[3]=txtD.getText();
						
						for(String cmd: text)
							if(cmd.length()>1 || cmd.length()==0)
								return;
						
						for(int a=0; a<text.length; a++)
							for(int b=a+1; b<text.length; b++)
								if(text[a]==text[b])
									return;
							
						commands[0]=txtW.getText().toCharArray()[0];
						commands[1]=txtS.getText().toCharArray()[0];
						commands[2]=txtA.getText().toCharArray()[0];
						commands[3]=txtD.getText().toCharArray()[0];
						commands[4]=txtV.getText().toCharArray()[0];
						
						setVisible(false);
					}
				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public char[] getCmds() {
		return commands.clone();
	}

}
