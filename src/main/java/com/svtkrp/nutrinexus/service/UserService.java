package com.svtkrp.nutrinexus.service;

import com.svtkrp.nutrinexus.domain.UserModel;
import com.svtkrp.nutrinexus.form.UserCredsForm;
import com.svtkrp.nutrinexus.form.UserEditCredsForm;
import com.svtkrp.nutrinexus.form.UserEditProfileForm;
import com.svtkrp.nutrinexus.repository.UserRepository;
import com.svtkrp.nutrinexus.validation.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserModel createUser(UserEditCredsForm form, Errors errors) {
        if (errors.hasErrors()) {
            return null;
        }

        if (!validUsername(form.getUsername())) {
            errors.rejectValue("username", "Not valid");
        }
        if (!validEmail(form.getEmail())) {
            errors.rejectValue("email", "Not valid");
        }
        if (!validPassword(form.getPassword())) {
            errors.rejectValue("password", "Not valid");
        }

        if (errors.hasErrors()) {
            return null;
        }

        if (userRepository.getUserModelByUsername(form.getUsername()) != null) {
            errors.rejectValue("username", "Not unique");
        }
        if (userRepository.getUserModelByEmail(form.getEmail()) != null) {
            errors.rejectValue("email", "Not unique");
        }

        if (errors.hasErrors()) {
            return null;
        }

        UserModel user = new UserModel();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        String salt = getNewSalt(user);
        user.setSalt(salt);
        user.setHashedPassword(getHashedPassword(form.getPassword(), salt));
        user.setCreatedAt(new Date());

        try {
            save(user);
            return user;
        } catch (Exception ex) {
            errors.reject(ex.getMessage());
            // todo log
            ex.printStackTrace();
        }
        return null;
    }

    public UserModel updateUser(Long id, UserEditCredsForm form) {
        // todo if has errors

        if (id == null) {
            // todo error required
            return null;
        }
        UserModel user = getById(id);
        if (user == null) {
            // todo error not found
            return null;
        }

        if (!validUsername(form.getUsername())) {
            // todo error bad username
            return null;
        }
        if (!validEmail(form.getEmail())) {
            // todo error bad email
            return null;
        }
        if (!validPassword(form.getPassword())) {
            // todo error bad password
            return null;
        }

        // todo if has errors

        UserModel userByUsername = userRepository.getUserModelByUsername(form.getUsername());
        if (userByUsername != null && !id.equals(userByUsername.getId())) {
            // todo error username not unique
            return null;
        }
        UserModel userByEmail = userRepository.getUserModelByEmail(form.getEmail());
        if (userByEmail != null && !id.equals(userByEmail.getId())) {
            // todo error email not unique
            return null;
        }

        // todo if has errors

        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setHashedPassword(getHashedPassword(form.getPassword(), user.getSalt()));

        return save(user);
    }

    private boolean validUsername(String username) {
        // todo ?
        return true;
    }

    private boolean validEmail(String email) {
        // todo ?
        return true;
    }

    private boolean validPassword(String password) {
        // todo ?
        return true;
    }

    public UserModel updateUserProfile(Long id, UserEditProfileForm form) {
        if (id == null) {
            // todo error required
            return null;
        }
        UserModel user = getById(id);
        if (user == null) {
            // todo error not found
            return null;
        }
        if (form.getName() != null && form.getName().length() > 30) {
            // todo error bad name
            return null;
        }
        if (form.getBio() != null && form.getBio().length() > 100) {
            // todo error bad bio
            return null;
        }
        if (form.getAvatarId() != null && false) { // todo get pic by id == null, check author
            // todo error pic not found
            return null;
        }

        // todo if has errors

        user.setName(form.getName());
        user.setBio(form.getBio());
        user.setAvatarId(form.getAvatarId());

        return save(user);
    }

    public boolean deleteUser(Long id) {
        if (id == null) {
            // todo error required
            return false;
        }
        UserModel user = getById(id);
        if (user == null) {
            // todo error not found
            return false;
        }
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            // todo error e
            return false;
        }
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public List<UserModel> findAllUsersByUsernameLike(String username) {
        return userRepository.getAllByUsernameContainingIgnoreCase(username);
    }

    public UserModel findUserById(Long id) {
        if (id == null) {
            // todo error required ?
            return null;
        }
        UserModel user = getById(id);
        if (user == null) {
            // todo error not found ?
            return null;
        }
        return user;
    }

    public UserModel findUserByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            // todo error required ?
            return null;
        }
        UserModel user = userRepository.getUserModelByUsername(username);
        if (user == null) {
            // todo error not found ?
            return null;
        }
        return user;
    }

    public boolean checkCredentials(UserCredsForm form) {
        if (!StringUtils.hasText(form.getUsernameOrEmail())) {
            // todo error required
            return false;
        }

        if (!StringUtils.hasText(form.getPassword())) {
            // todo error required
            return false;
        }

        // todo if has errors

        UserModel user = (form.getUsernameOrEmail().contains("@")) ?
                userRepository.getUserModelByEmail(form.getUsernameOrEmail())
                : userRepository.getUserModelByUsername(form.getUsernameOrEmail());

        if (user == null) {
            // todo error wrong credentials
            return false;
        }

        String hashedPassword = getHashedPassword(form.getPassword(), user.getSalt());
        if (!hashedPassword.equals(user.getHashedPassword())) {
            // todo error wrong credentials
            return false;
        }
        return true;

    }

    private String getNewSalt(UserModel user) {
        return user.getUsername(); // todo: replace with unique random string
    }

    private String getHashedPassword(String password, String salt) {
        return (password + salt).toUpperCase(); // todo: replace with real hash function
    }

    public UserModel getById(Long id) {
        return userRepository.getUserModelById(id);
    }

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }
}
