package mif.ps.psp.FirstAssignment.TemplateMethodRealisation.Model;

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private long age;

    public long getAge() { return age; }

    public void setAge(long age) { this.age = age; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
