/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yawlfoundation.yawl.resourcing.codelets;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;
import static org.yawlfoundation.yawl.resourcing.codelets.AbstractCodelet.XSD_NAMESPACE;

/**
 *
 * @author Sasha
 */
public class CodeletInfografica extends AbstractCodelet{

    public CodeletInfografica(){
        super();
        setDescription("Permette di visualizzare 1'immagine. La variabile di task contenente il nome del file si deve chiamare Immagine");
    }
    
    @Override
    public Element execute(Element elmnt, List<YParameter> inParams, List<YParameter> outParams) throws CodeletExecutionException {
        setInputs(elmnt, inParams, outParams);
        
        
        JFrame frame= new JFrame();
        JPanel p1= new JPanel();
        Container contentPane = frame.getContentPane();
        contentPane.add(p1);
        JLabel lab= new JLabel();
        frame.setSize(800,800);
        p1.setSize(800,800);
        
        String nomeFile=(String) getParameterValue("Immagine");
        
        lab.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/org/yawlfoundation/yawl/resourcing/codelets/"+nomeFile)));
        p1.add(lab);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        
        
        return getOutputData();
    }

        
    public List<YParameter> getRequiredParams(){
        List<YParameter> params = new ArrayList<>();
        
        YParameter param = new YParameter(null, YParameter._INPUT_PARAM_TYPE);
        param.setDataTypeAndName("string", "Immagine", XSD_NAMESPACE);
        param.setDocumentation("Input variable, called Immagine, string type");
        params.add(param);
        
        return params;
    }
    
}
