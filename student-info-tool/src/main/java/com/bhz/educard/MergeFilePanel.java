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
import com.bhz.educard.student.info.service.MergeDataService;
import com.bhz.educard.student.info.service.MergeDataServiceImpl;
import com.bhz.utils.CSVFileFilter;

public class MergeFilePanel extends JPanel {
	private static final long serialVersionUID = -1443538416209197868L;
	
	public JPanel createMergePanel(){
		JPanel panel = new JPanel(new BorderLayout());
		JPanel paramPanel = new JPanel(new GridLayout(3, 1));
		JScrollPane jp = new JScrollPane(App.monitor);
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel inputLabel = new JLabel("原始数据目录：",JLabel.RIGHT);
		inputLabel.setPreferredSize(new Dimension(100, 30));
		JTextField inputPathField = new JTextField("");
		inputPathField.setPreferredSize(new Dimension(400, 28));
		JButton inputButton = new JButton("请选择...");
		inputButton.setPreferredSize(new Dimension(85, 28));
		inputButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			      int intRetVal = fc.showOpenDialog(App.frame);
			      if( intRetVal == JFileChooser.APPROVE_OPTION){
			        inputPathField.setText(fc.getSelectedFile().getPath());
			    }
			}
			
		});
		
		inputPanel.add(inputLabel);
		inputPanel.add(inputPathField);
		inputPanel.add(inputButton);
		paramPanel.add(inputPanel);
		
		JLabel outputLabel = new JLabel("转换后保存目录：",JLabel.RIGHT);
		outputLabel.setPreferredSize(new Dimension(100, 25));
		JTextField outputPathField = new JTextField("");
		outputPathField.setPreferredSize(new Dimension(400, 28));
		JButton outputButton = new JButton("请选择...");
		outputButton.setPreferredSize(new Dimension(85, 28));
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
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton okButton = new JButton("开始处理");
		okButton.setPreferredSize(new Dimension(85, 28));
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputPathField.getText().trim().equals("")){
					TipDialog.showMessage(ResultMessage.OP_ERROR, "请指定\"原始数据目录\"！");
					return;
				}
				if(outputPathField.getText().trim().equals("")){
					TipDialog.showMessage(ResultMessage.OP_ERROR, "请指定\"转换后保存目录\"！");
					return;
				}
				App.monitor.setText("");
				MergeDataService mds = new MergeDataServiceImpl();
				try {
					mds.merge(inputPathField.getText().trim(), outputPathField.getText().trim());
					int sel = JOptionPane.showConfirmDialog(
							App.frame,
			                "处理完成，确定打开处理后文件的目录？",
			                "处理完成",
			                JOptionPane.YES_NO_OPTION);
					if(sel==JOptionPane.YES_OPTION){
						try {
							java.awt.Desktop.getDesktop().open(new java.io.File(outputPathField.getText()));
						} catch (IOException e1) {
							TipDialog.showMessage(ResultMessage.OP_ERROR, e1.getMessage());
						}
					}
				} catch (Exception e2) {
					TipDialog.showMessage(ResultMessage.OP_ERROR, e2.getMessage());
				}
			}
			
		});
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension(495, 25));
		buttonPanel.add(placeHolder);
		buttonPanel.add(okButton);
		paramPanel.add(buttonPanel);
		
		paramPanel.setBorder(BorderFactory.createTitledBorder("设定文件路径"));
		
		panel.add(paramPanel,BorderLayout.NORTH);
		jp.setBorder(BorderFactory.createTitledBorder("处理日志"));
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
