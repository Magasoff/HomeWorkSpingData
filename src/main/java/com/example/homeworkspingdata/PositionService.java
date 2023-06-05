package com.example.homeworkspingdata;

import java.util.List;

public interface PositionService {
    PositionDto createPosition(PositionDto positionDto);

    PositionDto updatePosition(Long id, PositionDto positionDto);

    void deletePosition(Long id);

    PositionDto getPositionById(Long id);

    List<PositionDto> getAllPositions();
}