package seedu.address.logic.parser.job;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUALIFICATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_TWO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.SALARY_DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FULL_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_HIGH_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_LOW_PROJECT_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.job.EditJob;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;
import seedu.address.testutil.EditJobDescriptorBuilder;


class EditJobParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditJob.MESSAGE_USAGE);

    private EditJobParser parser = new EditJobParser();

    @Test
    void parse() {
        assertParseFailure(parser, "1" + INVALID_JOB_TITLE_DESC,
                JobTitle.MESSAGE_CONSTRAINTS); // invalid job title
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC,
                CompanyName.MESSAGE_CONSTRAINTS); // invalid company name

        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_QUALIFICATION,
                Qualification.MESSAGE_CONSTRAINTS); // invalid qualification

        assertParseFailure(parser, "1" + INVALID_POSITION_DESC,
                Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, "1" + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS); // invalid salary

        // invalid job title followed by valid address
        assertParseFailure(parser, "1" + INVALID_JOB_TITLE_DESC + ADDRESS_DESC_AMY, JobTitle.MESSAGE_CONSTRAINTS);
        // invalid company name followed by valid position
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + POSITION_DESC_DATA_ANALYSIS,
                CompanyName.MESSAGE_CONSTRAINTS);
        // invalid salary followed by valid job title
        assertParseFailure(parser, "1" + INVALID_SALARY_DESC + JOB_TITLE_DESC_DATA_ANALYSIS,
                Salary.MESSAGE_CONSTRAINTS);

        // valid company name followed by invalid salary. The test case for invalid company name followed by valid
        // company name is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + COMPANY_NAME_DESC_DATA_ANALYSIS + INVALID_SALARY_DESC,
                Salary.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_SALARY_DESC + VALID_ADDRESS_AMY,
                CompanyName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_JOB_TITLE_DESC + INVALID_COMPANY_NAME_DESC
                + INVALID_SALARY_DESC + INVALID_POSITION_DESC, JobTitle.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + JOB_TITLE_DESC_DATA_ANALYSIS + COMPANY_NAME_DESC_PROJECT_MANAGER
                + ADDRESS_DESC_AMY + QUALIFICATION_DESC + POSITION_DESC_DATA_ANALYSIS + SALARY_DESC_PROJECT_MANAGER;

        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS).withCompanyName(VALID_COMPANY_NAME_PROJECT_MANAGER)
                .withAddress(VALID_ADDRESS_AMY).withQualification(VALID_QUALIFICATION)
                .withPosition(VALID_POSITION_FULL_TIME)
                .withSalary(VALID_SALARY_LOW_PROJECT_MANAGER, VALID_SALARY_HIGH_PROJECT_MANAGER).build();
        EditJob expectedCommand = new EditJob(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_DATA_ANALYSIS + QUALIFICATION_TWO_DESC;

        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_DATA_ANALYSIS).withQualification(VALID_QUALIFICATION_TWO).build();
        EditJob expectedJob = new EditJob(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedJob);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_COMPANY_NAME_DESC + COMPANY_NAME_DESC_DATA_ANALYSIS;
        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_DATA_ANALYSIS).build();
        EditJob expectedCommand = new EditJob(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + JOB_TITLE_DESC_PROJECT_MANAGER + INVALID_SALARY_DESC
                + SALARY_DESC_PROJECT_MANAGER + ADDRESS_DESC_BOB;
        descriptor = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER)
                .withSalary(VALID_SALARY_LOW_PROJECT_MANAGER, VALID_SALARY_HIGH_PROJECT_MANAGER)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditJob(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
