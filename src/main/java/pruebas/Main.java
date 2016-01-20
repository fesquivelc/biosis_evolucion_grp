/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Francis
 */
public class Main {

    public static void main(String args[]) throws ParseException {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = f.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        MaskFormatter mf1 = new MaskFormatter("##/##/####");
        mf1.setPlaceholderCharacter('_');
        JFormattedTextField ftf1 = new JFormattedTextField(df);
        mf1.install(ftf1);
        content.add(ftf1);

        MaskFormatter mf2 = new MaskFormatter("(###) ###-####");
        JFormattedTextField ftf2 = new JFormattedTextField(mf2);
        content.add(ftf2);
        f.setSize(300, 100);
        f.setVisible(true);
    }
}
