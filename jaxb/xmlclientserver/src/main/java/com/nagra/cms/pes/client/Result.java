package com.nagra.cms.pes.client;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: marcomaccio
 * Date: 08/07/2013
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class Result {

    private String  command;
    private int     code;
    private String  description;
    private ArrayList<ToDolistitem> toDolist = new ArrayList<ToDolistitem>();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ToDolistitem> getToDolist() {
        return toDolist;
    }

    public void setToDolist(ArrayList<ToDolistitem> toDolist) {
        this.toDolist = toDolist;
    }

}
