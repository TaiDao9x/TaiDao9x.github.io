package backend.User.service;

import backend.User.model.User;
import backend.User.repository.UserRepository;
import backend.User.request.UserRequest;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserService {
    UserRepository userRepository = new UserRepository();
    ArrayList<User> ALL_USER = userRepository.findAll();

    public boolean checkEmailValid(String email) {
        String EMAIL_PATTERN =
                "^[a-zA-Z][\\w-]+@([\\w]{5}+\\.[\\w]{3,}+|[\\w]{5}+\\.[\\w]{3,}\\.[\\w]{2,})$";

        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public boolean checkPasswordValid(String password) {
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";

        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public boolean checkPhoneValid(String phone) {
        String PHONE_PATTERN =
                "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))" +
                        "(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        return Pattern.matches(PHONE_PATTERN, phone);
    }

    public boolean checkEmailExist(String email) {
        for (User user : ALL_USER) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        for (User users : ALL_USER) {
            if (users.getEmail().equalsIgnoreCase(email)) {
                user = users;
            }
        }
        return user;
    }

    public void createNewUser(User newUser) {
        ALL_USER.add(newUser);
        userRepository.updateFile(ALL_USER);
    }

    public void changePassword(UserRequest changePasswordRequest) {
        for (User user : ALL_USER) {
            if (user.getEmail().equalsIgnoreCase(changePasswordRequest.getEmail())) {
                user.setPassword(changePasswordRequest.getPassword());
            }
        }
        userRepository.updateFile(ALL_USER);
    }
}
