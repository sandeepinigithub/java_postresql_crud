package com.sandeep.java_postresql_crud.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	public List<User> getStudents() {
		return userRepository.findAll();
	}

	public void addNewStudent(User user) {
		Optional<User> userOptional = userRepository.findStudentByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("email exist");
		}
		userRepository.save(user);
	}

	public void deleteStudent(Long userId) {
		boolean exist = userRepository.existsById(userId);
		if (!exist) {
			throw new IllegalStateException("User with ID " + userId + " does not exist");
		}
		userRepository.deleteById(userId);
	}

	@Transactional
	public void updateStudent(Long userId, String name, String email) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("Student with ID " + userId + " does not exist"));

		if (name != null && name.length() > 0 && !Objects.equals(name, user.getName())) {
			user.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(email, user.getEmail())) {
			Optional<User> userOptional = userRepository.findStudentByEmail(email);
			if (userOptional.isPresent()) {
				throw new IllegalStateException("email exist");
			}
			user.setEmail(email);
		}

		userRepository.save(user);

	}

}
