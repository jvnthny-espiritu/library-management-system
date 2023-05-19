package data;

public class Student {
    private int studentid;
    private String name;
    private String yearlevel;
    private String program;

    public Student(int studentid, String name, String yearlevel, String program){
        this.studentid = studentid;
        this.name = name;
        this.yearlevel = yearlevel;
        this.program = program;
    }
    
    public int getStudentid(){
        return studentid;
    }
    public String getName(){
        return name;
    }
    public String getYearLevel(){
        return yearlevel;
    }
    public String getProgram(){
        return program;
    }  
}
