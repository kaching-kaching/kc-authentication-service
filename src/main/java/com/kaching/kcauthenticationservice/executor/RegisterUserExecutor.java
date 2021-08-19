package com.kaching.kcauthenticationservice.executor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.entity.User;
import com.kaching.kcauthenticationservice.exception.EmailAlreadyExistException;
import com.kaching.kcauthenticationservice.exception.UnhandledException;
import com.kaching.kcauthenticationservice.repository.UserRepository;
import com.kaching.libcommand.core.BaseCommand;
import com.kaching.libcommand.core.CommandExecutor;
import com.kaching.libcommand.feature.CommandConvertor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class RegisterUserExecutor extends CommandExecutor implements CommandConvertor<RegisterUserCommand> {

    private FirebaseAuth auth;
    private UserRepository userRepository;

    @Override
    public void execute() {
        var command = convert(getCommand());

        checkEmailExists(command.getEmail());
        saveUser(saveFirebaseUser(command));
    }

    private UserRecord saveFirebaseUser(RegisterUserCommand command) {
        var request = new CreateRequest()
           .setEmail(command.getEmail())
           .setEmailVerified(false)
           .setPassword(command.getPassword())
           .setDisplayName(command.getUsername())
           .setDisabled(false);
        try {
            return auth.createUser(request);
        } catch (FirebaseAuthException exception) {
            throw new UnhandledException(exception.getMessage(), exception);
        }
    }

    private void saveUser(UserRecord firebaseUser) {
        var newUser = User.builder()
           .email(firebaseUser.getEmail())
           .uid(firebaseUser.getUid())
           .username(firebaseUser.getDisplayName())
           .build();
        userRepository.save(newUser);
    }

    private void checkEmailExists(String email) {
        if (userRepository.isEmailExist(email)) {
            throw new EmailAlreadyExistException(String.format("Email %s already exist", email));
        }
    }

    @Override
    public RegisterUserCommand convert(BaseCommand command) {
        if (command instanceof RegisterUserCommand) {
            return (RegisterUserCommand) command;
        } else {
            return null;
        }
    }
}
