package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 07.Nis.2014, 15:43:02
 */
public class GSMStyle {
    
    private String feature, element;
    private ArrayList<GSMStyleRule> rules=new ArrayList<GSMStyleRule>();

    public GSMStyle(String feature, String element) {
        this.feature = feature;
        this.element = element;
    }

    public GSMStyle(String feature) {
        this.feature = feature;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
    
    public void addRule(GSMStyleRule r){
        rules.add(r);
    }
    
    public void appendRules(ArrayList<GSMStyleRule> rules){
        rules.addAll(rules);
    }
    
    
    @Override
    public String toString(){
        String str="style=";
        
        if (getFeature()!=null && !getFeature().isEmpty()){
            str=str+"feature:"+getFeature()+"|";
        }
        
        if (getElement()!=null && !getElement().isEmpty()){
            str=str+"element:"+getElement()+"|";
        }
        
        for (int i=0;i<rules.size();i++){
            str=str+ rules.get(i).toString()+"|";
        }
        
        while (str.endsWith("|")){
            str=str.substring(0, str.lastIndexOf("|"));
        }
        
        return str;
    }
}
