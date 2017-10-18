package commcare.capstone.comcare.webservice;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.6
//
// Date Of Creation: 10/15/2017 3:39:54 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class Assistances implements KvmSerializable {
    
    public boolean befriend;
    public boolean befriendSpecified;
    public boolean food;
    public boolean foodSpecified;
    public boolean grant;
    public boolean grantSpecified;
    public boolean homeFix;
    public boolean homeFixSpecified;
    public long id;
    public boolean idSpecified;
    public boolean longTerm;
    public boolean longTermSpecified;
    public boolean mobility;
    public boolean mobilitySpecified;
    public String otherAssistance;
    public DataCollectionForm parent;
    public boolean shortTerm;
    public boolean shortTermSpecified;
    public boolean tingkat;
    public boolean tingkatSpecified;
    public boolean utility;
    public boolean utilitySpecified;
    public boolean wheels;
    public boolean wheelsSpecified;
    
    public Assistances(){}
    
    public Assistances(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("befriend"))
        {
            Object obj = soapObject.getProperty("befriend");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                befriend = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                befriend = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("befriendSpecified"))
        {
            Object obj = soapObject.getProperty("befriendSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                befriendSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                befriendSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("food"))
        {
            Object obj = soapObject.getProperty("food");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                food = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                food = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("foodSpecified"))
        {
            Object obj = soapObject.getProperty("foodSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                foodSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                foodSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("grant"))
        {
            Object obj = soapObject.getProperty("grant");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                grant = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                grant = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("grantSpecified"))
        {
            Object obj = soapObject.getProperty("grantSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                grantSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                grantSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("homeFix"))
        {
            Object obj = soapObject.getProperty("homeFix");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                homeFix = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                homeFix = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("homeFixSpecified"))
        {
            Object obj = soapObject.getProperty("homeFixSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                homeFixSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                homeFixSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("id"))
        {
            Object obj = soapObject.getProperty("id");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                id = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                id = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("idSpecified"))
        {
            Object obj = soapObject.getProperty("idSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                idSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                idSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("longTerm"))
        {
            Object obj = soapObject.getProperty("longTerm");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                longTerm = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                longTerm = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("longTermSpecified"))
        {
            Object obj = soapObject.getProperty("longTermSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                longTermSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                longTermSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("mobility"))
        {
            Object obj = soapObject.getProperty("mobility");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                mobility = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                mobility = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("mobilitySpecified"))
        {
            Object obj = soapObject.getProperty("mobilitySpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                mobilitySpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                mobilitySpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("otherAssistance"))
        {
            Object obj = soapObject.getProperty("otherAssistance");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                otherAssistance = j.toString();
            }else if (obj!= null && obj instanceof String){
                otherAssistance = (String) obj;
            }
        }
        if (soapObject.hasProperty("parent"))
        {
            SoapObject j = (SoapObject)soapObject.getProperty("parent");
            parent =  new DataCollectionForm (j);
            
        }
        if (soapObject.hasProperty("shortTerm"))
        {
            Object obj = soapObject.getProperty("shortTerm");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                shortTerm = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                shortTerm = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("shortTermSpecified"))
        {
            Object obj = soapObject.getProperty("shortTermSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                shortTermSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                shortTermSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("tingkat"))
        {
            Object obj = soapObject.getProperty("tingkat");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                tingkat = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                tingkat = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("tingkatSpecified"))
        {
            Object obj = soapObject.getProperty("tingkatSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                tingkatSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                tingkatSpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("utility"))
        {
            Object obj = soapObject.getProperty("utility");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                utility = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                utility = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("utilitySpecified"))
        {
            Object obj = soapObject.getProperty("utilitySpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                utilitySpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                utilitySpecified = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("wheels"))
        {
            Object obj = soapObject.getProperty("wheels");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                wheels = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                wheels = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("wheelsSpecified"))
        {
            Object obj = soapObject.getProperty("wheelsSpecified");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                wheelsSpecified = Boolean.parseBoolean(j.toString());
            }else if (obj!= null && obj instanceof Boolean){
                wheelsSpecified = (Boolean) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return befriend;
            case 1:
                return befriendSpecified;
            case 2:
                return food;
            case 3:
                return foodSpecified;
            case 4:
                return grant;
            case 5:
                return grantSpecified;
            case 6:
                return homeFix;
            case 7:
                return homeFixSpecified;
            case 8:
                return id;
            case 9:
                return idSpecified;
            case 10:
                return longTerm;
            case 11:
                return longTermSpecified;
            case 12:
                return mobility;
            case 13:
                return mobilitySpecified;
            case 14:
                return otherAssistance;
            case 15:
                return parent;
            case 16:
                return shortTerm;
            case 17:
                return shortTermSpecified;
            case 18:
                return tingkat;
            case 19:
                return tingkatSpecified;
            case 20:
                return utility;
            case 21:
                return utilitySpecified;
            case 22:
                return wheels;
            case 23:
                return wheelsSpecified;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 24;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "befriend";
                break;
            case 1:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "befriendSpecified";
                break;
            case 2:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "food";
                break;
            case 3:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "foodSpecified";
                break;
            case 4:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "grant";
                break;
            case 5:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "grantSpecified";
                break;
            case 6:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "homeFix";
                break;
            case 7:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "homeFixSpecified";
                break;
            case 8:
                info.type = Long.class;
                info.name = "id";
                break;
            case 9:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "idSpecified";
                break;
            case 10:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "longTerm";
                break;
            case 11:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "longTermSpecified";
                break;
            case 12:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "mobility";
                break;
            case 13:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "mobilitySpecified";
                break;
            case 14:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "otherAssistance";
                break;
            case 15:
                info.type = DataCollectionForm.class;
                info.name = "parent";
                break;
            case 16:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "shortTerm";
                break;
            case 17:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "shortTermSpecified";
                break;
            case 18:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "tingkat";
                break;
            case 19:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "tingkatSpecified";
                break;
            case 20:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "utility";
                break;
            case 21:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "utilitySpecified";
                break;
            case 22:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "wheels";
                break;
            case 23:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "wheelsSpecified";
                break;
        }
    }
    
    @Override
    public String getInnerText() {
        return null;
    }
    
    
    @Override
    public void setInnerText(String s) {
    }
    
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}