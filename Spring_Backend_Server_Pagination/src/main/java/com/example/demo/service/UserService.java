package com.example.demo.service;

import com.example.demo.repo.UserRepo;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<UserEntity> fetchAllUsers() {

        return userRepo.findAll();

    }

    public APIResponse addUser(UserEntity userEntity) {

        if ( userEntity != null ){

            String userEmail = userEntity.getEmail();

            UserEntity fetchedUser = userRepo.findByEmail(userEmail).orElse(null);

            if ( fetchedUser == null ) {

                userRepo.save(
                        UserEntity
                                .builder()
                                .firstName(userEntity.getFirstName() != null ? userEntity.getFirstName().trim() : null)
                                .lastName(userEntity.getLastName() != null ? userEntity.getLastName().trim() : null)
                                .email(userEntity.getEmail() != null ? userEntity.getEmail().trim() : null)
                                .age(userEntity.getAge())
                                .build()
                );

                return APIResponse
                        .builder()
                        .success(true)
                        .message("User Successfully Saved")
                        .build();

            }else {

                return APIResponse
                        .builder()
                        .success(false)
                        .message("User Already Exist")
                        .build();

            }

        }else {

            return APIResponse
                    .builder()
                    .success(false)
                    .message("Request Object is empty")
                    .build();

        }

    }

    public Page<UserEntity> paginate(int pageNumber, int pageSize, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return userRepo.findAll(PageRequest.of(pageNumber, pageSize, sort));

    }

    public Boolean deleteUserByUserEmail(String userEmail) {

        if ( userEmail == null || userEmail.isBlank() ){

            return false;

        }

        return userRepo.findByEmail(userEmail)
                .map(userObject -> {

                    userRepo.delete(userObject);

                    return true;

                })
                .orElse(false);

    }

}
