package com.example.homeworkspingdata;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<PositionDto> createPosition(@RequestBody PositionDto positionDto) {
        PositionDto createdPosition = positionService.createPosition(positionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPosition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable Long id, @RequestBody PositionDto positionDto) {
        PositionDto updatedPosition = positionService.updatePosition(id, positionDto);
        return ResponseEntity.ok(updatedPosition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable Long id) {
        PositionDto position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        List<PositionDto> positions = positionService.getAllPositions();
        return ResponseEntity.ok(positions);
    }
}