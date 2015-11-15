package fr.univtln.tperron710jpoupon997.d12.project.university;

/**
 * Created by jeremypoupon on 12/10/15.
 */
public enum ELessonType {

    MagistralLesson("CM"),

    Exercises("TD"),

    PracticalWorking("TP");

    private final String name;

    ELessonType(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
