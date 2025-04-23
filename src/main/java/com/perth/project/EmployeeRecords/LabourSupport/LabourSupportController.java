package com.perth.project.EmployeeRecords.LabourSupport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LabourSupportController {
    private final LabourSupportService labourSupportService;

    @GetMapping(value ="user/laboursupport/read/information/{token}/{id}")
    public ResponseEntity<Object> readLabourSupport(@PathVariable("id") String id,
                                                    @PathVariable("token") String token) {    
        return ResponseEntity.ok(labourSupportService.readLabourSupport(token, id));
    }
}