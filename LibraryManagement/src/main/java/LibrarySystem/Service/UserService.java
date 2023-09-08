package LibrarySystem.Service;

import LibrarySystem.DTO.UserDTO;
import LibrarySystem.Entity.User;
import LibrarySystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        User existUser = userRepository.findByUserName(userDTO.getUsername()).orElse(null);
        if(existUser == null) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
        }else{
            throw new RuntimeException("회원가입이 이미 완료되었습니다.");
        }
    }
}
