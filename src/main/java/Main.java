import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Task {
    private String body, title;
    private int id, priority;
    private boolean done;

    public Task(String body, String title, int id, int priority, boolean done) {
        this.body = body;
        this.title = title;
        this.id = id;
        this.priority = priority;
        this.done = done;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPriority(){
        return priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public boolean getDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    @Override
    public String toString(){
        if (done) {
            return "The task " + title + " (id " + id + ") " + body +
                    " with a priority of " + priority + " is done";
        }
        else {
            return "The task " + title + " (id " + id + ") " + body +
                    " with a priority of " + priority + " is not done";
        }
    }
}

class ToDoList extends ArrayList<Task>{}

public class Main {
    static final String FILENAME = "data.json";

    public static ToDoList load(String filename) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        try {
            return gson.fromJson(reader, ToDoList.class);
        }
        finally {
            reader.close();
        }
    }


    public static void save(String filename, ToDoList data) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.println(json);
/*        FileWriter writer = new FileWriter(filename);
        try {
            gson.toJson(data, writer);
        }
        finally {
            writer.close();
        }*/
    }

    public static void main(String[] args) {


        // first load data, print and save
        try {

            ToDoList todos = new ToDoList();
            Task t1 = new Task("Walk the dog", "dog",0, 3, false);
            todos.add(t1);
            Task t2 = new Task("Pay the bills","bills",1, 1, false);
            todos.add(t2);

            // print each forecast from the list
            for (Task t: todos) {
                System.out.println(t);
            }

            // save the data to a file
            save(FILENAME, todos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        //Then read from file and print
/*        try {

            // load forecasts from file
            ToDoList readJobs = load(FILENAME);

            // print each forecast from the file
            for (Task t: readJobs) {
                System.out.println(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
    }
