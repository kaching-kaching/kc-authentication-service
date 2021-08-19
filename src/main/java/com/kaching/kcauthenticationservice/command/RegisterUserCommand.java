package com.kaching.kcauthenticationservice.command;

import com.kaching.libcommand.core.BaseCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegisterUserCommand extends BaseCommand {
    String email;
    String username;
    String password;
}
