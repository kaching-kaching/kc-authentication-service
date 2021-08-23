package com.kaching.kcauthenticationservice.executor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.exception.UnhandledException;
import com.kaching.libcommand.core.BaseCommand;
import com.kaching.libcommand.core.CommandExecutor;
import com.kaching.libcommand.feature.CommandConvertor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class RegisterUserExecutor extends CommandExecutor implements CommandConvertor<RegisterUserCommand> {

    private FirebaseAuth auth;

    @Override
    public void execute() {
        var command = convert(getCommand());
        saveFirebaseUser(command);
    }

    private void saveFirebaseUser(RegisterUserCommand command) {
        var request = new CreateRequest()
           .setEmail(command.getEmail())
           .setEmailVerified(false)
           .setPassword(command.getPassword())
           .setDisplayName(command.getUsername())
           .setDisabled(false);
        try {
            auth.createUser(request);
        } catch (FirebaseAuthException exception) {
            throw new UnhandledException(exception.getMessage(), exception);
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
