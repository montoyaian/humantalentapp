package com.perth.project.Parameterization.Neighbourhood;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perth.project.Login.Auth.AuthResponse;
import com.perth.project.Parameterization.Neighbourhood.NeighbourhoodTools.NeighbourhoodRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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
public class NeighbourhoodController {
    private final NeighbourhoodService neighbourhoodService;

    @PostMapping("admin/neighbourhood/create")
    public ResponseEntity<AuthResponse> createNeighbourhood(@RequestBody @Valid NeighbourhoodRequest request) {
        return ResponseEntity.ok(neighbourhoodService.createNeighbourhood(request));
    }

    @PutMapping("admin/neighbourhood/edit/{neighbourhoodId}")
    public ResponseEntity<AuthResponse> editNeighbourhood(@RequestBody @Valid NeighbourhoodRequest request, @PathVariable("neighbourhoodId") Integer neighbourhoodId) {
        return ResponseEntity.ok(neighbourhoodService.editNeighbourhood(neighbourhoodId, request));
    }

    @DeleteMapping("admin/neighbourhood/delete/{neighbourhoodId}")
    public ResponseEntity<AuthResponse> deleteNeighbourhood(@PathVariable("neighbourhoodId") Integer neighbourhoodId) {
        return ResponseEntity.ok(neighbourhoodService.deleteNeighbourhood(neighbourhoodId));
    }

    @GetMapping("admin/neighbourhood/read/{neighbourhoodId}")
    public ResponseEntity<Object> readNeighbourhood(@PathVariable("neighbourhoodId") String neighbourhoodId) {
        return ResponseEntity.ok(neighbourhoodService.readNeighbourhood(neighbourhoodId));
    }
}