package com.perth.project.EmployeeRecords.LabourSupport;


import org.springframework.stereotype.Service;

import com.perth.project.Officials.Officials;
import com.perth.project.Parameterization.Charge.Charge;
import com.perth.project.Parameterization.Charge.ChargeRepository;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportResponse;
import com.perth.project.EmployeeRecords.LabourSupport.LabourSupportTools.LabourSupportTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabourSupportService {
    
    private final LabourSupportTools labourSupportTools;
    private final ChargeRepository chargeRepository;
    public Object readLabourSupport(String token, String id) {

        labourSupportTools.checkToken(token, id);
        
        Officials labourSupport = labourSupportTools.checkInfo(id);
        Charge charge = chargeRepository.findById(labourSupport.getChargeID()).orElse(null);
        return new LabourSupportResponse(
                labourSupport.getUserId(),
                labourSupport.getFirstName(),
                labourSupport.getLastName(),
                labourSupport.getTypeOfRelationship(),
                labourSupport.getDateOfEntry(),
                charge.getName(),
                labourSupport.getSalary());
        
    }
}