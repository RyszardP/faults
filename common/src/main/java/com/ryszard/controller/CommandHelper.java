package com.ryszard.controller;

import com.ryszard.controller.command.CommandInterface;
import com.ryszard.controller.command.TestCommand;
import com.ryszard.controller.impl.EditPasswordCommand;
import com.ryszard.controller.impl.LoginCommand;
import com.ryszard.controller.impl.LogoutCommand;
import com.ryszard.controller.impl.RegistrationCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String DASH = "-";
    private static final String UNDERSCORE = "_";

    private final Map<CommandName, CommandInterface> commands = new HashMap<>();

    public CommandHelper() {
        commands.put(CommandName.AUTORIZATION, LoginCommand.getInstance());
        commands.put(CommandName.REGISTRATION, RegistrationCommand.getInstance());

        commands.put(CommandName.LOGOUT, LogoutCommand.getInstance());
        commands.put(CommandName.EDIT_PASSWORD, EditPasswordCommand.getInstance());

    }
    private enum CommandName {
        AUTORIZATION, REGISTRATION, LOGOUT, EDIT_PASSWORD
    }

    public CommandInterface getCommand(HttpServletRequest request) {
        String commandName = request.getParameter(ATTRIBUTE_COMMAND);
        if (commandName != null) {
            CommandName name = CommandName.valueOf(commandName.toUpperCase().replace(DASH, UNDERSCORE));
            return commands.get(name);
        } else {
            return null;
        }
    }
}