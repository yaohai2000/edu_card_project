package com.bhz.educard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.bhz.educard.student.info.service.LogExportService;
import com.bhz.educard.student.info.service.LogExportServiceImpl;
import com.bhz.educard.student.info.service.StudentInfoService;
import com.bhz.educard.student.info.service.StudentInfoServiceImpl;
import com.bhz.utils.CSVFileFilter;

public class TransInfoPanel extends JPanel {
	private static final long serialVersionUID = -1443538416209197868L;
	
	public JPanel createTransPanel(){
		JPanel panel = new JPanel(new BorderLayout());
		JPanel paramPanel = new JPanel(new GridLayout(3, 1));
		JScrollPane jp = new JScrollPane(App.monitor);
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel inputLabel = new JLabel("原学生信息文件（CSV）：",JLabel.RIGHT);
		inputLabel.setPreferredSize(new Dimension(145, 25));
		JTextField inputPathField = new JTextField("",30);
		JButton inputButton = new JButton("请选择...");
		inputButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
				fc.setMultiSelectionEnabled(false);
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setFileHidingEnabled(true);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(new CSVFileFilter("csv"));
				int returnValue = fc.showOpenDialog(App.frame);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selFile = fc.getSelectedFile();
					inputPathField.setText(selFile.getAbsolutePath());
				}
			}
			
		});
		
		inputPanel.add(inputLabel);
		inputPanel.add(inputPathField);
		inputPanel.add(inputButton);
		paramPanel.add(inputPanel);
		
		JLabel outputLabel = new JLabel("转换后文件保存目录：",JLabel.RIGHT);
		outputLabel.setPreferredSize(new Dimension(145, 25));
		JTextField outputPathField = new JTextField("",30);
		JButton outputButton = new JButton("请选择...");
		outputButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			      int intRetVal = fc.showOpenDialog(App.frame);
			      if( intRetVal == JFileChooser.APPROVE_OPTION){
			        outputPathField.setText(fc.getSelectedFile().getPath());
			    } 
			}
			
		});
		
		outputPanel.add(outputLabel);
		outputPanel.add(outputPathField);
		outputPanel.add(outputButton);
		paramPanel.add(outputPanel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,45,5));
		JButton okButton = new JButton("开始转换");
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputPathField.getText().trim().equals("")){
					TipDialog.showMessage(ResultMessage.OP_ERROR, "请指定\"原学生信息文件\"！");
					return;
				}
				if(outputPathField.getText().trim().equals("")){
					TipDialog.showMessage(ResultMessage.OP_ERROR, "请指定\"转换后文件保存目录\"！");
					return;
				}
				App.monitor.setText("");
				StudentInfoService sis = new StudentInfoServiceImpl();
				String filePath = inputPathField.getText();
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator) +1, filePath.lastIndexOf("."));
				sis.translateStudentInfo(inputPathField.getText(), outputPathField.getText() + File.separator + fileName + "（转换）.csv");
				int sel = JOptionPane.showConfirmDialog(
						App.frame,
		                "转换完成，确定打开转换后文件的目录？",
		                "转换完成",
		                JOptionPane.YES_NO_OPTION);
				if(sel==JOptionPane.YES_OPTION){
					try {
						java.awt.Desktop.getDesktop().open(new java.io.File(outputPathField.getText()));
					} catch (IOException e1) {
						TipDialog.showMessage(ResultMessage.OP_ERROR, e1.getMessage());
					}
				}
			}
			
		});
		buttonPanel.add(okButton);
		paramPanel.add(buttonPanel);
		
		paramPanel.setBorder(BorderFactory.createTitledBorder("设定文件路径"));
		
		panel.add(paramPanel,BorderLayout.NORTH);
		jp.setBorder(BorderFactory.createTitledBorder("转换日志"));
		panel.add(jp,BorderLayout.CENTER);
		
		JPanel exportLogPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton exportButton = new JButton("导出日志...");
		exportButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
				fc.setApproveButtonText("保存日志");
				String suffix = "log";
				fc.setMultiSelectionEnabled(false);
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setFileHidingEnabled(true);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(new CSVFileFilter("log"));
				int returnValue = fc.showOpenDialog(App.frame);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File chFile = fc.getSelectedFile();
					File savedFile = new File(chFile.getAbsoluteFile() + "." + suffix);
					LogExportService les = new LogExportServiceImpl();
					int result = les.export(App.monitor.getText(), savedFile);
					if(result==ResultMessage.OP_SUCCESS){
						TipDialog.showMessage(ResultMessage.OP_SUCCESS, "导出成功");
					}else{
						TipDialog.showMessage(ResultMessage.OP_ERROR, "导出失败");
					}
				}
			}
			
		});
		exportLogPanel.add(exportButton);
		panel.add(exportLogPanel,BorderLayout.SOUTH);
		return panel;
		
	}
}
