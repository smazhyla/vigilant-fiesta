package com.company;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Back  {
    private String command ="ls";
    private String path="/";

    public void setPath(String path) {
        this.path = path;
    }
    public void changePath (String filepath){
        path=path+"/"+filepath;


    }
    public void getBack() throws Exception {
        File dicrectory = new File(path);
        path=dicrectory.getParent();
    }

    public String getPath() {
        return path;
    }

    public void openFile(){
        File file = new File(path);
        try
        {
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public ArrayList<String> runCommand() throws Exception {
        File whereToRun=new File(path);
        System.out.println("Running in: " + whereToRun);
        System.out.println("Command: " + command);

        ProcessBuilder builder = new ProcessBuilder();


        builder.command("sh", "-c", command);
        builder.directory(whereToRun);

        Process process = builder.start();

        OutputStream outputStream = process.getOutputStream();
        InputStream inputStream = process.getInputStream();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String line;
            ArrayList<String> output = new ArrayList<String>();
            while((line = bufferedReader.readLine()) != null) {
                output.add(line);
            }
            return output;

        }

    }


}
