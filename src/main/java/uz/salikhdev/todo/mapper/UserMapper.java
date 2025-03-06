package uz.salikhdev.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.todo.dto.RegisterDto;
import uz.salikhdev.todo.entitiy.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toRegisterEntity(RegisterDto dto);

}
