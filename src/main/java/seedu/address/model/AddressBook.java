package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniqueApplicantList;
import seedu.address.model.job.Job;
import seedu.address.model.job.UniqueJobList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueApplicantList applicants;
    private final UniqueJobList jobs;
    private int idCount;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        applicants = new UniqueApplicantList();
        jobs = new UniqueJobList();
        idCount = 9;
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Applicants in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setApplicants(newData.getApplicantList());
        setJobs(newData.getJobList());
        this.idCount = newData.getIdCount();

    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        System.out.println(p.hashCode());
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// applicant-level operations

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the address book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds an applicant to the address book.
     * The applicant must not already exist in the address book.
     */
    public void addApplicant(Applicant applicant) {
        System.out.println(applicant.hashCode());
        applicants.add(applicant);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant
     * in the address book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);
        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    // job-level operations

    /**
     * Returns true if a job with the same identity as {@code job} exists in the address book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a job to the address book.
     * The job must not already exist in the address book.
     */
    public void addJob(Job job) {
        jobs.add(job);
    }

    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * The job identity of {@code editedJob} must not be the same as another existing job
     * in the address book.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);
        jobs.setJob(target, editedJob);
    }

    //// IdCount methods

    /**
     * Sets the idCount of this AddressBook
     */
    public void setIdCount(int idCount) {
        this.idCount = idCount;
    }

    /**
     * Increments the IdCount by 1, after a new Job Id has been designated.
     * To be used only in ModelManager
     */
    public void incrementIdCount() {
        this.idCount += 1;
    }

    //// util methods

    @Override
    public String toString() {
        // TODO: change this back if there is an error
        return persons.asUnmodifiableObservableList().size() + " persons "
                + applicants.asUnmodifiableObservableList().size() + " applicants "
                + jobs.asUnmodifiableObservableList().size() + " jobs";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public int getIdCount() {
        return this.idCount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                // TODO: change back if there is error
                && persons.equals(((AddressBook) other).persons)
                && applicants.equals(((AddressBook) other).applicants)
                && jobs.equals(((AddressBook) other).jobs))
                && idCount == (((AddressBook) other).idCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, applicants);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
    }
}
