package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEINTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.applicant.EditApplicant;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.NameApplicantContainsKeywordsPredicate;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobTitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHARLIE = "Charlie Goh";
    public static final String VALID_NAME_DON = "Don Lee";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CHARLIE = "33333333";
    public static final String VALID_PHONE_DON = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CHARLIE = "charlie@example.com";
    public static final String VALID_EMAIL_DON = "don@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_CHARLIE = "Block 321 Charlie Street 2";
    public static final String VALID_ADDRESS_DON = "Block 132 Don Street 4";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_NRIC = "S1234556D";
    public static final String VALID_NRIC_TWO = "S9987236F";
    public static final String VALID_DATE = "2022-12-12";
    public static final String VALID_DATE_TWO = "2022-11-11";
    public static final String VALID_JOB = "12345678";
    public static final String VALID_JOB_TWO = "12678";
    public static final String VALID_QUALIFICATION = "Degree in Computing";
    public static final String VALID_QUALIFICATION_TWO = "Degree in Engineering";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String NRIC_DESC = " " + PREFIX_NRIC + VALID_NRIC;
    public static final String DATEAPPLIED_DESC = " " + PREFIX_DATEAPPLIED + VALID_DATE;
    public static final String JOB_DESC = " " + PREFIX_JOB + VALID_JOB;
    public static final String QUALIFICATION_DESC = " " + PREFIX_QUALIFICATION + VALID_QUALIFICATION;
    public static final String QUALIFICATION_TWO_DESC = " " + PREFIX_QUALIFICATION + VALID_QUALIFICATION_TWO;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_NRIC = " " + PREFIX_NRIC + "1234567d"; // nric must end and start with alphabet
    public static final String INVALID_DATEAPPLIED = " " + PREFIX_DATEAPPLIED + "123-fs-12"; // yyyy-mm-dd format
    public static final String INVALID_INTERVIEWDATE = " " + PREFIX_DATEINTERVIEW + "fsd-fsd-fsd"; // yyyy-mm-dd format
    // cannot have special characters in qualification
    public static final String INVALID_QUALIFICATION = " " + PREFIX_QUALIFICATION + " **degree in science";
    public static final String INVALID_JOB = " " + PREFIX_JOB + "a12345"; // job id must be all numbers

    // ========================== Jobs ====================================
    public static final String VALID_JOB_TITLE_DATA_ANALYSIS = "Data Analyst";
    public static final String VALID_JOB_TITLE_PROJECT_MANAGER = "Project Manager";
    public static final String VALID_COMPANY_NAME_DATA_ANALYSIS = "Facebook";
    public static final String VALID_COMPANY_NAME_PROJECT_MANAGER = "OCBC";
    public static final String VALID_JOB_ID_DATA_ANALYSIS = "10101";
    public static final String VALID_JOB_ID_PROJECT_MANAGER = "12345";
    public static final String VALID_ADDRESS_DATA_ANALYSIS = "9 Straits View, Marina One";
    public static final String VALID_ADDRESS_PROJECT_MANAGER = "65 Chulia Street, OCBC Centre";
    public static final String VALID_QUALIFICATION_DATA_ANALYSIS = "Degree in Data Science";
    public static final String VALID_QUALIFICATION_PROJECT_MANAGER = "Degree in Business Management";
    // TODO: MAY NEED TO CAHNGE THIS DEPENDING ON HOW JOB STATUS IS IMPLEMENTED
    public static final String VALID_JOB_STATUS_DATA_ANALYSIS = "vacant";
    public static final String VALID_JOB_STATUS_PROJECT_MANAGER = "filled";

    public static final String VALID_POSITION_DATA_ANALYSIS = "ft";
    public static final String VALID_POSITION_PROJECT_MANAGER = "ft";
    public static final String VALID_SALARY_LOW_DATA_ANALYSIS = "6000";
    public static final String VALID_SALARY_HIGH_DATA_ANALYSIS = "8000";
    public static final String VALID_SALARY_LOW_PROJECT_MANAGER = "4000";
    public static final String VALID_SALARY_HIGH_PROJECT_MANAGER = "5000";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    // ==================== Applicant =================================
    public static final EditApplicant.EditApplicantDescriptor DESC_CHARLIE;
    public static final EditApplicant.EditApplicantDescriptor DESC_DON;
    // ===================== Job ===============================
    // TODO THIS IS FOR THE EDITJOB COMMAND;
    // public static final EditJob.EditJobDescripter DESC_DATA_ANALYSIS;
    // public static final EditJob.EditJobDescripter DESC_PROJECT_MANAGER;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CHARLIE = new EditApplicantDescriptorBuilder().withName(VALID_NAME_CHARLIE)
                .withPhone(VALID_PHONE_CHARLIE).withEmail(VALID_EMAIL_CHARLIE).withAddress(VALID_ADDRESS_CHARLIE)
                .withNric(VALID_NRIC).withDateApplied(VALID_DATE).withInterviewDate(VALID_DATE)
                .withQualification(VALID_QUALIFICATION).build();
        DESC_DON = new EditApplicantDescriptorBuilder().withName(VALID_NAME_DON)
                .withPhone(VALID_PHONE_DON).withEmail(VALID_EMAIL_DON).withAddress(VALID_ADDRESS_DON)
                .withNric(VALID_NRIC).withDateApplied(VALID_DATE).withInterviewDate(VALID_DATE)
                .withQualification(VALID_QUALIFICATION).build();
        // TODO ADD DESC_DATAANALYSIS AND PROJECT MANAGER
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new NameApplicantContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the job at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showJobAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredJobList().size());

        Job job = model.getFilteredJobList().get(targetIndex.getZeroBased());
        final String[] splitJobTitle = job.getJobTitle().jobTitle.split("\\s+");
        model.updateFilteredJobList(new JobTitleContainsKeywordsPredicate(Arrays.asList(splitJobTitle[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }


}
