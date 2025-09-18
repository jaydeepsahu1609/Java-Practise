package com.jaydeep.learn.multithreading;

/**
 * In some regions, students are homeschooled and rely on specialized tutors
 * for each subject. Each tutor is paid to teach a subject for exactly 3 seconds
 * (to keep program execution fast).
 * <p>
 * There is no fixed timetable — tutors arrive at the student's home at random times.
 * If a tutor finds that the student is already busy studying another subject,
 * they patiently wait for their turn.
 * <p>
 * While waiting, tutors log a message in the format:
 * "<Subject>-Tutor is waiting to teach <Subject>"
 * so that the student's guardian knows they arrived on time and are punctual.
 * <p>
 * Once the current lesson ends, the student becomes free. At that point, one of
 * the waiting tutors enters the study-room and begins teaching the student.
 * This process continues until all subjects have been taught.
 */
public class WaitNotifyDemoPrivateTutors {
    public static void main(String[] args) {
        String[] subjects = {"Hindi", "English", "Maths", "Physics"};
        Student student = new Student("Jaydeep");

        // Create and start a tutor thread for each subject
        for (String subject : subjects) {
            new Tutor(student, subject).start();
        }
    }
}

/**
 * A Student is home-schooled by multiple Tutors.
 * Only one Tutor can teach the Student at a time.
 * If the Student is busy, Tutors wait and log their punctuality.
 */
class Student {
    private final String name;
    private boolean isBusy = false; // true when a tutor is teaching

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Tutor calls this to teach a subject.
     * If the student is busy, the tutor waits and logs a message.
     * The actual teaching is simulated by sleeping for 3 seconds.
     */
    public void teachSubject(String subject) {
        try {
            synchronized (this) {
                while (isBusy) {
                    System.out.println("[" + Thread.currentThread().getName() +
                            " is waiting to teach " + subject + "]");
                    wait(); // release lock and wait until notified
                }
                // Student is free, occupy for teaching
                System.out.println("==> " + Thread.currentThread().getName() + " walks in.");
                isBusy = true;
                System.out.println(this.getName() + " is studying " + subject);
            }

            Thread.sleep(3000); // simulate study time

            // Release student and notify all waiting tutors
            synchronized (this) {
                isBusy = false;
                // notify(); // wake up one of the waiting tutors
                notifyAll(); // wake up all the waiting tutors
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/**
 * Each Tutor is a thread responsible for teaching a single subject.
 */
class Tutor extends Thread {
    private final Student student;
    private final String subject;

    public Tutor(Student student, String subject) {
        super(subject + "-Tutor");
        this.student = student;
        this.subject = subject;
    }

    @Override
    public void run() {
        student.teachSubject(subject);
    }
}

