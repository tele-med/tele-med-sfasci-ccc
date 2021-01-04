/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yawlfoundation.yawl.resourcing.codelets;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;

/**
 *
 * @author Sasha
 */
public class CodeletWebSite extends AbstractCodelet{
    
    public CodeletWebSite(){
        super();
        setDescription("Codelet usate per aprire il sito web degli specialisti cefalee.");
    }

    @Override
    public Element execute(Element elmnt, List<YParameter> list, List<YParameter> list1) throws CodeletExecutionException {
        
        JFrame frame = new JFrame();
        frame.setTitle("RICERCA SPECIALISTA CEFALEE");
        JPanel p= new JPanel();
        frame.add(p);
        p.setLayout(new BorderLayout());
        JTextArea l= new JTextArea("Clicca nel bottone sottostante per visitare il sito SISC"+"\n e cercare uno specialista per il tuo paziente");
        l.setEditable(false);
        p.add(l,BorderLayout.NORTH);
        JButton b= new JButton("SISC-Specialisti");
        p.add(b,BorderLayout.CENTER);
        
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                URI uri = null;
  
                try {
                    uri = new URI("https://www.sisc.it/ita/centri-cefalee-in-italia/");
                } catch (URISyntaxException ex) {
                    Logger.getLogger(CodeletWebSite.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Desktop.getDesktop().browse(uri);
                } catch (IOException ex) {
                    Logger.getLogger(CodeletWebSite.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.exit(0);

                    }
                });

    
        return getOutputData();
    
    } 

}