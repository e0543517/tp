package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.job.Job;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // ============================= Applicant ================================
    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the address book.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the address book.
     */
    void deleteApplicant(Applicant target);

    // ============================= Job ================================

    /**
     * Add a given job to filteredlist
     */
    void addJob(Job job);

    /**
     * Returns true if a job with the same identity as {@code job} exists in the address book.
     */
    boolean hasJob(Job job);

    /**
     * Returns the current IdCount()
     */
    String getIdCount();

    //--------------ReCLIne------------------------------

    /**
     * Adds a given Applicant to filteredList
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant
     * in the address book.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);

    /** Returns an unmodifiable view of the filtered job list */
    ObservableList<Job> getFilteredJobList();

    /**
     * Deletes the given job.
     * The job must exist in the address book.
     */
    void deleteJob(Job target);

    /**
     * Updates the filter of the filtered job list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredJobList(Predicate<Job> predicate);
}

