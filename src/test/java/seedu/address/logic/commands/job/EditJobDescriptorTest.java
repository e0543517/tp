package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_PART_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_HIGH_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_LOW_DATA_ANALYSIS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditJobDescriptorBuilder;

class EditJobDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditJob.EditJobDescriptor descriptorWithSameValues = new EditJob.EditJobDescriptor(DESC_PROJECT_MANAGER);
        assertTrue(DESC_PROJECT_MANAGER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PROJECT_MANAGER.equals(DESC_PROJECT_MANAGER));

        // null -> returns false
        assertFalse(DESC_PROJECT_MANAGER.equals(null));

        // different types -> returns false
        assertFalse(DESC_PROJECT_MANAGER.equals(5));

        // different values -> returns false
        assertFalse(DESC_PROJECT_MANAGER.equals(DESC_DATA_ANALYSIS));

        // different job title -> returns false
        EditJob.EditJobDescriptor editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withJobTitle(VALID_NAME_BOB).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

        // different company name -> returns false
        editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withCompanyName(VALID_COMPANY_NAME_DATA_ANALYSIS).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

        // different address -> returns false
        editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

        // different qualification -> returns false
        editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withQualification(VALID_QUALIFICATION_TWO).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

        // different position -> returns false
        editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withPosition(VALID_POSITION_PART_TIME).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

        // different salary -> returns false
        editedProjectManager = new EditJobDescriptorBuilder(DESC_PROJECT_MANAGER)
                .withSalary(VALID_SALARY_LOW_DATA_ANALYSIS, VALID_SALARY_HIGH_DATA_ANALYSIS).build();
        assertFalse(DESC_PROJECT_MANAGER.equals(editedProjectManager));

    }
}
