/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Color;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author caroR
 */
public class RoundedBorder extends AbstractBorder{
    
    private final int radius;
    private final Color color;
    private final int thickness;
    
    public RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }
    
     @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x + thickness/2, y + thickness/2,
                         width - thickness, height - thickness, radius, radius);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int pad = thickness + 4;
        return new Insets(pad, pad, pad, pad);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets i = getBorderInsets(c);
        insets.top = i.top; insets.left = i.left; insets.bottom = i.bottom; insets.right = i.right;
        return insets;
    }
}
