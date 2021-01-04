/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yawlfoundation.yawl.resourcing.codelets;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import org.yawlfoundation.yawl.elements.data.YParameter;

/**
 *
 * @author Sasha
 */
public class CodeletCausePreoccupazione extends AbstractCodelet{

    
    public CodeletCausePreoccupazione(){
        super();
        setDescription("Codelet per visualizzare le sole c d p presenti e generare il suggerimento della lg.");
    }
    
    @Override
    public Element execute(Element inData, List<YParameter> inParams, List<YParameter> outParams) throws CodeletExecutionException {
       setInputs(inData, inParams, outParams);
       
       String output="Le cause di preoccupazione presenti sono:\n";
       String Suggerimento="MalDiTestaPrimario";
       
       int count=0;
       String causePreoccupazione=(String) getParameterValue("CauseDiPreoccupazione");
       String[] array = causePreoccupazione.split("\n");
       String[] truefalse= new String[9];
       for (int i=1; i<=9;i++){
           String cp= array[i].split(">")[1].split("<")[0];
           truefalse[i-1]=cp;
           if (cp.equalsIgnoreCase("true")) count+=1;
       }
       
       if (truefalse[8].equalsIgnoreCase("true")){
           output=output+"nessuna\n";
       }else{
           if(truefalse[0].equalsIgnoreCase("true")) output=output+"Convulsioni\n";
           if(truefalse[1].equalsIgnoreCase("true")) output=output+"MalDiTestaAdInsorgenzaImprovvisa\n";
           if(truefalse[2].equalsIgnoreCase("true")) output=output+"MalDiTestaChePeggioraNelTempo\n";
           if(truefalse[3].equalsIgnoreCase("true")) output=output+"MalDiTestaOver50\n";
           if(truefalse[4].equalsIgnoreCase("true")) output=output+"MalDiTestaPeggioreDiSempre\n";
           if(truefalse[5].equalsIgnoreCase("true")) output=output+"MalDiTestaPostManovraDiValsava\n";
           if(truefalse[6].equalsIgnoreCase("true")) output=output+"SegniNeurologici\n";
           if(truefalse[7].equalsIgnoreCase("true")) output=output+"SintomiDisordineSistemico\n";
       }
       
       if (count>2 || truefalse[0].equalsIgnoreCase("true") ||truefalse[4].equalsIgnoreCase("true") || truefalse[6].equalsIgnoreCase("true")){
           Suggerimento="MalDiTestaSecondario";
       }
       
       setParameterValue("Suggerimento_LG", Suggerimento);
       
       setParameterValue("CauseDiPreoccupazionePresenti", output);
       return getOutputData();
       
    }
    
    
    public List<YParameter> getRequiredParams(){
        List<YParameter> params = new ArrayList<>();
        
        YParameter param = new YParameter(null, YParameter._INPUT_PARAM_TYPE);
        param.setDataTypeAndName("CauseDiPreoccupazione", "CauseDiPreoccupazione", XSD_NAMESPACE);
        param.setDocumentation("Input variable, called CauseDiPreoccupazione, CauseDiPreoccupazione type");
        params.add(param);
        
        
        param = new YParameter(null, YParameter._OUTPUT_PARAM_TYPE);
        param.setDataTypeAndName("string","Suggerimento_LG", XSD_NAMESPACE);
        param.setDocumentation("Output variable, Suggerimento_LG, String type");
        params.add(param);
        
        param = new YParameter(null, YParameter._OUTPUT_PARAM_TYPE);
        param.setDataTypeAndName("string","CauseDiPreoccupazionePresenti", XSD_NAMESPACE);
        param.setDocumentation("Output variable, called CauseDiPreoccupazionePresenti, String type");
        params.add(param);
        
        return params;
    }


}
