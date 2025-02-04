package com.perth.project.Parameterization.Area
;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.Area.AreaTools.AreaRequest;
import com.perth.project.Parameterization.Area.AreaTools.EditAreaRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping("admin/area/create")
    public ResponseEntity<AuthResponse> createArea(@RequestBody @Valid AreaRequest request) {

        return ResponseEntity.ok(areaService.createArea(request));
    }
    @PutMapping("admin/area/edit/{areaCode}")
    public ResponseEntity<AuthResponse> editArea(@RequestBody @Valid EditAreaRequest request,@PathVariable("areaCode") Integer areaCode) {

        return ResponseEntity.ok(areaService.editArea(areaCode, request));
    }
    @DeleteMapping("admin/area/delete/{areaCode}")
    public ResponseEntity<AuthResponse> deleteArea (@PathVariable("areaCode") Integer areaCode) {

        return ResponseEntity.ok(areaService.deleteArea(areaCode));
    }
    @GetMapping("admin/area/read/{areaCode}")
    public ResponseEntity<Object> readArea(@PathVariable("areaCode") String areaCode) {

        return ResponseEntity.ok(areaService.readArea(areaCode));
    }
}