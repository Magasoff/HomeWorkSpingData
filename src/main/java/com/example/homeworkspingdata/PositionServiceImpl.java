package com.example.homeworkspingdata;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper modelMapper) {
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PositionDto createPosition(PositionDto positionDto) {
        Position position = modelMapper.map(positionDto, Position.class);
        Position savedPosition = positionRepository.save(position);
        return modelMapper.map(savedPosition, PositionDto.class);
    }

    @Override
    public PositionDto updatePosition(Long id, PositionDto positionDto) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found with id: " + id));
        position.setName(positionDto.getName());
        Position savedPosition = positionRepository.save(position);
        return modelMapper.map(savedPosition, PositionDto.class);
    }

    @Override
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found with id: " + id));
        positionRepository.delete(position);
    }

    @Override
    public PositionDto getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found with id: " + id));
        return modelMapper.map(position, PositionDto.class);
    }

    @Override
    public List<PositionDto> getAllPositions() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream()
                .map(position -> modelMapper.map(position, PositionDto.class))
                .collect(Collectors.toList());
    }
}