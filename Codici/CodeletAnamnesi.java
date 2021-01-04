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
import static org.yawlfoundation.yawl.resourcing.codelets.AbstractCodelet.XSD_NAMESPACE;

/**
 *
 * @author Sasha
 */
public class CodeletAnamnesi extends AbstractCodelet{

    public CodeletAnamnesi(){
        super();
        setDescription("Codelet con cui suggerisco le cause di Preoccupazione");
    }
    
    @Override
    public Element execute(Element inData, List<YParameter> inParams, List<YParameter> outParams) throws CodeletExecutionException {
       
        setInputs(inData, inParams, outParams);
        boolean SintomiDisordineSistemico=false;
        boolean SegniNeurologici=false;
       
        String output="Le cause di preoccupazione presenti sono:\n";
        String Suggerimento="MalDiTestaPrimario";

        int count=0;
        String misureDiagnostiche=(String) getParameterValue("MisureDiagnostiche");
        String[] array = misureDiagnostiche.split("\n");

        String temperatura= array[1].split(">")[1].split("<")[0];
        String riflessiSpinali=array[4].split(">")[1].split("<")[0];
        String simmetriaPupillare=array[5].split(">")[1].split("<")[0];
        String statoConfusionale=array[9].split(">")[1].split("<")[0];

        if (Double.parseDouble(temperatura)>37.5){
            SintomiDisordineSistemico = true;
        }

        if (riflessiSpinali.equals("Presente") || simmetriaPupillare.equals("Presente") || statoConfusionale.equals("Presente")){
            SegniNeurologici=true;
        }

               
       String CauseDiPreoccupazione =   "<Convulsioni>"+false+"</Convulsioni>"+
                                        "<MalDiTestaAdInsorgenzaImprovvisa>"+false+"</MalDiTestaAdInsorgenzaImprovvisa>"+
                                        "<MalDiTestaChePeggioraNelTempo>"+false+"</MalDiTestaChePeggioraNelTempo>"+
                                        "<MalDiTestaOver50>"+false+"</MalDiTestaOver50>"+
                                        "<MalDiTestaPeggioreDiSempre>"+false+"</MalDiTestaPeggioreDiSempre>"+
                                        "<MalDiTestaPostManovraDiValsava>"+false+"</MalDiTestaPostManovraDiValsava>"+
                                        "<SegniNeurologici>"+SegniNeurologici+"</SegniNeurologici>"+
                                        "<SintomiDisordineSistemico>"+SintomiDisordineSistemico+"</SintomiDisordineSistemico>"+
                                        "<Nessuna>"+false+"</Nessuna>";
       setParameterValue("CauseDiPreoccupazione", CauseDiPreoccupazione);
    
       return getOutputData();
    }
    
        public List<YParameter> getRequiredParams(){
        List<YParameter> params = new ArrayList<>();
        
        YParameter param = new YParameter(null, YParameter._INPUT_PARAM_TYPE);
        param.setDataTypeAndName("MisureDiagnostiche", "MisureDiagnostiche", XSD_NAMESPACE);
        param.setDocumentation("Input variable, called MisureDiagnostiche, MisureDiagnostiche type");
        params.add(param);
        
        
        param = new YParameter(null, YParameter._OUTPUT_PARAM_TYPE);
        param.setDataTypeAndName("CauseDiPreoccupazione","CauseDiPreoccupazione", XSD_NAMESPACE);
        param.setDocumentation("Output variable, CauseDiPreoccupazine, CauseDiPreoccupazione type");
        params.add(param);
        
        return params;
        
        
        }

    
}
