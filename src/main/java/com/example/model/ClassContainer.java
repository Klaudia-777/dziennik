package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {

    public static Map<String, Class>  classMap = new HashMap<>();
    

    public void addClass(String nameGroup, int limitGroup){   
        Class group = new Class(nameGroup, limitGroup);
        classMap.put(nameGroup, group);
    }

    public void addClass(Class myGroup){                        
        classMap.put(String.valueOf(myGroup.getGroupName()), myGroup);          
    }                                                           
    public void removeClass(String nameGroup){
        classMap.remove(nameGroup);
    }

    public List<Class> findEmpty(){             
        List<Class> emptyGroups = new ArrayList<>();
        for( var element: classMap.entrySet()){         
            
            if(element.getValue().getStudentsList().size() == 0){
                emptyGroups.add(element.getValue());
            }
        }
        return emptyGroups;
    }

    public void summary(){                          
        for( var element: classMap.entrySet()){
            double percent = Double.valueOf(element.getValue().getStudentsList().size())/Double.valueOf(element.getValue().getLimit())*100;
            
            System.out.println("Nazwa grupy: " + element.getKey() + ", procentowe zapelnienie: " + percent + "%");
        }
    }
}
