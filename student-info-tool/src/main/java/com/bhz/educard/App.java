package com.bhz.educard;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.bhz.utils.ConsoleTextArea;
import com.bhz.utils.UIFont;

public class App {
    public static JFrame frame = new JFrame();
    public static ConsoleTextArea monitor = new ConsoleTextArea();
    JPanel contentPane = null;
    JButton buttonStudentInfoTrans;
	JButton buttonStudentProcess;
	public App(){
		contentPane = new JPanel(new BorderLayout());
		frame.getContentPane().add(contentPane,BorderLayout.CENTER);
		frame.setTitle("教育卡学生信息管理系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(createMenuBar());
		
		frame.setSize(666, 700);
		frame.setLocationRelativeTo(null);
//		frame.pack();
		frame.setVisible(true);
		KeyEventManager.getInstance().addShortcutListener(new KeyEventManager.ShortcutListener() {
			public void handle() {
				TipDialog.showMessage(ResultMessage.OP_WARNING, "本程序由\"北京华大智宝电子系统有限公司\"友情提供。\n部门：系统应用事业部\n作者：姚海");
			}
		}, KeyEvent.VK_ALT, KeyEvent.VK_Y);
		buttonStudentProcess.doClick();
	}
	public static void main(String[] args){
		UIFont.InitGlobalFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
//		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		String lookAndFeel = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		try{
			UIManager.setLookAndFeel(lookAndFeel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				new App();
			}
		});
		ImageIcon titleIcon = new ImageIcon(App.class.getClassLoader().getResource("images/bhz-logo.gif"));
		frame.setIconImage(titleIcon.getImage());
	}
	
	private void changePanel(JPanel parent,JPanel panel){
		parent.removeAll();
		parent.add(panel,BorderLayout.CENTER);
		parent.validate();
		frame.getContentPane().add(parent);
		frame.getContentPane().repaint();
		monitor.setText("");
	}
	
	protected JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		JToolBar toolBar = new JToolBar();
		JMenu sysMenu = new JMenu("系统");
		sysMenu.setMnemonic('s');
		JMenuItem item = new JMenuItem("关于...");
		item.setMnemonic('a');
		item.setAccelerator(KeyStroke.getKeyStroke(
			    KeyEvent.VK_A, InputEvent.ALT_MASK));
		ActionListener lst = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipDialog.showMessage(ResultMessage.OP_SUCCESS, "教育卡学生信息管理系统工具 v1.0");
			}	
		};
		item.addActionListener(lst);
		
		sysMenu.add(item);
		sysMenu.addSeparator();
		
		item = new JMenuItem("退出");
		item.setMnemonic('x');
		lst = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  System.exit(0);
			  }
		};
		item.addActionListener(lst);
		
		//
		ImageIcon iconCor = new ImageIcon(App.class.getClassLoader().getResource("images/process.png"));
		Action studentInfoTranslateAction = new AbstractAction("StudentInfoTrans", iconCor) {
			private static final long serialVersionUID = -7320591999597024140L;
			public void actionPerformed(ActionEvent e) {
				TransInfoPanel tip = new TransInfoPanel();
				changePanel(contentPane, tip.createTransPanel());
			}
		};
		//
		ImageIcon iconGrade = new ImageIcon(App.class.getClassLoader().getResource("images/person.png"));
		Action studentInfoProcess = new AbstractAction("Grade", iconGrade) {
			private static final long serialVersionUID = 4246366682734650103L;

			public void actionPerformed(ActionEvent e) {
				MergeFilePanel mfp = new MergeFilePanel();
				changePanel(contentPane,mfp.createMergePanel());
			}
		};

		sysMenu.add(item);
		menuBar.add(sysMenu);
		
		buttonStudentInfoTrans = new SmallButton(studentInfoTranslateAction,"学生信息转换");
		buttonStudentProcess = new SmallButton(studentInfoProcess,"处理学生信息");
		toolBar.add(buttonStudentProcess);
		toolBar.addSeparator();
		toolBar.add(buttonStudentInfoTrans);
		
		frame.getContentPane().add(toolBar,BorderLayout.NORTH);
		return menuBar;
	}
	
}
