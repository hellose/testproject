package study.testproject.mapstruct.ex6;

import java.util.List;

import org.mapstruct.MappingTarget;

public interface GenericMapper<D, E> {
	
    D toDto(E e);
    List<D> toDto(List<E> e);
    void updateFromDto(D dto, @MappingTarget E entity);
}