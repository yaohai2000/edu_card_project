package com.bhz.educard;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class SmallButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	protected Border m_raised = new SoftBevelBorder(BevelBorder.RAISED);
	protected Border m_lowered = new SoftBevelBorder(BevelBorder.LOWERED);
	protected Border m_inactive = new EmptyBorder(3, 3, 3, 3);
	protected Border m_border = m_inactive;
	protected Insets m_ins = new Insets(4, 4, 4, 4);

	public SmallButton(Action act, String tip) {
		super((Icon) act.getValue(Action.SMALL_ICON));
		setBorder(m_inactive);
		setMargin(m_ins);
		setToolTipText(tip);
		setRequestFocusEnabled(false);
		addActionListener(act);
		addMouseListener(this);
	}

	public float getAlignmentY() {
		return 0.5f;
	}

	public Border getBorder() {
		return m_border;
	}

	public Insets getInsets() {
		return m_ins;
	}

	public void mousePressed(MouseEvent e) {
		m_border = m_lowered;
		setBorder(m_lowered);
	}

	public void mouseReleased(MouseEvent e) {
		m_border = m_inactive;
		setBorder(m_inactive);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		m_border = m_raised;
		setBorder(m_raised);
	}

	public void mouseExited(MouseEvent e) {
		m_border = m_inactive;
		setBorder(m_inactive);
	}
}
