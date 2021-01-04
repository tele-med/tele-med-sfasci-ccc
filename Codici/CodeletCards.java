/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yawlfoundation.yawl.resourcing.codelets;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;
import org.yawlfoundation.yawl.worklet.rdrutil.RdrConditionFunctions;


/**
 *
 * @author Sasha
 */



public class CodeletCards extends AbstractCodelet{
    public CodeletCards(){
        super();
        setDescription("Codelet usata per visualizzare le info"+
                       " relative alle cause di preoccupazione");   
    }
    

    @Override
    public Element execute(Element inData, List<YParameter> inParams,
        List<YParameter> outParams) throws CodeletExecutionException {

        setInputs(inData, inParams, outParams);
        
        
        JFrame frame= new JFrame();
        frame.setTitle("CAUSE DI PREOCCUPAZIONE PRESENTI NEL PAZIENTE");
        JPanel p1= new JPanel();
        Container contentPane = frame.getContentPane();
        contentPane.add(p1);
        JLabel lab= new JLabel();
        
        JTabbedPane tab= new JTabbedPane();
        tab.setPreferredSize(new Dimension(700, 700));
        p1.add(tab);
        
        JPanel p = null;
        boolean cicla=true;
        String causePreoccupazione=(String) getParameterValue("CauseDiPreoccupazione");
        String[] array = causePreoccupazione.split("\n");
        
        String cp= array[9].split(">")[1].split("<")[0];
        if(cp.equalsIgnoreCase("true")){
            lab.setText("NON CI SONO CAUSE DI PREOCCUPAZIONE");
            p.add(lab);
            cicla = false;
        }
        
        int idx;
        
        if (cicla==true){
            for (int i=1;i<=8;i++){
                cp= array[i].split(">")[1].split("<")[0];
                if (cp.equalsIgnoreCase("true")){
                    p=new JPanel();
                    idx=i;
                    JLabel lab1= new JLabel();
                    lab1.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/org/yawlfoundation/yawl/resourcing/codelets/"+idx+".jpg")));
                    p.add(lab1);
             
                    p.revalidate();
                    tab.addTab("tab"+idx+"", p);
                }
            }
            frame.setSize(new Dimension(800,800));
            p.setSize(new Dimension(800, 800));
        }else{
            frame.setSize(800,800);
            p.setSize(800,800);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        
        return getOutputData();
        
    }
    
    
}
    



 

