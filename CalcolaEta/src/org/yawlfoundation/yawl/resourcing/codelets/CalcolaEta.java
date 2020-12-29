package org.yawlfoundation.yawl.resourcing.codelets;

import java.awt.Container;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;


public class CalcolaEta extends AbstractCodelet {
    
    

    public CalcolaEta() {
        super();
        setDescription("Codelet per il calcolo dell'età a partire dalla data di nascita in formato dd/mm/yyyy");
    }

    /**
     *
     * @param inData
     * @param inParams
     * @param outParams
     * @return
     * @throws CodeletExecutionException
     */
    public Element execute(Element inData, List<YParameter> inParams, List<YParameter> outParams) throws CodeletExecutionException {

        setInputs(inData, inParams, outParams);
        
        int eta;
        Date dataNascita = new Date();    
        Calendar birthDay;
        Calendar now;
        int annoNascita;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            
            
            
           String a = ((String) getParameterValue("dataNascita")); 
           // String a = ("1920/11/11"); 
           String [] anno = a.split("/");
           annoNascita = Integer.parseInt(anno[0]);
            //System.out.println("annoNascita = " + annoNascita);
//            String data = ((String) getParameterValue("dataNascita"));
            //dataNascita = sdf.parse("2008/01/25"); 
           
             
        } catch (ClassCastException cce) {
            throw new CodeletExecutionException("Exception casting input" + " values to int types.");
        } 
//        catch (ParseException ex) {
////            Logger.getLogger(CalcolaEta.class.getName()).log(Level.SEVERE, null, ex);
////        }
        
        
        
//            birthDay = Calendar.getInstance();
//            birthDay.setTimeInMillis(dataNascita.getTime());
            
            //Calendario currente
            long currentTime = System.currentTimeMillis();
            now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);
            eta = now.get(Calendar.YEAR) - annoNascita;
       
            
//set the value for the output parameter
        

//***************************** ATTENZIONE è COMMENTATO******
      setParameterValue("eta", String.valueOf(eta));
        
        
        System.out.println("eta = " + eta);
        
        
        

        return getOutputData();
    }

    @Override
    public List<YParameter> getRequiredParams() {
        List<YParameter> params = new ArrayList<>();

        YParameter param = new YParameter(null, YParameter._INPUT_PARAM_TYPE);
        param.setDataTypeAndName("Date", "dataNascita", XSD_NAMESPACE);
        param.setDocumentation("Data di nascita");
        params.add(param);


        param = new YParameter(null, YParameter._OUTPUT_PARAM_TYPE);
        param.setDataTypeAndName("int", "eta", XSD_NAMESPACE);
        param.setDocumentation("Eta del soggetto");
        params.add(param);
        return params;
    }
    
    
//    public static void main (String [] args) throws CodeletExecutionException{
//        CalcolaEta ce = new CalcolaEta();
//        ce.execute(null, null, null);
//    }

    
     

}

