package com.bhz.utils;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class UIFont {
    /**
 * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
 */
	public static void InitGlobalFont(Font font) {
	  FontUIResource fontRes = new FontUIResource(font);
	  for (Enumeration<Object> keys = UIManager.getDefaults().keys();
	  keys.hasMoreElements(); ) {
	  Object key = keys.nextElement();
	  Object value = UIManager.get(key);
	  if (value instanceof FontUIResource) {
	  UIManager.put(key, fontRes);
	  }
	  }
}  
}
