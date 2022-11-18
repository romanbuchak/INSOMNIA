package com.rbvgt.dogcellar.mapper;

import org.modelmapper.ModelMapper;

public class DtoConverter<Entity extends Convertible, Dto extends Convertible> {

    private final ModelMapper modelMapper;

    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <Entity extends Convertible, Dto extends Convertible> Dto convertToDto(
            final Entity entity,
            final Class<Dto> dto) {

        return modelMapper.map(entity, dto);
    }

    public <Dto extends Convertible, Entity extends Convertible> Entity convertToEntity(final Dto dto,
                                                                                        final Class<Entity> entity) {
        return modelMapper.map(dto, entity);
    }

    public <Dto extends Convertible, Entity extends Convertible> void update(final Dto dto, final Entity entity) {
        modelMapper.map(dto, entity);
    }
}
