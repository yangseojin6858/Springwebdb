package com.example.springwebdb.service;

import com.example.springwebdb.DTO.UserDTO;
import com.example.springwebdb.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(UserDTO userDTO) {
        validateUser(userDTO);

        String encodedPassword =
                passwordEncoder.encode(userDTO.getPassword());

        userDTO.setPassword(encodedPassword);

        int result = userMapper.insertUser(userDTO);

        if (result != 1) {
            throw new IllegalStateException("会員登録に失敗しました。");
        }
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.findAllUsers();
    }

    public UserDTO getUserById(int id) {
        UserDTO user = userMapper.findById(id);

        if (user == null) {
            throw new IllegalArgumentException(
                    "該当する会員が見つかりません。"
            );
        }

        return user;
    }

    public void updateUser(UserDTO userDTO) {
        validateUser(userDTO);

        String encodedPassword =
                passwordEncoder.encode(userDTO.getPassword());

        userDTO.setPassword(encodedPassword);

        int result = userMapper.updateUser(userDTO);

        if (result != 1) {
            throw new IllegalStateException(
                    "会員情報の更新に失敗しました。"
            );
        }
    }

    public void deleteUser(int id) {
        int result = userMapper.deleteById(id);

        if (result != 1) {
            throw new IllegalStateException(
                    "会員の削除に失敗しました。"
            );
        }
    }

    private void validateUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException(
                    "会員情報がありません。"
            );
        }

        if (userDTO.getName() == null
                || userDTO.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "名前を入力してください。"
            );
        }

        if (userDTO.getPassword() == null
                || userDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "パスワードを入力してください。"
            );
        }

        if (userDTO.getPassword().length() < 4) {
            throw new IllegalArgumentException(
                    "パスワードは4文字以上で入力してください。"
            );
        }

        if (userDTO.getEmail() == null
                || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "メールアドレスを入力してください。"
            );
        }

        if (!userDTO.getEmail()
                .matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException(
                    "正しいメールアドレス形式で入力してください。"
            );
        }
    }
}