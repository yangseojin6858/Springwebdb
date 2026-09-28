package com.example.springwebdb.mapper;

import com.example.springwebdb.DTO.UserDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("""
            SELECT id, name, password, email
            FROM users
            WHERE id = #{id}
            """)
    UserDTO findById(@Param("id") int id);

    @Select("""
            SELECT id, name, password, email
            FROM users
            ORDER BY id DESC
            """)
    List<UserDTO> findAllUsers();

    @Insert("""
            INSERT INTO users(name, password, email)
            VALUES(#{name}, #{password}, #{email})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserDTO userDTO);

    @Update("""
            UPDATE users
            SET name = #{name},
                password = #{password},
                email = #{email}
            WHERE id = #{id}
            """)
    int updateUser(UserDTO userDTO);

    @Delete("""
            DELETE FROM users
            WHERE id = #{id}
            """)
    int deleteById(@Param("id") int id);
}