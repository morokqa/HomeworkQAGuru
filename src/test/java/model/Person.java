package model;

import java.util.List;

public class Person {
        private String name;
        private int age;
        private String profession;
        private Skills skills;

    public String getName() {
            return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
}

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }
    public static class Skills {
        private List<String> hardSkills;
        private List<String> softSkills;


        public List<String> getHardSkills() {
            return hardSkills;
        }
        public void setHardSkills(List<String> hardSkills) {
            this.hardSkills = hardSkills;
        }
        public List<String> getSoftSkills() {
            return softSkills;
        }
        public void setSoftSkills(List<String> softSkills) {
            this.softSkills = softSkills;
        }
    }
}
