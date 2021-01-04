/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yawlfoundation.yawl.resourcing.codelets;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;

/**
 *
 * @author Sasha
 */
public class CalcolareEta extends AbstractCodelet{

    public CalcolareEta(){
        super();
        setDescription("Codelet per calcolo dell'età");
    }
   
    
    public Element execute(Element elmnt, List<YParameter> inParams, List<YParameter> outParams)  {
        
        setInputs(elmnt, inParams, outParams);
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String name = null;  
        String[] split = null;
        try {
            name = formatter.format(getParameterValue("dN"));
            split = name.split("-");
        } catch (CodeletExecutionException ex) {
            System.out.println(split[0]);
        }
        //String name = formatter.format(s);  
        
        int annoNascita = Integer.parseInt(split[0]);
        
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);
        int eta = now.get(Calendar.YEAR) - annoNascita;
        
        setParameterValue("eta",String.valueOf(eta));
        
        
        return getOutputData();
        
        
    }

    @Override
    public List<YParameter> getRequiredParams(){
        List<YParameter> params = new ArrayList<>();
        
        YParameter param = new YParameter(null, YParameter._INPUT_PARAM_TYPE);
        param.setDataTypeAndName("date", "dN", XSD_NAMESPACE);
        param.setDocumentation("Input variable, called dN, date type");
        params.add(param);
        
        param = new YParameter(null, YParameter._OUTPUT_PARAM_TYPE);
        param.setDataTypeAndName("int", "eta", XSD_NAMESPACE);
        param.setDocumentation("Output variable, called eta, int type");
        params.add(param);
        
        return params;
        
    }
    
//    
//    public static void main(String [] args) throws CodeletExecutionException, ParseException{
//        CalcolareEta cc= new CalcolareEta();
//        String sDate1="31-12-1998";  
//        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
//
//        
//        
//        System.out.println(date);
//        cc.execute(null, null, null,date);
//    }

    
}
