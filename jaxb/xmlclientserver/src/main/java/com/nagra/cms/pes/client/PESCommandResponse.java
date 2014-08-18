package com.nagra.cms.pes.client;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 05/07/2013
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class PESCommandResponse {

    private int     code;
    private String  commandName;
    private String  description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
