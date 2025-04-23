package com.perth.project.EmployeeRecords.LabourSupport;


import org.springframework.stereotype.Service;

import com.perth.project.Officials.Officials;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportResponse;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabourSupportService {
    
    private final LabourSupportTools labourSupportTools;

    public Object readLabourSupport(String token, String id) {

        labourSupportTools.checkToken(token, id);
        
        Officials labourSupport = labourSupportTools.checkInfo(id);
        return new LabourSupportResponse(
                labourSupport.getUserId(),
                labourSupport.getFirstName(),
                labourSupport.getLastName(),
                labourSupport.getTypeOfRelationship(),
                labourSupport.getDateOfEntry(),
                labourSupport.getChargeID(),
                labourSupport.getSalary());
        
    }
}