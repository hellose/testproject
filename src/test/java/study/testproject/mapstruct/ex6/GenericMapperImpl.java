package study.testproject.mapstruct.ex6;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GenericMapperImpl extends GenericMapper<TestDto, Test> {
}
