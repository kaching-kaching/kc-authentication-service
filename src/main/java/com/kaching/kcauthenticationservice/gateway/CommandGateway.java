package com.kaching.kcauthenticationservice.gateway;

import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.factory.ExecutorFactory;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class CommandGateway {
    private ExecutorFactory executorFactory;

    public void send(RegisterUserCommand command) {
        var executor = executorFactory.produce(command);
        executor.execute();
    }
}
