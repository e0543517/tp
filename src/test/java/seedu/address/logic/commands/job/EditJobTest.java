package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.TypicalJobs;

class EditJobTest {
    private Model model = new ModelManager(TypicalJobs.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Job currentJob = model.getFilteredJobList().get(0);

        // EditJob should not edit the JobId. JobId should not change.
        Job editedJob = new JobBuilder().withJobId(currentJob.getJobId().toString()).build();
        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder(editedJob).build();
        EditJob editJob = new EditJob(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastJob = Index.fromOneBased(model.getFilteredJobList().size());
        Job lastJob = model.getFilteredJobList().get(indexLastJob.getZeroBased());

        JobBuilder JobInList = new JobBuilder(lastJob);
        Job editedJob = JobInList.withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS)
                .withJobAddress(VALID_ADDRESS_DATA_ANALYSIS).build();

        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS)
                .withAddress(VALID_ADDRESS_DATA_ANALYSIS).build();
        EditJob editJob = new EditJob(indexLastJob, descriptor);

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(lastJob, editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditJob editJob =
                new EditJob(INDEX_FIRST_PERSON, new EditJob.EditJobDescriptor());
        Job editedJob = model.getFilteredJobList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_PERSON);

        Job JobInFilteredList = model.getFilteredJobList().get(INDEX_FIRST_PERSON.getZeroBased());
        Job editedJob = new JobBuilder(JobInFilteredList).withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER).build();
        EditJob editJob = new EditJob(INDEX_FIRST_PERSON,
                new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER).build());

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateJobUnfilteredList_failure() {
        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder(firstJob).build();
        EditJob editCommand = new EditJob(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditJob.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_duplicateJobFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_PERSON);

        // edit Job in filtered list into a duplicate in address book
        Job JobInList = model.getAddressBook().getJobList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditJob editCommand = new EditJob(INDEX_FIRST_PERSON,
                new EditJobDescriptorBuilder(JobInList).build());

        assertCommandFailure(editCommand, model, EditJob.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_invalidJobIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_DATA_ANALYSIS).build();
        EditJob editCommand = new EditJob(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidJobIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getJobList().size());

        EditJob editCommand = new EditJob(outOfBoundIndex,
                new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditJob standardCommand = new EditJob(INDEX_FIRST_PERSON, DESC_DATA_ANALYSIS);

        // same values -> returns true
        EditJob.EditJobDescriptor copyDescriptor = new EditJob.EditJobDescriptor(DESC_DATA_ANALYSIS);
        EditJob commandWithSameValues = new EditJob(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditJob(INDEX_SECOND_PERSON, DESC_DATA_ANALYSIS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditJob(INDEX_FIRST_PERSON, DESC_PROJECT_MANAGER)));
    }





}
