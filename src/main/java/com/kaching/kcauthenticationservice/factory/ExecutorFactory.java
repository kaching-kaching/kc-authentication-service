package com.kaching.kcauthenticationservice.factory;

import com.google.firebase.auth.FirebaseAuth;
import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.executor.RegisterUserExecutor;
import com.kaching.kcauthenticationservice.repository.UserRepository;
import com.kaching.libcommand.core.CommandExecutor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true)
public class ExecutorFactory {

    private FirebaseAuth firebaseAuth;
    private UserRepository userRepository;

    @Autowired
    public ExecutorFactory(FirebaseAuth firebaseAuth, UserRepository userRepository) {
        this.firebaseAuth = firebaseAuth;
        this.userRepository = userRepository;
    }

    public CommandExecutor produce(RegisterUserCommand command) {
        var executor = RegisterUserExecutor.builder()
           .auth(firebaseAuth)
           .userRepository(userRepository)
           .build();
        executor.setCommand(command);

        return executor;
    }
}
