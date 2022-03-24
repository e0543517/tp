package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;


class DeleteJobTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteJob deleteJob = new DeleteJob(INDEX_FIRST_JOB);

        String expectedMessage = String
                .format(DeleteJob.MESSAGE_DELETE_JOB_SUCCESS, jobToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteJob(jobToDelete);

        assertCommandSuccess(deleteJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        DeleteJob deleteJob = new DeleteJob(outOfBoundIndex);

        assertCommandFailure(deleteJob, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobToDelete = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        DeleteJob deleteJob = new DeleteJob(INDEX_FIRST_JOB);

        String expectedMessage = String.format(DeleteJob
                .MESSAGE_DELETE_JOB_SUCCESS, jobToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteJob(jobToDelete);
        showNoJob(expectedModel);

        assertCommandSuccess(deleteJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getJobList().size());

        DeleteJob deleteJob = new DeleteJob(outOfBoundIndex);

        assertCommandFailure(deleteJob, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteJob deleteJobFirstCommand = new DeleteJob(INDEX_FIRST_JOB);
        DeleteJob deleteJobSecondCommand = new DeleteJob(INDEX_SECOND_JOB);

        // same object -> returns true
        assertTrue(deleteJobFirstCommand.equals(deleteJobFirstCommand));

        // same values -> returns true
        DeleteJob deleteJobFirstCommandCopy = new DeleteJob(INDEX_FIRST_JOB);
        assertTrue(deleteJobFirstCommand.equals(deleteJobFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteJobFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteJobFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteJobFirstCommand.equals(deleteJobSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoJob(Model model) {
        model.updateFilteredJobList(p -> false);

        assertTrue(model.getFilteredJobList().isEmpty());
    }
}
