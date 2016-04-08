package com.bhz.educard;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class TipDialog {
	
	private static JDialog processDialog = null;
	
	public static void showMessage(int messageType,String title,String content){
		if(messageType == ResultMessage.OP_SUCCESS){
			JOptionPane.showMessageDialog(App.frame, content,title==null?"消息":title,JOptionPane.INFORMATION_MESSAGE);
		}
		if(messageType == ResultMessage.OP_ERROR){
			JOptionPane.showMessageDialog(App.frame, content,title==null?"错误":title,JOptionPane.ERROR_MESSAGE);
		}
		if(messageType == ResultMessage.OP_WARNING){
			JOptionPane.showMessageDialog(App.frame, content,title==null?"消息":title,JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void showMessage(int messageType,String content){
		showMessage(messageType,null,content);
	}

	public static void showProcessDialog(String content){
		final JOptionPane optionPane = new JOptionPane(content,JOptionPane.INFORMATION_MESSAGE);
		processDialog = new JDialog(App.frame, "正在处理中，请稍候 ... ",false);
		processDialog.setContentPane(optionPane);
		processDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//		processDialog.pack();
		processDialog.setLocationRelativeTo(App.frame);
		processDialog.setVisible(true);
	}
	
	public static void closeProcessDialog(){
		if(processDialog == null) return;
		processDialog.setVisible(false);
	}
}
