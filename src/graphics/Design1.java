package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridBagLayout;
import javax.swing.JDesktopPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFormattedTextField;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuItem;
import handlers.*;

public class Design1 {

	private JFrame frame;
	private JTextArea txtrArea;
	private JFormattedTextField frmtdtxtfldConsole;
	private JTextArea txtrIntel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design1 window = new Design1();
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
	public Design1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFocusable(true);
		frame.setBackground(new Color(0, 0, 255));
		frame.setForeground(Color.BLACK);
		frame.setBounds(100, 100, 807, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		menuBar.setForeground(new Color(242, 249, 0));
		frame.setJMenuBar(menuBar);
		
		JMenu mnMain = new JMenu("Recon");
		mnMain.setBackground(new Color(0, 0, 51));
		mnMain.setForeground(new Color(0, 0, 0));
		menuBar.add(mnMain);
		
		JMenuItem mntmConfiguration = new JMenuItem("Scan Subnet");
		mnMain.add(mntmConfiguration);
		
		JMenuItem mntmListLocalInterfaces = new JMenuItem("List Local interfaces");
		mnMain.add(mntmListLocalInterfaces);
		
		JMenuItem mntmListActiveInterfaces = new JMenuItem("List active interfaces");
		mnMain.add(mntmListActiveInterfaces);
		
		JMenuItem mntmScanLan = new JMenuItem("Scan LAN");
		mnMain.add(mntmScanLan);
		
		JMenu mnSecondary = new JMenu("Actions");
		mnSecondary.setBackground(new Color(0, 0, 0));
		mnSecondary.setForeground(new Color(0, 0, 0));
		menuBar.add(mnSecondary);
		
		JMenu mnMusic = new JMenu("Music");
		mnMusic.setForeground(new Color(0, 0, 0));
		mnMusic.setBackground(new Color(0, 0, 0));
		menuBar.add(mnMusic);
		
		JMenuItem mntmPlaylist = new JMenuItem("Playlist");
		mnMusic.add(mntmPlaylist);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][grow,shrink 0][]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 32, 240));
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(new LineBorder(null, 2, true));
		panel.add(panel_1, "cell 0 0, grow");
		panel_1.setLayout(new MigLayout("", "[grow][][grow]", "[][shrink 0]"));
		
		txtrArea = new JTextArea();
		txtrArea.setEditable(false);
		txtrArea.setWrapStyleWord(true);
		txtrArea.setForeground(new Color(0, 254, 0));
		txtrArea.setBackground(new Color(0, 0, 0));
		txtrArea.setFont(new Font("Consolas", Font.BOLD, 15));
		panel_1.add(txtrArea, "cell 0 0,grow,alignx left,aligny top");
		txtrArea.setRows(18);
		txtrArea.setColumns(50);
		txtrArea.setText("Mainframe: ");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBorder(new LineBorder(new Color(0, 255, 102), 3, true));
		panel.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow][][grow]", "[][shrink 0][][]"));
		
		JLabel lblLocalArea = new JLabel("Local Network Options");
		lblLocalArea.setBackground(new Color(0, 0, 0));
		lblLocalArea.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblLocalArea.setForeground(new Color(242, 249, 0));
		panel_2.add(lblLocalArea, "cell 0 0,grow");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 51, 0));
		comboBox.setFont(new Font("Consolas", Font.BOLD, 14));
		comboBox.setBackground(new Color(0, 0, 0));
		panel_2.add(comboBox, "cell 0 1, gapleft 20, gapright 20, gaptop 8, grow, alignx center,aligny top");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"127.0.0.1"}));
		comboBox.setToolTipText("Hosts\r\n");
		
		JLabel lblPacketType = new JLabel("Packet Type");
		lblPacketType.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblPacketType.setBackground(new Color(0, 0, 0));
		lblPacketType.setForeground(new Color(242, 249, 0));
		panel_2.add(lblPacketType, "cell 0 2, gaptop 10");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Consolas", Font.BOLD, 13));
		comboBox_1.setBackground(new Color(0, 0, 0));
		comboBox_1.setForeground(new Color(255, 51, 0));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"TCP", "UDP", "ICMP"}));
		panel_2.add(comboBox_1, "cell 0 3,alignx center,gapx 20 20,aligny top,gapy 8,grow");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut, "cell 0 1,alignx left,aligny top");
		
		JPanel panel_4 = new JPanel();
		panel_4.setToolTipText("Various intel information");
		panel_4.setBackground(new Color(0, 0, 0));
		panel_4.setForeground(new Color(204, 0, 202));
		panel_4.setBorder(new LineBorder(null, 2, true));
		panel.add(panel_4, "cell 1 1 1 2,grow");
		panel_4.setLayout(new MigLayout("", "[grow]", "[grow,shrink 0][]"));
		
		txtrIntel = new JTextArea();
		txtrIntel.setEditable(false);
		panel_4.add(txtrIntel, "cell 0 0 1 2,grow");
		txtrIntel.setBackground(new Color(0, 0, 0));
		txtrIntel.setForeground(new Color(0, 254, 0));
		txtrIntel.setFont(new Font("Consolas", Font.BOLD, 12));
		txtrIntel.setText("Intel");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_3, "cell 0 2,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[]"));
		
		frmtdtxtfldConsole = new JFormattedTextField();
		panel_3.add(frmtdtxtfldConsole, "cell 0 0,grow");
		frmtdtxtfldConsole.setForeground(new Color(0, 243, 249));
		frmtdtxtfldConsole.setBackground(new Color(0, 0, 0));
		frmtdtxtfldConsole.setBorder(new LineBorder(null, 2, true));
		frmtdtxtfldConsole.setFont(new Font("Consolas", Font.PLAIN, 14));
		frmtdtxtfldConsole.setColumns(51);
		frmtdtxtfldConsole.setText("Console# ");
		
		//append listeners here
		this.frmtdtxtfldConsole.addActionListener(new CommandHandler(this));
		comboBox.addActionListener(new HostSelectionHandler());
		comboBox_1.addActionListener(new PacketSelectionHandler());
	}
	public void addTxt(String txt)
	{
		this.txtrArea.append(txt);
	}
	public void setText(String txt)
	{
		this.txtrArea.setText(txt);
	}
	public void setCons(String txt)
	{
		this.frmtdtxtfldConsole.setText(txt);
	}
}
