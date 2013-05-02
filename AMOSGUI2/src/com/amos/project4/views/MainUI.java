package com.amos.project4.views;
/*
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTable;

import com.amos.project4.controllers.ClientController;


public class MainUI {

	private JFrame frame;
	private final Action action = new SwingAction();
	private JTable tclients;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lili\\Desktop\\Facebook.ico"));
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// set the system look and feel
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		initMenus();
		
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JSplitPane horizontalSplitPane = new JSplitPane();
		springLayout.putConstraint(SpringLayout.NORTH, horizontalSplitPane, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, horizontalSplitPane, 0, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(horizontalSplitPane);
		
		// The Status bar
		JPanel StatusbarPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, horizontalSplitPane, 0, SpringLayout.NORTH, StatusbarPanel);
		springLayout.putConstraint(SpringLayout.EAST, horizontalSplitPane, 0, SpringLayout.EAST, StatusbarPanel);
		
		springLayout.putConstraint(SpringLayout.WEST, StatusbarPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, StatusbarPanel, 0, SpringLayout.EAST, frame.getContentPane());
		StatusbarPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, StatusbarPanel, -20, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, StatusbarPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(StatusbarPanel);
		SpringLayout sl_StatusbarPanel = new SpringLayout();
		StatusbarPanel.setLayout(sl_StatusbarPanel);
		
		JLabel lbl_statusBar = new JLabel("Loading ...");
		sl_StatusbarPanel.putConstraint(SpringLayout.SOUTH, lbl_statusBar, 0, SpringLayout.SOUTH, StatusbarPanel);
		sl_StatusbarPanel.putConstraint(SpringLayout.WEST, lbl_statusBar, 5, SpringLayout.WEST, StatusbarPanel);
		StatusbarPanel.add(lbl_statusBar);
		
		// The left Panel
		//JPanel panel_left = new JPanel();		
		//horizontalSplitPane.setLeftComponent(panel_left);
		
		// The right Panel
		JPanel panel_right = new JPanel();
		
		horizontalSplitPane.setRightComponent(panel_right);
		SpringLayout sl_panel_right = new SpringLayout();
		panel_right.setLayout(sl_panel_right);
		
		JSplitPane verticalSplitPane = new JSplitPane();
		sl_panel_right.putConstraint(SpringLayout.NORTH, verticalSplitPane, 0, SpringLayout.NORTH, panel_right);
		sl_panel_right.putConstraint(SpringLayout.WEST, verticalSplitPane, 0, SpringLayout.WEST, panel_right);
		sl_panel_right.putConstraint(SpringLayout.SOUTH, verticalSplitPane, 0, SpringLayout.SOUTH, panel_right);
		sl_panel_right.putConstraint(SpringLayout.EAST, verticalSplitPane, 0, SpringLayout.EAST, panel_right);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_right.add(verticalSplitPane);		
		
		// The right Top panel
		verticalSplitPane.setLeftComponent(initRightTopPanel());
		
		
		// The right botom panel
		verticalSplitPane.setRightComponent(initRightBottomPanel());
		
		// The left Panel		
		JScrollPane left_scroll_pane = initLeftpanel();
		horizontalSplitPane.setLeftComponent(left_scroll_pane);
		
	}
	
	private JScrollPane initLeftpanel(){
		JScrollPane left_scroll_pane = new JScrollPane();
		left_scroll_pane.setPreferredSize(new Dimension(150,0));
		
		JPanel panel_left = new JPanel();
		panel_left.setPreferredSize(new Dimension(150,0));
		left_scroll_pane.setViewportView(panel_left);
		panel_left.setLayout(new BorderLayout(0, 0));
		
		JTree leftMenuTree = initLeftmenuTree();
		panel_left.add(leftMenuTree);
		
		return left_scroll_pane;
	}
	
	private JTree initLeftmenuTree(){
				
		// Initialize the settings short cut
		Vector<String> settingsMenu_vec = new TreeNodeVector<String>("Settings", new String[] { "General Settings"});
		
		// Initialize the user menu short cut
		Vector<String> usersMenu_vec = new TreeNodeVector<String>("Users", new String[] { "Profile", "Change password"});
		
		// Initialize the Social media menus short cut
		Vector<String> socialsMenu_vec = new TreeNodeVector<String>("Social", new String[] { "Search account", "Listen","React" });
		
		// Initialize the Social media menus short cut
		Vector<String> exitsMenu_vec = new TreeNodeVector<String>("Exit", new String[] { "Logout", "Exit"});
		
		// Initialize the categories
		Object rootNodes[] = new Object[]{socialsMenu_vec,usersMenu_vec,settingsMenu_vec,exitsMenu_vec};
		
		Vector<Object> root_vec = new TreeNodeVector<Object>("Menu Root", rootNodes);
		JTree leftMenuTree = new JTree(root_vec);
		leftMenuTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// Put horizontal line to separe the categories
		UIManager.put("Tree.line", Color.GREEN);
		leftMenuTree.putClientProperty("JTree.lineStyle", "Horizontal");
		
		// expand all
		int row = 0;
	    while (row < leftMenuTree.getRowCount()) {
	    	leftMenuTree.expandRow(row);
	      row++;
	    }
	    
		return leftMenuTree;
		
	}
	
	private JPanel initRightTopPanel(){
		JPanel panel_right_top = new JPanel();
		panel_right_top.setPreferredSize(new Dimension(10, 300));
		
		SpringLayout sl_panel_right_top = new SpringLayout();
		panel_right_top.setLayout(sl_panel_right_top);
		
		
		
		JButton btnCheckSocialMedia = new JButton("Check Social Media");
		sl_panel_right_top.putConstraint(SpringLayout.NORTH, btnCheckSocialMedia, 10, SpringLayout.NORTH, panel_right_top);
		sl_panel_right_top.putConstraint(SpringLayout.EAST, btnCheckSocialMedia, -5, SpringLayout.EAST, panel_right_top);
		btnCheckSocialMedia.setPreferredSize(new Dimension(150, 25));
		panel_right_top.add(btnCheckSocialMedia);
		
		JButton btnFilter = new JButton("Filter");
		sl_panel_right_top.putConstraint(SpringLayout.NORTH, btnFilter, 0, SpringLayout.NORTH, btnCheckSocialMedia);
		sl_panel_right_top.putConstraint(SpringLayout.EAST, btnFilter, -5, SpringLayout.WEST, btnCheckSocialMedia);
		panel_right_top.add(btnFilter);
		btnFilter.setPreferredSize(new Dimension(70, 25));
		
		
		JButton btnSearch = new JButton("Search");
		sl_panel_right_top.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, btnFilter);
		sl_panel_right_top.putConstraint(SpringLayout.EAST, btnSearch, -5, SpringLayout.WEST, btnFilter);
		panel_right_top.add(btnSearch);
		btnSearch.setPreferredSize(new Dimension(75, 25));
		
		JTextField search_textField = new JTextField();
		sl_panel_right_top.putConstraint(SpringLayout.WEST, search_textField, 5, SpringLayout.WEST, panel_right_top);
		sl_panel_right_top.putConstraint(SpringLayout.NORTH, search_textField, 0, SpringLayout.NORTH, btnSearch);
		sl_panel_right_top.putConstraint(SpringLayout.EAST, search_textField, -5, SpringLayout.WEST, btnSearch);
		panel_right_top.add(search_textField);
		search_textField.setColumns(20);		
		
		
		JPanel client_result_panel = new JPanel();
		client_result_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_panel_right_top.putConstraint(SpringLayout.NORTH, client_result_panel, 10, SpringLayout.SOUTH, btnCheckSocialMedia);
		sl_panel_right_top.putConstraint(SpringLayout.WEST, client_result_panel, 0, SpringLayout.WEST, panel_right_top);
		sl_panel_right_top.putConstraint(SpringLayout.SOUTH, client_result_panel, -10, SpringLayout.SOUTH, panel_right_top);
		sl_panel_right_top.putConstraint(SpringLayout.EAST, client_result_panel, 0, SpringLayout.EAST, btnCheckSocialMedia);
		panel_right_top.add(client_result_panel);
		client_result_panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		client_result_panel.add(scrollPane);
		
		tclients = new JTable(new ClientTableModel(new ClientController()));
		scrollPane.setViewportView(tclients);
		
		return panel_right_top;
	}
		
	private JPanel initRightBottomPanel(){
		JPanel panel_right_bottom = new JPanel();		
		panel_right_bottom.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane mediaPanes = new JTabbedPane(JTabbedPane.TOP);
		panel_right_bottom.add(mediaPanes, BorderLayout.CENTER);
		
		JTabbedPane facebookPane = new JTabbedPane(JTabbedPane.TOP);
		mediaPanes.addTab("Facebook", new ImageIcon("C:\\Users\\Lili\\Desktop\\Facebook.ico"), facebookPane, null);
		mediaPanes.setEnabledAt(0, true);
		
		JTabbedPane xingPane = new JTabbedPane(JTabbedPane.TOP);
		mediaPanes.addTab("Xing", null, xingPane, null);
		
		JTabbedPane linkedInPane = new JTabbedPane(JTabbedPane.TOP);
		mediaPanes.addTab("LinkedIn", null, linkedInPane, null);
		
		JTabbedPane twitterPane = new JTabbedPane(JTabbedPane.TOP);
		mediaPanes.addTab("Twitter", null, twitterPane, null);
		
		return panel_right_bottom;
	}
	private void initMenus(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmUpdateDatabase = new JMenuItem("Update Database");
		mnFile.add(mntmUpdateDatabase);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAction(action);
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}

