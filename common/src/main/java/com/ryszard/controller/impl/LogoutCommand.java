package com.ryszard.controller.impl;



import com.ryszard.controller.command.CommandException;
import com.ryszard.controller.command.CommandInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements CommandInterface {

    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    public LogoutCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LogoutCommand();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PagePath page = null;
        page = PagePath.INDEX;
        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        return page.toString().toLowerCase();
    }
}
