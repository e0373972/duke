package duke.task;

import duke.DukeException;
import duke.command.Command;
import duke.Storage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ToDo extends Task{
    ToDo (String description){
        super(description);
    }
    static void CheckDescription(String[] command)throws DukeException{
        if (command.length == 1){
            throw new DukeException();
        }
    }

    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand -> {
            try {
                CheckDescription(fullCommand);
                String[] keyword = Arrays.copyOfRange(fullCommand, 1, fullCommand.length);
                Task todoTask = new ToDo(String.join(" ", keyword));
                tasks.add(todoTask);
                storage.store(tasks.ConvertAsLines());
                return List.of("    Got it. I've added this task: " + System.lineSeparator() + "    " + todoTask +
                        System.lineSeparator() +
                        "    Now you have " + tasks.size() + " tasks in the list.");
            }catch (DukeException e){
                return List.of("    ☹ OOPS!!! The description of a " + "todo" + " cannot be empty.");

            }
        };

    }

    @Override
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("T");
        list.addAll(super.getList());
        return list;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
