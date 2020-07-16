/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbridging.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author the-s
 */
public class NetworkListRenderer extends DefaultListCellRenderer {

    Font font = new Font("helvitica", Font.BOLD, 11);
    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/imgs/ic_monitor-small.png"));
    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setIcon(imageIcon);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        label.setForeground(Color.white);
        label.setHorizontalTextPosition(RIGHT);
        label.setVerticalTextPosition(TOP);
        return label;
    }
}
