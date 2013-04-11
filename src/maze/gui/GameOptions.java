package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameOptions extends JDialog {

	private JPanel contentPanel = new JPanel();
	JComboBox<String> comboBox;
	private String gameoptions="P P 1";
	private JSlider mazeslider, dragonslider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GameOptions dialog = new GameOptions();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOptions() {
		return gameoptions;
	}
	/**
	 * Create the dialog.
	 */
	public GameOptions() {
		setBounds(100, 100, 267, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JToggleButton tglbtnDefaultMaze = new JToggleButton("Default maze");
		tglbtnDefaultMaze.setSelected(true);
		tglbtnDefaultMaze.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tglbtnDefaultMaze.getModel().isSelected()) {
					mazeslider.setEnabled(false);
					dragonslider.setEnabled(false);
				} else {
					mazeslider.setEnabled(true);
					dragonslider.setEnabled(true);
				}
			}
		});

		tglbtnDefaultMaze.setBounds(10, 11, 95, 23);
		contentPanel.add(tglbtnDefaultMaze);

		String []options={"Stopped","Asleep","Random & Asleep"};
		comboBox = new JComboBox<String>(options);
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(115, 12, 95, 23);

		contentPanel.add(comboBox);

		dragonslider = new JSlider();
		dragonslider.setEnabled(false);
		dragonslider.setValue(1);
		dragonslider.setMinimum(1);
		dragonslider.setMaximum(3);
		dragonslider.setBounds(10, 65, 200, 26);
		contentPanel.add(dragonslider);

		mazeslider = new JSlider();
		mazeslider.setEnabled(false);
		mazeslider.setValue(10);
		mazeslider.setMaximum(20);
		mazeslider.setMinimum(8);
		mazeslider.setBounds(10, 122, 200, 26);
		contentPanel.add(mazeslider);

		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(10, 48, 142, 14);
		contentPanel.add(lblNumberOfDragons);

		JLabel lblMazeSize = new JLabel("Maze size");
		lblMazeSize.setBounds(10, 102, 83, 14);
		contentPanel.add(lblMazeSize);


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");

				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String o;
						if(tglbtnDefaultMaze.getModel().isSelected()) {
							o="P ";

							switch(comboBox.getSelectedIndex()) {
							case 0:
								o+="A 1";
								break;
							case 1:
								o+="P 1";
								break;
							case 2:
								o+="D 1";
								break;
							}
						}
						else {
							o="A ";
							o+=mazeslider.getValue();
							switch(comboBox.getSelectedIndex()) {
							case 0:
								o+=" A ";
								break;
							case 1:
								o+=" P ";
								break;
							case 2:
								o+=" D ";
								break;
							}
							o+=dragonslider.getValue();
						}


						gameoptions=o;
						setVisible(false);
					}
				});
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
}
