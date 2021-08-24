package com.kaching.kcauthenticationservice.factory;

import com.google.firebase.auth.FirebaseAuth;
import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.executor.RegisterUserExecutor;
import com.kaching.libcommand.core.CommandExecutor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class ExecutorFactory {

    private FirebaseAuth firebaseAuth;

    public CommandExecutor produce(RegisterUserCommand command) {
        var executor = RegisterUserExecutor.builder()
           .auth(firebaseAuth)
           .build();
        executor.setCommand(command);

        return executor;
    }
}
